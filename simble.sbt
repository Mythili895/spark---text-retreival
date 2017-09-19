name := "Simple Project"

version := "1.0"

scalaVersion := "2.10.5"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

resolvers += "Apache Repos" at "https://repository.apache.org/content/repositories/releases"
resolvers += "OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "org.apache.spark" % "spark-core_2.10" % "1.6.0"

libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "1.6.0-M2"

// https://mvnrepository.com/artifact/org.apache.spark/spark-sql_2.10
libraryDependencies += "org.apache.spark" % "spark-sql_2.10" % "1.6.0"





//libraryDependencies += "com.stratio.cassandra" % "cassandra-lucene-index-parent" % "3.9.1"

// https://mvnrepository.com/artifact/com.datastax.cassandra/cassandra-driver-core
libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "3.1.0"

//libraryDependencies += "org.zouzias" %% "spark-lucenerdd" % "0.1.0"



libraryDependencies += "com.stratio.cassandra" % "cassandra-lucene-index-plugin" % "3.9.1"



libraryDependencies += "com.stratio.cassandra" % "cassandra-thrift" % "2.1.3.1"

libraryDependencies += "org.zouzias" % "spark-lucenerdd_2.11" % "0.1.0"


libraryDependencies += "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.2"






resolvers += "OSS Sonatype" at "https://repo1.maven.org/maven2/"






		         



