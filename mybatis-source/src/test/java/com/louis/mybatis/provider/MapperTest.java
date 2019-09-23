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
        user.setId(1L);
        user.setFivarite("conanli");
        user.setAge(123);
        System.out.println("insert: " + userMapper.insert(user));
        sqlSession.commit();
        sqlSession.close();


    }

    public void test2() {
        SqlSessionFactory sessionFactory = MybatisConfig.getSessionFactory();
        SqlSession sqlSession= sessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        LocalUser user = null;
        // 更新测试
        System.out.println("------------ 更新测试 ------------");
        user = new LocalUser();
        user.setId(1L);
        user.setAge(123);
        System.out.println("update: " + userMapper.updateById(user));

        // 获取测试
        System.out.println("------------ 获取测试 ------------");
        System.out.println("user: " + userMapper.getById(1L));

        // 删除测试
        System.out.println("------------ 删除测试 ------------");
        System.out.println("delete: " + userMapper.deleteById(1L));

        // 存在测试
        System.out.println("------------ 存在测试 ------------");
        System.out.println("exist: " + userMapper.existById(1L));

        sqlSession.commit();
        sqlSession.close();
    }
}
