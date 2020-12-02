package com.dmall.csws.domain.service.impl;

import com.dmall.csws.domain.model.Employees;
import com.dmall.csws.domain.mapper.EmployeesMapper;
import com.dmall.csws.domain.service.IEmployeesService;
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
public class EmployeesServiceImpl extends BaseServiceImpl<EmployeesMapper, Employees> implements IEmployeesService {

}
