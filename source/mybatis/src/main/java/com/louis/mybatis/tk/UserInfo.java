package com.louis.mybatis.tk;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "language")
    private String language;

    @Column(name = "city")
    private String city;

    @Column(name = "province")
    private String province;

    @Column(name = "country")
    private String country;

    @Column(name = "avata_url")
    private String avataUrl;

    private String json;

}
