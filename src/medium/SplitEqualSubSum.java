package medium;

/**
 * @author vdsklnl
 * @create 2022-09-07 21:18
 * @description 同1049：石头重量II，返回方式不同
 */
public class SplitEqualSubSum {
    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        System.out.println(canPartitionSimplify(nums));
    }

    public static boolean canPartition(int[] nums) {
        //0-1背包问题
        int sum = 0;
        for (int num:nums) {
            sum += num;
        }

        if (sum % 2 == 1)
            return false;
        int target = sum / 2;

        //dp[i][j]代表可装物品为0-i，背包容量为j的情况下，背包内容量的最大价值
        int[][] dp = new int[nums.length][target + 1];

        //初始化,dp[0][j]的最大价值nums[0](比其小的均为0)
        //dp[i][0]均为0，不用初始化
        for (int j = nums[0]; j <= target; j++) {
            dp[0][j] = nums[0];
        }

        //遍历物品，遍历背包
        //递推公式:
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                //背包容量可以容纳nums[i]
                if (j >= nums[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[nums.length - 1][target] == target;
    }

    public static boolean canPartitionSimplify(int[] nums) {
        //0-1背包问题
        int sum = 0;
        for (int num:nums) {
            sum += num;
        }

        if (sum % 2 == 1)
            return false;
        int target = sum / 2;

        //dp[i]代表背包容量为i的情况下，背包内容量的最大价值
        int[] dp = new int[target + 1];

        //遍历物品，遍历背包，将二维数组更新到一维数组中，物品重量 = 价值
        //递推公式:
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        return dp[target] == target;
    }
}
