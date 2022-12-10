package easy;

/**
 * @author vdsklnl
 * @create 2022-08-10 20:20
 * @description
 */
public class Upstairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(45));
    }

    public static int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        int[] nums = new int[45];
        nums[0] = 1;
        nums[1] = 2;
        for (int i = 2; i < 45; i++) {
            if (i == (n - 1)) {
                break;
            }
            nums[i] = nums[i - 1] + nums[i - 2];
        }

        return nums[n - 2] + nums[n - 3];
    }
}
