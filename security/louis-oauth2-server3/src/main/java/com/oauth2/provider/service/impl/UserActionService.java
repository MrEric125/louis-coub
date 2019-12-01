package com.oauth2.provider.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.oauth2.provider.entity.SysUser;
import com.oauth2.provider.entity.UserAction;
import com.oauth2.provider.repository.UserActionRepository;
import com.oauth2.provider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * @author louis
 * <p>
 * Date: 2019/11/20
 * Description:
 */
@Service
public class UserActionService {

    @Autowired
    private UserActionRepository userActionRepository;

    @Autowired
    private UserRepository userRepository;

    Cache<Long, List<UserAction>> userActionCache = CacheBuilder.newBuilder().build();




    @PostConstruct
    public void init() {
//        UserAction userAction1 = UserAction.builder().url("/api/**").build();
//        UserAction userAction2 = UserAction.builder().url("/**").build();
//        UserAction userAction3 = UserAction.builder().url("/user/**").build();
////        增加用户操作
//        userActionMap.put(1L, Lists.newArrayList(userAction1, userAction3));
//        userActionMap.put(2L, Lists.newArrayList(userAction1));
//        userActionMap.put(3L, Lists.newArrayList(userAction2));
//
//        userNameActionMap.put("user", Lists.newArrayList(userAction1, userAction3));
//        userNameActionMap.put("visitor", Lists.newArrayList(userAction1));
//        userNameActionMap.put("admin", Lists.newArrayList(userAction2));
    }

    public List<UserAction> getOwnActionListByUserId(Long userID) {
        List<UserAction> userActionList = userActionRepository.findByUser(userID);
        userActionCache.put(userID, userActionList);
        return userActionList;
    }

    public List<UserAction> loadActionUrl(String userName) {
        Optional<SysUser> userOptional = userRepository.findByUsername(userName);
        SysUser user = userOptional.orElse(null);
        if (user != null) {
            return Optional.ofNullable(userActionCache.getIfPresent(user.getId())).orElseGet(() -> userActionRepository.findByUser(user.getId()));
        }
        return null;


    }


}
