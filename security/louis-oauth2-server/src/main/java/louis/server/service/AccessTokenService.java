package louis.server.service;

import louis.server.entity.AccessTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface AccessTokenService{

    Optional<AccessTokenEntity> findOneByTokenId(String tokenId);

    Optional<AccessTokenEntity> findOneByAuthenticationId(String authenticationId);

    void deleteByTokenId(String tokenId);

    void deleteByRefreshTokenTokenId(String refreshTokenId);

    List<AccessTokenEntity> findAllByClientIdAndUserName(String clientId, String userName);

    List<AccessTokenEntity> findAllByClientId(String clientId);

}
