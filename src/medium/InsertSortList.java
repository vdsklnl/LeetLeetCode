package medium;

/**
 * @author vdsklnl
 * @create 2022-10-17 15:44
 * @description
 */
public class InsertSortList {
    public static void main(String[] args) {

    }

    public ListNode insertionSortList(ListNode head) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode pre = head, cur = head.next;
        while (cur != null) {
            if (pre.val <= cur.val) {
                pre = pre.next;
            } else {
                ListNode temp = newHead;
                while (temp.next.val <= cur.val) {
                    temp = temp.next;
                }
                pre.next = cur.next;
                cur.next = temp.next;
                temp.next = cur;
            }
            cur = pre.next;
        }
        return newHead.next;
    }
}
