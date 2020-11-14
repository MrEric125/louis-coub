package com.louis.mybatis.tkmybatis.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.session.Configuration;

import java.io.Reader;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
public class LouisConfiguration {

    private static SqlSessionFactory sqlSessionFactory;

    private static Reader reader;



//    static {
//        try {
//            reader = Resources.getResourceAsReader("mybatis/SqlMapConfig.xml");
//            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//            sqlSessionFactory.getConfiguration().addMapper(ProviderMapper.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    private static SqlSession getSession() {
        return sqlSessionFactory.openSession(true);
    }


    private  static <T>  T getMapper(SqlSession sqlSession,Class<T> tClass) {
        return sqlSession.getMapper(tClass);
    }

    public void after(SqlSessionFactory sqlSessionFactory) {
        SqlSession session=sqlSessionFactory.openSession();
        MapperHelper mapperHelper = new MapperHelper();
        Config config = new Config();
        config.setIDENTITY("MYSQL");
        config.setEnableMethodAnnotation(true);
        config.setNotEmpty(true);
        config.setCheckExampleEntityClass(true);
//启用简单类型
        config.setUseSimpleType(true);
//枚举按简单类型处理
        config.setEnumAsSimpleType(true);
//自动处理关键字 - mysql
        config.setWrapKeyword("`{0}`");
//设置配置
        mapperHelper.setConfig(config);
//注册通用接口，和其他集成方式中的 mappers 参数作用相同
//4.0 之后的版本，如果类似 Mapper.class 这样的基础接口带有 @RegisterMapper 注解，就不必在这里注册
        mapperHelper.registerMapper(Mapper.class);
//配置 mapperHelper 后，执行下面的操作
        mapperHelper.processConfiguration(session.getConfiguration());
    }

    public void before() {
        Configuration configuration = new Configuration();
        configuration.setMapperHelper(new MapperHelper());
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

}
