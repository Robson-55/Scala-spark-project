**SCALA / SPARK PROJECT**



This project aims to use Spark Scala to extract relevant information (dates, URIs and Ips, with their respective counts for some particular circumstances) from a data file and output the result in a JSON file.

It also aims to package the all project through spark-submit, and delivers it with sbt configuration. There are also included the steps needed in order to perform the integration with AWS Glue, Athena and S3.

The tools used for this project were the following: Jdk 11, Sbt 1.4.9, Ubuntu 18, Intellij Idea 2021, Spark 3.1.1 and Scala 2.11.12.

The Sbt aims at compiling scala code, so it generates a .jar file in which, once executed with the terminal, two paths are passed, specifying both the input file and the ouput json files. In this case, one json file is for displaying the dates having more than 20000 connections with their respective lists of IP addresses and its count. The other Json file is also for the dates having more than 20000 connections but in this case with its corresponding URIs and its count.

**Set up for running the program locally:**
![image](https://user-images.githubusercontent.com/44393451/120082329-a5a25600-c0c2-11eb-8660-41308956e6cb.png)

Here are the commands:

```bash
sbt assembly
```

```
TARGET_LOCATION=<location>
cp target/scala-2.12/spark-sbt-template-assembly-1.0.jar $TARGET_LOCATION
```

```
JAR_PATH=$(pwd)/target/scala-2.12/spark-sbt-template-assembly-1.0.jar
spark-submit --master=local[*] --deploy-mode client --class App $JAR_PATH
```

**AWS Configuration:**

![image](https://user-images.githubusercontent.com/44393451/120082376-f2862c80-c0c2-11eb-89fc-948ddd29188b.png)
![image](https://user-images.githubusercontent.com/44393451/120082380-fa45d100-c0c2-11eb-9cb5-928e75fd883f.png)
![image](https://user-images.githubusercontent.com/44393451/120082382-00d44880-c0c3-11eb-8d5f-59f4ea385904.png)
![image](https://user-images.githubusercontent.com/44393451/120082385-07fb5680-c0c3-11eb-993d-3008465af357.png)
![image](https://user-images.githubusercontent.com/44393451/120082391-10539180-c0c3-11eb-8a77-431a6d448c79.png)
![image](https://user-images.githubusercontent.com/44393451/120082393-15184580-c0c3-11eb-8311-302f1b9027b7.png)
![image](https://user-images.githubusercontent.com/44393451/120082395-19dcf980-c0c3-11eb-93ac-d67f3f97e3e9.png)
![image](https://user-images.githubusercontent.com/44393451/120082398-1ea1ad80-c0c3-11eb-93ee-44482e392550.png)
![image](https://user-images.githubusercontent.com/44393451/120082401-24978e80-c0c3-11eb-8017-2726dd13f328.png)
![image](https://user-images.githubusercontent.com/44393451/120082404-29f4d900-c0c3-11eb-9f81-964a23178b39.png)
![image](https://user-images.githubusercontent.com/44393451/120082407-2e20f680-c0c3-11eb-98a0-933e4e38f1d3.png)
![image](https://user-images.githubusercontent.com/44393451/120082412-337e4100-c0c3-11eb-85bb-b07f4902a09b.png)
![image](https://user-images.githubusercontent.com/44393451/120082414-3842f500-c0c3-11eb-92b2-77aa3086773c.png)
![image](https://user-images.githubusercontent.com/44393451/120082415-3c6f1280-c0c3-11eb-9e4f-2df1717fd0d1.png)
![image](https://user-images.githubusercontent.com/44393451/120082420-41cc5d00-c0c3-11eb-8572-f64efe1efcd6.png)
![image](https://user-images.githubusercontent.com/44393451/120082424-4d1f8880-c0c3-11eb-8b5f-e02fa9cc179b.png)
![image](https://user-images.githubusercontent.com/44393451/120082429-5872b400-c0c3-11eb-95dc-f736ef69e81d.png)


Upload the file to S3: dsti-roberto-pipeline

![image](https://user-images.githubusercontent.com/44393451/120082434-61fc1c00-c0c3-11eb-8112-cd202fcdec02.png)



Crawler logs:

![image](https://user-images.githubusercontent.com/44393451/120082436-6aeced80-c0c3-11eb-9fea-f9d5557c302c.png)
![image](https://user-images.githubusercontent.com/44393451/120082440-70e2ce80-c0c3-11eb-96c4-47dc688db6b1.png)


Add the test-csv to the crawler:

![image](https://user-images.githubusercontent.com/44393451/120082458-87892580-c0c3-11eb-949d-bc78507fa828.png)


Run crawler:

![image](https://user-images.githubusercontent.com/44393451/120082479-9ff94000-c0c3-11eb-948a-3f96eda7451c.png)


More tables have been automatically created:

![image](https://user-images.githubusercontent.com/44393451/120082499-b43d3d00-c0c3-11eb-9a34-3fe877087b47.png)


Crawler\_test\_txt reads the csv data:

![image](https://user-images.githubusercontent.com/44393451/120082505-c028ff00-c0c3-11eb-83c8-390764f4bd88.png)


Now we move to AnalyticsAthena

![image](https://user-images.githubusercontent.com/44393451/120082520-ca4afd80-c0c3-11eb-8720-373074ba46f3.png)

![image](https://user-images.githubusercontent.com/44393451/120082534-de8efa80-c0c3-11eb-98d3-de1a079fe854.png)
We need to créate a new S3 bucket for receiving the outputs.
![image](https://user-images.githubusercontent.com/44393451/120082562-f49cbb00-c0c3-11eb-87b4-8bcf2a1b70d4.png)



We create the folder outputs inside the S3 bucket:
![image](https://user-images.githubusercontent.com/44393451/120082582-0aaa7b80-c0c4-11eb-8cc1-d56f4a78b6e6.png)



Select this folder inside this S3-output bucket for the Athena queries results:

![image](https://user-images.githubusercontent.com/44393451/120082595-1433e380-c0c4-11eb-9c95-e991dfc84f0f.png)


All the actions of the queries are stored in the S3-output bucket:

![image](https://user-images.githubusercontent.com/44393451/120082603-1eee7880-c0c4-11eb-988d-1a748550027d.png)
