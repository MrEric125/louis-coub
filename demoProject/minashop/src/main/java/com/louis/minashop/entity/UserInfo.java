package com.louis.minashop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John·Louis
 * @date created on 2020/2/24
 * description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private String nickName;

    private int gender;

    private String language;

    private String city;

    private String province;

    private String country;

    private String avataUrl;

}
