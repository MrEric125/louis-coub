package louis.server.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author louis
 * <p>
 * Date: 2019/11/29
 * Description:
 */

public class CacheConfig {

    public static <T extends Serializable,F> Cache<T, F> createCache(){
        return CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.DAYS)
                .concurrencyLevel(10)
                .recordStats()
                .build();
    }
}
