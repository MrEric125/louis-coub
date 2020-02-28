package com.louis.minashop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John·Louis
 * @date created on 2020/2/23
 * description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {

    private String code;

    private String WxAppId;

    private String token;

    private UserInfo UserInfo;

    private String encryptedData;

    private String signature;







}
