//package com.louis;
//
//import com.google.common.collect.Maps;
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//
//import java.util.*;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.function.Function;
//import java.util.function.Supplier;
//import java.util.stream.Collectors;
//
///**
// * @author John·Louis
// * @date created on 2020/2/26
// * description:
// */
//
//public class Main {
//
//    public static void main(String[] args) throws Exception {
////        thenApply();
////        SparkConf conf = new SparkConf().setMaster("local")
////                .setAppName("HelloSpark");
////        try (JavaSparkContext jsc = new JavaSparkContext(conf)) {
////            List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
////            JavaRDD<Integer> rdd = jsc.parallelize(list);
////            Integer result = rdd.map(t -> t + 1).reduce(Integer::sum);
////            System.out.println("执行结果：" + result);
////
////        }
//
//        elegant();
//
//    }
//
//    private static void thenApply() throws Exception {
//        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
//            long result = new Random().nextInt(100);
//            System.out.println("result1="+result);
//            return result;
//        }).thenApply(t -> {
//            long result = t*5;
//            System.out.println("result2="+result);
//            return result;
//        });
//
//        long result = future.get();
//        System.out.println(result);
//
//
//        Executor executor = Executors.newSingleThreadExecutor();
//        CompletableFuture.supplyAsync(() -> {
//            long result2 = new Random().nextInt(100);
//            System.out.println("result1=" + result);
//            return result;
//        }, executor);
//    }
//
//    private static void elegant() {
//        String[] strings = new String[]{"zhangsan", "sili", "wangwu", "zhaoliu"};
//
//
//        List<String> lists = new ArrayList<>();
//        for (String string : strings) {
//            String[] split = string.split("");
//            List<String> list = Arrays.asList(split);
//            lists.addAll(list);
//        }
//        for (String li :lists){
//            System.out.print(li+" ");
//        }
//
//        System.out.println("=================");
//
//         Arrays.stream(strings)
//                 .flatMap(item -> Arrays.stream(item.split("")))
////                 .flatMap(Arrays::stream)
//                 .forEach(item->System.out.print(item+" "));
//
//    }
//}
