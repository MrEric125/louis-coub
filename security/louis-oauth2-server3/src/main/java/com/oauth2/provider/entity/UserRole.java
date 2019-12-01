package com.oauth2.provider.entity;

import lombok.*;

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
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sys_user_role")
public class UserRole extends BaseEntity<Long> {

    @NonNull
    @NotNull
    @Column(name = "role_id", nullable = false, length = 36)
    private Long role;

    @NonNull
    @NotNull
    @Column(name = "user_id",   length = 36)
    private Long user;
}
