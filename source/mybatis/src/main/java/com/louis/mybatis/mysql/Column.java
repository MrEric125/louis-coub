package com.louis.mybatis.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Column {

    private int index;
    private String colName;
    private String dataType;
    private String schema;
    private String table;

}
