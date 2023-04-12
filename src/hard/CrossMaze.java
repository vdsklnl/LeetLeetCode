package hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author vdsklnl
 * @create 2023-02-05 16:07
 * @description 1210 BFS || 动态规划
 */
public class CrossMaze {
    public static void main(String[] args) {

    }

    //BFS: 存放蛇尾坐标，三元组表示状态
    public int minimumMoves(int[][] grid) {
        int n = grid.length;
        int[][][] res = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(res[i][j], -1);
            }
        }
        res[0][0][0] = 0;

        Queue<int[]> que = new ArrayDeque<>();
        que.offer(new int[] {0, 0, 0});

        while (!que.isEmpty()) {
            int[] pair = que.poll();
            int x = pair[0], y = pair[1], status = pair[2];
            if (status == 0) {
                //右移
                if (y + 2 < n && grid[x][y + 2] == 0 && res[x][y + 1][0] == -1) {
                    res[x][y + 1][0] = res[x][y][0] + 1;
                    que.offer(new int[] {x, y + 1, 0});
                }
                //下移
                if (x + 1 < n && grid[x + 1][y] == 0 && grid[x + 1][y + 1] == 0 && res[x + 1][y][0] == -1) {
                    res[x + 1][y][0] = res[x][y][0] + 1;
                    que.offer(new int[] {x + 1, y, 0});
                }
                //旋转90°
                if (x + 1 < n && y + 1 < n && grid[x + 1][y] == 0 && grid[x + 1][y + 1] == 0 && res[x][y][1] == -1) {
                    res[x][y][1] = res[x][y][0] + 1;
                    que.offer(new int[] {x, y, 1});
                }
            } else {
                //右移
                if (y + 1 < n && grid[x][y + 1] == 0 && grid[x + 1][y + 1] == 0 && res[x][y + 1][1] == -1) {
                    res[x][y + 1][1] = res[x][y][1] + 1;
                    que.offer(new int[] {x, y + 1, 1});
                }
                //下移
                if (x + 2 < n && grid[x + 2][y] == 0 && res[x + 1][y][1] == -1) {
                    res[x + 1][y][1] = res[x][y][1] + 1;
                    que.offer(new int[] {x + 1, y, 1});
                }
                //旋转90°
                if (x + 1 < n && y + 1 < n && grid[x][y + 1] == 0 && grid[x + 1][y + 1] == 0 && res[x][y][0] == -1) {
                    res[x][y][0] = res[x][y][1] + 1;
                    que.offer(new int[] {x, y, 0});
                }
            }
        }

        return res[n - 1][n - 2][0];
    }

    //动态规划: 存放蛇尾坐标，三元组表示状态，双循环
    public int minimumMovesII(int[][] grid) {
        int n = grid.length;
        final int UNREACHED = Integer.MAX_VALUE / 2;
        int[][][] res = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(res[i][j], UNREACHED);
            }
        }
        res[0][0][0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean horizontal = (j + 1 < n && grid[i][j] == 0 && grid[i][j + 1] == 0);
                boolean vertical = (i + 1 < n && grid[i][j] == 0 && grid[i + 1][j] == 0);

                if (i - 1 >= 0 && horizontal)
                    res[i][j][0] = Math.min(res[i][j][0], res[i - 1][j][0] + 1);
                if (j - 1 >= 0 && horizontal)
                    res[i][j][0] = Math.min(res[i][j][0], res[i][j - 1][0] + 1);
                if (i - 1 >= 0 && vertical)
                    res[i][j][1] = Math.min(res[i][j][1], res[i - 1][j][1] + 1);
                if (j - 1 >= 0 && vertical)
                    res[i][j][1] = Math.min(res[i][j][1], res[i][j - 1][1] + 1);

                if (vertical && horizontal && grid[i + 1][j + 1] == 0) {
                    res[i][j][0] = Math.min(res[i][j][0], res[i][j][1] + 1);
                    res[i][j][1] = Math.min(res[i][j][1], res[i][j][0] + 1);
                }
            }
        }

        return res[n - 1][n - 2][0] == UNREACHED ? -1:res[n - 1][n - 2][0];
    }
}
