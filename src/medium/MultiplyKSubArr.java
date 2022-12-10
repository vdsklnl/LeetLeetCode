package medium;

/**
 * @author vdsklnl
 * @create 2022-11-08 15:21
 * @description 713 滑动窗口
 */
public class MultiplyKSubArr {
    public static void main(String[] args) {

    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0, l = 0, mul = 1;
        //以r为右边界，分别计算[l, r]之间乘积小于k的l位置，以r为尾的子数组数为r - l + 1
        for (int r = 0; r < nums.length; r++) {
            mul *= nums[r];
            while (l <= r && mul >= k) {
                mul /= nums[l];
                l++;
            }
            res += (r - l + 1);
        }
        return res;
    }
}
