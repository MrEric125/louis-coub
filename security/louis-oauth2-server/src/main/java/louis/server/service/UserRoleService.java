package louis.server.service;
import louis.server.entity.asocciate.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleService {

    Optional<UserRole> findFirstByUserIdAndRoleId(Long userEntity, Long roleEntity);

}
