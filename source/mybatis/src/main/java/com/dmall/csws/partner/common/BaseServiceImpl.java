package com.dmall.csws.partner.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author Louis
 * @date created on 2020/11/30
 * description:
 */
public class BaseServiceImpl<M extends BaseMapper<E>,E extends BaseEntity> extends ServiceImpl<M,E> implements IService<E> {
}
