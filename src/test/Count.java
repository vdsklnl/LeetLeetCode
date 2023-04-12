package test;

/**
 * @author vdsklnl
 * @create 2023-03-28 20:06
 * @description
 */
public class Count {
    public static void main(String[] args) {
        Count count = new Count();
        System.out.println(count.function(100000, 222, "WRRRRR"));
    }

    char[] chars;
    boolean[] booleans;

    public int function(int n, int p, String str) {
         chars = String.valueOf(n).toCharArray();
         booleans = new boolean[chars.length];
         int begin = -1;
         for (int i = 0; i < chars.length; i++) {
             if ('R' == (str.charAt(i))) {
                 booleans[i] = true;
                 if (i == 0)
                     chars[i] = '1';
                 else
                     chars[i] = '0';
                 if (begin == -1)
                     begin = i;
             }
         }
         int index = begin;
         while (Integer.parseInt(String.valueOf(chars)) < p) {
             if (booleans[begin] && chars[begin] < '9') {
                 chars[begin]++;
             } else {
                 begin++;
             }
         }
         if (Integer.parseInt(String.valueOf(chars)) < p)
             return -1;
         backtracking(index, p);
         int res = Integer.parseInt(String.valueOf(chars));
         return res % p == 0 ? res:-1;
    }

    public void backtracking(int i, int p) {
        if (!booleans[i])
            return;
        char start = chars[i];
        for (char j = start; j <= '9'; j++) {
            chars[i] = j;
            if (i + 1 < chars.length) {
                backtracking(i + 1, p);
            }
            if (Integer.parseInt(String.valueOf(chars)) % p == 0)
                return;
            if (i + 1 < chars.length)
                chars[i + 1] = '0';
        }

    }
}
