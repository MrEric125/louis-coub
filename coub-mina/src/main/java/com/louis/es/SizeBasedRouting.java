//package com.louis.es;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class SizeBasedRouting {
//    // 选择包含最小倒排列表的分片进行查询
//    public List<ShardId> selectShardsBySize(List<GlobalTermIndex.TermMetadata> terms) {
//        return terms.stream()
//            .sorted(Comparator.comparingLong(t -> t.documentFrequency))
//            .limit(10) // 选择文档频率最小的10个术语
//            .flatMap(t -> t.shards.stream())
//            .distinct()
//            .collect(Collectors.toList());
//    }
//}