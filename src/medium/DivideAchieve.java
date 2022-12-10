package medium;

/**
 * @author vdsklnl
 * @create 2022-10-12 16:28
 * @description 不能使用乘除取模，使用位运算、二分查找，快速加模拟乘求值
 */
public class DivideAchieve {
    public static void main(String[] args) {
        System.out.println(divide(Integer.MIN_VALUE, -3));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(1).append(2);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        System.out.println(stringBuilder.toString());
    }

    public static int clumsy(int n) {
        int rem = n % 4, res = 0;
        if (rem == 3)
            res = -6;
        else if (rem == 2)
            res = -2;
        else if (rem == 1)
            res = -1;
        if (n == rem)
            return -res;

        res = res + n * (n - 1) / (n - 2) + n - 3;
        n -= 4;
        if (n == 0)
            return res;
        while (n > rem) {
            res -= 4;
            n -= 4;
        }
        if (rem == 0)
            res--;
        return res;
    }

    public static int divide(int dividend, int divisor) {
        if (divisor == 1)
            return dividend;

        //判断被除数最小
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1)
                return Integer.MAX_VALUE;
        }
        //判断除数最小
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1:0;
        }
        //判断被除数为0
        if (divisor == 0 || dividend == 0)
            return 0;

        //将两个数全转化为负数防止数据越界
        boolean flag = false;
        if (dividend > 0) {
            dividend = -dividend;
            flag = !flag;
        }
        if (divisor > 0) {
            divisor = -divisor;
            flag = !flag;
        }

        //二分查找
        int res = 0;
        int left = 1, right = Integer.MAX_VALUE;
        //迭代靠近，找到满足res * divisor <= dividend && (res + 1) * divisor <= dividend
        //取消余数，left更新时更新结果
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            boolean board = quickAdd(dividend, divisor, mid);
            if (board) {
                res = mid;
                //防止溢出
                if (mid == Integer.MAX_VALUE)
                    break;
                left = mid + 1;
            } else
                right = mid - 1;
        }
        return flag ? res:-res;
    }

    //快速乘算法，乘以二进制数各位相加，此题dividend || divisor均为负数
    public static boolean quickAdd(int dividend, int divisor, int res) {
        //判断res * divisor >= dividend是否成立，res作为乘数转二进制
        int result = 0, add = divisor;
        while (res != 0) {
            //取最后一位为1
            if ((res & 1) == 1) {
                //保证result + add >= dividend
                if (result < dividend - add)
                    return false;
                result += add;
            }
            if (res != 1) {
                //二进制加法，每位相加数为之前两倍，但不能小于dividend
                //不用减法会溢出判断失败
                if (add < dividend - add) {
                    return false;
                }
                add += add;
            }
            res >>= 1;
        }
        //此时表示乘完没越界，res偏小，false表示偏大
        return true;
    }
}
