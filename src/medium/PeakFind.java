package medium;

/**
 * @author vdsklnl
 * @create 2022-11-13 18:47
 * @description 162 || 852
 */
public class PeakFind {
    public static void main(String[] args) {

    }

    //直接二分判断中值是位于坡上还是谷底，坡上往右，谷底往左
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = (l + r) >> 1;
            if (nums[m] < nums[m + 1])
                l = m + 1;
            else
                r = m;
        }
        return r;
    }
}
