package com.dmall.csws.domain.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
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
@TableName("employees")
public class Employees extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("fname")
    private String fname;

    @TableField("lname")
    private String lname;

    @TableField("hired")
    private LocalDate hired;

    @TableField("separated")
    private LocalDate separated;

    @TableField("job_code")
    private Integer jobCode;

    @TableField("store_id")
    private Integer storeId;


}
