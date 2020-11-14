package com.louis.mybatis.dynamic.base;

import lombok.NonNull;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author louis
 * <p>
 * Date: 2019/9/23
 * Description:
 */
public class SqlSourceBuilder {


    /**
     * 存放mapper缓存的class
     */
    private  Map<String,Class> mapperClassMap = new ConcurrentHashMap<>();

    /**
     * 存放实体类缓存class
     */
    private  Map<String,Class> entityClassMap = new ConcurrentHashMap<>();

    private  XMLLanguageDriver languageDriver = new XMLLanguageDriver();

    public String  build(Configuration configuration) {
        for (MappedStatement mappedStatement : configuration.getMappedStatements()) {
            if (mappedStatement.getSqlSource() instanceof ProviderSqlSource) {
                Class<?> providerClass = getProviderClass(mappedStatement);
                if (providerClass != SqlSourceBuilder.class)
                    continue;


                String sqlScript = getSqlScript(mappedStatement);
                SqlSource sqlSource = createSqlSource(mappedStatement, sqlScript);
                setSqlSource(mappedStatement, sqlSource);
            }
        }
        return "sql";
    }

    private  SqlSource createSqlSource(MappedStatement mappedStatement, String script) {
        return languageDriver.createSqlSource(mappedStatement.getConfiguration(), "<script>" + script + "</script>", null);
    }

    private  void setSqlSource(MappedStatement mappedStatement, SqlSource sqlSource) {
        MetaObject metaObject = SystemMetaObject.forObject(mappedStatement);
        metaObject.setValue("sqlSource", sqlSource);
    }

    private  String getById(MappedStatement mappedStatement) {
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

    private  String insert(MappedStatement mappedStatement) {

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

    private  String updateById(MappedStatement mappedStatement) {
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

    private  String deleteById(MappedStatement mappedStatement) {

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

    private  String existById(MappedStatement mappedStatement) {

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

    private  Class<?> getProviderClass(MappedStatement mappedStatement) {
        try {
            Field providerTypeField = ProviderSqlSource.class.getDeclaredField("providerType");
            providerTypeField.setAccessible(true);
            return (Class<?>) providerTypeField.get(mappedStatement.getSqlSource());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private  Class<?> getMapperClass(MappedStatement mappedStatement) {

        try {
            String mappedStatementId = mappedStatement.getId();
            Class mapperClass = mapperClassMap.get(mappedStatementId);
            if (mapperClass == null) {
                String className = mappedStatementId.substring(0, mappedStatementId.lastIndexOf("."));
                mapperClass=Class.forName(className);
                mapperClassMap.put(mappedStatementId, mapperClass);
                return mapperClass;

            }
            return mapperClass;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private  Method getMapperMethod(MappedStatement mappedStatement, Class<?>... parameterTypes) {
        String mappedStatementId = mappedStatement.getId();
        String methodName = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
        Method[] methods = getMapperClass(mappedStatement).getMethods();
        for (Method method : methods) {
            if (!method.getName().equals(methodName))
                continue;
            Class<?>[] types = method.getParameterTypes();
            if (types.length == parameterTypes.length) {
                boolean isEqual = true;
                for (int i = 0; i < types.length; i++) {
                    if (types[i] == Object.class)
                        continue;
                    if (types[i] != parameterTypes[i])
                        isEqual = false;
                }
                if (isEqual)
                    return method;
            }
        }
        return null;
    }

    private  String getSqlScript(MappedStatement mappedStatement) {
        try {
            Method builderMethod = getBuilderMethod(mappedStatement, this, MappedStatement.class);
            return builderMethod.invoke(this, mappedStatement).toString();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private   Method getBuilderMethod(MappedStatement mappedStatement,Object target, Class<?>... parameterTypes) {
        try {
            String mappedStatementId = mappedStatement.getId();
            String methodName = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
            return target.getClass().getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * // TODO: 2019/9/23  这里的获取ResultMap 还有一定的问题，经常性的获取不到ResultMap，
     * @param mappedStatement
     * @param modelClass
     * @return
     */
    private  ResultMap getResultMap(MappedStatement mappedStatement, Class<?> modelClass) {
        Configuration configuration = mappedStatement.getConfiguration();
        for (ResultMap resultMap : configuration.getResultMaps())
            if (modelClass == resultMap.getType() && !resultMap.getId().contains("-"))
                return resultMap;
//            if (modelClass == resultMap.getType() )
//                return resultMap;

        return null;
    }

    private  String getTableName( Class<?> modelClass) {
//        if (resultMap != null)
//            return resultMap.getId().substring(mapperClass.getName().length() + 1);
        return toUnderline(modelClass.getSimpleName());
    }

    /**
     * 获取实体类
     * @param mappedStatement
     * @return
     */
    private  Class<?> getModelClass(MappedStatement mappedStatement) {
        String id = mappedStatement.getId();
        Class entityClass = entityClassMap.get(id);
        if (entityClass == null) {
            Class<?>[] mapperGenerics = getMapperGenerics(mappedStatement);
            if (mapperGenerics != null) {
                entityClass = mapperGenerics[0];
                entityClassMap.put(id, entityClass);
                return entityClass;
            }
            throw new IllegalArgumentException("没有找到对应的实体类");
        }
        return entityClass;


    }

    /**
     * 获取主键
     * @param moduleClass
     * @param primaryFieldClass
     * @return
     */
    private  String getPrimaryColumnName(@NonNull Class<?> moduleClass,@NonNull Class<?> primaryFieldClass) {
        Field[] modelField = getModelField(moduleClass);
        for (Field field : modelField) {
            Id annotation = field.getAnnotation(Id.class);
            if (annotation != null) {
                if (field.getType().equals(primaryFieldClass)) {
                    Column column = field.getAnnotation(Column.class);
                    if (column != null) {
                        return column.name();
                    } else {
                        return field.getName();
                    }
                }
            }

        }
        throw new IllegalArgumentException("没有设置主键");
    }

    private  Class<?> getPrimaryFieldClass(MappedStatement mappedStatement) {
        Class<?>[] mapperGenerics = getMapperGenerics(mappedStatement);
        if (mapperGenerics != null && mapperGenerics.length == 2) {
            return mapperGenerics[1];
        }
        return null;
    }

//    private  ResultMapping getResultMapping(String fieldName) {
//        if (resultMap != null) {
//            for (ResultMapping resultMapping : resultMap.getResultMappings()) {
//                if (resultMapping.getProperty().equals(fieldName))
//                    return resultMapping;
//            }
//        }
//        return null;
//    }

    private  Class<?>[] getMapperGenerics(MappedStatement mappedStatement) {
        Class<?> mapperClass = getMapperClass(mappedStatement);
        Type[] types = mapperClass.getGenericInterfaces();
        for (Type type : types) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            if (BaseMapper.class != (Class<?>) parameterizedType.getRawType())
                continue;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            Class<?>[] generics = new Class[typeArguments.length];
            for (int i = 0; i < typeArguments.length; i++)
                generics[i] = (Class<?>) typeArguments[i];
            return generics;
        }
        return null;
    }

    private  Field[] getModelField(Class<?> modelClass) {
        List<Field> fields = new ArrayList<>();
        Field[] declaredFields = modelClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if ("serialVersionUID".equals(field.getName()))
                continue;
            fields.add(field);
        }
        return fields.toArray(new Field[0]);
    }

    private  String toUnderline(String str) {
        StringBuilder buf = new StringBuilder();
        buf.append(Character.toLowerCase(str.charAt(0)));
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                buf.append("_").append(Character.toLowerCase(c));
            } else {
                buf.append(c);
            }
        }
        return buf.toString();
    }


}
