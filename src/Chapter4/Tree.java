package Chapter4;

import java.util.ArrayList;

public class Tree {
    TreeNode root;
    BinaryTree sonsAndBros;

    public Tree(TreeNode node) {
        root = node;
        sonsAndBros = convert(root);
    }

    public Tree(String string) {
        TreeNode treeNode = new TreeNode("");
        create(treeNode, string, 0);
        root = treeNode.children.get(0);
        sonsAndBros = convert(root);
    }

    public BinaryTree convert(TreeNode treeNode) {
        BinaryTree result = new BinaryTree(new BinaryTreeNode(treeNode.element));
        BinaryTreeNode former;
        BinaryTreeNode latter;

        for (int i = 0; i < treeNode.children.size(); i++) {
            if (result.root.left == null) {
                result.root.left = convert(treeNode.children.get(i)).root;
            } else {
                former = result.root.left;
                latter = result.root.left.right;
                while (latter != null) {
                    former = latter;
                    latter = former.right;
                }
                former.right = convert(treeNode.children.get(i)).root;
            }
        }
        return result;
    }

    public int create(TreeNode treeNode, String a, int i) {
        for (; i < a.length(); i++) {
            if ('a' <= a.charAt(i) && a.charAt(i) <= 'z') {
                treeNode.addition(new TreeNode(a.substring(i,i+1)));
            } else if (a.charAt(i) == ',') {
                continue;
            } else if (a.charAt(i) == '(') {
                i = create(treeNode.children.get(treeNode.children.size() - 1), a, i + 1);
            } else if (a.charAt(i) == ')') {
                return i;
            }
        }
        return i;
    }

    public BinaryTreeNode search1(String e){
        return sonsAndBros.search(e);
    }

    public void insert(BinaryTreeNode node, String e){
        BinaryTreeNode current = search1(e);

        if(current.left == null){
            current.left = node;
        }else{
            current = current.right;
            while(current != null){
                current = current.right;
            }
            current = node;
        }
    }

    public TreeNode search2(String e){
        TreeNode result;
        ArrayList<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);

        while (true) {
            int length = nodes.size();
            for(int i = 0; i<length; i++) {
                TreeNode theNode = nodes.get(0);
                nodes.remove(0);
                if(theNode.element.equals(e)){
                    return theNode;
                }
                for(int j = 0;j < theNode.children.size();j++){
                    nodes.add(theNode.children.get(j));
                }
            }
            if(nodes.isEmpty()){
                return null;
            }else{
                continue;
            }
        }
    }

    public void insert(TreeNode node, String e){
        TreeNode current = search2(e);
        current.children.add(node);
    }
}
