package louis.server.service;

import com.google.common.cache.Cache;
import louis.server.config.CacheConfig;
import louis.server.entity.UserEntity;
import louis.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author louis
 * <p>
 * Date: 2019/11/29
 * Description:
 */
@Service
public class UserService {

    private Cache<String, UserEntity> userEntityCahce = CacheConfig.createCache();

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserById(String userName) {
        return Optional.ofNullable(userEntityCahce.getIfPresent(userName))
                .orElseGet(() -> userRepository.findOneByUsername(userName).map(entity -> {
                    userEntityCahce.put(userName, entity);
                    return entity;
                }).orElseThrow(() -> new RuntimeException("没有找到对应的用户" + userName)));
    }

    public UserEntity direactGetByCache(String userName) {
        return userEntityCahce.getIfPresent(userName);
    }

    public void cleanCache() {
        userEntityCahce.invalidateAll();
    }
}
