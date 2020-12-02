package com.dmall.csws.domain.service.impl;

import com.dmall.csws.domain.model.Login;
import com.dmall.csws.domain.mapper.LoginMapper;
import com.dmall.csws.domain.service.ILoginService;
import com.dmall.csws.partner.common.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ERIC
 * @since 2020-12-01
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl<LoginMapper, Login> implements ILoginService {

}
