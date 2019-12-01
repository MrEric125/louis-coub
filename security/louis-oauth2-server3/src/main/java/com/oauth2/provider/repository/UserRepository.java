package com.oauth2.provider.repository;

import com.oauth2.provider.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author John·Louis
 * @date create in 2019/11/30
 * description:
 */
public interface UserRepository extends JpaRepository<SysUser, Long> {

    Optional<SysUser> findByUsername(String userName);

}
