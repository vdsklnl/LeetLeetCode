package easy;

/**
 * @author vdsklnl
 * @create 2022-08-14 16:12
 * @description
 */
public class ReverseString {
    public static void main(String[] args) {
        String s = "abcdefg";
        System.out.println(reverseString(s, 2));
    }

    //不用数组
    public String reverseStr(String s, int k) {
        StringBuffer buffer = new StringBuffer();
        int n = s.length();
        int start = 0;

        while (start < n) {
            //找到k处和2K处
            StringBuffer temp = new StringBuffer();
            int first = (start + k > n) ? n:start + k;
            int second = (start + 2 * k > n) ? n:start + 2 * k;

            //将start~k处反转
            temp.append(s.substring(start, first));
            buffer.append(temp.reverse());

            //将剩余部分加入
            if (first < second) {
                buffer.append(s.substring(first, second));
            }
            start += 2 * k;
        }

        return buffer.toString();
    }

    //使用数组
    public static String reverseString(String s, int k) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i += 2 * k) {
            if (i + k <= chars.length) {
                reverse(chars, i, i + k - 1);
                continue;
            }
            //反转剩余小于k的部分
            reverse(chars, i, chars.length - 1);
        }
        return new String(chars);
    }

    //交换元素
    public static void reverse(char[] chars, int i, int j) {
        char temp;
        for (; i < j; i++, j--) {
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }
}
