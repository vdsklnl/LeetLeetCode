package hard;

/**
 * @author vdsklnl
 * @create 2022-08-28 18:53
 * @description
 */
public class OneExistsNumbers {
    public static void main(String[] args) {

    }

    public static int countDigitOne(int n) {
        int res = 0;
        for (long i = 1; i <= n; i *= 10) {
            /* 对n = 1234567，考虑百位数，每1000个数，出现100次
             * 第一部分 = n / 1000 * 100;
             * 第二部分看x = 567，小于100，为0，[100，200) x - 100 + 1，200以后均为100
             * 第二部分 = min(max((x - 100 + 1), 0), 100);
             */
            res += ((n / (10 * i)) * i + Math.min(Math.max((n % (10 * i) - i + 1), 0), i));
        }
        return res;
    }
}
