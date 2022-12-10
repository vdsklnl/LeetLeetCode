package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-08-10 15:31
 * @description
 */
public class MergeTwoLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(3);
        l2.next =  new ListNode(5);
        l2.next.next = new ListNode(6);

        ListNode result = mergeTwoLists(l1, l2);
        List<Integer> list = translate(result);
        System.out.println(Arrays.toString(list.toArray()));
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;

        ListNode list3 = new ListNode();

        ListNode l1_head = list1;
        ListNode l2_head = list2;
        ListNode result = list3;

        while (l1_head != null || l2_head != null) {
            if (l1_head == null) {
                result.val = l2_head.val;
                result.next = l2_head.next;
                break;
            }

            if (l2_head == null) {
                result.val = l1_head.val;
                result.next = l1_head.next;
                break;
            }

            if (l1_head.val <= l2_head.val) {
                result.val = l1_head.val;
                result.next = new ListNode();
                result = result.next;
                l1_head = l1_head.next;
            } else {
                result.val = l2_head.val;
                result.next = new ListNode();
                result = result.next;
                l2_head = l2_head.next;
            }
        }

        return list3;
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
