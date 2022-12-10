package hard;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2022-09-20 10:45
 * @description 127 图论，BFS：寻找最短路径，能找到则最短
 *              有点类似二叉树层序遍历
 */
public class WordSolitaire {
    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        System.out.println(ladderLength("hit", "cog", wordList));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //将List转为Set加快处理速度
        HashSet<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return 0;

        Queue<String> que = new LinkedList<>();
        que.offer(beginWord);
        HashMap<String, Integer> map = new HashMap<>(); //记录单词对应路径长度
        map.put(beginWord, 1);

        while (!que.isEmpty()) {
            //取出队头单词
            String word = que.poll();
            int path = map.get(word);
            //转换为字符数组方便替换
            for (int i = 0; i < word.length(); i++) {
                char[] chars = word.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    chars[i] = j;
                    String newWord = String.valueOf(chars);
                    if (endWord.equals(newWord))
                        return path + 1;
                    if (wordSet.contains(newWord) && !map.containsKey(newWord)) {
                        map.put(newWord, path + 1);
                        que.offer(newWord);
                    }
                }
            }
        }

        //循环结束，表示没有路径
        return 0;
    }
}
