package extra1;

public class CursorList {
    /**
     * copy from PDF, no meaning
     * @return
     */

    private static int alloc() {
        int p = cursorSpace[0].next;
        cursorSpace[0].next = cursorSpace[p].next;
        if (p == 0)
            throw new OutOfMemoryError();
        return p;
    }

    private static void free(int p) {
        cursorSpace[p].element = null;
        cursorSpace[p].next = cursorSpace[0].next;
        cursorSpace[0].next = p;
    }

    public CursorList() {
        header = alloc();
        cursorSpace[header].next = 0;
    }

    public boolean isEmpty() {
        return cursorSpace[header].next == 0;
    }

    public void makeEmpty() {
        while (!isEmpty())
            remove(first().retrieve());
    }

    public CursorListItr zeroth() {
        return new CursorListItr(header);
    }

    public CursorListItr first() {
        return new CursorListItr(cursorSpace[header].next);
    }

    public CursorListItr find(Object x) {
        int itr = cursorSpace[header].next;
        while (itr != 0 && !cursorSpace[itr].element.equals(x))
            itr = cursorSpace[itr].next;
        return new CursorListItr(itr);
    }

    public void insert(Object x, CursorListItr p) {
        if (p != null && p.current != 0) {
            int pos = p.current;
            int tmp = alloc();
            cursorSpace[tmp].element = x;
            cursorSpace[tmp].next = cursorSpace[pos].next;
            cursorSpace[pos].next = tmp;
        }
    }

    public void remove(Object x) {
        CursorListItr p = findPrevious(x);
        int pos = p.current;
        if (cursorSpace[pos].next != 0) {
            int tmp = cursorSpace[pos].next;
            cursorSpace[pos].next = cursorSpace[tmp].next;
            free(tmp);
        }
    }

    public CursorListItr findPrevious(Object x){
        int itr = cursorSpace[header].next;
        while (itr != 0 && !cursorSpace[cursorSpace[itr].next].element.equals(x))
            itr = cursorSpace[itr].next;
        return new CursorListItr(itr);
    }

    private int header;
    static CursorNode[] cursorSpace;
    private static final int SPACE_SIZE = 100;

    static {
        cursorSpace = new CursorNode[SPACE_SIZE];
        for (int i = 0; i < SPACE_SIZE; i++)
            cursorSpace[i] = new CursorNode(null, i + 1);
        cursorSpace[SPACE_SIZE - 1].next = 0;
    }
}