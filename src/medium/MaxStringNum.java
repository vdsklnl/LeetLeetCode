package medium;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-11-14 16:19
 * @description 179 215 数组排序
 */
public class MaxStringNum {
    public static void main(String[] args) {
        
    }

    public String largestNumber(int[] nums) {
        //数学推导：x + y > y + x 则x * s(y) + y > y * s(x) + x （s(x)表示大于x的十位整数）
        //排序，先转化为包装类，以便comparator对象传入
        int n = nums.length;
        Integer[] newNums = new Integer[n];
        for (int i = 0; i < n; i++) {
            newNums[i] = nums[i];
        }
        Arrays.sort(newNums, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x)
                sx *= 10;
            while (sy <= y)
                sy *= 10;
            return (int) (y * sx + x - x * sy - y);
        });
        if (nums[0] == 0)
            return "0";
        StringBuilder sb = new StringBuilder();
        for (int num:newNums) {
            sb.append(num);
        }
        return sb.toString();
    }

    //快排与堆排
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
