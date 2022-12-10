package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-09-02 13:47
 * @description
 */
public class SubVector {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        boolean[] flag = new boolean[nums.length];
        backtracking(nums, 0, flag);
        return result;
    }

    public void backtracking(int[] nums, int index, boolean[] flag) {
        result.add(new ArrayList<>(path));

        if (index >= nums.length)
            return;

        for (int i = index; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !flag[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            flag[i] = true;
            backtracking(nums, i + 1, flag);
            flag[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {

    }
}
