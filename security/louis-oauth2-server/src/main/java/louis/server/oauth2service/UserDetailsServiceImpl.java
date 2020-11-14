package louis.server.oauth2service;

import louis.server.entity.UserEntity;
import louis.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author louis
 * <p>
 * Date: 2019/11/29
 * Description:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return  Optional.ofNullable(userService.getUserById(username))
                .map(userEntity -> User.builder()
                        .username(userEntity.getUsername())
                        .password(userEntity.getPassword())
                        .authorities("ROLE_USER")
                        .build())
                .orElseThrow(() -> new RuntimeException("没有找到对应用户"));

    }
}
