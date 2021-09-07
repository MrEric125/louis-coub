package com.louis.spark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
object SparkA{
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("My App")
    val sc = new SparkContext(conf)
    val textFile = "readme.md"
    val url = this.getClass.getClassLoader.getResource("readme.spark.md")
    val path = url.getPath
    val inputFile = sc.textFile(path)
    val words = inputFile.flatMap(line => line.split(" "))
    var counts = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
    var wcounts = counts.filter(x => x._1.startsWith("w"))
    var aCount = counts.filter(x => x._1.startsWith("a"))
    val AAndWCount=wcounts.union(aCount)

    AAndWCount.sortBy(x => x._2).foreach(println)
    // 一般情况下rdd 不能通过collect() 来收集到驱动器中，因为他们一般都很大，通常需要把数据写到诸如hdfs这样的分布式存储系统中

  }
}
class SparkA(val query:String){
  def isMatch(s:String): Boolean = {
    s.contains(query)
  }
  def getMatchesFunctionReference(rdd:RDD[String]): RDD[String] = {
    rdd.map(this.isMatch)
  }

}


