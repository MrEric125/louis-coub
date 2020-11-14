package louis.server.service;

import louis.server.entity.ResourceIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ResourceIdService {

    Optional<ResourceIdEntity> findOneByValue(String value);
}
