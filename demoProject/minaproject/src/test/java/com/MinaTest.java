package com;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

/**
 * @author jun.liu
 * @date created on 2020/11/2
 * description:
 */
@Setter
@Getter
public class MinaTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {

            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,400,400);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        launch(args);
        String a = "aaabbcccabbbb";
        System.out.println(char_count(a));

    }

    static String char_count(String str) {
        if (str==null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char c = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            char s = str.charAt(i);
            if (s == c) {
                count++;
            } else {
                if (count > 1) {
                    sb.append(count);
                    sb.append(c);
                    count = 1;
                } else {
                    sb.append(count);
                    sb.append(c);
                }
            }
            c = s;
        }
        sb.append(count);
        sb.append(c);

        return sb.toString();

    }



//    static String quick_sort(String str) {
//        if (str == null) {
//            return null;
//        }
//        String[] split = str.split(",");
//        Integer[] sortArr = new Integer[split.length];
//        for (int i = 0; i < split.length; i++) {
//            sortArr[i] = Integer.valueOf(split[i]);
//        }
//        quick_sort(sortArr,0,sortArr.length-1);
//        return Arrays.stream(sortArr).map(Object::toString).collect(Collectors.joining(","));
//
//
//    }
//    static void quick_sort(Integer arr[],int lo,int hi){
//        if (lo >= hi)
//            return;
//        int p = partition(arr, lo, hi);
//        quick_sort(arr, lo,p-1);
//        quick_sort(arr, p + 1, hi);
//    }
//    static int partition(Integer[] arr, int lo, int hi) {
//        Integer v = arr[lo];
////        arr[lo+1..j]<v;  arr[j+1...i]>v
//        int j = lo;
//        for (int i = lo+1; i <=hi ; i++) {
//            if (less(arr[i],v)) {
//                exch(arr, j+1, i);
//                j++;
//            }
//        }
//        exch(arr, lo, j);
//        return j;
//    }
//    public static  boolean less(Integer v,Integer w) {
//        return v.compareTo(w) < 0;
//    }
//    public static  void exch(Integer[] objects, int i, int j) {
//        Integer swap = objects[i];
//        objects[i] = objects[j];
//        objects[j] = swap;
//    }
}
