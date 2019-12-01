package com.oauth2.provider.service.impl;

import com.oauth2.provider.entity.SysRole;
import com.oauth2.provider.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author louis
 * <p>
 * Date: 2019/11/19
 * Description:
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Optional<SysRole> getRoleByRoleName(String roleName)  {
//        return repository.getFirstByRoleName(roleName).orElseThrow(() -> new NotFoundEntityException("实体类没有找到"));
        return repository.getByRoleName(roleName);

    }


}
