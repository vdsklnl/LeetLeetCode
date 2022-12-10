package hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author vdsklnl
 * @create 2022-11-09 15:18
 * @description 85 DP || 单调栈优化
 */
public class MaxRectangleInMatrix {
    public static void main(String[] args) {

    }

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;
        //dp[i][j]记录第i行以j为结尾的连续1长度
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (j == 0)
                        dp[i][j] = 1;
                    else
                        dp[i][j] = dp[i][j - 1] + 1;
                }
            }
        }
        int res = 0;
        //计算以i，j为右下角矩形最大面积并更新结果
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0')
                    continue;
                int width = dp[i][j];
                int area = dp[i][j];
                for (int k = i - 1; k >= 0; k--) {
                    width = Math.min(width, dp[k][j]);
                    area = Math.max(area, width * (i - k + 1));
                }
                res = Math.max(res, area);
            }
        }
        return res;
    }

    public int maximalRectangleII(char[][] matrix) {
        int m = matrix.length;
        if (m == 0)
            return 0;
        int n = matrix[0].length;
        //dp[i][j]记录第i行以j为结尾的连续1长度
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (j == 0)
                        dp[i][j] = 1;
                    else
                        dp[i][j] = dp[i][j - 1] + 1;
                }
            }
        }
        int res = 0;
        //计算以i，j为右下角矩形最大面积并更新结果
        for (int j = 0; j < n; j++) {
            //记录对点[i，j]中，同列上下第一次比dp[i][j]小的结果，计算以此点为宽获得最大矩形
            //由于每一点都会进行计算，所以不会遗漏
            int[] up = new int[m], down = new int[m];
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && dp[stack.peek()][j] >= dp[i][j])
                    stack.pop();
                up[i] = stack.isEmpty() ? -1:stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = m - 1; i >= 0; i--) {
                while (!stack.isEmpty() && dp[stack.peek()][j] >= dp[i][j])
                    stack.pop();
                down[i] = stack.isEmpty() ? m:stack.peek();
                stack.push(i);
            }
            for (int i = 0; i < m; i++) {
                int height = down[i] - up[i] - 1;
                res = Math.max(res, height * dp[i][j]);
            }
        }
        return res;
    }
}
