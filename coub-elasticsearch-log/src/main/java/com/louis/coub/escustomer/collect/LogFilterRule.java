package com.louis.coub.escustomer.collect;

import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class LogFilterRule {

    private boolean collect;

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    private long logLineMaxSize;

    private String level;

    private Map<String,String> classLevel;

    private Set<String> filterClassList;

    private Set<String> filterIpList;
}
