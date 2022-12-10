package easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author vdsklnl
 * @create 2022-07-26 17:03
 * @description
 */
public class TwoNumberPlus {
    public static void main(String[] args) {
        int[] nums = {-1, -2, -3, -4, -5};
        int target = -8;
        int[] res = twoSum(nums, target);
        System.out.println(Arrays.toString(res));
    }

//    public static int[] twoSum(int[] nums, int target) {
//        int[] res = new int[2];
//        int num = 0;
//        int length = nums.length;
//        for (int i = 0; i < length; i++) {
//            res[0] = i;
//            num = target - nums[i];
//            for (int j = i + 1; j < length; j++) {
//                if (nums[j] == num) {
//                    res[1] = j;
//                    break;
//                }
//            }
//            if (res[1] != 0)
//                break;
//        }
//        return res;
//    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]))
                return new int[]{map.get(target - nums[i]), i};
            map.put(nums[i], i);
        }
        return null;
    }
}
