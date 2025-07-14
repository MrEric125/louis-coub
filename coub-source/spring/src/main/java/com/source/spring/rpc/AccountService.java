package com.source.spring.rpc;

import java.util.List;

/**
 * @author jun.liu
 * @since 2021/5/18 17:40
 */
public interface AccountService {

    public void insertAccount(Account account);

    public List<Account> getAccounts(String name);
}
