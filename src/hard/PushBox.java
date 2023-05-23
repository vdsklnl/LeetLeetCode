package hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author vdsklnl
 * @create 2023-05-08 17:44
 * @description 1263 bfs + dp状态优化
 */
public class PushBox {
    public int minPushBox(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int sx = -1, sy = -1, bx = -1, by = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }

        int[] d = {-1, 0, 1, 0, -1};
        /*
            dp[i][j]：表示人位于i位置，箱子位于j位置时，总推动次数
            位置采用 x * n + y 来确定
        */
        int[][] dp = new int[m * n][m * n];
        for (int i = 0; i < m * n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        Queue<int[]> que = new ArrayDeque<>();
        dp[sx * n + sy][bx * n + by] = 0;
        que.offer(new int[] {sx * n + sy, bx * n + by});
        while (!que.isEmpty()) {
            Queue<int[]> que1 = new ArrayDeque<>();
            while (!que.isEmpty()) {
                int[] pair = que.poll();
                int s1 = pair[0], b1 = pair[1];
                int sx1 = s1 / n, sy1 = s1 % n, bx1 = b1 / n, by1 = b1 % n;
                if (grid[bx1][by1] == 'T')
                    return dp[s1][b1];
                for (int i = 0; i < 4; i++) {
                    int sx2 = sx1 + d[i], sy2 = sy1 + d[i + 1], s2 = sx2 * n + sy2;
                    if (!judge(grid, sx2, sy2, m, n))
                        continue;
                    if (sx2 == bx1 && sy2 == by1) {
                        int bx2 = bx1 + d[i], by2 = by1 + d[i + 1], b2 = bx2 * n + by2;
                        if (!judge(grid, bx2, by2, m, n) || dp[s2][b2] <= dp[s1][b1] + 1)
                            continue;
                        dp[s2][b2] = dp[s1][b1] + 1;
                        que1.offer(new int[] {s2, b2});
                    } else {
                        if (dp[s2][b1] <= dp[s1][b1])
                            continue;
                        dp[s2][b1] = dp[s1][b1];
                        que.offer(new int[] {s2, b1});
                    }
                }
            }
            que = que1;
        }
        return -1;
    }

    public boolean judge(char[][] grid, int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != '#';
    }

    public static void main(String[] args) {
        String[][] grid = {{".",".","#",".",".",".",".",".",".","."},
                           {".","#",".","#","B","#",".","#",".","."},
                           {".","#",".",".",".",".",".",".","T","."},
                           {"#",".",".",".",".",".",".",".",".","."},
                           {".",".",".",".",".",".",".",".",".","#"},
                           {".",".",".",".",".",".",".",".","#","."},
                           {".",".",".","#",".",".","#","#",".","."},
                           {".",".",".",".","#",".",".","#",".","."},
                           {".","#",".","S",".",".",".",".",".","."},
                           {"#",".",".","#",".",".",".",".",".","#"}};
        char[][] newGrid = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                newGrid[i][j] = grid[i][j].charAt(0);
            }
        }
    }
}

class Solution {
    public int minPushBox(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int sx = -1, sy = -1, bx = -1, by = -1; // 玩家、箱子的初始位置
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (grid[x][y] == 'S') {
                    sx = x;
                    sy = y;
                } else if (grid[x][y] == 'B') {
                    bx = x;
                    by = y;
                }
            }
        }

        int[] d = {0, -1, 0, 1, 0};

        int[][] dp = new int[m * n][m * n];
        for (int i = 0; i < m * n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new ArrayDeque<int[]>();
        dp[sx * n + sy][bx * n + by] = 0; // 初始状态的推动次数为 0
        queue.offer(new int[]{sx * n + sy, bx * n + by});
        while (!queue.isEmpty()) {
            Queue<int[]> queue1 = new ArrayDeque<int[]>();
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int s1 = arr[0], b1 = arr[1];
                int sx1 = s1 / n, sy1 = s1 % n, bx1 = b1 / n, by1 = b1 % n;
                if (grid[bx1][by1] == 'T') { // 箱子已被推到目标处
                    return dp[s1][b1];
                }
                for (int i = 0; i < 4; i++) { // 玩家向四个方向移动到另一个状态
                    int sx2 = sx1 + d[i], sy2 = sy1 + d[i + 1], s2 = sx2*n+sy2;
                    if (!ok(grid, m, n, sx2, sy2)) { // 玩家位置不合法
                        continue;
                    }
                    if (bx1 == sx2 && by1 == sy2) { // 推动箱子
                        int bx2 = bx1 + d[i], by2 = by1 + d[i + 1], b2 = bx2*n+by2;
                        if (!ok(grid, m, n, bx2, by2) || dp[s2][b2] <= dp[s1][b1] + 1) { // 箱子位置不合法 或 状态已访问
                            continue;
                        }
                        dp[s2][b2] = dp[s1][b1] + 1;
                        queue1.offer(new int[]{s2, b2});
                    } else {
                        if (dp[s2][b1] <= dp[s1][b1]) { // 状态已访问
                            continue;
                        }
                        dp[s2][b1] = dp[s1][b1];
                        queue.offer(new int[]{s2, b1});
                    }
                }
            }
            queue = queue1;
        }
        return -1;
    }

    public boolean ok(char[][] grid, int m, int n, int x, int y) { // 不越界且不在墙上
        return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != '#';
    }
}