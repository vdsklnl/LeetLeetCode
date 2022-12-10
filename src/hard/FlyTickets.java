package hard;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2022-09-04 17:45
 * @description 不是太懂
 */
public class FlyTickets {
    public static void main(String[] args) {

    }

    //回溯法
    Deque<String> result;
    //存放机票信息，防止闭环，出发机场-到达机场，班次->排序
    Map<String, Map<String, Integer>> map;

    public List<String> findItinerary(List<List<String>> tickets) {
        //将机票按字母排序
        map = new HashMap<>();
        result = new LinkedList<>();
        for (List<String> ticket:tickets) {
            Map<String, Integer> temp;
            if (map.containsKey(ticket.get(0))) {
                temp = map.get(ticket.get(0));
                temp.put(ticket.get(1), temp.getOrDefault(ticket.get(1), 0) + 1);
            } else {
                //TreeMap 自动排序
                temp = new TreeMap<>();
                temp.put(ticket.get(1), 1);
            }
            map.put(ticket.get(0), temp);
        }
        result.add("JFK");
        backtracking(tickets.size());
        return new ArrayList<>(result);
    }

    public boolean backtracking(int ticketsSize) {
        if (result.size() == ticketsSize + 1) {
            return true;
        }

        String last = result.getLast();
        if (map.containsKey(last)) {
            for(Map.Entry<String, Integer> target : map.get(last).entrySet()){
                int count = target.getValue();
                if(count > 0){
                    result.add(target.getKey());
                    target.setValue(count - 1);
                    if(backtracking(ticketsSize))
                        return true;
                    result.removeLast();
                    target.setValue(count);
                }
            }
        }

        return false;
    }
}
