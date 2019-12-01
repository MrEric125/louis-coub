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

package com.oauth2.provider.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * The class Uac action.
 *
 * @author paascloud.net@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_action")

public class UserAction extends BaseEntity<Long> {

	private static final long serialVersionUID = 6943147531573339665L;

	@NonNull
	@NotNull
	@Column(name = "user_id", nullable = false,  length = 36)
	private Long user;


	@NonNull
	@NotNull
	@Column(name = "act_url", nullable = false,  length = 36)
	private String url;

	@NonNull
	@NotNull
	@Column(name = "action_code", nullable = false,  length = 36)
	private String actionCode;

	@NonNull
	@NotNull
	@Column(name = "menu_id", nullable = false,  length = 36)
	private Long menuId;

	@Transient
	private List<Long> menuIdList;
}