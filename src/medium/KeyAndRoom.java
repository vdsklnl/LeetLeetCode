package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-09-20 10:28
 * @description 841 图论，dfs，bfs
 */
public class KeyAndRoom {
    public static void main(String[] args) {

    }

    //搜寻能否进入每一个房间，而不是寻找路线，不用回溯
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        //标记每个房间是否被访问过
        List<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            visited.add(false);
        }

        DFS(0, rooms, visited);

        for (boolean v:visited) {
            if (!v)
                return false;
        }

        return true;
    }

    public static void DFS(int key, List<List<Integer>> rooms, List<Boolean> visited) {
        //key表示房间号可以开启，使用钥匙
        if (visited.get(key)) {
            //已被访问过，则返回
            return;
        }
        //设置本次房间已访问
        visited.set(key, true);

        for (int k:rooms.get(key)) {
            DFS(k, rooms, visited);
        }
    }
}
