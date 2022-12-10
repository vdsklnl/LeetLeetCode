package medium;

import java.util.HashMap;

/**
 * @author vdsklnl
 * @create 2022-08-22 15:48
 * @description
 */
public class ReorderList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        reorderList(head);
    }

    public static void reorderList(ListNode head) {
        //将链表分为两部分，反转后一部分，进行链表相加
        //求出链表中点，slow.next 为后一链表起点
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode left = head, right = slow.next;
        slow.next = null;

        //反转右链表
        right = reverse(right);

        //右链表长度小于等于左链表
        while (right != null) {
            ListNode curleft = left.next;
            left.next = right;
            left = curleft;

            ListNode curright = right.next;
            right.next = left;
            right = curright;
        }
    }

    public static ListNode reverse(ListNode head) {
        ListNode node = new ListNode(0);
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = node.next;
            node.next = cur;
            cur = next;
        }
        return node.next;
    }
}
