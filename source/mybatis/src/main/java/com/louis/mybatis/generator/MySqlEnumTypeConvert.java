package com.louis.mybatis.generator;


import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

import java.util.List;
import java.util.Optional;

/**
 * @author Louis
 * @date created on 2020/12/1
 * description:
 */
public class MySqlEnumTypeConvert extends MySqlTypeConvert {

    public IColumnType processTypeConvert(GlobalConfig globalConfig, TableField tableField) {
        if (globalConfig instanceof CswsGlobalConfig) {
            List<FieldEnumInfo> fieldEnumList = ((CswsGlobalConfig) globalConfig).getFieldEnumList();
            String columnName = tableField.getName();
            Optional<FieldEnumInfo> fieldEnumInfo = fieldEnumList.stream().filter(item -> item.getColumnName().equals(columnName)).findFirst();
            IColumnType columnType = fieldEnumInfo.map(field -> {
                return (IColumnType) new OrdinaryColumnType(field.getEnumName(), field.getEnumFullName());
            }).orElseGet(() -> this.processTypeConvert(globalConfig, tableField.getType()));
            return columnType;

        } else {
            return this.processTypeConvert(globalConfig, tableField.getType());
        }
    }

    @Override
    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        return super.processTypeConvert(globalConfig, fieldType);
    }

}
