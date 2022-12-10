package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-08-07 19:59
 * @description
 */
public class TwoNumberPlus {

    public static void main(String[] args) {

        /*
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next =  new ListNode(6);
        l2.next.next = new ListNode(4);
        */

        /*
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
         */

        /*
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next =  new ListNode(9);
        l2.next.next = new ListNode(9);
         */

        /*
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next =  new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
         */

        /*
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next =  new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        l2.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next = new ListNode(9);
         */

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next =  new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result = addTwoNumbers(l1, l2);
        List<Integer> list = translate(result);
        System.out.println(Arrays.toString(list.toArray()));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode l1_head = l1;
        ListNode l2_head = l2;
        ListNode l3 = new ListNode();
        ListNode result = l3;
        int count = 0;

        while (l1_head != null || l2_head != null) {
            if (l1_head == null) {
                result.val += l2_head.val;
                count = 0;
                if (result.val >= 10) {
                    count = 1;
                    result.val -= 10;
                }
                l2_head = l2_head.next;
                result.next = new ListNode(count);
                result = result.next;
                continue;
            }
            if (l2_head == null) {
                result.val += l1_head.val;
                count = 0;
                if (result.val >= 10) {
                    count = 1;
                    result.val -= 10;
                }
                l1_head = l1_head.next;
                result.next = new ListNode(count);
                result = result.next;
                continue;
            }
            result.val = result.val + l1_head.val + l2_head.val;
            count = 0;
            if (result.val >= 10) {
                count = 1;
                result.val -= 10;
            }
            result.next = new ListNode(count);
            result = result.next;
            l1_head = l1_head.next;
            l2_head = l2_head.next;
        }

        result = l3;
        while (result.next != null) {

            if (result.next.val == 0 && result.next.next == null) {
                result.next = null;
                break;
            }
            result = result.next;
        }

        return l3;

    }

    public static List<Integer> translate(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode != null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        return list;
    }

}

class ListNode {

    int val;
    ListNode next;

    ListNode() {};

    ListNode(int val) { this.val = val; }

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

}