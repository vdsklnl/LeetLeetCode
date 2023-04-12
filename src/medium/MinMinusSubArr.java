package medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vdsklnl
 * @create 2023-03-10 11:33
 * @description 1590 Hash表前缀和 + 余数
 */
public class MinMinusSubArr {
    public static void main(String[] args) {
//        minSubarray(new int[]{3, 1, 4, 5, 2, 6, 6, 1, 1}, 6);
        String[] s = {"1", "2", "3", "4"};
    }

    /*
        先统计整个数组对p的余数x，相当于最右侧元素为末尾，找最短子数组和消除余数x
        逐步添加新元素，并记录余数Hash表，相当于i元素之前子数组余数y
        若子数组间出现余数x，即出现(y - x + p) % p (+p防止出现负数)
     */
    public static int minSubarray(int[] nums, int p) {
        int sum = 0;
        for (int num:nums) {
            sum = (sum + num) % p;
        }
        if (sum == 0)
            return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int cur = 0, res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            map.put(cur, i);
            cur = (cur + nums[i]) % p;
            int temp = (cur + p - sum) % p;
            //计算i不包含自己，先记录后计算，结果计算需+1
            if (map.containsKey(temp))
                res = Math.min(res, i - map.get(temp) + 1);
        }
        return res == nums.length ? -1:res;
    }
}
