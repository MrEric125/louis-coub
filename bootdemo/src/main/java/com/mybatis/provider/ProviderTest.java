package com.mybatis.provider;

import com.mybatis.entity.User;

/**
 * @author John·Louis
 * @date create in 2019/9/13
 * description:
 */
public class ProviderTest extends BaseSqlProvider<User> {

    public static void main(String[] args) {
        ProviderTest providerTest = new ProviderTest();
        String add = providerTest.add();
        System.out.println("sql+"+add);
    }


}
