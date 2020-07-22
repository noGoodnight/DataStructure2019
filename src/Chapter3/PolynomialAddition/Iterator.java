package Chapter3.PolynomialAddition;

public class Iterator {
    ListNode current;

    public Iterator(ListNode theNode) {
        current = theNode;
    }

    public boolean isPastEnd(){
        return current == null;
    }

    public void advance(){
        if(!isPastEnd()){
            current = current.next;
        }
    }

    public String toString(){
        return current.toString();
    }
}
