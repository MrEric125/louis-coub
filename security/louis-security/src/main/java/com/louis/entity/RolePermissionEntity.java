package com.louis.entity;

import lombok.*;

import java.util.List;

/**
 * @author louis
 * <p>
 * Date: 2019/11/18
 * Description:
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionEntity {

    private Long id;

    private String roleName;

    private String permissionName;

    private String url;


}
