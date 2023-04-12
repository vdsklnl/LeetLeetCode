package hard;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2023-02-08 20:43
 * @description 1819 问题转换
 */
public class CountDifferentSubsequenceGCDs {
    public static void main(String[] args) {
        System.out.println(countDifferentSubsequenceGCDs(new int[]{6, 10, 3}));
    }

    //不同子序列最大公约数，将问题转化为枚举数值，只要能被整除，则一定是某一个子序列最大公约数
    public static int countDifferentSubsequenceGCDs(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        boolean[] res = new boolean[max + 1];
        for (int num:nums) {
            res[num] = true;
        }
        int ans = 0;
        for (int i = 1; i <= max; i++) {
            int sub = 0;
            //倍数增加，res[j] = true时进行判断，表示此数能被整除
            for (int j = i; j <= max; j += i) {
                if (res[j]) {
                    //首次被记录，只有原数组中存在，才直接记录，否则在第二次存在时记录
                    if (sub == 0)
                        sub = j;
                    else
                        sub = gcd(j, sub);
                    if (sub == i) {
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a:gcd(b ,a % b);
    }
}
