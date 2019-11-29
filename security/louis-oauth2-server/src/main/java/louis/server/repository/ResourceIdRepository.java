package louis.server.repository;

import louis.server.entity.ResourceIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceIdRepository extends JpaRepository<ResourceIdEntity, Long> {

    Optional<ResourceIdEntity> findOneByValue(String value);
}
