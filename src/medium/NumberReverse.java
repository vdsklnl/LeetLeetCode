package medium;

/**
 * @author vdsklnl
 * @create 2022-08-10 15:59
 * @description
 */
public class NumberReverse {
    public static void main(String[] args) {
        int x = -2147483648;
        System.out.println(reverse(x));
    }

    public static int reverse(int x) {
        if (x >= -9 && x <= 9)
            return x;
        else if (x == Integer.MIN_VALUE)
            return 0;

        boolean flag = false;
        if (x < 0) {
            flag = true;
            x = 0 - x;
        }

        long res = 0;
        while (true) {
            if (x == 0)
                break;

            if (x % 10 == 0) {
                if (res == 0) {
                    x /= 10;
                    continue;
                }
            }

            res = 10 * res + x % 10;
            x /= 10;
        }

        if (res > Integer.MAX_VALUE)
            return 0;

        if (flag) {
            return (int) -res;
        }
        return (int) res;
    }
}
