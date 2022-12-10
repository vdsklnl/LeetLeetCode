package medium;

/**
 * @author vdsklnl
 * @create 2022-08-09 14:15
 * @description
 */
public class ArithmeticSubsequence {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(numberOfArithmeticSlices(nums));
    }

    public static int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3)
            return 0;

        int res = 0;
        int num = 0;

        //连续等差数列，数量1 + 2 + 3 + ... + n-2
        for (int i = 2; i < nums.length; i++) {
            if ((nums[i] - nums[i-1]) == (nums[i-1] - nums[i-2])) {
                num++;
                res += num;
            }
            else
                num = 0;
        }
        return res;
    }

}
