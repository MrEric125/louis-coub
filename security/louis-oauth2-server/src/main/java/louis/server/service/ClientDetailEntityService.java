package louis.server.service;

import com.google.common.cache.Cache;
import louis.server.config.CacheConfig;
import louis.server.entity.ClientDetailsEntity;
import louis.server.repository.ClientDetailsRepository;
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
public class ClientDetailEntityService {

    public Cache<String, ClientDetailsEntity> cache = CacheConfig.createCache();

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    public ClientDetailsEntity loadClientByClientId(String clientId) {
       return Optional.
               ofNullable(cache.getIfPresent(clientId))
               .orElseGet(
                       () -> clientDetailsRepository.findOneByClientId(clientId)
                       .map(clientDetailsEntity -> {
                           cache.put(clientId, clientDetailsEntity);
                           return clientDetailsEntity;
                       }).orElseThrow(() -> new RuntimeException("没有找到对应 client id" + clientId)
                       )
               );

    }

}
