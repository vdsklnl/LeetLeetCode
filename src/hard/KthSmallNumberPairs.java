package hard;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2022-10-31 13:53
 * @description 719
 */
public class KthSmallNumberPairs {
    public static void main(String[] args) {
        Random random = new Random();
        int[][] paths = {{1, 2}, {2, 3}, {3, 1}};
        gardenNoAdj(3, paths);
    }

    public static int[] gardenNoAdj(int n, int[][] paths) {
        List<Integer>[] list = new List[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] path:paths) {
            list[path[0] - 1].add(path[1] - 1);
            list[path[1] - 1].add(path[0] - 1);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            boolean[] flag = new boolean[5];
            for (int p:list[i]) {
                flag[res[p]] = true;
            }
            for (int j = 1; j <= 4; j++) {
                if (!flag[j]) {
                    res[i] = j;
                    break;
                }
            }
        }
        return res;
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
