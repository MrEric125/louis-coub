package com.louis.minaProject.spark;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.louis.common.common.HttpResult;
import com.louis.common.common.HttpResult;
import com.louis.common.common.HttpResult;
import com.louis.minaProject.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.spark.HashPartitioner;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.StorageLevels;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.rdd.RDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import scala.Tuple2;

import java.io.Serializable;
import java.util.*;

/**
 * @author jun.liu
 * @date created on 2020/7/4
 * description:
 */
@RestController
@RequestMapping("spark")
public class SparkController {

    @Autowired
    private JavaSparkContext context;



//    @Autowired
//    private SparkContext sparkContext;

    @RequestMapping("filePath")
    public HttpResult HttpResult(@RequestParam String filePath) {
        JavaRDD<String> javaRDD = context.textFile(filePath);
        JavaRDD<String> words = javaRDD.flatMap(s -> {
            List<String> list = Arrays.asList(s.split(" "));
            return list.iterator();
        }).filter(s -> {
            if (StringUtils.isNotEmpty(s)) {
                String sub = s.substring(0, 1);
                return !NumberUtils.isInteger(sub);
            }
            return false;
        });

        JavaPairRDD<String, Integer> counts = words.mapToPair(s -> new Tuple2<>(s, 1)).reduceByKey(Integer::sum).persist(StorageLevels.MEMORY_ONLY);

        List<String> collect = counts.keys().collect();

        Map<String, Integer> countMap = counts.collectAsMap();

        Map<String, Object> returnMap = Maps.newHashMap();

        returnMap.put("keys", collect);
        returnMap.put("count", countMap);
        return HttpResult.ok(returnMap);
    }

    @RequestMapping("scala")
    public HttpResult scala(@RequestParam(required = false) String filePath) {

        long start = System.currentTimeMillis();
        JavaRDD<Integer> rdd = context.parallelize(Lists.newArrayList(1, 2, 3, 4, 5));

        JavaRDD<String> strRdd = context.parallelize(Lists.newArrayList("coffee panda", "happy panda", "happiest panda party")).persist(StorageLevels.MEMORY_ONLY);

        ArrayList<String> tokenize = Lists.newArrayList("coffee", "panda");

        JavaRDD<String> words = strRdd.flatMap(item -> Arrays.asList(item.split(" ")).iterator()).distinct();

//        strRdd.flatMap()

//        JavaPairRDD<Object, Object> persist = context.sequenceFile().partitionBy(new HashPartitioner(100)).persist();

        JavaRDD<Integer> javaRDD = context.parallelize(Lists.newArrayList(1, 1, 3));

        JavaRDD<Integer> map = rdd.map(i -> i * i);

        long end = System.currentTimeMillis() - start;

        ImmutableMap<String, Object> immutableMap = ImmutableMap.of("join", map.collect(), "words", words.collect(), "cost", end);

        return HttpResult.ok(immutableMap);
    }
}
