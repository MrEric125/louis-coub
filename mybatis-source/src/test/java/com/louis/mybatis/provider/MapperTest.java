package com.louis.mybatis.provider;

import com.louis.mybatis.dynamic.config.MybatisConfig;
import com.louis.mybatis.dynamic.mapper.UserMapper;
import com.louis.mybatis.tkmybatis.entity.LocalUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
public class MapperTest {




    @Test
    public void test() throws Exception {
        SqlSessionFactory sessionFactory = MybatisConfig.getSessionFactory();
        SqlSession sqlSession= sessionFactory.openSession();


        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        LocalUser user = null;

        // 新增测试
        System.out.println("------------ 新增测试 ------------");
        user = new LocalUser();
//        user.setId(1L);
        user.setFivarite("conanli");
        user.setUsername("louis");
        user.setAge(123);
        System.out.println("insert: " + userMapper.insert(user));
        sqlSession.commit();
        sqlSession.close();


    }

    @Test
    public void testselect() {
        SqlSessionFactory sessionFactory = MybatisConfig.getSessionFactory();
        SqlSession sqlSession= sessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        LocalUser user;


        // 获取测试
        System.out.println("------------ 获取测试 ------------");
        user = userMapper.getById(1L);
        System.out.println("user: " + user);


        sqlSession.commit();
        sqlSession.close();
    }
}
