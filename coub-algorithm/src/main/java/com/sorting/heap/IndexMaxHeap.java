package com.sorting.heap;


/**
 * @author John·Louis
 * @date create in 2019/9/9
 * description:
 * 将数据和索引分别存储,图论的时候会经常用到索引堆
 */
public class IndexMaxHeap<Item extends Comparable<Item>> {

    private Item[] data;
    private int count;
    private int capacity;
    private int[] indexes;
    private int[] reverse;


    /**
     * Constructs an IndexMaxHeap with a specified capacity.
     * Initializes the data array, indexes, and reverse arrays.
     *
     * @param capacity the maximum number of elements the heap can hold
     */
    public IndexMaxHeap(int capacity) {
        this.data = (Item[]) new Comparable[capacity + 1];
        this.count = 0;
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        for (int i = 0; i < capacity; i++) {
            reverse[i] = 0;
        }

        this.capacity = capacity;
    }

    /**
     * heapify的一个过程 算法的复杂度是O(n)
     * @param
     * @param
     */
//    @SuppressWarnings("unchecked")
//    public IndexMaxHeap(Item[] arr, int n) {
//        this.data = (Item[]) new Comparator[count+1];
//        capacity = n;
//        for (int i = 0; i < n; i++)
//            data[i + 1] = arr[i];
//        count = n;
//        for (int i = count / 2; i >= 1; i--)
//            shiftDown(i);
//
//    }

    public int size() {
        return count;
    }
    public boolean isEmply() {
        return count == 0;
    }

    public void insert(int i,Item item) {
        assert count + 1 <= capacity;
        assert  i + 1 >= 1 && i + 1 <= capacity;
        i += 1;
        data[i] = item;
        indexes[count + 1] = 1;
        reverse[i] = count + 1;
        count++;
        shiftUp(count);
    }

    /**
     * 交换的数据是索引的位置
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
//        Item item = data[i];
        int index = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = index;
    }
    public Item extractMax() {
        assert count > 0;
        Item ret = data[indexes[1]];
        swap(1, count);
        reverse[indexes[1]] = 1;
//        删除了元素
        reverse[indexes[count]] = 0;
        count--;
        shiftDown(1);
        return ret;
    }

    /**
     * 从1开始的索引转成从0开始的索引
     * @return
     */
    public int extractMaxIndex() {
        assert count > 0;
        int ret = indexes[1]-1;
        swap(1, count);
        count--;
        shiftDown(1);
        return ret;
    }

    Item getItem(int i) {
        return data[i + 1];
    }

    /**
     * 优化前的change,
     * @param i
     * @param newItem
     */
    public void change(int i, Item newItem) {
        i += 1;
        data[i] = newItem;

//      找到indexs[j]=1 ,j表示data[i]在堆中的位置
//        之后再shiftUp【j】再shiftdown[j]
        for (int j = 1; j <= count; j++) {
            if (indexes[j] == i) {
                shiftUp(j);
                shiftDown(j);
                return;
            }
        }
    }

    /**
     * 优化完之后的change
     * @param i
     * @param newItem
     * 维护一个reverse,来直接获取数据
     * 满足以下条件
     * indexes[i]=j
     * reverse[j]=1
     * indexes[reverse[i]]=i
     * reverse[indexes[i]]=i
     *
     */
    public void change2(int i, Item newItem) {
        assert contain(i);
        i += 1;
        data[i] = newItem;
        int j = reverse[i];
        shiftUp(j);
        shiftDown(j);

    }

    private boolean contain(int i) {
        assert i + 1 >= 1 && i + 1 <= capacity;
        return reverse[i + 1] != 0;
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
//            items[k] 和items[j] 交换位置
            int j = 2 * k;
            if (j + 1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) > 0) {
                j += 1;
            }
            if (data[indexes[k]].compareTo(data[indexes[j]]) >= 0) {
                break;
            }
            swap(k, j);
            reverse[indexes[k]] = k;
            reverse[indexes[j]] = j;
            k = j;
        }

    }

    /**
     * 将第k个元素shiftup 来保证MaxHeap满足最大堆的定义
     * @param k 索引中的位置
     */
    private void shiftUp(int k) {
        while (k > 1 && data[indexes[k / 2]] .compareTo( data[indexes[k]])<0) {
            swap(k, k / 2);
            reverse[indexes[k / 2]] = k / 2;
            reverse[indexes[k]] = k;
            k = k / 2;
        }
    }


    public static void main(String[] args) {

    }
}
