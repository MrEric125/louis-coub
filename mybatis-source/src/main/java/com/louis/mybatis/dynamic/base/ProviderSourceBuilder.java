package com.louis.mybatis.dynamic.base;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
public class ProviderSourceBuilder extends BaseBuilder{

    public  String getById(MappedStatement mappedStatement) {
        Class<?> mapperClass = getMapperClass(mappedStatement);
        Class<?> modelClass = getModelClass(mappedStatement);
        Class<?> primaryFieldClass = getPrimaryFieldClass(mappedStatement);
        try {
            SQL sql = new SQL();

            String tableName = getTableName(modelClass);
            String primaryColumnName = getPrimaryColumnName( modelClass,primaryFieldClass);
            sql.SELECT("*");
            sql.FROM(tableName);
            sql.WHERE(primaryColumnName + "=#{id}");
            return sql.toString();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  String insert(MappedStatement mappedStatement) {

        Class<?> mapperClass = getMapperClass(mappedStatement);
        Class<?> modelClass = getModelClass(mappedStatement);
        Class<?> primaryFieldClass = getPrimaryFieldClass(mappedStatement);
        try {
            SQL sql = new SQL();

            boolean generated = false;

            Method mapperMethod = getMapperMethod(mappedStatement,modelClass);
            Options methodOptions = mapperMethod.getAnnotation(Options.class);
            if (methodOptions != null) {
                generated = methodOptions.useGeneratedKeys();
            }

            String tableName = getTableName( modelClass);
            String primaryColumnName = getPrimaryColumnName(modelClass,primaryFieldClass);
            Field[] fields = getModelField(modelClass);

            sql.INSERT_INTO(tableName);

            for (Field field : fields) {
                if (generated && primaryColumnName.equals(field.getName()))
                    continue;
                sql.VALUES(field.getName(), "#{"+field.getName()+"}");
            }
            return sql.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  String updateById(MappedStatement mappedStatement) {
        Class<?> modelClass = getModelClass(mappedStatement);
        Class<?> primaryFieldClass = getPrimaryFieldClass(mappedStatement);
        try {
            SQL sql = new SQL();

            String tableName = getTableName(modelClass);
            String primaryColumnName = getPrimaryColumnName(modelClass,primaryFieldClass);
            Field[] fields = getModelField(modelClass);

            sql.UPDATE(tableName);

            for (Field field : fields) {
                if (primaryColumnName.equals(field.getName()))
                    continue;
                sql.SET(field.getName(), "#{" + field.getName() + "}");
//                ResultMapping resultMapping = getResultMapping(field.getName());
            }
            sql.WHERE(primaryColumnName + "=#{id}");
            return sql.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  String deleteById(MappedStatement mappedStatement) {

        Class<?> modelClass = getModelClass(mappedStatement);
        Class<?> primaryFieldClass = getPrimaryFieldClass(mappedStatement);
        try {
            SQL sql = new SQL();


            String tableName = getTableName(modelClass);
            String primaryColumnName = getPrimaryColumnName(modelClass,primaryFieldClass);
            sql.DELETE_FROM(tableName);
            sql.WHERE(primaryColumnName + "=#{id}");
            return sql.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  String existById(MappedStatement mappedStatement) {

        Class<?> modelClass = getModelClass(mappedStatement);
        Class<?> primaryFieldClass = getPrimaryFieldClass(mappedStatement);
        try {
            SQL sql = new SQL();

            String tableName = getTableName( modelClass);
            String primaryColumnName = getPrimaryColumnName(modelClass,primaryFieldClass);
            sql.SELECT("count(*)");
            sql.FROM(tableName);
            sql.WHERE(primaryColumnName + "=#{id}");
            return sql.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
