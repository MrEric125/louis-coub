package com.sorting.heap;


import java.util.Comparator;

/**
 * @author John·Louis
 * @date create in 2019/9/1
 * description:
 * <p>
 * 使用堆可以很容易来实现优先队列，事实上java中的
 * {@link java.util.concurrent.PriorityBlockingQueue}
 * 就是使用的堆这种数据结构
 * 最大堆就是在构建一个完全二叉树
 * 堆这种数据结构更多的是在维护一个动态的数据维护
 * parent(i)=i/2
 * left child (i)=2*i
 * right child (i)=2*i+1
 * 将n个元素逐个插入到空堆中，算法复杂度是O(nlogn)
 *
 *  堆可以很容易来获取前多少名的数据，也可以很容易
 *  来是实现多路归并排序，和d叉堆（d-ary com.sorting.heap）,我们的
 *  案例使用的是二叉堆
 */
public class MaxHeap<Item extends Comparable<Item>> {

    private Item[] items;
    private int count;
    private int capacity;


    @SuppressWarnings("unchecked")
    public MaxHeap(int capacity) {
        this.items = (Item[]) new Comparable[capacity + 1];
        this.count = 0;
        this.capacity = capacity;
    }

    /**
     * heapify的一个过程 算法的复杂度是O(n)
     * @param arr
     * @param n
     */
    @SuppressWarnings("unchecked")
    public MaxHeap(Item[] arr, int n) {
        this.items = (Item[]) new Comparable[count+1];
        capacity = n;
        for (int i = 0; i < n; i++)
            items[i + 1] = arr[i];
        count = n;
        for (int i = count / 2; i >= 1; i--)
            shiftDown(i);

    }

    public int size() {
        return count;
    }
    public boolean isEmply() {
        return count == 0;
    }

    public void insert(Item item) {
        assert count + 1 <= capacity;
        items[count + 1] = item;
        count++;
        shiftUp(count);
    }

    private void swap(int i, int j) {
        Item item = items[i];
        items[i] = items[j];
        items[j] = item;
    }
    public Item extractMax() {
        assert count > 0;
        Item ret = items[1];
        swap(1, count);
        count--;
        shiftDown(1);
        return ret;
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
//            items[k] 和items[j] 交换位置
            int j = 2 * k;
            if (j + 1 <= count && items[j + 1].compareTo(items[j]) > 0) {
                j += 1;
            }
            if (items[k].compareTo(items[j]) >= 0) {
                break;
            }
            swap(k, j);
            k = j;
        }

    }

    /**
     * 将第k个元素shiftup 来保证MaxHeap满足最大堆的定义
     * @param k
     */
    private void shiftUp(int k) {
        while (k > 1 && items[k / 2] .compareTo( items[k])<0) {
            swap(k, k / 2);
            k = k / 2;
        }
    }


    public static void main(String[] args) {

    }
}
