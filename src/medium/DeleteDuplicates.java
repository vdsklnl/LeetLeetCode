package medium;

import java.util.List;

/**
 * @author vdsklnl
 * @create 2022-08-10 22:04
 * @description
 */
public class DeleteDuplicates {
    public static void main(String[] args) {

    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode listNode = head.next;
        head = delete(head, listNode);
        if (head == null || head.next == null)
            return head;
        ListNode head_n = head;
        ListNode res = head.next;
        listNode = res.next;

        while (listNode != null) {
            res = delete(res, listNode);
            if (res == null) {
                head_n.next = null;
                break;
            } else {
                head_n.next = res;
                head_n = head_n.next;
                res = res.next;
                if (res == null) {
                    break;
                }
                listNode = res.next;
            }
            if (listNode == null) {
                break;
            }
        }

        return head;
    }

    public static ListNode delete(ListNode head, ListNode listNode) {
        while (head.val == listNode.val) {
            head = head.next;
            listNode = listNode.next;
            if (listNode == null)
                return null;
            if (head.val != listNode.val) {
                head = head.next;
                listNode = listNode.next;
                if (listNode == null)
                    return head;
            }
        }
        return head;
    }
}
