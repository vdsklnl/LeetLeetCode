package easy;

/**
 * @author vdsklnl
 * @create 2022-08-10 14:29
 * @description
 */
public class Palindrome {
    public static void main(String[] args) {
        int x = 121232121;
        System.out.println(isPalindrome(x));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        }

        if (x % 10 == 0) {
            return false;
        }

//        StringBuffer stringBuffer = new StringBuffer(String.valueOf(x));
//        return String.valueOf(x).equals(stringBuffer.reverse().toString());

//        String s = String.valueOf(x);
//        int l = s.length() / 2;
//        for (int i = 0; i < l; i++) {
//            if (s.charAt(i) != s.charAt(s.length() - i -1))
//                return false;
//        }
//        return true;

        int num = 0;
        while (true) {
            num = 10 * num + x % 10;
            x /= 10;
            if (num == x)
                return true;
            if (num * 10 > x)
                return false;
            if ((x - num * 10) < 10) {
                return true;
            }
        }
    }
}
