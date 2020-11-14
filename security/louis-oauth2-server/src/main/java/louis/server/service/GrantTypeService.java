package louis.server.service;

import louis.server.entity.GrantTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface GrantTypeService {

    Optional<GrantTypeEntity> findOneByValue(String value);
}
