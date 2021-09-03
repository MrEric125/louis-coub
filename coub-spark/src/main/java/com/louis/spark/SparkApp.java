package com.louis.spark;


import org.apache.commons.collections.CollectionUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

//@SpringBootApplication
public class SparkApp {

    public static void main(String[] args) {
        String inputFile = "readme.md";
        String outputFile = "/Users/louis/workspace/louis/louis-coub/coub-spark/src/main/resources/outputFile.txt";
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("My Spark");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);

        JavaRDD<String> stringJavaRDD = jsc.textFile(inputFile);

        JavaRDD<String> words = stringJavaRDD.flatMap(s -> {
            List<String> tList = Arrays.asList(s.split(" "));
            return tList.iterator();

        });
        JavaPairRDD<String, Integer> counts = words.mapToPair(s -> new Tuple2<>(s, 1)).reduceByKey(Integer::sum);

        counts.collectAsMap();

        SparkSession sparkSession = SparkSession.builder().appName("app").getOrCreate();

        Dataset<String> cache = sparkSession.read().textFile(inputFile).cache();

        System.out.println(cache.count());


    }
}
