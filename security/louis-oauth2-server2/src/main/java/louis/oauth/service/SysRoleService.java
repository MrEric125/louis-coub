package louis.oauth.service;

import com.louis.core.service.IWebService;
import louis.oauth.dto.RoleDto;
import louis.oauth.entity.SysRole;

/**
 * @author John·Louis
 * @date create in 2019/7/3
 * description:
 */
public interface SysRoleService extends IWebService<SysRole, RoleDto,Long> {

    SysRole findByRoleName(String roleName);
}
