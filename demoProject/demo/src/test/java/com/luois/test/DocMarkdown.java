//package com.luois.test;
//
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.sun.javadoc.ClassDoc;
//import com.sun.javadoc.RootDoc;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.ClassUtils;
//import org.springframework.util.StringUtils;
//
//import java.lang.reflect.*;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Slf4j
//public class DocMarkdown {
//
//    public static void main(String[] args) throws Exception {
//        String typeName = JVMTest.class.getTypeName();
//
//        execute(Doclet.class, typeName);
//        buildTable(typeName);
//    }
//
//    public static void buildInterfaceDoc(Class<?> interfaceClass) {
//        Method[] methods =interfaceClass.getMethods();
//
//        int num = 1;
//        for (Method method : methods) {
//            Parameter[] parameters = method.getParameters();
//
//            System.out.println("#### " + num + ". 接口名：" + method.getName());
//
//            List<NameDoc> methodDoc = classDocDetail.get("methodDoc");
//
//            String methodDocumentation=methodDoc.stream().filter(item -> method.getName().equals(item.getName())).map(NameDoc::getDocumentation).findFirst().orElse("");
//            System.out.println("*接口功能：*" + methodDocumentation);
//            for (Parameter parameter : parameters) {
//
//                buildTable(parameter.getParameterizedType().getTypeName());
//
//            }
//            System.out.println("响应：" + buildReturnType(method));
//
//            num++;
//        }
//    }
//
//    public static void buildTable(String typeName) {
//        Class<?> instanceClass = buildHeader(typeName);
//
//        if (instanceClass == null) {
//            return;
//        }
//
//        List<Field> declaredFields = Lists.newArrayList(instanceClass.getDeclaredFields());
//        Class<?> superclass = instanceClass.getSuperclass();
//
//        if (superclass != null) {
//            Field[] superclassFields = superclass.getDeclaredFields();
//            declaredFields.addAll(Lists.newArrayList(superclassFields));
//        }
//        buildTableDetail(declaredFields);
//
//    }
//
//    public static Class<?> buildHeader(String typeName) {
////        String typeName = parameter.getParameterizedType().getTypeName();
//
//        ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
//        Class<?> instanceClass = null;
//
//        try {
//            instanceClass= ClassUtils.forName(typeName, defaultClassLoader);
//        } catch (Exception e) {
//        }
//        System.out.println("参数类型："+typeName);
//        if (instanceClass == null) {
//            return instanceClass;
//        }
//        // 如果参数是java.lang 开头的，就不构建表格
//        if (typeName.startsWith("java.lang")){
//            return null;
//        }
//        System.out.println("|参数|类型|是否必传|备注|");
//        System.out.println("|--|--|--|--|");
//        return instanceClass;
//
//    }
//
//    public static void buildTableDetail(List<Field> declaredFields) {
//        for (Field declaredField : declaredFields) {
//            String param = declaredField.getName();
//            String name = declaredField.getType().getName();
//            String[] split = name.split("\\.");
//            String type = null;
//            if ("serialVersionUID".equals(param)) {
//                continue;
//            }
//            if (split != null && split.length != 0) {
//                type = split[split.length - 1];
//            } else {
//                type = name;
//            }
////            NotNull annotation = declaredField.getAnnotation(NotNull.class);
////            NotEmpty notEmpty = declaredField.getAnnotation(NotEmpty.class);
//            Boolean haveTo = Boolean.FALSE;
////            if (annotation != null || notEmpty != null) {
////                haveTo = Boolean.TRUE;
////            }
//            String y = (haveTo ? "Y" : "");
//
//            List<NameDoc> fieldsDoc = classDocDetail.get("fieldsDoc");
//
//            String doc = fieldsDoc.stream().filter(item -> param.equals(item.getName())).map(item -> item.getDocumentation()).findAny().orElse("");
//
//            doc=doc.replace("\r", "").replace("\n", ",");
//
//            System.out.println("| " + param + "\t|" + type + "\t|" + y+ "\t| "+doc+" |");
//        }
//        System.out.println();
//
//    }
//
//    public static String buildReturnType(Method method) {
//        Type type = method.getGenericReturnType();// 获取返回值类型
//        String returnType = "";
//        if (type instanceof ParameterizedType) { // 判断获取的类型是否是参数类型
//            Type[] typesto = ((ParameterizedType) type).getActualTypeArguments();// 强制转型为带参数的泛型类型，
//            // getActualTypeArguments()方法获取类型中的实际类型，如map<String,Integer>中的
//            // String，integer因为可能是多个，所以使用数组
//            if (typesto != null) {
//                returnType = typesto[0].getTypeName();
//            }
//        }
//        return returnType;
//    }
//
//    private static RootDoc rootDoc;
//
//    public static class Doclet{
//        public Doclet(){}
//
//        public static boolean start(RootDoc rootDoc) {
//            DocMarkdown.rootDoc = rootDoc;
//            return true;
//        }
//    }
//
//    public static RootDoc getRootDoc() {
//        return rootDoc;
//    }
//
//    /**
//     * https://cloud.tencent.com/developer/article/1011706
//     */
//    public static Map<String,List<NameDoc>> buildDoc(){
//        ClassDoc[] classes = rootDoc.classes();
//        Map<String, List<NameDoc>> docMap = Maps.newConcurrentMap();
//        for (int i = 0; i < classes.length; ++i) {
//
//            List<NameDoc> methodsDocs= Arrays.stream(classes[i].methods()).map(item -> new NameDoc(item.name(), item.commentText())).collect(Collectors.toList());
//            docMap.put("methodDoc", methodsDocs);
//
//            List<NameDoc> fieldsDoc=Arrays.stream(classes[i].fields(false)).map(item -> new NameDoc(item.name(), item.commentText())).collect(Collectors.toList());
//            docMap.put("fieldsDoc", fieldsDoc);
//        }
//        return docMap;
//    }
//
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Setter
//    @Getter
//    public static class NameDoc {
//        private String name;
//        private String documentation;
//    }
//
//    private static final String projectPath = "D:\\ideaworkspace\\local-project-path\\";
//
//    private static final String classPathPrefix = projectPath + "target\\classes";
//
//    private static final String sourcePathPrefix = projectPath + "src\\main\\java\\";
//
//    private static Map<String, List<NameDoc>> classDocDetail = Maps.newConcurrentMap();
//
//    public static void execute(Class<?> docClass, String fullClassName) throws Exception {
//
//        if (docClass == null) {
//            throw new IllegalAccessException("docClass can not be null");
//        }
//        if (StringUtils.isEmpty(fullClassName)) {
//            throw new IllegalAccessException("docClass can not be null");
//        }
//        String replace = fullClassName.replace(".", "\\");
//        String sourcePath = sourcePathPrefix + replace+".java";
//
//        com.sun.tools.javadoc.Main.execute(new String[]{
//                "-doclet",
//                Doclet.class.getName(),
//                "-docletpath",
//                Doclet.class.getResource("/").getPath(),
//                "-encoding",
//                "utf-8",
//                "-classpath",
//                classPathPrefix,
//                sourcePath
//        });
//        classDocDetail = buildDoc();
//    }
//}
