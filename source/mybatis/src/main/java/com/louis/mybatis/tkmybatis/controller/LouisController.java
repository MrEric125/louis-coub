package com.louis.mybatis.tkmybatis.controller;


import com.google.common.collect.Lists;
import com.louis.mybatis.tkmybatis.entity.ColumnPo;
import com.louis.mybatis.tkmybatis.entity.TablePo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author louis
 * <p>
 * Date: 2019/9/11
 * Description:
 */
@Slf4j
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
            String tableName = rs.getString("TABLE_NAME");
            TablePo tablePo = TablePo.builder()
                    .tableName(tableName)
                    .tableType(rs.getString("TABLE_TYPE"))
                    .tableMark(rs.getString("REMARKS"))
                    .build();
            tablePos.add(tablePo);

            List<ColumnPo> columnPoList = this.queryAllColumn(databaseMetaData,tableName);
            tablePo.setColumnPos(columnPoList);


        }
        return tablePos;

    }

    public List<ColumnPo> queryAllColumn(DatabaseMetaData databaseMetaData, String tableName) throws SQLException {
        ResultSet columns = databaseMetaData.getColumns(connection().getCatalog(), databaseMetaData.getUserName(), tableName, null);
        List<ColumnPo> columnPoList = Lists.newArrayList();
        while (columns.next()) {
            String column_name = columns.getString("COLUMN_NAME");
            String type_name = columns.getString("TYPE_NAME");
            String data_type = columns.getString("DATA_TYPE");
            String column_size = columns.getString("COLUMN_SIZE");
            String decimal_digits = columns.getString("DECIMAL_DIGITS");
            ColumnPo columnPo = ColumnPo.builder()
                    .columnName(column_name)
                    .columnType(type_name)
                    .columnLength(column_size)
                    .build();
            columnPoList.add(columnPo);

        }

        return columnPoList;

    }


}
