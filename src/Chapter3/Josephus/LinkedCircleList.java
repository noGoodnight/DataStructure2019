package Chapter3.Josephus;

public class LinkedCircleList {
    public ListNode header;
    public int length;
    public int step;

    public LinkedCircleList(int n, int m) {
        length = n;
        step = m;
        header = new ListNode();
        LoopIterator iterator = new LoopIterator(header);

        for (int i = 1; i <= n; i++) {
            this.add(new ListNode(i));
            iterator.advance();
        }

        iterator.current.next = header.next;
    }

    public void add(ListNode listNode) {
        LoopIterator iterator = new LoopIterator(header);
        while (!iterator.current.next.equals(header)) {
            iterator.advance();
        }
        iterator.current.next = listNode;
        listNode.next = header;
    }

    public String toString(){
        String result = "";
        LoopIterator iterator = new LoopIterator(header.next);

        while(iterator.current.next!=header.next){
            result = result + iterator.current.toString() + " -> ";
            iterator.advance();
        }

        result = result + iterator.current.toString() + " -> " + header.next.toString();
        return result;
    }

    public void remove(LoopIterator previous){
        previous.current.next = previous.current.next.next;
    }

    public LoopIterator leftOne(){
        LoopIterator result = new LoopIterator(header);
        int rank = 0;

        while(!result.isEnd()){
            if(rank == step-1){
                remove(result);
                rank = 0;
            }else{
                result.advance();
                rank++;
            }
        }

        return result;
    }
}
