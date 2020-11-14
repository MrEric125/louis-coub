package louis.server.devdata;

import louis.server.entity.RoleEntity;
import louis.server.entity.UserEntity;
import louis.server.entity.asocciate.UserRole;
import louis.server.repository.RoleRepository;
import louis.server.repository.UserRepository;
import louis.server.repository.UserRoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/11/29
 * Description:
 */
@Configuration
public class DefaultUserAndRoleConfig implements InitializingBean {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public void afterPropertiesSet() throws Exception {

//        load Roles
        List<RoleEntity> savedRole = DevDataAutoCreate.loadBaseRole().stream().
                map(role -> roleRepository.findOneByName(role.getName())
                        .orElseGet(
                                () -> roleRepository.save(role)
                        )
                ).collect(Collectors.toList());
//        load User
        List<UserEntity> savedUser = DevDataAutoCreate.loadBaseUser().stream()
                .map(user -> userRepository.findOneByUsername(user.getUsername())
                        .orElseGet(
                                () -> userRepository.save(user)
                        )
                ).collect(Collectors.toList());
//        加载同名用户角色对应关系
        savedRole.forEach(role->{
            UserEntity userEntity=savedUser.stream()
                    .filter(
                            user -> user.getUsername().equalsIgnoreCase(role.getName())
                    ).findAny().orElseThrow(() -> new RuntimeException("没有找到同名用户或角色"));

            userRoleRepository.findFirstByUserIdAndRoleId(userEntity.getId(), role.getId())
                    .orElseGet(
                            () -> userRoleRepository.save(UserRole.builder()
                                    .roleId(role.getId())
                                    .userId(userEntity.getId()).build()
                            )
                    );
        });


    }
}
