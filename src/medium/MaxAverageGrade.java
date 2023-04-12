package medium;

import java.util.PriorityQueue;

/**
 * @author vdsklnl
 * @create 2023-02-19 14:28
 * @description
 */
public class MaxAverageGrade {
    public static void main(String[] args) {
//        int[][] classes = {{2,4}, {3,9}, {4,5}, {2,10}};
        int[][] classes = {{1,2}, {3,5}, {2,2}};
        System.out.println(maxAverageRatio(classes, 2));

    }

    public static double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> {
            double v1 = (double) (a[0] + 1) / (a[1] + 1) - a[0] / a[1];
            double v2 = (double) (b[0] + 1) / (b[1] + 1) - b[0] / b[1];
            return v1 == v2 ? 0:v1 < v2 ? 1:-1;
        });
        for (int[] c:classes) {
            que.offer(c);
        }
        while (extraStudents > 0) {
            int[] pair = que.poll();
            pair[0]++; pair[1]++;
            que.offer(pair);
            extraStudents--;
        }
        double res = 0.0;
        while (!que.isEmpty()) {
            int[] pair = que.poll();
            res += (double) pair[0] / pair[1];
        }
        return res / classes.length;
    }
}
