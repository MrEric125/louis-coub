package com.louis.mybatis.dynamic.provider;

import com.louis.mybatis.dynamic.Table;
import com.louis.mybatis.dynamic.TableNoDefineException;
import com.louis.mybatis.dynamic.entity.LocalUser;
import com.louis.mybatis.dynamic.helper.FieldUtils;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/9/19
 * Description:
 *
 * 如果在执行的时候动态的获取表名，字段名，可能会再执行的时候有一定的性能问题，
 * 那么其实我们可以将之在初始化的时候存储起来，后期使用直接获取
 *
 *
 */

public class LocalUserProvider<T> {


    /**
     * todo 通过反射的方式还需要找到执行泛型类型方式还需要优化
     * @return
     */
    public String getTableName() {
        Class<T> tableClass =getCurrentClassGeneric();
        Table annotation = tableClass.getAnnotation(Table.class);
        String tableName;
        if (annotation != null) {
            tableName = annotation.name();
        } else {
            throw new TableNoDefineException("实体类上面没有 @Table 注解");
        }
        return tableName;
    }


    /**
     * // TODO: 2019/9/19
     * @return
     */
    public String getPrimaryKey(){
        return null;
    }

    /**
     * // TODO: 2019/9/19 不仅仅要找到这个field 我们还要通过Exclude排除某个属性，
     * 还要通过@Column找到某个字段对应的属性，如果没有@Colume那么就按照默认的规则来办事
     * @return
     */
    public List<String > getField() {
        Class<T> currentClass = getCurrentClassGeneric();
        Field[] fields = currentClass.getDeclaredFields();
        return Arrays.stream(fields).map(field -> {
            String original = field.getName();
            return FieldUtils.humpToLine(original);
        }).collect(Collectors.toList());
    }

    public Class<T> getCurrentClassGeneric() {
        Class<T> returnType = null;
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        System.out.println("generic superclass"+genericSuperclass);
        Type[] genericInterfaces = this.getClass().getGenericInterfaces();

        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) genericSuperclass;
            returnType = (Class<T>) type.getActualTypeArguments()[0];
        }
        return returnType;
    }

    public String selectById(final Long id) {
        SQL sql = new SQL();
        sql.SELECT("id,age,username,fivarite");
        sql.FROM("local_user");
        sql.WHERE("id=#{id}");
        return sql.toString();
    }

    public String deleteById(final long id) {

        SQL sql = new SQL();
        sql.DELETE_FROM("local_user");
        sql.WHERE("id=#{id}");
        return sql.toString();
    }

    public String updateById(LocalUser localUser) {

        SQL sql = new SQL();
        sql.UPDATE("local_user");
        sql.SET();
        sql.WHERE("id=#{id}");
        return sql.toString();
    }

    public String insert(LocalUser localUser) {
        SQL sql = new SQL();
        sql.INSERT_INTO("local_user");

        sql.VALUES("id", "#{id}");
        sql.VALUES("username", "#{username}");
        sql.VALUES("age", "#{age}");
        sql.VALUES("fivarite", "#{fivarite}");
        return sql.toString();
    }

}
