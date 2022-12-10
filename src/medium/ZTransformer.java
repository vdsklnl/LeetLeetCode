package medium;

/**
 * @author vdsklnl
 * @create 2022-10-05 20:11
 * @description
 */
public class ZTransformer {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        System.out.println(convert(s, 3));
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1)
            return s;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int m = 2 * (numRows - 1 - i), n = 2 * i, count = 0;
            for (int j = i; j < s.length();) {
                if (n == 0) {
                    sb.append(s.charAt(j));
                    j += m;
                } else if (m == 0) {
                    sb.append(s.charAt(j));
                    j += n;
                } else {
                    sb.append(s.charAt(j));
                    count++;
                    if (count % 2 == 1)
                        j += m;
                    else
                        j += n;
                }
            }
        }
        return sb.toString();
    }
}
