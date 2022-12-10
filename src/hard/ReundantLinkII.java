package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-09-20 15:45
 * @description 684 并查集经典题目，有向图
 */
public class ReundantLinkII {
    /**
     * 需要考虑删去边后，剩下来是否还是树
     * 在题目中，存在三种情况：
     *      存在入度为2的节点（情况1、2）
     *      存在有向环
     *
     * 情况1、2：删除指向入度为2节点两条边中一条，删除后图仍然形成树，则返回边
     *          从后向前遍历，优先删除后面的边
     * 情况3：明确没有入度为2的情况，那么一定有有向环，找到构成环的边就是要删除的边
     */

    private int n;
    private int[] father;

    public ReundantLinkII() {
        n = 1005;
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    private int find(int x) {
        if (x == father[x]) {
            return father[x];
        }

        return find(father[x]);
    }

    private void join(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        father[y] = x;
    }

    private boolean same(int x, int y) {
        return find(x) == find(y);
    }

    /*
        在模板基础上，添加两个函数：
            isTreeAfterRemoveEdge() 判断删一个边之后是不是树了
            getRemoveEdge 确定图中一定有了有向环，那么要找到需要删除的那条边
     */

    private boolean isTreeAfterRemoveEdge(int[][] edges, int delete) {
        initFather();
        for (int i = 0; i < edges.length; i++) {
            if (i == delete)
                continue;
            if (same(edges[i][0], edges[i][1])) {
                return false;
            }
            join(edges[i][0], edges[i][1]);
        }
        return true;
    }

    private int[] getRemoveEdge(int[][] edges) {
        //在排除情况1、2后，情况3只需要找到最后一条边删除即可
        initFather();
        for (int i = 0; i < edges.length; i++) {
            if (same(edges[i][0], edges[i][1])) {
                return edges[i];
            }
            join(edges[i][0], edges[i][1]);
        }
        return null;
    }

    //在后续判断时，要更新father数组
    private void initFather() {
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] inDegree = new int[n];
        //计算入度
        for (int i = 0; i < edges.length; i++) {
            inDegree[edges[i][1]]++;
        }

        //倒序添加入度为2的节点
        List<Integer> twoInDegree = new ArrayList<>();
        for (int i = edges.length - 1; i >= 0; i--) {
            if (inDegree[edges[i][1]] == 2)
                twoInDegree.add(i);
        }

        //处理情况1、2：删除指向2节点边
        if (!twoInDegree.isEmpty()) {
            //删除一边能成树就返回，不能就返回另一条
            if (isTreeAfterRemoveEdge(edges, twoInDegree.get(0))) {
                return edges[twoInDegree.get(0)];
            } else {
                return edges[twoInDegree.get(1)];
            }
        }

        //处理情况3：找到构成环的边返回
        return getRemoveEdge(edges);
    }
}
