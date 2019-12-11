package com.oauth2.provider.service.impl;

import com.oauth2.provider.dto.UserDto;
import com.oauth2.provider.entity.SysUser;
import com.oauth2.provider.entity.UserAction;
import com.oauth2.provider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/11/19
 * Description:
 */
@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserActionService userActionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Collection<GrantedAuthority> loadUserAuthorities(Long userId) {
        List<UserAction> ownAuthList = userActionService.getOwnActionListByUserId(userId);
        return ownAuthList.stream()
                .map(x -> new SimpleGrantedAuthority(x.getUrl()))
                .collect(Collectors.toList());
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> grantedAuthorities;
        SysUser user=userRepository.findByUsername(username).orElseThrow(()-> new BadCredentialsException("用户名不存在或者密码错误"));
        grantedAuthorities = loadUserAuthorities(user.getId());
        return new User(user.getUsername(), user.getLoginPwd(), grantedAuthorities);
    }

    public String login(UserDto userDto) {
        UserDetails userDetails = loadUserByUsername(userDto.getUsername());
        if (!passwordEncoder.matches(userDto.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        return UUID.randomUUID().toString();

    }


}
