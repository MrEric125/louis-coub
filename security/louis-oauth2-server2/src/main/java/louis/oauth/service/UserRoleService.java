package louis.oauth.service;

import com.louis.core.service.ICRUDService;
import louis.oauth.dto.UserRoleDto;
import louis.oauth.entity.SysRole;
import louis.oauth.entity.UserRole;

import java.util.List;

/**
 * @author John·Louis
 * @date create in 2019/6/29
 * description:
 */

public interface UserRoleService extends ICRUDService<UserRole, Long> {

    List<UserRole> findByUserId(long userId);

    List<UserRole> findByRoleId(long roleId);

    void blindRole(UserRoleDto dto);

    List<SysRole> getRoleByUser(long userId);

//    void updateUserRole(long userId, String oldRoleName, RoleDto roleDto);



}
