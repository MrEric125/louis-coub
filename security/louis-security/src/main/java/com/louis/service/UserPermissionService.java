package com.louis.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author louis
 * <p>
 * Date: 2019/11/19
 * Description:
 */
public interface UserPermissionService {

    /**
     * 校验是否有权限
     * @param authentication
     * @param request
     * @return
     */
    boolean hasPermission(Authentication authentication, HttpServletRequest request);
}
