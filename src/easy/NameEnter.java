package easy;

/**
 * @author vdsklnl
 * @create 2022-08-22 19:55
 * @description
 */
public class NameEnter {
    public static void main(String[] args) {
        System.out.println(isLongPressedName("rick", "kric"));
    }

    public static boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        int n = name.length(), t = typed.length();

        while (i < n && j < t) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else {
                if (j == 0)
                    return false;
                while (j < t - 1 && typed.charAt(j) == typed.charAt(j - 1)) {
                    j++;
                }
                if (name.charAt(i) == typed.charAt(j)) {
                    i++;
                    j++;
                } else
                    return false;
            }
        }

        //name没有匹配完
        if (i < n)
            return false;
        //typed没有匹配完
        while (j < t) {
            if (typed.charAt(j - 1) != typed.charAt(j))
                return false;
            j++;
        }

        return true;
    }
}
