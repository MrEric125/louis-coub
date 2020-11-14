package louis.server.repository;

import louis.server.entity.ScopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScopeRepository extends JpaRepository<ScopeEntity, Long> {

    Optional<ScopeEntity> findOneByValue(String value);
}
