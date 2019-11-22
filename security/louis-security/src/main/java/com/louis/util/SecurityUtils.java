package com.louis.util;

import com.google.common.collect.Sets;
import com.louis.entity.RolePermissionEntity;
import com.louis.entity.UserAction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/11/19
 * Description:
 */
@NoArgsConstructor
public class SecurityUtils {


    /**
     * Gets current login name.
     *
     * @return the current login name
     */
    public static String getCurrentLoginName() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        if (principal instanceof Principal) {
            return ((Principal) principal).getName();
        }

        return String.valueOf(principal);
    }

    public static Set<String> getCurrentAuthorityUrl(List<UserAction> lists) {
        return Optional.ofNullable(lists).orElse(Lists.newArrayList()).stream().map(UserAction::getUrl).collect(Collectors.toSet());
    }
}
