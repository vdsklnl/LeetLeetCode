package medium;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2022-09-06 13:43
 * @description
 */
public class MinSubArraySum {
    /*
        原问题为求所有子数组的最小值之和，其可等价为求每个 arr[i] 对答案的贡献，
        即每个 arr[i]arr[i] 可作为多少个子数组的最小值。

        假定我们能预处理出两数组 l 和 r 分别代表 arr[i] 作为子数组最小值时，其所能到达的最远两端：
            l[i] = a 代表下标 a 为 arr[i] 能够作为子数组最小值时的最远左边界，
                即为 arr[i]左边第一个比其小的元素（若不存在，则为 a = -1）
            r[i] = b 代表下标 b 为 arr[i]能够作为子数组最小值时的最远右边界，
                即为 arr[i]右边第一个比其小的元素（若不存在，则为 b = n）
        子数组左端点个数为(i−a) 个，右端点个数为(b−i) 个，根据乘法原理可知，子数组个数为两者乘积，
        每个子数组对答案的贡献为 arr[i]*(i−a)*(b−i)。

        由于 arr 可能有重复元素，我们需要考虑取左右端点时，是取成「小于等于」还是「严格小于」：
            若两端均取成严格小于，且两端中间存在与 arr[i] 等值元素，会导致相同子数组被重复统计；
            若两端均取成小于等于，且两端恰好存在与 arr[i] 等值元素，会导致原本可以被添加到子数组的等值元素漏加；
            若一端取成严格小于，另一端取成小于等于，可确保不重不漏。
        至于预处理 l 和 r 则可以使用「单调栈」求解。
    */
    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] l = new int[n];
        int[] r = new int[n];
        Arrays.fill(l, -1);
        Arrays.fill(r, n);

        //对左右两端进行处理
        Deque<Integer> que = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            while (!que.isEmpty() && arr[que.peekLast()] > arr[i])
                r[que.pollLast()] = i;
            que.addLast(i);
        }
        que.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!que.isEmpty() && arr[que.peekLast()] >= arr[i])
                l[que.pollLast()] = i;
            que.addLast(i);
        }

        //综合
        long result = 0;
        int mod = (int) (1e9 + 7);
        for (int i = 0; i < n; i++) {
            result += (1l * arr[i] * (i - l[i]) * (r[i] - i));
            result %= mod;
        }

        return (int) result;
    }

    public static void main(String[] args) {
        int[] arr = {3,1,2,4};
        System.out.println(sumSubarrayMins(arr));
    }
}
