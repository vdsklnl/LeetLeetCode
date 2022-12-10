package medium;

/**
 * @author vdsklnl
 * @create 2022-10-16 16:26
 * @description 79 回溯边界注意
 */
public class SearchWord {
    public static void main(String[] args) {

    }

    int[][] flag;
    int[] direction = {-1, 0, 1, 0, -1};

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        flag = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtracking(0, i, j, word, board))
                    return true;
            }
        }
        return false;
    }

    public boolean backtracking(int index, int x, int y, String word, char[][] board) {
        if (word.charAt(index) != board[x][y])
            return false;
        else if (index == word.length() - 1)
            return true;
        flag[x][y] = 1;
        boolean result = false;
//        boolean up = false, down = false, left = false, right = false;
//        if (x - 1 >= 0 && flag[x - 1][y] == 0)
//            up = backtracking(index + 1, x - 1, y, word, board);
//        if (y - 1 >= 0 && flag[x][y - 1] == 0)
//            left = backtracking(index + 1, x, y - 1, word, board);
//        if (x + 1 < board.length && flag[x + 1][y] == 0)
//            down = backtracking(index + 1, x + 1, y, word, board);
//        if (y + 1 < board[0].length && flag[x][y + 1] == 0)
//            right = backtracking(index + 1, x, y + 1, word, board);
        for (int i = 0; i < 4; i++) {
            int newX = x + direction[i], newY = y + direction[i + 1];
            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length) {
                if (flag[newX][newY] == 0) {
                    boolean ans = backtracking(index + 1, newX, newY, word, board);
                    if (ans) {
                        result = true;
                        break;
                    }
                }
            }
        }
        flag[x][y] = 0;
        return result;
    }
}
