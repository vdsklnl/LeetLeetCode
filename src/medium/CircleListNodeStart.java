package medium;

/**
 * @author vdsklnl
 * @create 2022-08-13 20:31
 * @description
 */
public class CircleListNodeStart {
    public static void main(String[] args) {

    }

    /*
        两个指针，前一指针比后一指针快1，若存在环，入口为x，环长为l，相遇在环第y个
        2（x + y）= x + n * l + y;
        x = n * l - y;
        在环中循环时，两个指针始终会相遇，最多转n圈
        因此，当两个指针相遇时，环入口x + y = l
        设置一个指针，与后一指针一起移动，当二者相遇则表示入口找到
    */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode pre = head;
        ListNode cur = head;

        while (pre.next != null && pre.next.next != null) {
            pre = pre.next.next;
            cur = cur.next;
            if (pre == cur) {
                ListNode node = head;
                while (node != cur) {
                    node = node.next;
                    cur = cur.next;
                }
                return cur;
            }
        }

        return null;
    }
}
