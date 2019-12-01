package com.oauth2.provider.devdata;

import com.oauth2.provider.entity.RolePermissionEntity;
import com.oauth2.provider.entity.SysRole;
import com.oauth2.provider.entity.SysUser;
import com.oauth2.provider.entity.UserAction;
import org.assertj.core.util.Lists;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * @author John·Louis
 * @date create in 2019/11/30
 * description:
 */
public class DevDataAutoCreate {

    public static List<SysRole> loadRole() {
        SysRole role = SysRole.builder().roleName("admin").build();
        SysRole role2 = SysRole.builder().roleName("user").build();
        return Lists.newArrayList(role, role2);


    }
    public static List<SysUser> loadUser() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        SysUser user1 = SysUser.builder().username("user").loginPwd(passwordEncoder.encode("123456")).build();
        SysUser user2 = SysUser.builder().username("admin").loginPwd(passwordEncoder.encode("123456")).build();
        SysUser user3 = SysUser.builder().username("visitor").loginPwd(passwordEncoder.encode("123456")).build();
        return Lists.newArrayList(user1, user2, user3);

    }
    public static List<UserAction> loadUserAction() {
        return null;
    }
    public  static List<RolePermissionEntity> loadrolePermissionEntity() {
        return null;

    }

    public static void main(String[] args) {
        loadUser();
    }
}
