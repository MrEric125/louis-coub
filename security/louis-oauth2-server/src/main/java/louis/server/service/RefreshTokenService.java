package louis.server.service;

import louis.server.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RefreshTokenService {

    Optional<RefreshTokenEntity> findOneByTokenId(String tokenId);

    void deleteByTokenId(String tokenId);
}
