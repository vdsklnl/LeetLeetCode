package hard;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2022-11-10 10:52
 * @description 864 状态压缩 + BFS + 二进制记录
 */
public class GetAllKeys {
    public static void main(String[] args) {
        System.out.println(0 | 1 << 2);
    }

    //BFS求最短路径
    public int shortestPathAllKeys(String[] grid) {
        //使用三元组记录状态: (x, y)为位置，mask为钥匙持有情况
        int m = grid.length, n = grid[0].length();
        int bx = 0, by = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i].charAt(j) == '@') {
                    bx = i;
                    by = j;
                } else if (Character.isLowerCase(grid[i].charAt(j))) {
                    if (!map.containsKey(grid[i].charAt(j))) {
                        int size = map.size();
                        map.put(grid[i].charAt(j), size);
                    }
                }
            }
        }

        //初始化为-1，表示未访问过，重复访问时，钥匙数目应该不同
        //存放到达(i, j)时，持有钥匙情况为mask的最短路径
        int[][][] dp = new int[m][n][1 << map.size()];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int[] dir = {-1, 0, 1, 0, -1};
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[] {bx, by, 0});
        //起始位置，走0步
        dp[bx][by][0] = 0;
        while (!deque.isEmpty()) {
            int[] poll = deque.poll();
            int x = poll[0], y = poll[1], mask = poll[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i], ny = y + dir[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx].charAt(ny) != '#') {
                    if (grid[nx].charAt(ny) == '.' || grid[nx].charAt(ny) == '@') {
                        //持有钥匙时，不可能经过同一房间两次
                        if (dp[nx][ny][mask] == -1) {
                            //新房间走的步数加1
                            dp[nx][ny][mask] = dp[x][y][mask] + 1;
                            deque.offer(new int[] {nx, ny, mask});
                        }
                    } else if (Character.isLowerCase(grid[nx].charAt(ny))) {
                        int index = map.get(grid[nx].charAt(ny));
                        //表示钥匙未被访问过，将对应mask位置更新为步数 + 1
                        if (dp[nx][ny][mask | (1 << index)] == -1) {
                            dp[nx][ny][mask | (1 << index)] = dp[x][y][mask] + 1;
                            //表示将新钥匙加入mask中，如果加入后全部钥匙都获得，返回路径
                            if ((mask | (1 << index)) == (1 << map.size()) - 1) {
                                return dp[nx][ny][mask | (1 << index)];
                            }
                            //表明将有钥匙这一个房间加入搜索
                            deque.offer(new int[] {nx, ny, mask | (1 << index)});
                        }
                    } else {
                        //此时，表示遇到锁，只有持有对应钥匙才能走
                        int index = map.get(Character.toLowerCase(grid[nx].charAt(ny)));
                        //跳跃式更新，不存在持有钥匙相同的情况经过同一房间
                        if ((mask & (1 << index)) != 0 && dp[nx][ny][mask] == -1) {
                            dp[nx][ny][mask] = dp[x][y][mask] + 1;
                            deque.offer(new int[] {nx, ny, mask});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
