package com.generator;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author Louis
 * @date created on 2020/12/1
 * description:
 */
@Setter
@Getter
public class CswsGlobalConfig extends GlobalConfig {

    private List<FieldEnumInfo> fieldEnumList = Lists.newArrayList();

    private Map<String, String> subdivisionOutDir = Maps.newConcurrentMap();



}
