package com.louis.coub.escustomer.registry;

import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class GetAnnotationUtil {
    //缓存的entitypaths
    private static Map<Class, List<String>> entityPathsMap = new HashMap<>();

    private Class<? extends Annotation> annotation;

    public GetAnnotationUtil(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public Stream<String> getEntityPackage(AnnotationMetadata annotationMetadata, String attributeName) {
        if(entityPathsMap.containsKey(annotation)){
            return Stream.of(entityPathsMap.get(annotation)).flatMap(list -> list.stream());
        }
        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(annotation.getName());
        AnnotationAttributes attributes = new AnnotationAttributes(annotationAttributes);
        String[] entityPaths = attributes.getStringArray(attributeName);
        //没配注解参数
        if (entityPaths.length == 0) {
            String className = annotationMetadata.getClassName();
            entityPathsMap.put(annotation, Arrays.asList(ClassUtils.getPackageName(className)));
            return  Stream.of(ClassUtils.getPackageName(className));
        }
        entityPathsMap.put(annotation, Arrays.asList(entityPaths));
        return Stream.of(Arrays.asList(entityPaths)).flatMap(list -> list.stream());
    }

}