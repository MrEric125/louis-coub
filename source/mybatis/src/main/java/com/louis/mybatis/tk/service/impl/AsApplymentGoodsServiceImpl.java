package com.louis.mybatis.tk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.louis.mybatis.tk.entry.AsApplymentGoods;
import com.louis.mybatis.tk.mapper.AsApplymentGoodsMapper;
import com.louis.mybatis.tk.service.IAsApplymentGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 售后退货受理单-商品详情 服务实现类
 * </p>
 *
 * @author xiongkunwei
 * @since 2021-07-29
 */
@Primary
@Service
@Slf4j
public class AsApplymentGoodsServiceImpl extends ServiceImpl<AsApplymentGoodsMapper, AsApplymentGoods> implements IAsApplymentGoodsService {

}
