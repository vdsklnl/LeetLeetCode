package hard;

/**
 * @author vdsklnl
 * @create 2022-10-22 16:14
 * @description 25 K个一组翻转链表
 */
public class KthGroupsReverseListNode {
    public static void main(String[] args) {

    }

    //先分割为k个一组，再合并
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = new ListNode(0, head);
        ListNode start = newHead;
        ListNode end = newHead;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null)
                break;
            ListNode top = start.next;
            ListNode tail = end.next;
            end.next = null;
            start.next = reverse(top);
            //此时top为第一段最后一个
            top.next = tail;
            start = top;
            end = start;
        }
        return newHead.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) { this.val = val; }

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
