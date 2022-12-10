package medium;

/**
 * @author vdsklnl
 * @create 2022-11-08 21:28
 * @description 152 DP
 */
public class MultiplyMaxSubArr {
    public static void main(String[] args) {

    }

    public int maxProduct(int[] nums) {
        /*
            对位置i，若nums[i] < 0，其最大值为前一位置最小值 * nums[i]
            因此，需维护两个数，记录前一位置最大最小值。
            位置i最大最小值更新，需比较前一位置最值与nums[i]相乘和nums[i]
            包含本身，在正负数切换时，始终记录连续数组极限值
        */
        int max = nums[0], min = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int preX = max, preN = min;
            max = Math.max(preX * nums[i], Math.max(nums[i], preN * nums[i]));
            min = Math.min(preX * nums[i], Math.min(nums[i], preN * nums[i]));
            res = Math.max(res, max);
        }
        return res;
    }
}
