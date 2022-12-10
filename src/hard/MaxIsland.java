package hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author vdsklnl
 * @create 2022-09-18 19:04
 * @description 827 给你一个大小为 n x n 二进制矩阵 grid ,最多只能将一格 0 变成 1 ,
 *                  返回执行此操作后，grid 中最大的岛屿面积.
 */
public class MaxIsland {
    public static void main(String[] args) {

    }

    //表示向上下左右进行遍历
    private static int[] direction = new int[] {0, -1, 0, 1, 0};

    //并查集 + 枚举
    public static int largestIsland(int[][] grid) {
        //先将原数组中所有岛屿面积求出并保存，再遍历所有点求最大面积
        int n = grid.length, res = 0;
        int[][] flag = new int[n][n];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && flag[i][j] == 0) {
                    //每片岛屿相同，但互相不同
                    int t = i * n + j + 1;
                    map.put(t, DFS(grid, flag, i, j, t));
                    res = Math.max(res, map.get(t));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int area = 1;
                    //防止同一片岛屿多次计算
                    Set<Integer> connected = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int x = i + direction[k], y = j + direction[k + 1];
                        if (!valid(n, x, y) || grid[x][y] == 0 || connected.contains(flag[x][y]))
                            continue;
                        area += map.get(flag[x][y]);
                        connected.add(flag[x][y]);
                    }
                    res = Math.max(res, area);
                }
            }
        }

        return res;
    }

    public static int DFS(int[][] grid, int[][] flag, int x, int y, int t) {
        int n = grid.length, res = 1;
        flag[x][y] = t;
        for (int i = 0; i < 4; i++) {
            int x1 = x + direction[i], y1 = y + direction[i + 1];
            if (valid(n, x1, y1) && grid[x1][y1] == 1 && flag[x1][y1] == 0) {
                res += DFS(grid, flag, x1, y1, t);
            }
        }
        return res;
    }

    public static boolean valid(int n, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
