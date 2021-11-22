package com.sorting.common;

import java.util.Arrays;

/**
 * @author John·Louis
 * @date create in 2019/9/11
 * description:
 */
public class BaseMergeSort<T extends Comparable<T>> {


    /**
     * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
     * @param arr 需要排序的数组
     * @param lo  排序的最低位
     * @param mid 数组的中位
     * @param hi 数组的高位
     */
    public void baseMerge(T[] arr ,int lo, int mid, int hi) {
//
        T[] aux = Arrays.copyOfRange(arr, lo, hi + 1);
        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = lo, j = mid + 1;
        for (int k = lo ; k <=hi ; k++) {
//            前面两个if 相当于判断索引是否合法
            // 如果[i,mid]的元素已经全部处理完毕，那么就将[mid+1,h]的元素防止到arr后面的位置
            if (i>mid) {
//                这个地方的aux[j-lo] 是因为aux 每次都是从索引0开始的，如果aux是传入进来的话，
//                那么这个地方就不用aux[j-lo]
                arr[k] = aux[j - lo];
                j++;
                // 如果[mid+1,h]的元素都处理完了，那么我们就将i相应的元素放置到arr后面
            } else if (j > hi) {
                arr[k] = aux[i - lo];
                i++;
                // 左半部分所指元素 < 右半部分所指元素
            } else if (SortUtils.less(aux[i - lo], aux[j - lo])) {
                arr[k] = aux[i - lo];
                i++;
                // 左半部分所指元素 >= 右半部分所指元素
            } else {
                arr[k] = aux[j - lo];
                j++;
            }
        }
    }

    /**
     * 归并排序用接口将a[lo....]和a[mid+1...hi]两部分数据进行归并
     * 详细看图片
     * <a src="/algorithm/etc/merge.png">图片</a>
     * @param arr 需要排序的数组
     * @param aux 复制的aux
     * @param lo  排序的最低位
     * @param mid 数组的中位
     * @param hi 数组的高位
     */
    public void baseMerge(T[] arr, T[] aux, int lo, int mid, int hi) {
        //复制数组a
//        for (int i = lo; i <=hi ; i++) {
//            aux[i] = arr[i];
//        }
//        aux = Arrays.copyOfRange(a, lo, hi);
//        指向两个要合并数组开头的位置
        int i = lo, j = mid + 1;

        for (int k = lo; k <=hi; k++) {
            // 如果[i,mid]的元素已经全部处理完毕，那么就将[mid+1,h]的元素防止到arr后面的位置
            if (i>mid)                      arr[k] = aux[j++];
                // 如果右半部分元素已经全部处理完毕
            else if(j>hi)                   arr[k] = aux[i++];
                // 左半部分所指元素 < 右半部分所指元素
            else if (SortUtils.less(aux[i],aux[j]))   arr[k] = aux[i++];
                // 左半部分所指元素 >= 右半部分所指元素
            else                            arr[k] = aux[j++];
        }
//
    }

    /**
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param temp
     */
    private  void baseMerge2(int[] arr,int[] temp,int left,int mid,int right){
        int i = left;//左序列指针
        int j = mid+1;//右序列指针
        int t = 0;//临时数组指针
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){//将左边剩余元素填充进temp中
            temp[t++] = arr[i++];
        }
        while(j<=right){//将右序列剩余元素填充进temp中
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }
}
