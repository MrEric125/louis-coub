package com.louis.mybatis.generator;

import lombok.Data;

/**
 * @author Louis
 * @date created on 2020/12/1
 * description:
 */
@Data
public class FieldEnumInfo {

    private String tableName;

    private String columnName;

    private String enumName;

    private String enumFullName;

}
