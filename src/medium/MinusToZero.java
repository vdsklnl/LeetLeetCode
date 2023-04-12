package medium;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2023-02-06 17:16
 * @description 1658  左右变换数组
 */
public class MinusToZero {
    public static void main(String[] args) {

    }

    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int rsum = Arrays.stream(nums).sum();
        if (rsum < x)
            return -1;
        int r = 0, lsum = 0, res = n + 1;
        for (int l = -1; l < n; l++) {
            if (l != -1)
                lsum += nums[l];
            while (r < n && lsum + rsum > x) {
                rsum -= nums[r];
                r++;
            }
            if (lsum + rsum == x) {
                res = Math.min(res, (l + 1) + (n - r));
            }
        }
        return res > n ? -1:res;
    }
}
