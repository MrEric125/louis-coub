package louis.server.service;

import louis.server.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RoleService {

    Optional<RoleEntity> findOneByName(String roleName);

}
