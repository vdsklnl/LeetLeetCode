package hard;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author vdsklnl
 * @create 2022-10-31 19:23
 * @description
 */
public class KthMultiplyChart {
    public static void main(String[] args) {

    }

    public int findKthNumber(int m, int n, int k) {
        //结果在[1, m*n]之间，对结果，可以确定其对应列数，再每列相加
        int left = 1, right = m * n;
        while (left <= right) {
            int mid = (left + right) >> 1;
            //起码大过这些数
            int num = mid / n * n;
            //从1到m，1到n
            for (int i = mid / n + 1; i <= m; i++) {
                num += (mid / i);
            }
            if (num >= k)
                right = mid - 1;
            else
                left = mid - 1;
        }
        return left;
    }
}
