package louis.oauth.service;


import com.louis.core.service.IWebService;
import louis.oauth.SecurityUser;
import louis.oauth.dto.UserDto;
import louis.oauth.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author John·Louis
 * @date create in 2019/6/29
 * description:
 */
public interface SysUserService extends IWebService<SysUser, UserDto, Long> {


    SysUser findByUserName(String userName);

//    void modifyPsw(ModifyPswDto modifyPswDto);

    void handlerLoginData(OAuth2AccessToken token, SecurityUser principal, HttpServletRequest request);

//    SysUser registryUser(RegistryUserDto user, String defaultEmail, String defaultPassword, String defaultPhone);

    Collection<GrantedAuthority> loadUserAuthorities(long userId);





}
