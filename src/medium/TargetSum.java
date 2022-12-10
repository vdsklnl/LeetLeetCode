package medium;

/**
 * @author vdsklnl
 * @create 2022-09-08 15:50
 * @description
 */
public class TargetSum {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        System.out.println(findTargetSumWays(nums, 3));
    }

    public static int findTargetSumWays(int[] nums, int target) {
        /**相加为x，相减绝对值为sum - x，x - sum + x = target
         * x = (sum + target) / 2 => 0-1背包问题
         * sum + target为奇数无解，|target| > sum 也无解(nums[i] >= 0)
         * 构建dp[]数组，dp[j]表示重量为j时，存放方法
         * 当存在nums[i]时，dp[j] = dp[j - nums[i]]种方法，将每次遍历累加则得出结果
         * dp[0] = 1，可以理解为背包重量为0时，不放任何物品是一种方法
         * 求装满背包有几种方法的情况下，递推公式一般为：dp[j] += dp[j - nums[i]];
         */
        int sum = 0;
        for (int num:nums) {
            sum += num;
        }
        if ((sum + target) % 2 == 1 || Math.abs(target) > sum)
            return 0;

        int add = (sum + target) / 2;
        int[] dp = new int[add + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = add; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[add];
    }
}
