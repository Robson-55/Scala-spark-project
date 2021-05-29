import org.apache.spark.sql.SparkSession

object App {

  def main(args: Array[String]) = {

    val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
    val result = DstiJob.createReport(spark, args(0), args(1))
    spark.stop

    println(s"result of job is : $result")
  }
}