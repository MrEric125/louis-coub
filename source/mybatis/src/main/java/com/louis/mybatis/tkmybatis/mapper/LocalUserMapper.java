package com.louis.mybatis.tkmybatis.mapper;

import com.louis.mybatis.tkmybatis.entity.LocalUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

public interface LocalUserMapper extends BaseMapper<LocalUser> {
    int updateBatch(List<LocalUser> list);

    int batchInsert(@Param("list") List<LocalUser> list);

    int insertOrUpdate(LocalUser record);

    int insertOrUpdateSelective(LocalUser record);
}