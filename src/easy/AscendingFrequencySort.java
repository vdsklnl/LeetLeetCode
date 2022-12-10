package easy;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2022-09-19 13:41
 * @description
 */
public class AscendingFrequencySort {
    public static void main(String[] args) {

    }

    public static int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num:nums) {
//            if (map.containsKey(num)) {
//                map.put(num, map.get(num) + 1);
//            } else {
//                map.put(num, 1);
//            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<int[]> list = new ArrayList<>();
        for (int key: map.keySet()) {
            list.add(new int[]{key, map.get(key)});
        }

        Collections.sort(list, (a,b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);

        int index = 0;
        for (int[] fre:list) {
            while (fre[1] > 0) {
                nums[index++] = fre[0];
                fre[1]--;
            }
        }

        return nums;
    }
}
