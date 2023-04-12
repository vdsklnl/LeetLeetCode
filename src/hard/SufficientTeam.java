package hard;

import java.util.*;

/**
 * @author vdsklnl
 * @create 2023-04-08 15:28
 * @description 1125 动态规划 + 状态优化
 */
public class SufficientTeam {
    public static void main(String[] args) {

    }

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length, m = people.size();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(req_skills[i], i);
        }
        //使用dp[i]来记录已经满足的技能数 位或 |
        int[] dp = new int[1 << n];
        Arrays.fill(dp, m);
        dp[0] = 0;
        //记录加入技能和人的数组
        int[] pre_skills = new int[1 << n];
        int[] pre_people = new int[1 << n];
        for (int i = 0; i < m; i++) {
            List<String> list = people.get(i);
            int cur_skills = 0;
            for (String skill:list) {
                cur_skills |= (1 << map.get(skill));
            }
            for (int pre = 0; pre < (1 << n); pre++) {
                int cur = pre | cur_skills;
                if (dp[cur] > dp[pre] + 1) {
                    dp[cur] = dp[pre] + 1;
                    //相当于把对应技能和人编号记录
                    pre_skills[cur] = pre;
                    pre_people[cur] = i;
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        int skills = (1 << n) - 1;
        while (skills > 0) {
            res.add(pre_people[skills]);
            skills = pre_skills[skills];
        }

        return res.stream().mapToInt(i -> i).toArray();
    }
}
