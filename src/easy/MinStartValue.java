package easy;

/**
 * @author vdsklnl
 * @create 2022-08-09 10:48
 * @description
 */
public class MinStartValue {
    public static void main(String[] args) {
//        int[] nums = {-3,2,-3,4,2};
//        int[] nums = {1,2};
        int[] nums = {1,-2,-3};
        System.out.println(minStartValue(nums));
    }

    public static int minStartValue(int[] nums) {
        int minSum = 0;
        int thisSum = 0;

        for (int i = 0; i < nums.length; i++) {
            thisSum += nums[i];
            if (thisSum < minSum)
                minSum = thisSum;
        }

        if (minSum > 0)
            return 1;

        return 1-minSum;
    }
}
