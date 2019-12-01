package com.oauth2.provider.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author louis
 * <p>
 * Date: 2019/11/20
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseEntity<Long>{




    @NonNull
    @NotNull
    @Column(name = "username", nullable = false,  unique = true,length = 36)
    private String username;


    @NonNull
    @NotNull
    @Column(name = "login_pwd", nullable = false)
    private String loginPwd;



    @NonNull
    @NotNull
    @Builder.Default
    @Column(name = "user_status", nullable = false,  length = 36)
    private String status="1";




}
