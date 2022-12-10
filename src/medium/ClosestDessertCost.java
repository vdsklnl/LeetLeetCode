package medium;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-12-04 20:46
 * @description 1774 DFS
 */
public class ClosestDessertCost {
    public static void main(String[] args) {
        int[] baseCosts = {2,3}, toppingCosts = {4,5,100};
        closestCost(baseCosts, toppingCosts, 18);
        System.out.println(sum);
    }

    static int[] top;
    static int sum;

    public static int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        Arrays.sort(baseCosts);
        Arrays.sort(toppingCosts);
        top = toppingCosts;
        sum = 0;
        for (int i = 0; i < baseCosts.length; i++) {
            backtracking(baseCosts[i], target, 0);
            if (sum == target)
                return target;
        }
        return sum;
    }

    public static void backtracking(int cur, int target, int index) {
        if (Math.abs(sum - target) > Math.abs(cur - target))
            sum = cur;
        else if (Math.abs(sum - target) == Math.abs(cur - target))
            sum = Math.min(sum, cur);

        if (cur >= target)
            return;

        for (int i = index; i < top.length; i++) {
            for (int j = 1; j <= 2; j++) {
                backtracking(cur + j * top[i], target, i + 1);
            }
        }
    }
}
