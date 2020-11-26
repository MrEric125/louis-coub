package com.louis.mybatis.tkmybatis.entity;

import com.google.common.collect.Lists;
import lombok.*;

import java.util.List;

/**
 * @author Louis
 * @date created on 2020/11/26
 * description:
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndexColumns {

    private String indexName;

    private List<ColumnPo> indexColumn = Lists.newArrayList();
}
