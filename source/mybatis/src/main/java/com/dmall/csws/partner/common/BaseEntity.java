package com.dmall.csws.partner.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Louis
 * @date created on 2020/11/30
 * description:
 */
@Data
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
}
