package com.louis.workfow;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author jun.liu
 * @date created on 2020/11/8
 * description:
 */
public class LouisAutoConfigurationImportSelectors implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        System.out.println(annotationMetadata.getAnnotationTypes());
        return null;


    }
}
