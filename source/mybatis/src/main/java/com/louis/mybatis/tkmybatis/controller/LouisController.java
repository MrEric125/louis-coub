package com.louis.mybatis.tkmybatis.controller;

//import com.louis.mybatis.tkmybatis.entity.LocalUser;
//import com.louis.mybatis.tkmybatis.mapper.LocalUserMapper;
import com.google.common.collect.Lists;
import com.louis.mybatis.tkmybatis.entity.TablePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author louis
 * <p>
 * Date: 2019/9/11
 * Description:
 */
@RestController
public class LouisController {

//    @Autowired
//    LocalUserMapper louisMapper;
//
    @RequestMapping("/select")
    public List<TablePo> select() throws SQLException {
        return tableName();
    }

    @Autowired
    private DataSource dataSource;


    private Connection connection() throws SQLException {
        return dataSource.getConnection();
    }

    public List<TablePo> tableName() throws SQLException {
        DatabaseMetaData databaseMetaData = connection().getMetaData();
        String[] types = {"TABLE"};
        ResultSet rs = databaseMetaData.getTables(connection().getCatalog(), null, null, types);


        List<TablePo> tablePos = Lists.newArrayList();
        while (rs.next()) {
            TablePo tablePo = TablePo.builder()
                    .tableName(rs.getString("TABLE_NAME"))
                    .tableType(rs.getString("TABLE_TYPE"))
                    .tableMark(rs.getString("REMARKS"))
                    .build();
            tablePos.add(tablePo);

            ResultSetMetaData metaData = rs.getMetaData();


        }
        return tablePos;

    }


}
