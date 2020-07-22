package extra1;

public class CursorNode {
    CursorNode(Object theElement) {
        this(theElement, 0);
    }

    CursorNode(Object theElement, int n) {
        element = theElement;
        next = n;
    }

    Object element;
    int next;
}
