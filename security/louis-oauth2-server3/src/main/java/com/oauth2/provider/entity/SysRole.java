package com.oauth2.provider.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author John·Louis
 * @date create in 2019/11/30
 * description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_role")
public class SysRole extends BaseEntity<Long> {

    @NonNull
    @NotNull
    @Column(name = "role_name", nullable = false, unique = true,length = 36)
    private String roleName;

    @NotNull
    @Column(nullable = false)
    @ColumnDefault("False")
    private boolean disabled;



}
