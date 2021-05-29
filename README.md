**SCALA / SPARK PROJECT**



This project aims to use Spark Scala to extract relevant information (dates, URIs and Ips, with their respective counts for some particular circumstances) from a data file and output the result in a JSON file.

It also aims to package the all project through spark-submit, and delivers it with sbt configuration. There are also included the steps needed in order to perform the integration with AWS Glue, Athena and S3.

The tools used for this project were the following: Jdk 11, Sbt 1.4.9, Ubuntu 18, Intellij Idea 2021, Spark 3.1.1 and Scala 2.11.12.

The Sbt aims at compiling scala code, so it generates a .jar file in which, once executed with the terminal, two paths are passed, specifying both the input file and the ouput json files. In this case, one json file is for displaying the dates having more than 20000 connections with their respective lists of IP addresses and its count. The other Json file is also for the dates having more than 20000 connections but in this case with its corresponding URIs and its count.

**Set up for running the program locally:**

![](RackMultipart20210529-4-1l6ytlc_html_eac0e4ac6cc9d245.png)

![](RackMultipart20210529-4-1l6ytlc_html_22b0a0b6bfdaf90.png)

![](RackMultipart20210529-4-1l6ytlc_html_fdf75f9f87a0195a.png)

![](RackMultipart20210529-4-1l6ytlc_html_5088a585caab9c33.png)

# Spark sbt template

This sbt template enables you to create a new spark project 

## Package

To package your project:
```bash
sbt assembly
```

## Deploy 

Copy/Upload the fatjar to the destination
```
TARGET_LOCATION=<location>
cp target/scala-2.12/spark-sbt-template-assembly-1.0.jar $TARGET_LOCATION
```

## Run

To run your project locally:
```
JAR_PATH=$(pwd)/target/scala-2.12/spark-sbt-template-assembly-1.0.jar
spark-submit --master=local[*] --deploy-mode client --class App $JAR_PATH
```

**AWS Configuration:**

![](RackMultipart20210529-4-1l6ytlc_html_5af97d0758211834.png)

![](RackMultipart20210529-4-1l6ytlc_html_7cbfd6ee795b502e.png)

![](RackMultipart20210529-4-1l6ytlc_html_e370b2955b95f3c2.png)

![](RackMultipart20210529-4-1l6ytlc_html_4730e8e4607560d8.png)

![](RackMultipart20210529-4-1l6ytlc_html_c33a3db028bae37e.png)

![](RackMultipart20210529-4-1l6ytlc_html_4e004511275f93b4.png)

![](RackMultipart20210529-4-1l6ytlc_html_3e20e3d20a5e81fc.png)

![](RackMultipart20210529-4-1l6ytlc_html_98964017c747ef8c.png)

![](RackMultipart20210529-4-1l6ytlc_html_a98178aead5891a3.png) ![](RackMultipart20210529-4-1l6ytlc_html_a829eb5fd33132ba.png)

![](RackMultipart20210529-4-1l6ytlc_html_32514c3e73dea7d8.png)

![](RackMultipart20210529-4-1l6ytlc_html_d845f4d26f0ef85f.png)

![](RackMultipart20210529-4-1l6ytlc_html_f6629de0827af4ea.png)

![](RackMultipart20210529-4-1l6ytlc_html_fd68197ede18ab69.png)

![](RackMultipart20210529-4-1l6ytlc_html_3420840946a4cf55.png)

![](RackMultipart20210529-4-1l6ytlc_html_d347a09ba0b2a928.png)

![](RackMultipart20210529-4-1l6ytlc_html_c3747dc287d8cfa1.png)

Upload the file to S3: dsti-roberto-pipeline

![](RackMultipart20210529-4-1l6ytlc_html_3e8be41667836dbb.png)

Crawler logs:

![](RackMultipart20210529-4-1l6ytlc_html_43f012eb63e5d0c8.png)

![](RackMultipart20210529-4-1l6ytlc_html_bea8e94fe12609b1.png)

Add the test-csv to the crawler:

![](RackMultipart20210529-4-1l6ytlc_html_c0e4b36a5dcb7783.png)

Run crawler:

![](RackMultipart20210529-4-1l6ytlc_html_7737f15294e903d0.png)

More tables have been automatically created:

![](RackMultipart20210529-4-1l6ytlc_html_8cca81db77eeb2a0.png)

Crawler\_test\_txt reads the csv data:

![](RackMultipart20210529-4-1l6ytlc_html_d325919f7803a10c.png)

Now we move to AnalyticsAthena

![](RackMultipart20210529-4-1l6ytlc_html_3aa375213c72213f.png)

![](RackMultipart20210529-4-1l6ytlc_html_3c8e4e5a5a8e5914.png)

We need to créate a new S3 bucket for receiving the outputs.

![](RackMultipart20210529-4-1l6ytlc_html_2d0e3a6dca51a852.png)

We create the folder outputs inside the S3 bucket:

![](RackMultipart20210529-4-1l6ytlc_html_a16e0441b7df840a.png)

Select this folder inside this S3-output bucket for the Athena queries results:

![](RackMultipart20210529-4-1l6ytlc_html_43fc7301ef868547.png)

All the actions of the queries are stored in the S3-output bucket:

![](RackMultipart20210529-4-1l6ytlc_html_e7b08b4a58d6444b.png)