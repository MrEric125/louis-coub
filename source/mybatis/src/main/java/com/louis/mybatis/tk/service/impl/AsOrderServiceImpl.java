package com.louis.mybatis.tk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.mybatis.tk.entry.AsOrder;
import com.louis.mybatis.tk.mapper.AsOrderMapper;
import com.louis.mybatis.tk.service.IAsOrderService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 售后订单表 服务实现类
 * </p>
 *
 * @author xiongkunwei
 * @since 2021-08-06
 */
@Primary
@Service
public class AsOrderServiceImpl extends ServiceImpl<AsOrderMapper, AsOrder> implements IAsOrderService {

}