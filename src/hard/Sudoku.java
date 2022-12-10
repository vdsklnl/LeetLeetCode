package hard;

/**
 * @author vdsklnl
 * @create 2022-09-04 20:08
 * @description
 */
public class Sudoku {
    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},
                          {'6','.','.','1','9','5','.','.','.'},
                          {'.','9','8','.','.','.','.','6','.'},
                          {'8','.','.','.','6','.','.','.','3'},
                          {'4','.','.','8','.','3','.','.','1'},
                          {'7','.','.','.','2','.','.','.','6'},
                          {'.','6','.','.','.','.','2','8','.'},
                          {'.','.','.','4','1','9','.','.','5'},
                          {'.','.','.','.','8','.','.','7','9'}};
        System.out.println(solveSudoku(board));
    }

    public static boolean solveSudoku(char[][] board) {
        return backtracking(board);
    }

    public static boolean backtracking(char[][] board) {
        //每行每列判断
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.')
                    continue;
                //9个数试完，不成功则返回false
                for (char k = '1'; k <= '9'; k++) {
                    if (isValid(i, j, k, board)) {
                        board[i][j] = k;
                        if (backtracking(board)) {
                            //找到一组立马返回
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }

        //未返回false， 则表示找到，返回true
        return true;
    }

    public static boolean isValid(int row, int col, char val, char[][] board) {
        //判断行
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == val)
                return false;
        }

        //判断列
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == val)
                return false;
        }

        //判断九宫格
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == val)
                    return false;
            }
        }

        return true;
    }
}
