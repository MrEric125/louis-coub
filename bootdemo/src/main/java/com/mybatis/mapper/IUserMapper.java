package com.mybatis.mapper;

import com.mybatis.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @author louis
 * <p>
 * Date: 2019/9/10
 * Description:
 */
@Repository
public interface IUserMapper extends BaseMapper<User> {
}
