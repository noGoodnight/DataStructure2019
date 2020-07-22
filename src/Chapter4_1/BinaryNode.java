package Chapter4_1;

public class BinaryNode implements Comparable {
    String element;
    BinaryNode left;
    BinaryNode right;
    int key;

    public int getKey() {
        return key;
    }

    public BinaryNode(String s) {
        element = s;
        left = null;
        right = null;
        try {
            key = Integer.parseInt(element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BinaryNode(char c){
        element = c + "";
        left = null;
        right = null;
        key = (int)c;
    }

    public BinaryNode(int i){
        this(i+"");
    }

    @Override
    public int compareTo(Object o) {
        BinaryNode node = (BinaryNode) o;
        if (node.key == key) {
            return 0;
        } else if (node.key > key) {
            return -1;
        } else {
            return 1;
        }
    }

    public BinaryNode add(BinaryNode addition) {
        if (compareTo(addition) == 1) {
            if (left == null) {
                left = addition;
                return this;
            } else {
                return left.add(addition);
            }
        } else {
            if (right == null) {
                right = addition;
                return this;
            } else {
                return right.add(addition);
            }
        }
    }

    public String toString() {
        return element;
    }


}
