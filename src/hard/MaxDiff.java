package hard;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-11-13 19:55
 * @description 164
 */
public class MaxDiff {
    public static void main(String[] args) {

    }

    public int maximumGap(int[] nums) {
        //基数排序和桶排序
        Arrays.sort(nums);
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res, nums[i] - nums[i - 1]);
        }
        return res;
    }
}
