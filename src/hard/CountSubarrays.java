package hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vdsklnl
 * @create 2023-03-16 16:02
 * @description  2488 前缀和 + HashMap
 */
public class CountSubarrays {
    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,3,4,5,6,7,8,9};
        int[] arr = (int[]) Arrays.copyOfRange(nums, 0, 4);
        System.out.println(Arrays.toString(arr));
    }

    /*
        先计算左边的sum数，从kIndex开始，若出现相同sum(num(> k) == num(< k))，
        sum - 1(num(> k) = num(< k) + 1)(偏左也是中位数)
     */
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length, kIndex = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k)
                kIndex = i;
        }
        //前缀和，sum表示和(大小记+1-1)，记录出现次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += sign(nums[i] - k);
            if (i < kIndex)
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            else {
                res += map.getOrDefault(sum, 0) + map.getOrDefault(sum - 1, 0);
            }
        }
        return res;
    }

    public int sign(int value) {
        return value == 0 ? 0:value > 0 ? 1:-1;
    }
}
