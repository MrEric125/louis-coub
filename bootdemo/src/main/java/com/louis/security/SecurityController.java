//package com.louis.security;
//
//import com.google.common.collect.Maps;
//import com.louis.bootmybatis.common.WrapMapper;
//import com.louis.bootmybatis.common.Wrapper;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
///**
// * @author louis
// * <p>
// * Date: 2019/8/5
// * Description:
// */
//@RestController
//
//public class SecurityController {
//
//
//    @RequestMapping("/auth")
//    public Wrapper getAuth(Authentication auth) {
//        Map<String, Object> userInfo = Maps.newHashMap();
//        userInfo.put("user", auth.getPrincipal());
//        userInfo.put("authorities", AuthorityUtils.authorityListToSet(auth.getAuthorities()));
//        return WrapMapper.ok(userInfo);
//
//
//    }
//}
