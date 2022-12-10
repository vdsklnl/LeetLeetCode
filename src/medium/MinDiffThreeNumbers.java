package medium;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-10-05 21:06
 * @description
 */
public class MinDiffThreeNumbers {
    public static void main(String[] args) {

    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE, n = nums.length;
        //先确定一个值，再找两数作和
        for (int i = 0; i < n; i++) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target)
                    return target;
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
                if (sum > target) {
                    int r = right - 1;
                    //移动到下一个不相同的地方
                    while (left < r && nums[right] == nums[r])
                        r--;
                    right = r;
                } else {
                    int l = left + 1;
                    //移动到下一个不相同的地方
                    while (l < right && nums[left] == nums[l])
                        l++;
                    left = l;
                }
            }
        }
        return res;
    }
}
