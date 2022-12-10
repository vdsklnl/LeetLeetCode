package medium;

/**
 * @author vdsklnl
 * @create 2022-09-14 16:48
 * @description
 */
public class LongRepeatSubArray {
    public static void main(String[] args) {

    }

    public int findLength(int[] nums1, int[] nums2) {
        //dp[i][j] ：以下标i - 1为结尾的A，和以下标j - 1为结尾的B，最长重复子数组长度为dp[i][j]
        //初始化均为0，遍历顺序不影响结果
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int res = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    public int findLengthII(int[] nums1, int[] nums2) {
        //dp[i]:滚动数组
        //初始化均为0，内层数组需要后序遍历以免重复刷新(序列长度为前一加1)
        int res = 0;
        int[] dp = new int[nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = nums2.length; j > 0; j--) {
                if (nums1[i - 1] == nums2[j - 1])
                    dp[j] = dp[j - 1] + 1;
                else {
                    dp[j] = 0;
                }
                if (dp[j] > res)
                    res = dp[j];
            }
        }
        return res;
    }
}
