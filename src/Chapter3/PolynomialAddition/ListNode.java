package Chapter3.PolynomialAddition;

public class ListNode implements Cloneable{
    int exponent;
    int significand;
    ListNode next;

    public ListNode() {
        next = null;
    }

    public ListNode(int exponent, int significand) {
        this.exponent = exponent;
        this.significand = significand;
        next = null;
    }

    public String toString() {
        if (significand == 0) {
            return "";
        }

        if (exponent == 0) {
            return "(" + significand + ")";
        } else {
            return "(" + significand + "x^" + exponent + ")";
        }
    }

    public Object clone() throws CloneNotSupportedException {
        ListNode result = (ListNode) super.clone();
        try {
            result.next = this.next.cloneNext();
        }catch (Exception e){
            result.next = null;
        }
        return result;
    }
    public ListNode cloneNext() throws CloneNotSupportedException {
        ListNode result = (ListNode) super.clone();
        return result;
    }//避免疯狂递归又写了一个方法
}
