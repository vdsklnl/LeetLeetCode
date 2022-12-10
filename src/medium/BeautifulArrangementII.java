package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-09-08 13:40
 * @description
 */
public class BeautifulArrangementII {
    public static void main(String[] args) {
        constructArray(6,3);
    }

    public static int[] constructArray(int n, int k) {
        if (k >= n)
            return new int[0];

        //用n = 5推导下情况
        List<Integer> result = new ArrayList<>();

        //将不同种差值排列完成
        int start = 1, end = start + k, index = end + 1;
        while (end > start) {
            result.add(start++);
            result.add(end--);
        }
        if (k % 2 == 0)
            result.add(start);

        //将后续顺序加入
        for (int i = index; i <= n; i++) {
            result.add(i);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static int[] constructArrayII(int n, int k) {
        if (k >= n)
            return new int[0];

        //用n = 5推导下情况
        int[] result = new int[n];

        //将不同种差值排列完成
        int start = 1, end = start + k, index = 0;
        while (end > start) {
            result[index++] = start++;
            result[index++] = end--;
        }
        if (k % 2 == 0)
            result[index++] = start;

        //将后续顺序加入
        for (int i = index; i < n; i++) {
            result[i] = i + 1;
        }

        return result;
    }
}
