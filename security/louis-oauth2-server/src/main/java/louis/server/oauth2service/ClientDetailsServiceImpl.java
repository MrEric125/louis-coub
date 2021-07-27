package louis.server.oauth2service;

import com.google.common.cache.Cache;
import louis.server.config.CacheConfig;
import louis.server.entity.ClientDetailsEntity;
import louis.server.entity.RedirectUriEntity;
import louis.server.entity.asocciate.ClientDetailsToScopes;
import louis.server.service.ClientDetailEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/11/29
 * Description:
 */
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private Cache<String, ClientDetails> clientDetailsCache = CacheConfig.createCache();

    @Autowired
    ClientDetailEntityService clientDetailEntityService;



    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return Optional.ofNullable(clientDetailsCache.getIfPresent(s))
                .orElseGet(() -> {
                    BaseClientDetails clientDetails = Optional
                            .ofNullable(clientDetailEntityService.loadClientByClientId(s))
                            .map(entityToDomain)
                            .orElseThrow(() -> new RuntimeException("没有找到对应的clientDetail" + s));
                    clientDetailsCache.put(s, clientDetails);
                    return clientDetails;
                });
    }

    private final Function<? super ClientDetailsEntity, ? extends BaseClientDetails> entityToDomain = entity -> {
        BaseClientDetails clientDetails = new BaseClientDetails();

        clientDetails.setClientId(entity.getClientId());
        clientDetails.setClientSecret(entity.getClientSecret());

        clientDetails.setAccessTokenValiditySeconds(entity.getAccessTokenValiditySeconds());
        clientDetails.setRefreshTokenValiditySeconds(entity.getRefreshTokenValiditySeconds());


        clientDetails.setAuthorizedGrantTypes(entity.getAuthorizedGrantTypes().stream().map(
                grantTypeMultiEntity -> grantTypeMultiEntity.getGrantType().getValue()).collect(Collectors.toList()));

        clientDetails.setScope(entity.getScopes().stream().map(
                scopeMultiEntity -> scopeMultiEntity.getScope().getValue()).collect(Collectors.toList()));

        clientDetails.setAutoApproveScopes(entity.getScopes().stream().filter(ClientDetailsToScopes::getAutoApprove)
                .map(scopeMultiEntity -> scopeMultiEntity.getScope().getValue()).collect(Collectors.toList()));

        clientDetails.setResourceIds(entity.getResourceIds().stream().map(resMulti -> resMulti.getResourceId().getValue()).collect(Collectors.toList()));

        clientDetails.setRegisteredRedirectUri(entity.getRedirectUris().stream().map(RedirectUriEntity::getValue).collect(Collectors.toSet()));

        clientDetails.setAdditionalInformation(Collections.emptyMap());

        return clientDetails;
    };

}
