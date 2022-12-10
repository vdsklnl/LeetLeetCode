package easy;

/**
 * @author vdsklnl
 * @create 2022-09-24 15:22
 * @description
 */
public class RomanToInt {
    public static void main(String[] args) {

    }

    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int res = 0, n = chars.length;
        for (int i = 0; i < n; i++) {
            switch (chars[i]) {
                case 'M':
                    res += 1000;
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'C':
                    if (i + 1 < n) {
                        char next = chars[i + 1];
                        if (next == 'D' || next == 'M')
                            res -= 100;
                        else
                            res += 100;
                    } else {
                        res += 100;
                    }
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'X':
                    if (i + 1 < n) {
                        char next = chars[i + 1];
                        if (next == 'L' || next == 'C')
                            res -= 10;
                        else
                            res += 10;
                    } else {
                        res += 10;
                    }
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'I':
                    if (i + 1 < n) {
                        char next = chars[i + 1];
                        if (next == 'V' || next == 'X')
                            res -= 1;
                        else
                            res += 1;
                    } else {
                        res += 1;
                    }
                    break;
            }
        }
        return res;
    }
}
