package Chapter3.Josephus;

public class LoopIterator {
    public ListNode current;

    public LoopIterator(ListNode theNode) {
        current = theNode;
    }

    public boolean isEnd() {
        return current.equals(current.next);
    }

    public void advance() {
        if (!isEnd()) {
            current = current.next;
        }
    }

    public String toString() {
        return "Iterator " + current.toString();
    }
}
