package com.louis.mybatis.dynamic.app;

import com.louis.mybatis.dynamic.entity.LocalUser;
import com.louis.mybatis.dynamic.mapper.ProviderMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @author louis
 * <p>
 * Date: 2019/9/19
 * Description:
 */
public class ProviderApp {

    private static SqlSessionFactory sqlSessionFactory;

    private static Reader reader;



    static {
        try {
            reader = Resources.getResourceAsReader("mybatis/SqlMapConfig.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSessionFactory.getConfiguration().addMapper(ProviderMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static SqlSession getSession() {
        return sqlSessionFactory.openSession(true);
    }


    private  static <T>  T getMapper(SqlSession sqlSession,Class<T> tClass) {
        return sqlSession.getMapper(tClass);
    }


    public static void main(String[] args) {
//        insert();
        findById();

    }

    private static void insert() {
        LocalUser user = new LocalUser();
        SqlSession session = getSession();
        user.setUsername("wangwu");
        user.setAge(60);
        user.setFivarite("ffffffff");

        ProviderMapper mapper = getMapper(session,ProviderMapper.class);
        int i = mapper.insert(user);
        System.out.println(i);
    }
    private static void deleteById() {
        SqlSession session = getSession();

        ProviderMapper mapper = getMapper(session,ProviderMapper.class);
        int i = mapper.deleteById(3L);
        System.out.println(i);
    }
    private static void updateById() {
        LocalUser user = new LocalUser();
        SqlSession session = getSession();
        user.setId(3L);
        user.setUsername("updddddd");
        user.setAge(30);
        user.setFivarite("updddddd");

        ProviderMapper mapper = getMapper(session,ProviderMapper.class);
        int i = mapper.update(user);
        System.out.println(i);
    }
    private static void findById() {
        SqlSession session = getSession();

        ProviderMapper mapper = getMapper(session,ProviderMapper.class);
        LocalUser localUser = mapper.selectById(2L);
        System.out.println(localUser);
    }
}
