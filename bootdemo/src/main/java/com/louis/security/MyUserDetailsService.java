//package com.louis.security;
//
//import com.google.common.collect.Lists;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.com.louis.mybatis.entity.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author louis
// * <p>
// * Date: 2019/8/6
// * Description:
// */
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<GrantedAuthority> authorityList = Lists.newArrayList();
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(username);
//        authorityList.add(grantedAuthority);
//        return new com.louis.mybatis.entity.User(username, passwordEncoder.encode(username), authorityList);
//    }
//
//}
