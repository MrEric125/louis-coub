package com.generator;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Louis
 * @date created on 2020/11/30
 * description:
 */
public class GeneratorStrategy {

    protected void strategyConfig(AutoGenerator autoGenerator, List<String> tableNames,List
                                  <String> tablePrefixs) {

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);

        strategy.setRestControllerStyle(true);

        // 公共父类
        strategy.setSuperEntityClass("");
        strategy.setSuperServiceClass("");
        strategy.setSuperServiceImplClass("");
        strategy.setSuperControllerClass("");

        if (CollectionUtils.isNotEmpty(tableNames)) {
            strategy.setInclude(tableNames.toArray(new String[tableNames.size()]));
        }

        strategy.setEntityTableFieldAnnotationEnable(true);

        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
//        strategy.setInclude("ws_work_order_nw,base_area".split(","));
        strategy.setControllerMappingHyphenStyle(true);
        if (CollectionUtils.isNotEmpty(tablePrefixs)) {
            strategy.setTablePrefix(tablePrefixs.toArray(new String[tablePrefixs.size()]));
        }
        autoGenerator.setStrategy(strategy);

    }


    protected void globalConfig(AutoGenerator autoGenerator, Properties properties, List<FieldEnumInfo> fieldEnumInfos) {

        CswsGlobalConfig gc = new CswsGlobalConfig();
        gc.setFieldEnumList(fieldEnumInfos);

        if (Objects.isNull(properties) || StringUtils.isBlank(properties.getProperty("project.path"))) {
            throw new IllegalArgumentException("文件输出基础目录为空");
        }
        gc.setOutputDir(properties.getProperty("project.path"));
        Map<String, String> outDir = Maps.newConcurrentMap();
        outDir.put("project.entity.path", properties.getProperty("project.entity.path"));
        outDir.put("project.mapper.path", properties.getProperty("project.mapper.path"));
        outDir.put("project.service.path", properties.getProperty("project.service.path"));
        outDir.put("project.controller.path", properties.getProperty("project.controller.path"));
        gc.setSubdivisionOutDir(outDir);


        gc.setAuthor(System.getenv("COMPUTERNAME"));
        gc.setOpen(false);
        //实体属性 Swagger2 注解
        autoGenerator.setGlobalConfig(gc);
    }

    protected Properties getProperties() {
        Properties properties = new Properties();
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("generator.properties");
        try {
            properties.load(resourceAsStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(properties)) {
          throw new IllegalArgumentException("配置文件 generator.properties未配置");
        }
        return properties;
    }

    protected void dataSourceConfig(AutoGenerator autoGenerator, Properties properties) {

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setTypeConvert(new MySqlEnumTypeConvert());


        String url = properties.getProperty("jdbc.url");
        String userName = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        String driver = properties.getProperty("jdbc.driver");

        if (StringUtils.isNotBlank(url) && StringUtils.isNotBlank(userName)
                && StringUtils.isNotBlank(password) && StringUtils.isNotBlank(driver)) {
            dsc.setUrl(url);
            // dsc.setSchemaName("public");
            dsc.setDriverName(driver);
            dsc.setUsername(userName);
            dsc.setPassword(password);
            autoGenerator.setDataSource(dsc);
        } else {
            throw new IllegalArgumentException("数据库连接信息获取异常");
        }
    }

    protected void packageConfig(AutoGenerator autoGenerator) {

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setParent("b");
        pc.setParent("");
//        pc.setModuleName("a");
        pc.setMapper("mapper");
        pc.setEntity("model");
        pc.setXml("xml");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        autoGenerator.setPackageInfo(pc);
    }

    protected void injectionConfig(AutoGenerator autoGenerator) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

//        // 如果模板引擎是 freemarker
//        String templatePath = "D://a.txt";
//        // 如果模板引擎是 velocity
//        // String templatePath = "/templates/mapper.xml.vm";
//
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
//        cfg.setFileOutConfigList(focList);
        autoGenerator.setCfg(cfg);

    }

    protected void templateConfig(AutoGenerator autoGenerator) {

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);
    }
}
