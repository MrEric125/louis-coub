package com.louis.service.impl;

import com.louis.entity.SysUser;
import com.louis.entity.UserAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/11/19
 * Description:
 */
@Service
public class UserService implements UserDetailsService {

    private Map<String, User> userMap = new ConcurrentHashMap<>();

    private Map<String, SysUser> sysUserMap = new ConcurrentHashMap<>();


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserActionService userActionService;

    @PostConstruct
    public void init() {

//        基于角色的用户
        userMap.put("user", new User("user", passwordEncoder.encode("123456"), roleService.getAuthorityByUserName("user")));
        userMap.put("visitor", new User("visitor", passwordEncoder.encode("123456"), roleService.getAuthorityByUserName("visitor")));
        userMap.put("admin", new User("admin", passwordEncoder.encode("123456"), roleService.getAuthorityByUserName("admin")));

//        基于操作url的用户
        sysUserMap.put("user", new SysUser(1L, "user", "user", passwordEncoder.encode("123456"), 1L, "group1", "1"));
        sysUserMap.put("visitor", new SysUser(2L, "visitor", "visitor", passwordEncoder.encode("123456"), 1L, "group1", "1"));
        sysUserMap.put("admin", new SysUser(3L, "admin", "admin", passwordEncoder.encode("123456"), 1L, "group1", "1"));
    }

    @Autowired
    private RoleService roleService;

    public SysUser findByLoginName(String userName) {
        return sysUserMap.get(userName);
    }




    public Collection<GrantedAuthority> loadUserAuthorities(Long userId) {
        List<UserAction> ownAuthList = userActionService.getOwnActionListByUserId(userId);
        return ownAuthList.stream()
                .map(x -> new SimpleGrantedAuthority(x.getUrl()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> grantedAuthorities;
        SysUser user=Optional.ofNullable(this.findByLoginName(username)).orElseThrow(()-> new BadCredentialsException("用户名不存在或者密码错误"));

        grantedAuthorities = loadUserAuthorities(user.getId());
        return new User(user.getUserName(), user.getLoginPwd(), grantedAuthorities);
    }

}
