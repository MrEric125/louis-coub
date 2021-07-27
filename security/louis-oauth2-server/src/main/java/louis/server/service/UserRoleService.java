package louis.server.service;
import louis.server.entity.asocciate.UserRole;

import java.util.Optional;

public interface UserRoleService {

    Optional<UserRole> findFirstByUserIdAndRoleId(Long userEntity, Long roleEntity);

}
