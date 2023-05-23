package hard;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2023-04-20 15:36
 * @description 1187 动态规划
 */
public class MakeArrayIncreasing {
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        List<Integer> list = new ArrayList<>();
        int pre = -1;
        for (int num:arr2) {
            if (num != pre) {
                list.add(num);
                pre = num;
            }
        }
        int n = arr1.length;
        int m = list.size();
        int INF = 0x3f3f3f3f;
        //dp[i][j]表示前i个元素进行j次交换所得数组末尾最小元素
        int[][] dp = new int[n + 1][Math.min(m, n) + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, m); j++) {
                //当前元素大于序列最后一个元素
                if (arr1[i - 1] > dp[i - 1][j]) {
                    dp[i][j] = arr1[i - 1];
                }
                //查找大于dp[i - 1][j - 1]的最小元素
                if (j > 0 && dp[i - 1][j - 1] != INF) {
                    int index = binarySearch(list, j - 1, dp[i - 1][j - 1]);
                    if (index != list.size()) {
                        dp[i][j] = Math.min(dp[i][j], list.get(index));
                    }
                }
                if (i == n && dp[i][j] != INF) {
                    return j;
                }
            }
        }
        return -1;
    }

    public int binarySearch(List<Integer> list, int low, int target) {
        int high = list.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) > target)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
    }
}
