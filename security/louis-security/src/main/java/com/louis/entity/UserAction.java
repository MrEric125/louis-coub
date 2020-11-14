/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：UacAction.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.louis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * The class Uac action.
 *
 * @author paascloud.net@gmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAction implements Serializable {
	private static final long serialVersionUID = 6943147531573339665L;

	private Long id;

	private String url;

	private String actionCode;

	private Long menuId;

	private List<Long> menuIdList;
}