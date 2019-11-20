package com.louis.service.impl;

import com.louis.entity.UserAction;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author louis
 * <p>
 * Date: 2019/11/20
 * Description:
 */
@Service
public class UserActionService {

    private Map<Long, List<UserAction>> userActionMap = new ConcurrentHashMap<>();
    private Map<String, List<UserAction>> userNameActionMap = new ConcurrentHashMap<>();



    @PostConstruct
    public void init() {
        UserAction userAction1 = UserAction.builder().id(1L).url("/api/**").build();
        UserAction userAction2 = UserAction.builder().id(1L).url("/**").build();
        UserAction userAction3 = UserAction.builder().id(1L).url("/user/**").build();
//        增加用户操作
        userActionMap.put(1L, Lists.newArrayList(userAction1, userAction3));
        userActionMap.put(2L, Lists.newArrayList(userAction1));
        userActionMap.put(3L, Lists.newArrayList(userAction2));

        userNameActionMap.put("user", Lists.newArrayList(userAction1, userAction3));
        userNameActionMap.put("visitor", Lists.newArrayList(userAction1));
        userNameActionMap.put("admin", Lists.newArrayList(userAction2));
    }

    public List<UserAction> getOwnActionListByUserId(Long userID) {
        return userActionMap.get(userID);
    }

    public List<UserAction> loadActionUrl(String userName) {
        return userNameActionMap.get(userName);
    }


}
