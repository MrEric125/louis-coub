package com.dmall.csws.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.dmall.csws.partner.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ERIC
 * @since 2020-12-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("login")
public class Login extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("wx_app_id")
    private String wxAppId;

    @TableField("code")
    private String code;

    @TableField("encrypted_data")
    private String encryptedData;

    @TableField("signature")
    private String signature;

    @TableField("token")
    private String token;

    @TableField("user_into")
    private String userInto;


}
