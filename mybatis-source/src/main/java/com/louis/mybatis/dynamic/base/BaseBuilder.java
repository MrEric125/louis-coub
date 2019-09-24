package com.louis.mybatis.dynamic.base;

import lombok.NonNull;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
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
public class BaseBuilder {


    /**
     * 存放mapper缓存的class
     */
    private Map<String,Class> mapperClassMap = new ConcurrentHashMap<>();

    /**
     * 存放实体类缓存class
     */
    private Map<String,Class> entityClassMap = new ConcurrentHashMap<>();

    private XMLLanguageDriver languageDriver = new XMLLanguageDriver();

    public String  build(Configuration configuration) {
        for (MappedStatement mappedStatement : configuration.getMappedStatements()) {
            if (mappedStatement.getSqlSource() instanceof ProviderSqlSource) {
                Class<?> providerClass = getProviderClass(mappedStatement);
                if (providerClass != this.getClass())
                    continue;


                String sqlScript = getSqlScript(mappedStatement);
                SqlSource sqlSource = createSqlSource(mappedStatement, sqlScript);
                setSqlSource(mappedStatement, sqlSource);
            }
        }
        return "sql";
    }

    public SqlSource createSqlSource(MappedStatement mappedStatement, String script) {
        return languageDriver.createSqlSource(mappedStatement.getConfiguration(), "<script>" + script + "</script>", null);
    }

    public  void setSqlSource(MappedStatement mappedStatement, SqlSource sqlSource) {
        MetaObject metaObject = SystemMetaObject.forObject(mappedStatement);
        metaObject.setValue("sqlSource", sqlSource);
    }

    public Class<?> getProviderClass(MappedStatement mappedStatement) {
        try {
            Field providerTypeField = ProviderSqlSource.class.getDeclaredField("providerType");
            providerTypeField.setAccessible(true);
            return (Class<?>) providerTypeField.get(mappedStatement.getSqlSource());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Class<?> getMapperClass(MappedStatement mappedStatement) {

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

    public Method getMapperMethod(MappedStatement mappedStatement, Class<?>... parameterTypes) {
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

    public String getSqlScript(MappedStatement mappedStatement) {
        try {
            Method builderMethod = getBuilderMethod(mappedStatement, this, MappedStatement.class);
            return builderMethod.invoke(this, mappedStatement).toString();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public Method getBuilderMethod(MappedStatement mappedStatement,Object target, Class<?>... parameterTypes) {
        try {
            String mappedStatementId = mappedStatement.getId();
            String methodName = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
            return target.getClass().getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

//    /**
//     * // TODO: 2019/9/23  这里的获取ResultMap 还有一定的问题，经常性的获取不到ResultMap，
//     * @param mappedStatement
//     * @param modelClass
//     * @return
//     */
//    private  ResultMap getResultMap(MappedStatement mappedStatement, Class<?> modelClass) {
//        Configuration configuration = mappedStatement.getConfiguration();
//        for (ResultMap resultMap : configuration.getResultMaps())
//            if (modelClass == resultMap.getType() && !resultMap.getId().contains("-"))
//                return resultMap;
////            if (modelClass == resultMap.getType() )
////                return resultMap;
//
//        return null;
//    }

    public String getTableName(Class<?> modelClass) {
//        if (resultMap != null)
//            return resultMap.getId().substring(mapperClass.getName().length() + 1);
        return toUnderline(modelClass.getSimpleName());
    }

    /**
     * 获取实体类
     * @param mappedStatement
     * @return
     */
    public Class<?> getModelClass(MappedStatement mappedStatement) {
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
    public String getPrimaryColumnName(@NonNull Class<?> moduleClass, @NonNull Class<?> primaryFieldClass) {
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

    public Class<?> getPrimaryFieldClass(MappedStatement mappedStatement) {
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

    public Class<?>[] getMapperGenerics(MappedStatement mappedStatement) {
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

    public Field[] getModelField(Class<?> modelClass) {
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
