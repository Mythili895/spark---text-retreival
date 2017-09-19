/*
 * Licensed to STRATIO (C) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  The STRATIO (C) licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import com.datastax.spark.connector._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql
import org.apache.spark.sql.cassandra._
import org.apache.spark.sql.SQLContext._
import org.apache.spark.sql._
import scala.io.Source
import org.zouzias.spark.lucenerdd._
import org.zouzias.spark.lucenerdd.LuceneRDD
import org.zouzias.spark.lucenerdd.LuceneRDD._
import java.io._
import org.apache.spark.rdd.RDD._
/*import org.apache.spark.sql.cassandra.CassandraSQLContext._*/
import org.apache.spark.sql.DataFrameReader








object CapitalSearchExample {
  def main(args: Array[String]) {

    val KEYSPACE: String = "twitterdb"
    val TABLE: String = "twittertweets"
    val INDEX_COLUMN_CONSTANT: String = "lucene"
    
    val writer = new PrintWriter(new File("Write.txt"))

    
    val conf = new SparkConf(true)
        .set("spark.cassandra.connection.host", "127.0.0.1")
    
    
    val sc : SparkContext = new SparkContext(conf)
    
    
    
    val sqlContext = new SQLContext(sc)
    
    /*println("ppppppppppppppppppppppppppppppppppppppppppppp")*/ 

    import sqlContext.implicits._
   /* val df = sqlContext.sql("SELECT tweet_text from twitterdb.twitter_tweets")*/
    
    sqlContext.sql(
   """CREATE TEMPORARY TABLE twitter_tweets
     |USING org.apache.spark.sql.cassandra
     |OPTIONS (
     |  table "twitter_tweets",
     |  keyspace "twitterdb",
     |  cluster "Test Cluster",
     |  pushdown "true"
     |)""".stripMargin)
   
   
   
   val df = sqlContext.sql("SELECT * FROM twitter_tweets")
    
    
    println(df)
    /*val df = sqlContext.sql("SELECT * FROM twitter_tweets where  ( tweet_text like '%happy bday%' or tweet_text like'RT%') ") 
    df.show()*/
  
    /*val words = df.select(tweet_text)*/
   
    val luceneRDD = LuceneRDD(df)
    

    val results = luceneRDD.fuzzyQuery("tweet_text", "happy", 1)
    	
    println("--------------------------------------------------------------------------")
   
    val b = results.take(20)
    val string = b.mkString(" ")
    println(string)
    /*println(b.foreach(println))*/
    writer.write(string)
    writer.close()
    val a = (results.foreach(println))
    println(results.count)
    println(a)
    
   
    println("++++++++++++++++++++++++++++++++++++++++++++++++++")
   /* df.show() */

    /* df.printSchema() */
    
    /*val words = Source.fromFile("src/test/resources/words.txt").getLines().toSeq*/

    sc.stop()
  
  }
}
