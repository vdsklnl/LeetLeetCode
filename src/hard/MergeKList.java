package hard;

/**
 * @author vdsklnl
 * @create 2022-10-24 13:17
 * @description 23
 */
public class MergeKList {
    public static void main(String[] args) {
        int x = 0, y = 1;
        swap(x, y);
        System.out.println(x);
    }

    public static void swap(int i, int j) {
        int temp = i;
        i = j;
        j = temp;
    }

    //顺序合并
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        for (int i = 0; i < lists.length; i++) {
            res = mergeTwoLists(res, lists[i]);
        }
        return res;
    }

    //分治合并
    public ListNode mergeKListsII(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right)
            return lists[left];
        if (left > right)
            return null;

        int mid = (left + right) >> 1;
        return mergeTwoLists(merge(lists, left, mid), merge(lists, mid + 1, right));
    }

    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null)
            return head1 == null ? head2:head1;

        ListNode head = new ListNode(0);
        ListNode res = head, a = head1, b = head2;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                res.next = a;
                a = a.next;
            } else {
                res.next = b;
                b = b.next;
            }
            res = res.next;
        }
        res.next = (a == null) ? b:a;
        return head.next;
    }
}
