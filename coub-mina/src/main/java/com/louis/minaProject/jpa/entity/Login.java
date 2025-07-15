package com.louis.minaProject.jpa.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "wx_app_id")
    private String WxAppId;

    @Column(name = "token")
    private String token;

    @Column(name = "user_into")
    private String userInfo;

    @Column(name = "encrypted_data")
    private String encryptedData;

    @Column(name = "signature")
    private String signature;


}
