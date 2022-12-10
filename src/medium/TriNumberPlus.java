package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-08-09 19:32
 * @description
 */
public class TriNumberPlus {
    /*
    两层for循环就可以确定 a 和b 的数值了，可以使用哈希法来确定 0-(a+b) 是否在数组里出现过，
    其实这个思路是正确的，但是我们有一个非常棘手的问题，就是题目中说的不可以包含重复的三元组。

    把符合条件的三元组放进vector中，然后再去重，这样是非常费时的，很容易超时，也是这道题目通过率如此之低的根源所在。

    去重的过程不好处理，有很多小细节，如果在面试中很难想到位。

    时间复杂度可以做到O(n^2)，但还是比较费时的，因为不好做剪枝操作。

    这道题目使用双指针法要比哈希法高效一些
    */

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); //先排序，数组下标不影响结果

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                return result;

            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0)
                    right--;
                else if (sum < 0)
                    left++;
                else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (right > left && nums[right] == nums[right - 1])
                        right--;
                    while (right > left && nums[left] == nums[left + 1])
                        left++;
                    left++;
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> list = threeSum(nums);
        for (List<Integer> sub:list) {
            for (int item:sub) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}
