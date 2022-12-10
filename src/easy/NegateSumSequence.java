package easy;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-09-05 16:52
 * @description
 */
public class NegateSumSequence {
    public static void main(String[] args) {

    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        if (nums.length == 1) {
            return (k % 2 == 0) ? nums[0]:-nums[0];
        }
        Arrays.sort(nums);
        int sum = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0 || k == 0) {
                sum += nums[i];
            } else {
                nums[i] = - nums[i];
                sum += nums[i];
                count++;
                k--;
            }
        }

        int min = 0;
        if (k % 2 == 1) {
            if (count == 0) {
                min = nums[count];
            } else if (count == nums.length) {
                min = nums[count - 1];
            } else {
                min = Math.min(nums[count], nums[count - 1]);
            }
            sum -= (2 * min);
        }

        return sum;
    }
}
