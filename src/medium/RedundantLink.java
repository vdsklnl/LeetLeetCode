package medium;

import java.util.Arrays;

/**
 * @author vdsklnl
 * @create 2022-09-20 15:20
 * @description 684 并查集经典题目，无向图
 */
public class RedundantLink {
    //模板
    private int n; //顶点数
    private int[] father; //记录顶点父节点

    public RedundantLink() {
        n = 1005;
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    //find()函数：查找根节点
    public int find(int x) {
        if (x == father[x]) {
            return father[x];
        }

        return find(father[x]);
    }

    //join()函数：合并两条子链接
    public void join(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        father[y] = x;
    }

    //same()函数：判断两个节点根节点是否相同
    public boolean same(int x, int y) {
        return find(x) == find(y);
    }

    public int[] findRedundantConnection(int[][] edges) {
        //成环的最后一条边是需要删除的冗余连接
        for (int i = 0; i < edges.length; i++) {
            if (find(edges[i][0]) == find(edges[i][1])) {
                return edges[i];
            } else {
               join(edges[i][0], edges[i][1]);
            }
        }
        //未找到
        return null;
    }

    public static void main(String[] args) {
        RedundantLink rl = new RedundantLink();
        int[][] edges = {{1,2},
                         {1,3},
                         {2,3}};
        int[] redundantConnection = rl.findRedundantConnection(edges);
        System.out.println(Arrays.toString(redundantConnection));
    }
}
