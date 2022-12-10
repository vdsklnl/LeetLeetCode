package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vdsklnl
 * @create 2022-11-08 14:56
 * @description 560 前缀和 + Hash表
 */
public class SumKSubARR {
    public static void main(String[] args) {

    }

    public int subarraySum(int[] nums, int k) {
        int sum = 0, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        //不用取余，取余是求k倍数子数组
        //map中存放相应值对应个数
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
