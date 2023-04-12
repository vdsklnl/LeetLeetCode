package medium;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2023-02-06 19:34
 * @description 1129 BFS
 */
public class ShortestAlternatingPaths {
    public static void main(String[] args) {
        String[] name = {"alice","alice","alice","bob","bob","bob","bob"};
        String[] time = {"12:01","12:00","18:00","21:00","21:20","21:30","23:00"};
        alertNames(name, time);
    }

    //1604 HashMap + 排序
    public static List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> map = new HashMap<>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            String name = keyName[i], time = keyTime[i];
            map.putIfAbsent(name, new ArrayList<>());
            int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            int minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
            map.get(name).add(hour * 60 + minute);
        }
        List<String> res = new ArrayList<>();
        Set<String> nameSet = map.keySet();
        for (String name:nameSet) {
            List<Integer> list = map.get(name);
            Collections.sort(list);
            for (int i = 2; i < list.size(); i++) {
                if (list.get(i) - list.get(i - 2) <= 60) {
                    res.add(name);
                    break;
                }
            }
        }
        Collections.sort(res);
        return res;
    }

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        //BFS
        //记录当前位置点可以到达下一点位置集合，0表示红边，1表示蓝边
        List<Integer>[][] list = new List[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                list[i][j] = new ArrayList<>();
            }
        }
        for (int[] re:redEdges) {
            list[0][re[0]].add(re[1]);
        }
        for (int[] be:blueEdges) {
            list[1][be[0]].add(be[1]);
        }
        //表示由相应类型到点距离
        int[][] dis = new int[2][n];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        dis[0][0] = 0; dis[1][0] = 0;
        Queue<int[]> que = new LinkedList<>();
        //前一项表示类型，后一项表示当前点位
        que.offer(new int[] {0, 0});
        que.offer(new int[] {1, 0});
        while (!que.isEmpty()) {
            int[] pair = que.poll();
            int t = pair[0], p = pair[1];
            for (int next:list[1 - t][p]) {
                //表示该项在前面被更新过，一定优于当前数值
                if (dis[1 - t][next] != Integer.MAX_VALUE)
                    continue;
                dis[1 - t][next] = dis[t][p] + 1;
                que.offer(new int[] {1 - t, next});
            }
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = Math.min(dis[0][i], dis[1][i]);
            if (res[i] == Integer.MAX_VALUE)
                res[i] = -1;
        }
        return res;
    }
}
