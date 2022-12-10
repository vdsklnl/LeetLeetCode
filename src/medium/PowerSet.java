package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-10-28 16:50
 * @description
 */
public class PowerSet {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        subsets(nums);
    }

    static List<List<Integer>> res;
    static List<Integer> list;

    public static List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        list = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        backtracking(nums, 0);
        return res;
    }

    public static void backtracking(int[] nums, int index) {
        if (index == nums.length)
            return;

        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            //添加时需要新建list，否则会同步变化
            res.add(new ArrayList<>(list));
            backtracking(nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
