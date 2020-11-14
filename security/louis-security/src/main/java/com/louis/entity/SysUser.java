package com.louis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @author louis
 * <p>
 * Date: 2019/11/20
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Configurable
public class SysUser {

    private Long id;

    private String userName;

    private String loginName;

    private String loginPwd;

    private Long groupId;

    private String groupName;

    private String status;




}
