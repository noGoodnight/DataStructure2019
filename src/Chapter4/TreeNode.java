package Chapter4;

import java.util.ArrayList;

public class TreeNode {
    String element;
    ArrayList<TreeNode> children = new ArrayList<>();

    public TreeNode(String element){
        this.element = element;
    }

    public void addition(TreeNode treeNode){
        children.add(treeNode);
    }

    public String toString(){
        return element;
    }
}
