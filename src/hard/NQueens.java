package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-08-09 17:04
 * @description
 */
public class NQueens {
    public static void main(String[] args) {
        List<List<String>> list = solveNQueens(5);
        for (List<String> l:list) {
            for (String str:l) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        int[] arr = new int[n];
        List<List<String>> list = new ArrayList<>();
        check(0, n, arr, list);
        return list;
    }

    //放置第n个皇后
    private static void check(int n, int size, int[] arr, List<List<String>> list) {
        if (n == size) { //表示已经放置完成
            show(arr, list);
            return;
        }

        for (int i = 0; i < size; i++) {
            //先将第n个皇后排在第一列
            arr[n] = i;
            if (judge(n, arr)) { //不冲突，则放入第n+1个皇后
                check(n + 1, size, arr, list);
            }
            //若均冲突，则将皇后n摆在下一列，继续遍历
        }
    }

    //检查第n个皇后是否冲突
    private static boolean judge(int n, int[] arr) {
        for (int i = 0; i < n; i++) {
            //按照行来遍历，仅需考虑是否处于同一列或者斜线(形成“田”字形)
            if (arr[i] == arr[n]||Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    //输出摆放位置
    private static void show(int[] arr, List<List<String>> list) {
        List<String> subList = new ArrayList<>();
        char[] chars = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            chars[i] = '.';
        }
        for (int i = 0; i < arr.length; i++) {
            chars[arr[i]] = 'Q';
            subList.add(String.copyValueOf(chars));
            chars[arr[i]] = '.';
        }
        list.add(subList);
    }
}
