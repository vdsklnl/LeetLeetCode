package medium;

/**
 * @author vdsklnl
 * @create 2022-08-12 12:34
 * @description
 */
public class MinSubArray {
    public static void main(String[] args) {

    }

    public int minSubArrayLen(int target, int[] nums) {
        //双指针
        int left = 0, sum = 0, len = 0, min = target;
        for (int i = 0;i < nums.length;i++) {
            sum += nums[i];
            while (sum >= target) {
                len = i + 1 - left;
                if (len < min) {
                    min = len;
                }
                sum -= nums[left];
                left++;
            }
        }
        if (min == target)
            return 0;
        return min;
    }
}
