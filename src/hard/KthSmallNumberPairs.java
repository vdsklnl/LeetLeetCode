package hard;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-10-31 13:53
 * @description 719
 */
public class KthSmallNumberPairs {
    public static void main(String[] args) {

    }

    public int smallestDistancePair(int[] nums, int k) {
        //diff结果一定在(0, maxDiff)之间
        Arrays.sort(nums);
        int left = 0, right = nums[nums.length - 1] - nums[0];
        while (left <= right) {
            int mid = (left + right) >> 1;
            int cnt = 0;
            for (int i = 0, j = 1; j < nums.length; j++) {
                while (nums[j] - nums[i] > mid)
                    i++;
                cnt += (j - i);
            }
            if (cnt >= k)
                right = mid - 1;
            else if (cnt < k)
                left = mid + 1;
        }
        return left;
    }
}
