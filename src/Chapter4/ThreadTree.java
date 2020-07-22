package Chapter4;

public class ThreadTree {
    BinaryTree tree;

    public ThreadTree(BinaryTree binaryTree) {
        tree = binaryTree;
        create(tree.root);
    }

    public void create(BinaryTreeNode root) {
        if (root.left != null) {
            if (root.left.right == null) {
                root.leftThread = root.left;
                root.left.rightThread = root;
            } else {
                BinaryTreeNode theNode = root.left;
                while (theNode.right != null) {
                    theNode = theNode.right;
                }
                root.leftThread = theNode;
                theNode.rightThread = root;
            }
            create(root.left);
        }
        if (root.right != null) {
            if (root.right.left == null) {
                root.rightThread = root.right;
                root.right.leftThread = root;
            } else {
                BinaryTreeNode theNode = root.right;
                while (theNode.left != null) {
                    theNode = theNode.left;
                }
                root.rightThread = theNode;
                theNode.leftThread = root;
            }
            create(root.right);
        }
    }

    public String threadOut(){
        String result = "";
        BinaryTreeNode startingPoint = tree.root;
        BinaryTreeNode current;
        while(startingPoint.leftThread != null){
            startingPoint = startingPoint.leftThread;
        }
        current = startingPoint;
        while(current != null){
            result = result + current.element;
            current = current.rightThread;
        }
        return  result;
    }
}
