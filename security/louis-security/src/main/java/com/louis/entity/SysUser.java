package com.louis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class SysUser {

    private Long id;

    private String userName;

    private String loginName;

    private String loginPwd;

    private Long groupId;

    private String groupName;

    private String status;




}
