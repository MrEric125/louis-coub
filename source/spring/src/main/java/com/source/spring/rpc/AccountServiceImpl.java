package com.source.spring.rpc;

import com.google.common.collect.Lists;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    @Override
    public void insertAccount(Account acc) {
        // do something...
    }

    @Override
    public List<Account> getAccounts(String name) {
        // do something...
        Account account = new Account();
        account.setName("zhangsan");
        return Lists.newArrayList(account);
    }

}