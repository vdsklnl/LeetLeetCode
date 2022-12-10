package hard;

/**
 * @author vdsklnl
 * @create 2022-10-10 20:26
 * @description 801 dp交换机 对应位是否交换
 */
public class MinChangeIncreaseArray {
    public static void main(String[] args) {

    }

    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        /*
        int[][] dp = new int[n][2];
        for (int i = 1; i < n; i++) {
            Arrays.fill(dp[i], n + 1);
        }
        dp[0][1] = 1;
        //只有两种情况：同时满足继承，不满足则交换
        for (int i = 1; i < n; i++) {
            //满足，不交换继承，交换则+1
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            }
            //不满足，不交换则前一交换，交换则在前一不交换基础上+1
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i - 1][0] + 1, dp[i][1]);
            }
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
        */
        //滚动数组:相当于用两个值记录情况
        int a = 0, b = 1;
        //只有两种情况：同时满足继承，不满足则交换
        for (int i = 1; i < n; i++) {
            int temp1 = a, temp2 = b;
            a = b = n;
            //满足，不交换继承，交换则+1
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                a = Math.min(temp1, a);
                b = Math.min(temp2 + 1, b);
            }
            //不满足，不交换则前一交换，交换则在前一不交换基础上+1
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                a = Math.min(temp2, a);
                b = Math.min(temp1 + 1, b);
            }
        }
        return Math.min(a, b);
    }
}
