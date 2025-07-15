package com.louis.minaProject.jpa.repository;

import com.louis.minaProject.jpa.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author John·Louis
 * @date created on 2020/3/13
 * description:
 */
public interface LoginRepository extends JpaRepository<Login, Long> {
}
