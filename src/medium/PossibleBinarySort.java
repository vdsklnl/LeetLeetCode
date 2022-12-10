package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-10-16 16:50
 * @description 886 反向点 + 查并集 || 染色法判断二分图(DFS)
 */
public class PossibleBinarySort {
    public static void main(String[] args) {
//        System.out.println(3^3);
        String s = "  :LOAOfaf";
        System.out.println(s.toLowerCase().trim());
    }

    /*
    int[] father = new int[4010];

    public int find(int x) {
        if (father[x] != x)
            father[x] = find(father[x]);
        return father[x];
    }

    public void join(int x, int y) {
        father[find(x)] = father[find(y)];
    }

    public boolean same(int x, int y) {
        return find(x) == find(y);
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        for (int i = 1; i <= 2 * n; i++) {
            father[i] = i;
        }
        //对每一个点，设置反向点，a 与 a + n在不同组
        //对于a，b：a与b+n同组，b与a+n同组，当a b同组时，不能成功分组
        for (int[] dislike:dislikes) {
            int a = dislike[0], b = dislike[1];
            if (same(a, b))
                return false;
            join(a, b + n);
            join(a + n, b);
        }
        return true;
    }
     */

    /*
        用「染色法」来解决问题：假设第一组中的人是红色，第二组中的人为蓝色。我们依次遍历每一个人，如
        果当前的人没有被分组，就将其分到第一组（即染为红色），那么这个人不喜欢的人必须分到第二组中（
        染为蓝色）。然后任何新被分到第二组中的人，其不喜欢的人必须被分到第一组，依此类推。如果在染色
        的过程中存在冲突，就表示这个任务是不可能完成的，否则说明染色的过程有效（即存在合法的分组方案）
     */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        //使用0 1进行分组识别
        int[] color = new int[n + 1];
        //记录讨厌关系
        List<Integer>[] hate = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            hate[i] = new ArrayList<>();
        }
        for (int[] dislike:dislikes) {
            hate[dislike[0]].add(dislike[1]);
            hate[dislike[1]].add(dislike[0]);
        }

        for (int i = 1; i <= n; i++) {
            if (color[i] == 0 && !dfs(i, 1, color, hate))
                return false;
        }

        return true;
    }

    //判断能否将所有讨厌的人分到同一组
    public boolean dfs(int node, int nowColor, int[] color, List<Integer>[] hate) {
        color[node] = nowColor;
        for (int hateNode:hate[node]) {
            if (color[hateNode] != 0 && color[node] == color[hateNode])
                return false;
            //或运算 -> 0 1两组
            if (color[hateNode] == 0 && !dfs(hateNode, 3 ^ nowColor, color, hate))
                return false;
        }
        return true;
    }
}
