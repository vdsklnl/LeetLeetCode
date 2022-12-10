package medium;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2022-10-05 10:58
 * @description 811
 */
public class CountSubWebsites {
    public static void main(String[] args) {
        String[] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        List<String> list = subdomainVisits(cpdomains);
        for (String s:list) {
            System.out.println(s);
        }
    }

    public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (String cpdomain:cpdomains) {
            String[] strs = cpdomain.split(" ");
            int count = Integer.parseInt(strs[0]);
            map.put(strs[1], map.getOrDefault(strs[1], 0) + count);
            String[] sites = strs[1].split("\\.");
            int len = sites.length;
            map.put(sites[len - 1], map.getOrDefault(sites[len - 1], 0) + count);
            if (len == 3) {
                sb.append(sites[1]).append(".").append(sites[2]);
                map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + count);
                sb.delete(0, sb.length());
            }
        }
        List<String> res = new ArrayList<>();
        for (String key:map.keySet()) {
            sb.append(map.get(key)).append(" ").append(key);
            res.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return res;
    }
}
