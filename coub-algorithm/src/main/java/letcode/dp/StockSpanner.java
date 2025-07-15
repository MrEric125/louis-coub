package letcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class StockSpanner {
    private List<Integer> stock;
    //     表示第I 个位置上的span
    private Map<Integer, Integer> spanMap = new HashMap<>();

    public StockSpanner() {
        stock = new ArrayList<>();
    }

    public int next(int price) {
        if (stock.isEmpty()) {
            stock.add(price);
            spanMap.put(0, 1);
            return 1;
        }
        stock.add(price);
        int size = stock.size();
        int span = 1;
        int compareIndex = size - 1, preIndex = size - 2;
        // 比较当前位置的数据与上一次位置数据 其实是一个动态规划的思路
        while (preIndex >= 0 && price >= stock.get(preIndex) && price >= stock.get(compareIndex)) {
            Integer preSpan = spanMap.get(preIndex);
            span = span + preSpan;
            compareIndex = preIndex;
            preIndex = preIndex - preSpan;
        }
        spanMap.put(stock.size() - 1, span);
        return span;
    }

    public static void main(String[] args) {

        String[] param = new String[]{"StockSpanner", "next", "next", "next", "next", "next", "next", "next"};

        int[][] prices = new int[][]{{}, {28}, {14}, {28}, {35}, {46}, {53}, {66}, {80}, {87}, {88}};


        StockSpanner stockSpanner = new StockSpanner();

        Integer[] span = new Integer[prices.length];
        span[0] = null;
        for (int i = 1; i < prices.length; i++) {
            int next = stockSpanner.next(prices[i][0]);

            span[i] = next;
        }

        System.out.println(span);

    }
}