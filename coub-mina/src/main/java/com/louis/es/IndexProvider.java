package com.louis.es;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("indexProvider")
public class IndexProvider {


    private static final String INDEX_NAME = "my_index_";


    public static String indexName() {
        return indexName(LocalDate.now());
    }

    private static String indexName(LocalDate localDate) {
        return INDEX_NAME + localDate.toString().replace(":", "-");
    }
}
