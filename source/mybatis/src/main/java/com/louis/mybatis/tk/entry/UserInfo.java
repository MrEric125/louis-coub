package com.louis.mybatis.tk.entry;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_info")
public class UserInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String nickName;

    private Integer gender;

    private String language;

    private String city;

    private String province;

    private String country;

    private String avataUrl;

    private String json;

}
