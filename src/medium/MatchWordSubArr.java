package medium;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author vdsklnl
 * @create 2022-11-17 19:50
 * @description 792 字典树
 */
public class MatchWordSubArr {
    public static void main(String[] args) {

    }

    public int numMatchingSubseq(String s, String[] words) {
        //遍历s，每个字符与words进行匹配
        Queue<int[]>[] queue = new Queue[26];
        for (int i = 0; i < 26; i++) {
            queue[i] = new ArrayDeque<>();
        }
        //将每个word与最初指针放入队列
        for (int i = 0; i < words.length; i++) {
            queue[words[i].charAt(0) - 'a'].offer(new int[] {i, 0});
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int len = queue[ch - 'a'].size();
            while (len-- > 0) {
                int[] poll = queue[ch - 'a'].poll();
                if (poll[1] == words[poll[0]].length() - 1)
                    res++;
                else {
                    poll[1]++;
                    queue[words[poll[0]].charAt(poll[1]) - 'a'].offer(poll);
                }
            }
        }
        return res;
    }
}
