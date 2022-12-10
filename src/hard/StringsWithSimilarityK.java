package hard;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author vdsklnl
 * @create 2022-09-22 19:04
 * @description
 */
public class StringsWithSimilarityK {
    public static void main(String[] args) {

    }

    //「理论最小转换次数」: 不同字符串的转换成本之和，每一次交换最多可减少两个不同的字符
    public int f(String s, String temp) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += s.charAt(i) == temp.charAt(i) ? 0:1;
        }
        return res + 1 >> 1; //(res + 1) / 2
    }

    //启发式算法：f(n) = g(n) + h(n) 计算节点优先级
    public int kSimilarity(String s1, String s2) {
        if (s1.equals(s2))
            return 0;
        int n = s1.length();

        Map<String, Integer> map = new HashMap<>();
        //优先选取，综合步数更小的字符串进行变化
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int g1 = f(a, s2), g2 = f(b, s2);
            return g1 + map.get(a) - g2 - map.get(b);
        });
        //对原字符串，h(n) = 0，每次更新新字符串，就增加1
        map.put(s1, 0); pq.add(s1);


        while (!pq.isEmpty()) {
            String poll = pq.poll();
            int step = map.get(poll);
            char[] chars = poll.toCharArray();
            int index = 0;
            //寻找第一个交换点
            while (index < n && chars[index] == s2.charAt(index))
                index++;
            //遍历交换点后的字符
            for (int i = index + 1; i < n; i++) {
                if (chars[i] != s2.charAt(index) || chars[i] == s2.charAt(i))
                    continue;
                swap(chars, i, index);
                //交换获得新串后，还原字符串
                String newStr = String.valueOf(chars);
                swap(chars, i, index);
                /*
                    在当前这一步，如果新串已被添加且移动步数h更小，则此步无意义
                    在使用当前状态（字符串）poll 拓展新状态（字符串）nstr 时，
                    只拓展能够减少不同字符数量的方案，从而收窄搜索空间。
                */
                if (map.containsKey(newStr) && map.get(newStr) <= step + 1)
                    continue;
                //若子串相同，直接返回上一步 + 1
                if (s2.equals(newStr))
                    return step + 1;
                map.put(newStr, step + 1);
                pq.add(newStr);
            }
        }

        return -1;
    }

    public void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }
}
