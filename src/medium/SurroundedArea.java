package medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author vdsklnl
 * @create 2022-10-20 20:16
 * @description 130 DFS 速度快 || BFS 内存低
 */
public class SurroundedArea {
    public static void main(String[] args) {
        String s = "1 2 ag";
        String[] s1 = s.split(" ");
        System.out.println(Arrays.toString(s1));

    }

    int[][] flag;
    int[] d = {-1, 0, 1, 0, -1};

    public void solve(char[][] board) {
        //与周围连接的O不改，其余全改
        int m = board.length, n = board[0].length;
        flag = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                DFS(board, i, 0);
            if (board[i][n - 1] == 'O')
                DFS(board, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O')
                DFS(board, 0, i);
            if (board[m - 1][i] == 'O')
                DFS(board, m - 1, i);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O' && flag[i][j] == 0)
                    board[i][j] = 'X';
            }
        }
    }

    public void DFS(char[][] board, int x, int y) {
        if (board[x][y] == 'X' || flag[x][y] == 1)
            return;

        flag[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int newx = x + d[i], newy = y + d[i + 1];
            if (newx >= 0 && newx < board.length && newy >= 0 && newy < board[0].length)
                DFS(board, newx, newy);
        }
    }

    public void solveBFS(char[][] board) {
        //与周围连接的O不改，其余全改
        int m = board.length, n = board[0].length;
        Queue<int[]> que = new LinkedList<>();
        //记录边缘位置 O标记为A
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                board[i][0] = 'A';
                que.offer(new int[] {i, 0});
            }
            if (board[i][n - 1] == 'O') {
                board[i][n - 1] = 'A';
                que.offer(new int[] {i, n - 1});
            }
        }

        for (int i = 1; i < n - 1; i++) {
            if (board[0][i] == 'O') {
                board[0][i] = 'A';
                que.offer(new int[] {0, i});
            }
            if (board[m - 1][i] == 'O') {
                board[m - 1][i] = 'A';
                que.offer(new int[] {m - 1, i});
            }

        }

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int x = cur[0], y = cur[1];
            for (int i = 0; i < 4; i++) {
                int newx = x + d[i], newy = y + d[i + 1];
                if (newx >= 0 && newx < m && newy >= 0 && newy < n && board[newx][newy] == 'O') {
                    board[newx][newy] = 'A';
                    que.offer(new int[] {newx, newy});
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == 'A')
                    board[i][j] = 'O';
            }
        }
    }
}
