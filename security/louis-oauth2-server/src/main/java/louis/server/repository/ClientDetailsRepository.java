package louis.server.repository;

import louis.server.entity.ClientDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientDetailsRepository extends JpaRepository<ClientDetailsEntity, Long> {

    Optional<ClientDetailsEntity> findOneByClientId(String clientId);
}
