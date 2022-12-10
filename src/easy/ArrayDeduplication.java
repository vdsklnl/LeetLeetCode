package easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author vdsklnl
 * @create 2022-08-10 17:04
 * @description
 */
public class ArrayDeduplication {
    public static void main(String[] args) {
//        int[] nums = {1, 1, 2};
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    public static int removeDuplicates(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsValue(nums[i]))
                continue;
            map.put(count++, nums[i]);
        }

        for (int i = 0; i < map.size(); i++) {
            nums[i] = map.get(i);
        }

        return map.size();
    }
}
