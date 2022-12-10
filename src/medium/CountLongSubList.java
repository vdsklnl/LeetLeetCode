package medium;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-09-19 21:22
 * @description
 */
public class CountLongSubList {
    public static void main(String[] args) {
        int[] nums = {1,3,5,4,7,6,8};
        System.out.println(findNumberOfLIS(nums));
    }

    public static int findNumberOfLIS(int[] nums) {
        if (nums.length <= 1)
            return nums.length;

        int n = nums.length;
        int[] dp = new int[n], count = new int[n];
        //初始化：两者最小值均为一
        //dp[i]:记录以i结尾最长递增子序列长度 || count[i]:记录以i结尾最长递增子序列个数
        Arrays.fill(dp, 1); Arrays.fill(count, 1);

        int maxCount = 0; //记录最长子序列长度
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                //当以nums[i] > [j]时，更新数组
                if (nums[i] > nums[j]) {
                    //当dp[i] < dp[j] + 1时，更新dp[i]，子序列个数与j相同
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[i] == dp[j] + 1) {
                        //更新count数组，加上以j为结尾最长递增子序列个数
                        count[i] += count[j];
                    }
                }
                maxCount = Math.max(maxCount, dp[i]);
            }
        }

        int res = 0;
        //遍历dp数组，找到长度为maxCount的值，并把对应count相加
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxCount)
                res += count[i];
        }
        return res;
    }
}
