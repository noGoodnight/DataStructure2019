package Chapter4;

import java.util.ArrayList;
import java.util.Comparator;

public class Huffman {
    public static BinaryTreeNode huffman(int[] weights){
        ArrayList<BinaryTreeNode> nodes = new ArrayList<>();
        for(int i = 0;i<weights.length;i++){
            BinaryTreeNode binaryTreeNode = new BinaryTreeNode("");
            binaryTreeNode.setKey(weights[i]);
            nodes.add(binaryTreeNode);
        }
        nodes.sort(new compare());
        while(nodes.size() != 1){
            nodes.add(new BinaryTreeNode(nodes.get(0),nodes.get(1)));
            nodes.remove(0);
            nodes.remove(0);
            nodes.sort(new compare());
        }
        return nodes.get(0);
    }

    static class compare implements Comparator<BinaryTreeNode>{

        @Override
        public int compare(BinaryTreeNode o1, BinaryTreeNode o2) {
            if(o1 == null || o2 == null){
                return 0;
            }else{
                return o1.compareTo(o2);
            }
        }
    }
}
