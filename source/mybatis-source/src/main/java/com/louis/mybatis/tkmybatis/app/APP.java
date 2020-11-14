package com.louis.mybatis.tkmybatis.app;

import com.louis.mybatis.tkmybatis.entity.LocalUser;
import tk.mybatis.mapper.entity.Example;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
public class APP {


    public static void main(String[] args) {
        Example example = new Example(LocalUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("");

    }
}
