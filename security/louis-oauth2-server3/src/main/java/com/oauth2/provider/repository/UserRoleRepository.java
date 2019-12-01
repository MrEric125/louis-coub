package com.oauth2.provider.repository;
import com.oauth2.provider.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findFirstByUserAndRole(Long userEntity, Long roleEntity);

}
