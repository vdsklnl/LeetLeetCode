package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-11-15 20:50
 * @description 1109 1943 差分数组
 */
public class FlightCount {
    public static void main(String[] args) {
        int[][] books = {{3,3,5}, {1,3,20}, {1,2,15}};
        corpFlightBookings(books, 3);
    }

    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            res[bookings[i][0] - 1] += bookings[i][2];
            if (bookings[i][1] + 1 <= n)
                res[bookings[i][0] + 1] -= bookings[i][2];
        }
        for (int i = 1; i < n; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }

    public List<List<Long>> splitPainting(int[][] segments) {
        //差分数组：0表示加，1表示减
        long[][] count = new long[(int)1e5 + 1][2];
        List<List<Long>> res = new ArrayList<>();
        for (int i = 0; i < segments.length; i++) {
            count[segments[i][0]][0] += segments[i][2];
            count[segments[i][1]][1] -= segments[i][2];
        }
        //变化的地方即为端点
        long l = 0, sum = 0;
        for (int i = 1; i < count.length; i++) {
            if (count[i][0] != 0 || count[i][1] != 0) {
                if (sum == 0)
                    l = i;
                if (sum != 0) {
                    res.add(Arrays.asList(l, (long) i, sum));
                    l = i;
                }
                sum += count[i][0] + count[i][1];
            }
        }
        return res;
    }
}
