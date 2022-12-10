package medium;

/**
 * @author vdsklnl
 * @create 2022-10-18 15:46
 * @description 3 滑动窗口
 */
public class NoRepeatMaxSubstring {
    public static void main(String[] args) {
        //        String s = "abcabcdbb";
//        String s = "bbbbb";
//        String s = "pwwkew";

//        String s = "au";
//        String s = " ";
//        String s = "cdd";

//        String s = "abba";
//        String s = "dvdf";

        String s = "bbtablud";

        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {

//        char[] c = s.toCharArray();
//        HashMap<Character, Integer> map = new HashMap<>();
//        int count = 0;
//        int size = 0;
//
//        for (int i = 0; i < c.length; i++) {
//            for (int j = 0; j < c.length - i; j++) {
//                if (map.containsKey(c[i + j])) {
//                    count = map.get(c[i + j]);
//                    break;
//                } else {
//                    map.put(c[i + j], j);
//                }
//            }
//            if (map.size() > size) {
//                size = map.size();
//            }
//            map.clear();
//            i += count;
//            count = 0;
//        }
//
//        return size;

        //记录字符上一次出现的位置，总共128个字符
        int[] last = new int[128];
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }

        int res = 0;
        int start = 0; // 窗口开始位置

        for(int i = 0; i < s.length(); i++) {
            // index记录字符在数组位置
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;

    }
}
