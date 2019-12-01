package com.oauth2.provider.repository;

import com.oauth2.provider.entity.UserAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author John·Louis
 * @date create in 2019/12/1
 * description:
 */
public interface UserActionRepository extends JpaRepository<UserAction, Long> {

    List<UserAction> findByUser(Long userId);
}
