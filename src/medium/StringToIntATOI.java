package medium;

/**
 * @author vdsklnl
 * @create 2022-09-19 15:50
 * @description
 */
public class StringToIntATOI {
    public static void main(String[] args) {
        System.out.println(myAtoi("9223372036854775808"));
    }

    public static int myAtoi(String s) {
        s = s.trim();
        if (s.equals(""))
            return 0;
        char[] chars = s.toCharArray();

        boolean sign = chars[0] != '-';
        long num = 0;

        if (chars[0] >= '0' && chars[0] <= '9') {
            num = chars[0] - 48;
        } else if (chars[0] != '+' && chars[0] != '-') {
            return 0;
        }

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                num = num * 10 + chars[i] - 48;
                if (sign && num > Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
                if (!sign && num > Integer.MAX_VALUE + 1l)
                    return Integer.MIN_VALUE;
            } else {
                break;
            }
        }

        if (!sign)
            num = - num;
        return (int) num;
    }
}
