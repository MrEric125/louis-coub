// package com.luois.test;

// import com.sun.javadoc.ClassDoc;
// import com.sun.javadoc.FieldDoc;
// import com.sun.javadoc.MethodDoc;
// import com.sun.javadoc.RootDoc;
// import org.springframework.util.ReflectionUtils;

// import java.lang.reflect.Method;

// /**
//  * @author John·Louis
//  * @date created on 2020/2/11
//  * description:
//  */
// public class Main {

//     public static void main(String[] args){


//         com.sun.tools.javadoc.Main.execute(new String[]{
//                 "-doclet",
//                 Doclet.class.getName(),
//                 "-docletpath",
//                 Doclet.class.getResource("/").getPath(),
//                 "-encoding",
//                 "utf-8",
//                 "-classpath",
//                 "D:\\ideaworkspace\\louis\\louis-coub\\demoProject\\demo\\target\\classes",
//                 "D:\\ideaworkspace\\louis\\louis-coub\\demoProject\\demo\\src\\test\\java\\com\\MssUtil.java"
//         });
//         show();


//     }



//     private static RootDoc rootDoc;

//     public static class Doclet{
//         public Doclet(){}

//         public static boolean start(RootDoc rootDoc) {
//             Main.rootDoc = rootDoc;
//             return true;
//         }
//     }

//     public static RootDoc getRootDoc() {
//         return rootDoc;
//     }

//     /**
//      * https://cloud.tencent.com/developer/article/1011706
//      */
//     public static void show(){
//         ClassDoc[] classes = rootDoc.classes();
//         for (int i = 0; i < classes.length; ++i) {
//             System.out.println(classes[i]);
//             System.out.println(classes[i].commentText());
//             for(MethodDoc method:classes[i].methods()){
//                 System.out.printf("\t%s\n", method.commentText());
//             }
//             for (FieldDoc field : classes[i].fields()) {

//             }
//         }
//     }



// }
