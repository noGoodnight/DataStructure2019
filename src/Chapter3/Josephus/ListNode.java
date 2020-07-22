package Chapter3.Josephus;

public class ListNode {
    int element;
    ListNode next;

    public ListNode() {
        element = 0;
        next = this;
    }

    public ListNode(int element) {
        this.element = element;
    }

    public String toString() {
        return "" + element;
    }
}
