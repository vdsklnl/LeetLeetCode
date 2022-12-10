package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-08-12 15:54
 * @description
 */
public class CircleMatrix {
    public static void main(String[] args) {
//        generateMatrix(4);
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        rotate(matrix);
    }

    //顺时针旋转90°，交换三次就行
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int loop = n / 2; //圈数
        int startx = 0, starty = 0; //圈起始位置
        int len = 1; //墙，每转一圈加一
        int i = 0, j = 0; //原目标
        int k = 0, l = 0; //交换目标
        int temp = 0; //辅助交换

        //左闭右开
        while (loop > 0) {
            i = startx;
            j = starty;
            k = n - len;
            l = starty;
            for (i = startx;i < n - len;i++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[k][l];
                matrix[k][l] = temp;
                l++;
            }
            for (j = starty;j < n - len;j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[k][l];
                matrix[k][l] = temp;
                k--;
            }
            for (;i > startx;i--) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[k][l];
                matrix[k][l] = temp;
                l--;
            }
            startx++;
            starty++;
            len++;
            loop--;
        }
    }

    //输出M × N 矩阵
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();

        int r = matrix.length, c = matrix[0].length; //r：行 c：列
        int loop = (r <= c) ? (r / 2):(c / 2); //圈数
        int startx = 0, starty = 0; //每圈起始位置
        int i = 0, j = 0; //赋值指针
        int len = 1; //每转一圈边界需要收缩1

        while (loop > 0) {
            i = startx;
            j = starty;
            for (j = starty; j < c - len; j++) {
                list.add(matrix[i][j]);
            }
            for (i = startx; i < r - len; i++) {
                list.add(matrix[i][j]);
            }
            for (; j > starty; j--) {
                list.add(matrix[i][j]);
            }
            for (; i > startx; i--) {
                list.add(matrix[i][j]);
            }
            startx++;
            starty++;
            len++;
            loop--;
        }

        // 处理多出部分，分为两种情况
        if (r <= c && r % 2 == 1) {
            //行小于列
            for (int k = 0; k < c - r + 1; k++) {
                list.add(matrix[startx][starty++]);
            }
        } else if (c < r && c % 2 == 1) {
            for (int k = 0; k < r - c + 1; k++) {
                list.add(matrix[startx++][starty]);
            }
        }

        return list;
    }

    //生成回转矩阵
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int count = 1; //赋值
        int startx = 0, starty = 0; //每圈起始位置
        int i = 0, j = 0; //赋值指针
        int len = 1; //每转一圈边界需要收缩1
        int loop = n / 2; //圈数

        //左闭右开
        while (loop > 0) {
            i = startx;
            j = starty;
            //模拟转圈
            for (j = starty; j < n - len; j++) {
                matrix[i][j] = count++;
            }
            for (i = startx; i < n - len; i++) {
                matrix[i][j] = count++;
            }
            for (; j > starty; j--) {
                matrix[i][j] = count++;
            }
            for (; i > startx; i--) {
                matrix[i][j] = count++;
            }
            startx++;
            starty++;
            len++;
            loop--;
        }

        //奇数时，中间元素需要单独赋值
        if (n % 2 == 1)
            matrix[n / 2][n / 2] = n * n;

        return matrix;
    }
}
