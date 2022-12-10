package hard;

/**
 * @author vdsklnl
 * @create 2022-11-09 15:21
 * @description 44 DP
 */
public class WildcardMatching {
    public static void main(String[] args) {

    }

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        //dp[i][j]表示到s的第i个字符与p的第j个字符匹配情况
        //dp[0][0] = true，dp[i][0] = false，dp[0][j]情况中，只有*可以匹配空字符，其余情况为false
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i - 1) == '*')
                dp[0][i] = true;
            else
                break;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //如果p.j为'*'，则该处仅与前面匹配情况有关，*可以使用也可以不使用
                if (p.charAt(j - 1) == '*')
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                //如果为？或者两者相等，该处与两字符串前一字符匹配程度有关
                else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }

    //贪心
    public boolean isMatchII(String s, String p) {
        //p期望格式为*u1*u2*...*un*，先对头尾不为*情况进行处理
        //匹配p末尾子串直至p为*，不匹配则不成功
        int sR = s.length(), pR = p.length();
        while (sR > 0 && pR > 0 && p.charAt(pR - 1) != '*') {
            if (p.charAt(pR - 1) == '?' || p.charAt(pR - 1) == s.charAt(sR - 1)) {
                pR--;
                sR--;
            } else
                return false;
        }
        //如果p匹配完成，但s没有，则不成功
        if (pR == 0)
            return sR == 0;

        //从头开始匹配，当匹配子字符串完成时，sl，pl更新，记录结果
        //否则返回sl，pl重新开始匹配，此时p对应*多匹配一个，增加s的位置
        //回溯时，如果sl = -1，表示回溯失败，返回false
        //sl，pl = -1，表示p头部不为*，需要完全匹配
        int sI = 0, pI = 0;
        int sL = -1, pL = -1;
        while (sI < sR && pI < pR) {
            if (p.charAt(pI) == '*') {
                pI++;
                sL = sI;
                pL = pI;
            } else if (p.charAt(pI) == '?' || p.charAt(pI) == s.charAt(sI)) {
                pI++;
                sI++;
            } else if (sL != -1 && sL + 1 < sR) {
                sL++;
                sI = sL;
                pI = pL;
            } else
                return false;
        }

        //循环结束时，s匹配完成，如果此时p还没匹配完，剩余p应全为*
        for (int i = pI; i < pR; i++) {
            if (p.charAt(i) != '*')
                return false;
        }
        return true;
    }
}
