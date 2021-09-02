package com.louis.spark;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

//@SpringBootApplication
public class SparkApp {

    public static void main(String[] args) {
        String inputFile = "readme.md";
        String outputFile = "outputFile.txt";
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("My Spark");
        JavaSparkContext jsc = new JavaSparkContext();
        JavaRDD<String> stringJavaRDD = jsc.textFile(inputFile);

        JavaRDD<String> words = stringJavaRDD.flatMap(s -> (Iterator<String>) Arrays.asList(s.split(" ")));
        JavaPairRDD<String, Integer> counts = words.mapToPair(s -> new Tuple2<>(s, 1)).reduceByKey(Integer::sum);

        counts.saveAsTextFile(outputFile);


    }
}
