package com.louis.db.mongo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author Louis
 * @date created on 2020/12/2
 * description:
 */
@Data
public class Inventory {

    private String id;

    private String item;

    private String qty;

    private List<String> tags = Lists.newArrayList();

    private Size size;

}
