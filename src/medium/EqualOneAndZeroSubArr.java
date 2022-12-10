package medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vdsklnl
 * @create 2022-10-29 16:10
 * @description 525 523 前缀和数组 + Hash表
 */
public class EqualOneAndZeroSubArr {
    public static void main(String[] args) {

    }


    public int findMaxLength(int[] nums) {
        //相当于两个相同前缀和之间满足0，1相同
        int n = nums.length;
        int res = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            count += nums[i] == 1 ? 1:-1;
            if (map.containsKey(count)) {
                res = Math.max(res, i - map.get(count));
            } else
                map.put(count, i);
        }
        return res;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        //前缀和数组
        int n = nums.length;
        if (n < 2)
            return false;
        //存储对k的余数，如果存在两次相同余数，且下标相差超过2，则成功
        Map<Integer, Integer> map = new HashMap<>();
        //防止总和为整倍数
        map.put(0, -1);
        int rem = 0;
        for (int i = 0; i < n; i++) {
            rem = (rem + nums[i]) % k;
            if (map.containsKey(rem)) {
                if (i - map.get(rem) >= 2)
                    return true;
            } else
                map.put(rem, i);
        }
        return false;
    }
}
