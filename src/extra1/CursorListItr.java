package extra1;

public class CursorListItr {
    CursorListItr(int theNode) {
        current = theNode;
    }

    public boolean isPastEnd() {
        return current == 0;
    }

    public Object retrieve() {
        return isPastEnd() ? null :
                CursorList.cursorSpace[current].element;
    }

    public void advance() {
        if (!isPastEnd())
            current = CursorList.cursorSpace[current].next;
    }

    int current;
}
