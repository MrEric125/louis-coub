package louis.server.service;

import com.google.common.cache.Cache;
import louis.server.config.CacheConfig;
import louis.server.entity.ClientDetailsEntity;
import louis.server.repository.ClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

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

        Optional<ClientDetailsEntity> cacheIfPresent = Optional.ofNullable(cache.getIfPresent(clientId));

        if (!cacheIfPresent.isPresent()) {
            Optional<ClientDetailsEntity> detailFromJdbc = clientDetailsRepository.findOneByClientId(clientId);
            return detailFromJdbc.map(clientDetailsEntity -> {
                cache.put(clientId, clientDetailsEntity);
                return clientDetailsEntity;

            }).orElseThrow(() -> new RuntimeException("没有找到对应的clientDetail" + clientId));
        }
        return cacheIfPresent.get();

    }




}
