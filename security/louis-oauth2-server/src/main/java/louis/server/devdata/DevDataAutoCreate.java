package louis.server.devdata;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import louis.server.entity.RoleEntity;
import louis.server.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.List;

/**
 * @author louis
 * <p>
 * Date: 2019/11/28
 * Description:
 */
public class DevDataAutoCreate {




    public static final String[] DEFAULT_GRANT_TYPES = {"authorization_code", "refresh_token", "password"};

    public static final String[] DEFAULT_SCOPES = {"read", "write", "trust"};


    public static List<BaseClientDetails> loadBaseClientDetails() {
        BaseClientDetails clientDetails1 = new BaseClientDetails("test-client-id", null, "read,write,trust", "authorization_code,refresh_token", null);
        clientDetails1.setClientSecret("test-client-id-secret-123");
        clientDetails1.setRegisteredRedirectUri(Sets.newHashSet("http://localhost/login"));

        BaseClientDetails clientDetails2 = new BaseClientDetails("test-res-client", null, null, null, null);
        clientDetails2.setClientSecret("test-res-client-secret-123");
        clientDetails2.setRegisteredRedirectUri(Sets.newHashSet("http://localhost/login"));

        BaseClientDetails clientDetails3 = new BaseClientDetails("test_password_client", null, "trust", "password", null);
        clientDetails3.setClientSecret("1234567");
        clientDetails3.setRegisteredRedirectUri(Sets.newHashSet("http://localhost/login"));

        return Lists.newArrayList(clientDetails1, clientDetails2, clientDetails3);
    }

    public static List<UserEntity> loadBaseUser() {
         PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserEntity user1 = UserEntity.builder().username("admin").password(passwordEncoder.encode("123456")).build();
        UserEntity user2 = UserEntity.builder().username("user").password(passwordEncoder.encode("123456")).build();
        return Lists.newArrayList(user1, user2);
    }

    public static List<RoleEntity> loadBaseRole() {
        RoleEntity role1 = RoleEntity.builder().name("USER").build();
        RoleEntity role2 = RoleEntity.builder().name("ADMIN").build();
        return Lists.newArrayList(role1, role2);

    }



}
