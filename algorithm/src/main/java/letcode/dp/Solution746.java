package letcode.dp;

import java.util.Map;

/**
 * https://leetcode.cn/problems/min-cost-climbing-stairs/description/?envType=study-plan-v2&envId=dynamic-programming
 *
 *1. 要到达的目的是cost.length+1
 *2. 关键数据：每次可以上升1，或者2位
 *3. 到达第0，或者第1位无需支付费用，再上升则需要支付 当前位的费用，比如从第一位往上走一位，则需要花费的是cost[1]
 *4. 所以到达n+1的花费=Min(到达n的开销+第n位的开销,到达第n-1位的开销+第n位的开销)
 *
 *
 */
public class Solution746 {


    /**
     * 用递归的方式来理解
     * @param cost
     * @return
     */
    public int minCostClimbingStairs2(int[] cost){
        int cu = 0,pre=0,next=0, len = cost.length;

        // 这里用的是len+1 表示一直要上到的台阶是n+1位
        for (int i = 2; i < len + 1; i++) {

            next = Math.min(pre + cost[i - 2], cu + cost[i - 1]);

            pre = cu;

            cu = next;

        }
        return next;

    }

    /**
     *设 目的地为i
     * 到达目的地i的花费：   i=0,i=1  cost=0
     * 到达i的花费
     *
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length;
        if (length<2) {
            return cost[0];
        }

        int totalCost=0;
        int i = 0;

        while (i < length) {
            if (i + 2 >= length) {
                return totalCost;
            }
            if ( cost[i] + cost[i + 2] <= cost[i + 1]) {
                totalCost = totalCost + cost[i];
                i = i + 2;
            } else if (i + 1 < length) {
                totalCost = totalCost + cost[i + 1];
                i = i + 1;
            }else {
                totalCost = totalCost + cost[i];
                i = i + 1;
            }

        }
        return totalCost;
    }

    public static void main(String[] args) {
        Solution746 solution746 = new Solution746();
        System.out.println(solution746.minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}));
        System.out.println(solution746.minCostClimbingStairs2(new int[]{1,100,1,1,1,100,1,1,100,1}));
    }


}
