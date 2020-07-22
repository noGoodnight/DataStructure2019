package Chapter1;

public class ElementOfLinkedList {
    private ElementOfLinkedList next;

    public boolean hasNext(){
        if(next == null){
            return false;
        }else{
            return true;
        }
    }

    public ElementOfLinkedList getNext() {
        return next;
    }
}
