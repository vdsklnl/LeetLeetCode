package hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-10-22 14:19
 * @description 1235 序列dp + 二分查找
 */
public class PartTimeJobsPlan {
    public static void main(String[] args) {

    }


    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        //使用dp数组记录能够获得的最大利润，dp[i]为前i个工作能获得最大利润
        int n = startTime.length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new int[] {startTime[i], endTime[i], profit[i]});
        }
        //根据结束时间排序
        Collections.sort(list, (a, b) -> a[1] - b[1]);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int[] cur = list.get(i - 1);
            //寻找前面结束时间 <= 此时开始时间的位置，结束时right即为dp位置
            int left = 0, right = i - 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (list.get(mid)[1] > cur[0])
                    right = mid;
                else
                    left = mid + 1;
            }
            dp[i] = Math.max(dp[i - 1], dp[right] + cur[2]);
        }
        return dp[n];
    }
}
