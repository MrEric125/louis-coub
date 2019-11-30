package louis.oauth.service.impl;

import com.louis.core.service.AbstractWebCRUDService;
import louis.oauth.dto.RoleDto;
import louis.oauth.entity.SysRole;
import louis.oauth.repository.SysRoleRepository;
import louis.oauth.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author John·Louis
 * @date create in 2019/5/18
 */
@Service
public class SysRoleServiceImpl extends AbstractWebCRUDService<SysRole, RoleDto, Long> implements SysRoleService {

    /**
     *
     * @return
     */
    public SysRoleRepository getBaseRepository() {
        return (SysRoleRepository) baseRepository;
    }

    /**
     *
     * @param roleName
     * @return
     */
    public SysRole findByRoleName(String roleName) {
       return getBaseRepository().findByRoleName(roleName);
    }

    /**
     *
     * @param dto
     * @return
     */
    @Override
    public SysRole dtoToEntity(RoleDto dto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(dto, sysRole);
        return sysRole;
    }

    /**
     *
     * @param sysRole
     * @return
     */
    @Override
    public RoleDto entityToDto(SysRole sysRole) {
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(sysRole, roleDto);
        return roleDto;
    }

}
