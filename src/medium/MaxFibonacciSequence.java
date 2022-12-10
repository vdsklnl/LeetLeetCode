package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vdsklnl
 * @create 2022-11-15 18:26
 * @description 873 dp
 */
public class MaxFibonacciSequence {
    public static void main(String[] args) {

    }

    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }
        /*
            定义二维数组dp表示以数对元素作为最后两个数字的斐波那契子序列的最大长度，初始均为0；
            当i>j时，dp[j][i]表示以arr[j]和arr[i]作为最后两个数字的斐波那契子序列的最大长度
         */
        int res = 0;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            //严格递增序列，2 * arr[j] > arr[i]才可能
            for (int j = i - 1; j >= 0 && 2 * arr[j] > arr[i]; j--) {
                if (map.containsKey(arr[i] - arr[j])) {
                    int index = map.get(arr[i] - arr[j]);
                    //dp[j][i]上一数对为dp[index][j]
                    dp[j][i] = Math.max(dp[index][j] + 1, 3);
                }
                res = Math.max(res, dp[j][i]);
            }
        }
        return res;
    }
}
