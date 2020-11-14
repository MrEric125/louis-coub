package com.louis.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/11/19
 * Description:
 */
@Service
public class RoleService {

     private Map<String, GrantedAuthority> authorityMap = new ConcurrentHashMap<>();
    private Map<String, List<String>> roleMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void init() {
        authorityMap.put("admin", new SimpleGrantedAuthority("ROLE_admin"));
        authorityMap.put("user", new SimpleGrantedAuthority("ROLE_user"));
        authorityMap.put("visitor", new SimpleGrantedAuthority("ROLE_visitor"));
        roleMap.put("admin", Lists.newArrayList("admin"));
        roleMap.put("user", Lists.newArrayList("user","visitor"));
        roleMap.put("visitor", Lists.newArrayList("visitor"));


    }



    public List<GrantedAuthority> getAuthorityByUserName(String userName) {
        List<String> list = roleMap.get(userName);
        return list.stream().map(x -> authorityMap.get(x)).collect(Collectors.toList());
    }
}
