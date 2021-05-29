import org.apache.spark.sql.{ Dataset, SparkSession }
import org.apache.spark.sql.functions._

case class AccessLog(ip: String, ident: String, user: String, datetime: String, request: String, status: String, size: String, referer: String, userAgent: String, unk: String)

object DstiJob {
  def createReport(spark: SparkSession, inputPath: String, outputPath: String): String = {
    import spark.implicits._

    val logsAsString = spark.read.text(inputPath).as[String]

    val R = """^(?<ip>[0-9.]+) (?<identd>[^ ]) (?<user>[^ ]) \[(?<datetime>[^\]]+)\] \"(?<request>[^\"]*)\" (?<status>[^ ]*) (?<size>[^ ]*) \"(?<referer>[^\"]*)\" \"(?<useragent>[^\"]*)\" \"(?<unk>[^\"]*)\"""".r
    val dsParsed = logsAsString.flatMap(x => R.unapplySeq(x))
    def toAccessLog(params: List[String]) = AccessLog(params(0), params(1), params(2), params(3), params(5), params(5), params(6), params(7), params(8), params(9))

    val ds: Dataset[AccessLog] = dsParsed.map(toAccessLog _)
    val dsWithTime = ds.withColumn("datetime", to_timestamp(ds("datetime"), "dd/MMM/yyyy:HH:mm:ss X"))
    dsWithTime.cache
    dsWithTime.createOrReplaceTempView("AccessLog")

    val theDates = spark.sql("select cast(datetime as date) as date, count(*) as count from AccessLog group by date having count > 20000").map(_.getDate(0)).collect.toList

    val uris = spark.sql("select request, cast(datetime as date) as date from AccessLog")
    val urisByDate = uris.groupBy("date", "request").count

    val urisByDateFiltered = urisByDate
      .groupBy("date")
      .agg(collect_list(col("request")).as("uris"), collect_list("count").as("counts"))
      .select(col("date"), map_from_arrays(col("uris"), col("counts")).as("countByUri"))
      .filter($"date".isin(theDates: _*))

    urisByDateFiltered.coalesce(1).write.mode("overwrite").json(outputPath + "/uris")

    val ipAddresses = spark.sql("select ip, cast(datetime as date) as date from AccessLog")
    val ipAddressesByDate = ipAddresses.groupBy("date", "ip").count
    val ipAddressesByDateFiltered = ipAddressesByDate.groupBy("date").agg(collect_list(col("ip")).as("ips"), collect_list("count").as("counts")).select(col("date"), map_from_arrays(col("ips"), col("counts")).as("countByIpAddresses")).filter($"date".isin(theDates: _*))

    ipAddressesByDateFiltered.coalesce(1).write.mode("overwrite").json(outputPath + "/ips")

    return "Job finished successfully: [" + outputPath + "]"
  }
}

