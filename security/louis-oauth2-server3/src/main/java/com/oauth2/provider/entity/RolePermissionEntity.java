package com.oauth2.provider.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author louis
 * <p>
 * Date: 2019/11/18
 * Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "role_permission")
public class RolePermissionEntity extends BaseEntity<Long> {



    @NonNull
    @NotNull
    @Column(name = "role_name", nullable = false,  length = 36)
    private String roleName;

    @NonNull
    @NotNull
    @Column(name = "permission_name", nullable = false,  length = 36)
    private String permissionName;

    @NonNull
    @NotNull
    @Column(name = "url", nullable = false, length = 36)
    private String url;


}
