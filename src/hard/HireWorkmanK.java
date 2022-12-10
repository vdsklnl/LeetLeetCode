package hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author vdsklnl
 * @create 2022-09-22 20:09
 * @description
 */
public class HireWorkmanK {
    public static void main(String[] args) {
        int[] qual = {3,1,10,10,1};
        int[] wage = {4,8,2,2,7};
        mincostToHireWorkers(qual, wage, 3);
    }

    //cost X quality[i] / totalQ >= wage[i] -> cost >= totalQ x wage[i] / quality[i]
    /*
        以某一个工人 xx 作为一个工资组中权重最高时，工资组中其他人员只需要在权重小于工人x的集合中
        选择工作质量最少的k-1名工人来组成工资组即可，此时便能达到以工人x为权重最高的工资组的总工作
        量最小，从而达到以工人x为权重最高的工资组的最小工资开销

     */
    public static double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        double[][] arr = new double[n][2];
        for (int i = 0; i < n; i++) {
            //先乘1.0才能被认为是double数
            arr[i][0] = wage[i] * 1.0 / quality[i];
            arr[i][1] = i * 1.0;
        }

        //升序排列：无法直接完成，需要构建二维数组
        Arrays.sort(arr, Comparator.comparingDouble(a -> a[0]));

        //维护优先队列，将元素按照工作量降序排列
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        double res = 1e9, totalQuality = 0.0;

        //先加入k - 1名工人
        for (int i = 0; i < k - 1; i++) {
            totalQuality += quality[(int) arr[i][1]];
            pq.add(quality[(int) arr[i][1]]);
        }

        //每次新添加的成员 w/q 最高，以其作为基础，不断剔除工作量大的工人，遍历完找到最小结果
        for (int i = k - 1; i < n; i++) {
            int index = (int) arr[i][1];
            totalQuality += quality[index];
            pq.offer(quality[index]);
            double totalCost = arr[i][0] * totalQuality;
            res = Math.min(res, totalCost);
            totalQuality -= pq.poll();
        }

        return res;
    }
}
