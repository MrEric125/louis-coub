package com.louis.service.impl;

import com.google.common.base.Joiner;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.louis.entity.UserAction;
import com.louis.service.UserPermissionService;
import com.louis.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author louis
 * <p>
 * Date: 2019/11/19
 * Description:
 */
@Slf4j
@Service("permissionService")
public class PermissionServiceImpl implements UserPermissionService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private Cache<String, List<UserAction>> userPermissionCache = CacheBuilder.newBuilder().build();



    @Autowired
    private UserActionService userActionService;


    /**
     * 通过用户--->role---->permission-->permission url
     * 这里会有一个性能问题，就是每次调用接口的时候都会从数据库查询相关权限数据？
     * 1. 需要用到缓存
     * 2. 将相应的url在加载的时候显示出来，其他没有的权限在加载的时候就不显示出来
     * @param authentication authentication
     * @param request request
     * @return boolean
     */
    @Override
    public boolean hasPermission(Authentication authentication, HttpServletRequest request) {
        String currentLoginName = SecurityUtils.getCurrentLoginName();
        List<UserAction> ifPresent=Optional.ofNullable(userPermissionCache.getIfPresent(currentLoginName)).orElseGet(() -> {
            List<UserAction> entityList = userActionService.loadActionUrl(currentLoginName);
            if (CollectionUtils.isNotEmpty(entityList)) {
                userPermissionCache.put(currentLoginName, entityList);
            }
            return entityList;
        });
        Set<String> currentAuthorityUrl = SecurityUtils.getCurrentAuthorityUrl(ifPresent);
        String requestURI = request.getRequestURI();
        log.info("验证权限 loginName={}, requestURI={}, hasAuthorityUrl={}", currentLoginName, requestURI, Joiner.on(",").join(currentAuthorityUrl));

        return currentAuthorityUrl.stream()
                .filter(authorityUrl -> antPathMatcher.match(authorityUrl, requestURI))
                .map(StringUtils::isNotEmpty)
                .findFirst()
                .orElse(false);
    }
}
