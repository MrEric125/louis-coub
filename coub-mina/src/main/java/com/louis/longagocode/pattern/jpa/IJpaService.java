package com.louis.longagocode.pattern.jpa;

import com.pattern.jpa.IBaseService;

/**
 * @author jun.liu
 * @date created on 2020/7/18
 * description:
 */
public interface IJpaService extends IBaseService {

    String findByName(String name);

}
