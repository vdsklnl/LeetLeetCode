package medium;

import java.util.PriorityQueue;

/**
 * @author vdsklnl
 * @create 2022-10-31 16:49
 * @description 378
 */
public class SortMatrixKthElement {
    public static void main(String[] args) {

    }

    public int kthSmallest(int[][] matrix, int k) {
        //归并排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.add(new int[] {matrix[i][0], i ,0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] poll = pq.poll();
            if (poll[2] != n - 1)
                pq.add(new int[] {matrix[poll[1]][poll[2] + 1], poll[1], poll[2] + 1});
        }
        return pq.poll()[0];
    }

    public int kthSmallestII(int[][] matrix, int k) {
        //根据行列均递增特性，结果在最大和最小值之间
        int n = matrix.length;
        int left = matrix[0][0], right = matrix[n - 1][n - 1];
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (check(matrix, mid, k, n))
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }

    private boolean check(int[][] matrix, int mid, int k, int n) {
        int i = 0, j = n - 1, num = 0;
        while (i < n && j > 0) {
            if (matrix[i][j] <= mid) {
                i++;
                num += j + 1;
            } else
                j--;
        }
        return num >= k;
    }
}
