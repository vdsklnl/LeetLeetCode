package medium;

/**
 * @author vdsklnl
 * @create 2022-11-12 18:26
 * @description 790 状态转移 + DP || 矩阵快速幂
 */
public class DominoTiling {
    public static void main(String[] args) {

    }

    /*
        对每一列，有4个状态（0表示空，1表示填满）
        0   0   1   1
        0   1   0   1
        有两种填入情况：2 2 与 3 3
                             3
        i列基于i-1列状态
        0   1 0     0   3 0    0 0      1   3 3    2 2
        0   1 0     1   3 3    2 2      0   3 0    0 0

        1   2 2    1 3    3 3    1 2
        1   2 2    3 3    1 3    1 2
     */
    public int numTilings(int n) {
        int[] dp = new int[4];
        dp[3] = 1;
        int mod = (int) 1e9 + 7;
        for (int i = 1; i <= n; i++) {
            int a = dp[0], b = dp[1], c = dp[2], d = dp[3];
            dp[0] = d;
            dp[1] = (a + b) % mod;
            dp[2] = (a + c) % mod;
            dp[3] = (((a + b) % mod + c) % mod + d) % mod;
        }
        return dp[3];
    }

    //矩阵快速幂
    /*
        70.爬楼梯(矩阵乘法)
        1 1 fn      fn+1
        1 0 fn-1    fn
     */
    public int climbStairs(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    static final int MOD = 1000000007;

    public int numTilingsII(int n) {
        int[][] mat = {
                {0, 0, 0, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0},
                {1, 1, 1, 1}
        };
        int[][] matn = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        while (n > 0) {
            if ((n & 1) != 0) {
                matn = mulMatrix(matn, mat);
            }
            mat = mulMatrix(mat, mat);
            n >>= 1;
        }
        return matn[3][3];
    }

    public int[][] mulMatrix(int[][] m1, int[][] m2) {
        int n1 = m1.length, n2 = m2.length, n3 = m2[0].length;
        int[][] res = new int[n1][n3];
        for (int i = 0; i < n1; i++) {
            for (int k = 0; k < n3; k++) {
                for (int j = 0; j < n2; j++) {
                    res[i][k] = (int) ((res[i][k] + (long) m1[i][j] * m2[j][k]) % MOD);
                }
            }
        }
        return res;
    }
}
