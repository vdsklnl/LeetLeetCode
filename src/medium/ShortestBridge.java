package medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author vdsklnl
 * @create 2022-10-25 11:22
 * @description 934 DFS||BFS
 */
public class ShortestBridge {
    public static void main(String[] args) {

    }

    int[] d = {-1, 0, 1, 0, -1};

    public int shortestBridge(int[][] grid) {
        //先通过DFS将一个岛屿标记起来，更新为-1
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    Queue<int[]> queue = new LinkedList<>();
                    dfs(grid, i, j, queue);
                    int res = 0;
                    //BFS根据每轮搜索结果更新距离
                    while (!queue.isEmpty()) {
                        int len = queue.size();
                        for (int k = 0; k < len; k++) {
                            int[] cell = queue.poll();
                            for (int l = 0; l < 4; l++) {
                                int nx = cell[0] + d[l], ny = cell[1] + d[l + 1];
                                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length) {
                                    if (grid[nx][ny] == 0) {
                                        queue.add(new int[] {nx, ny});
                                        grid[nx][ny] = -1;
                                    } else if (grid[nx][ny] == 1) {
                                        return res;
                                    }
                                }
                            }
                        }
                        //每轮搜索完，res++
                        res++;
                    }
                }
            }
        }
        return 0;
    }

    public void dfs(int[][] grid, int x, int y, Queue<int[]> queue) {
        if (grid[x][y] != 1)
            return;

        grid[x][y] = -1;
        queue.add(new int[] {x, y});
        for (int i = 0; i < 4; i++) {
            int nx = x + d[i], ny = y + d[i + 1];
            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length)
                dfs(grid, nx, ny, queue);
        }
    }
}
