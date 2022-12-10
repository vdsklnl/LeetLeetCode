package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vdsklnl
 * @create 2022-11-17 13:45
 * @description 930 前缀和 + Hash表 || 滑动窗口
 *              923 三数之和
 */
public class SumEqualsBinaryArr {
    public static void main(String[] args) {
        int[] arr = new int[3000];
        threeSumMulti(arr, 0);
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            sum += nums[i];
            res += map.getOrDefault(sum - goal, 0);
        }
        return res;
    }

    public int numSubarraysWithSumII(int[] nums, int goal) {
        //滑动窗口
        int n = nums.length;
        int l1 = 0, l2 = 0, r = 0;
        int s1 = 0, s2 = 0, res = 0;
        while (r < n) {
            s1 += nums[r];
            //获得以r为右边界且满足sum = goal的左边界
            while (l1 <= r && s1 > goal)
                s1 -= nums[l1++];
            s2 += nums[r];
            //获得以r为右边界且不满足sum = goal的左边界
            while (l2 <= r && s2 >= goal)
                s2 -= nums[l2++];
            res += l2 - l1; //以r为右边界满足子数组有l2-l1个
            r++;
        }
        return res;
    }

    public static int threeSumMulti(int[] arr, int target) {
        //先统计出全部数字
        //int 会出现越界问题， count为long型
        long[] cnt = new long[101];
        int count = 0;
        for (int num:arr) {
            cnt[num]++;
            if (cnt[num] == 1)
                count++;
        }
        int[] uni = new int[count];
        int index = 0;
        for (int i = 0; i <= 100; i++) {
            if (cnt[i] != 0) {
                uni[index++] = i;
            }
        }

        //按三数之和计算
        int mod = (int) (1e9 + 7);
        int res = 0;
        for (int i = 0; i < count; i++) {
            int l = i, r = count - 1;
            int sum = target - uni[i];
            while (l <= r) {
                if (uni[l] + uni[r] > sum)
                    r--;
                else if (uni[l] + uni[r] < sum)
                    l++;
                else {
                    if (i < l && l < r)
                        res += cnt[uni[i]] * cnt[uni[l]] * cnt[uni[r]];
                    else if (i == l && l < r)
                        res += cnt[uni[i]] * (cnt[uni[i]] - 1) * cnt[uni[r]] / 2;
                    else if (i < l && l == r)
                        res += cnt[uni[i]] * (cnt[uni[r]] - 1) * cnt[uni[r]] / 2;
                    else
                        res += cnt[uni[i]] * (cnt[uni[i]] - 1) * (cnt[uni[i]] - 2) / 6 % mod;
                    res %= mod;
                    l++;
                    r--;
                }
            }
        }
        return (int) res;
    }
}
