package medium;

/**
 * @author vdsklnl
 * @create 2022-09-14 16:54
 * @description 同 1035/1143
 */
public class LongSameSubArray {
    public static void main(String[] args) {

    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        //相当于求两数组最大公共序列，不改变顺序，可以删除元素
        int[] dp = new int[nums2.length + 1];
        for (int i = 0; i < nums1.length; i++) {
            int pre = dp[0];
            for (int j = 1; j <= nums2.length; j++) {
                int cur = dp[j];
                if (nums1[i] == nums2[j - 1]) {
                    dp[j] = pre + 1;
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                pre = cur;
            }
        }
        return dp[nums2.length];
    }
}
