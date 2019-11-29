package louis.server.repository;
import louis.server.entity.asocciate.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findFirstByUserIdAndRoleId(Long userEntity, Long roleEntity);

}
