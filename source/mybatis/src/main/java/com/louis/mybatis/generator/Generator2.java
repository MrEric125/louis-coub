package com.louis.mybatis.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Generator2 {


    public static void main(String[] args) {
        // 代码生成器

        GeneratorStrategy generator = new GeneratorStrategy();
        AutoGenerator mpg = new AutoGenerator();
        ArrayList<String> tableName = Lists.newArrayList("employees","login");
        List<FieldEnumInfo> fieldEnumInfos = Lists.newArrayList();
        FieldEnumInfo fieldEnumInfo = new FieldEnumInfo();
        fieldEnumInfo.setColumnName("wx_app_id");
        fieldEnumInfo.setEnumFullName("com.louis.mybatis.enums.Fivarite");
        fieldEnumInfo.setEnumName("Fivarite");
        fieldEnumInfo.setTableName("login");
        List<String> tablePrefix = Lists.newArrayList("ws_");
        Properties properties = generator.getProperties();
        generator.globalConfig(mpg, properties, fieldEnumInfos);
        generator.dataSourceConfig(mpg, properties);
        generator.packageConfig(mpg);
        generator.strategyConfig(mpg, tableName, tablePrefix);

        generator.templateConfig(mpg);
        generator.injectionConfig(mpg);

//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


}
