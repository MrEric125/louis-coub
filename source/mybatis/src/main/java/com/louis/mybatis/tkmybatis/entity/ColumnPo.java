package com.louis.mybatis.tkmybatis.entity;

import lombok.*;

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
public class ColumnPo {

    private String columnName;

    private String columnLength;

    private String columnType;


}
