package louis.server.service;

import louis.server.entity.ScopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ScopeService {

    Optional<ScopeEntity> findOneByValue(String value);
}
