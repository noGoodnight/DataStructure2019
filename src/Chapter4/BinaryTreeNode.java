package Chapter4;

public class BinaryTreeNode implements Comparable{
    String element;
    BinaryTreeNode left;
    BinaryTreeNode right;
    BinaryTreeNode leftThread;
    BinaryTreeNode rightThread;
    int key;

    public BinaryTreeNode(String element){
        this.element = element;
        left = null;
        right = null;
        leftThread = null;
        rightThread = null;
        key = 0;
    }

    public BinaryTreeNode(BinaryTreeNode left, BinaryTreeNode right){
        element = "";
        this.left = left;
        this.right = right;
        leftThread = null;
        rightThread = null;
        key = left.key + right.key;
    }

    public String toString(){
        return element;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof BinaryTreeNode){
            if(((BinaryTreeNode) o).key == key){
                return 0;
            }else if(((BinaryTreeNode) o).key > key){
                return -1;
            }else{
                return 1;
            }
        }else{
            return 0;
        }
    }

    public void setKey(int key) {
        this.key = key;
    }
}
