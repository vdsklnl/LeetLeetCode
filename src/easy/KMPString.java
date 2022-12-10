package easy;

/**
 * @author vdsklnl
 * @create 2022-08-15 17:29
 * @description
 */
public class KMPString {
    public static void main(String[] args) {

    }

    //KMP解决重复子串，next数组
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();

        int[] next = new int[len];
        next[0] = 0; //字符串长度为一，匹配0
        for (int i = 1, j = 0; i < next.length; i++) {
            //sub.charAt(i) != sub.charAt(j)，则需要一直寻找
            //直至满足sub.charAt(i) == sub.charAt(j)或者j = 0
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = next[j - 1];

            if (s.charAt(i) == s.charAt(j))
                //满足时，表示部分匹配值加一
                j++;

            next[i] = j;
        }

        //判断是否为重复子串，最后值不为0，且len是重复子串的整倍数（next为0）
        if (next[len - 1] > 0 && len % (len - next[len]) == 0)
            return true;
        return false;
    }

    public int strStr(String haystack, String needle) {
        int[] next = kmpNext(needle);
        for (int i = 0, j = 0; i < haystack.length(); i++) {
            //KMP算法核心，使用next更新
            while (j > 0 && haystack.charAt(i) != needle.charAt(j))
                j = next[j - 1];

            if (haystack.charAt(i) == needle.charAt(j))
                j++;
            if (j == needle.length())
                return i - j + 1; //i的增加要慢一步，所以加一
        }
        return -1;
    }

    //求出字串部分匹配值表
    public int[] kmpNext(String sub) {
        int[] next = new int[sub.length()];
        next[0] = 0; //字符串长度为一，匹配0
        for (int i = 1, j = 0; i < next.length; i++) {
            //sub.charAt(i) != sub.charAt(j)，则需要一直寻找
            //直至满足sub.charAt(i) == sub.charAt(j)或者j = 0
            while (j > 0 && sub.charAt(i) != sub.charAt(j))
                j = next[j - 1];

            if (sub.charAt(i) == sub.charAt(j))
                //满足时，表示部分匹配值加一
                j++;

            next[i] = j;
        }
        return next;
    }
}
