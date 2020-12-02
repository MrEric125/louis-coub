package com.generator;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Louis
 * @date created on 2020/12/1
 * description:
 */
@Setter
@Getter
public class EnumGlobalConfig extends GlobalConfig {

    private List<FieldEnumInfo> fieldEnumList = Lists.newArrayList();



}
