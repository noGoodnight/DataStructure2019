package Chapter4;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTree {
    BinaryTreeNode root;

    public BinaryTree(BinaryTreeNode binaryTreeNode) {
        root = binaryTreeNode;
    }

    public BinaryTree(String a, String b, String sign) {
        if (sign.equals("PI")) {
            root = createPI(a, b);
        } else if (sign.equals("IP")) {
            root = createIP(a, b);
        } else if (sign.equals("PP")) {
            root = createPP(a, b);
        }
    }

    public BinaryTreeNode createPI(String pre, String in) {
        BinaryTreeNode result;
        String key;
        String leftPre;
        String leftIn;
        String rightPre;
        String rightIn;
        if (pre.length() == 1) {
            key = pre.substring(0, 1);
            result = new BinaryTreeNode(key);
        } else if (pre.length() == 2) {
            key = pre.substring(0, 1);
            result = new BinaryTreeNode(key);
            if (pre.equals(in)) {
                result.right = new BinaryTreeNode(pre.substring(1, 2));
            } else {
                result.left = new BinaryTreeNode(pre.substring(1, 2));
            }
        } else {
            key = pre.substring(0, 1);
            result = new BinaryTreeNode(key);
            try {
                String[] Ins = in.split(key);
                leftIn = Ins[0];
                rightIn = Ins[1];
                leftPre = pre.substring(1, 1 + leftIn.length());
                rightPre = pre.substring(1 + leftIn.length());

                result.left = createPI(leftPre, leftIn);
                result.right = createPI(rightPre, rightIn);
            }catch (Exception e){
                if(in.substring(0,1).equals(key)){
                    result.left = null;
                    rightIn = in.substring(1);
                    rightPre = pre.substring(1);

                    result.right = createPI(rightPre,rightIn);
                }else{
                    result.right = null;
                    leftIn = in.substring(0,in.length()-1);
                    leftPre = pre.substring(1);

                    result.left = createPI(leftPre,leftIn);
                }
            }
        }
        return result;
    }

    public BinaryTreeNode createIP(String in, String post) {
        BinaryTreeNode result;
        String key;
        String leftPost;
        String leftIn;
        String rightPost;
        String rightIn;
        if (post.length() == 1) {
            key = post.substring(post.length() - 1);
            result = new BinaryTreeNode(key);
        } else if (post.length() == 2) {
            key = post.substring(post.length() - 1);
            result = new BinaryTreeNode(key);
            if (post.equals(in)) {
                result.left = new BinaryTreeNode(post.substring(0, 1));
            } else {
                result.right = new BinaryTreeNode(post.substring(0, 1));
            }
        } else {
            key = post.substring(post.length() - 1);
            result = new BinaryTreeNode(key);
            String[] Ins = in.split(key);
            leftIn = Ins[0];
            rightIn = Ins[1];
            leftPost = post.substring(0, leftIn.length());
            rightPost = post.substring(leftIn.length(), post.length() - 1);

            result.left = createIP(leftIn, leftPost);
            result.right = createIP(rightIn, rightPost);
        }
        return result;
    }

    /*  The consequence of the method "createPP" is not only, so writing it makes no sense. */
    public BinaryTreeNode createPP(String pre, String post) {
        return null;
    }

    public String rePreOut() {
        String result = "";
        if (root.left == null && root.right == null) {
            result = root.element;
        } else if (root.left == null) {
            result = root.element + new BinaryTree(root.right).rePreOut();
        } else if (root.right == null) {
            result = root.element + new BinaryTree(root.left).rePreOut();
        } else {
            result = root.element + new BinaryTree(root.left).rePreOut() + new BinaryTree(root.right).rePreOut();
        }
        return result;
    }

    public String reInOut() {
        String result = "";
        if (root.left == null && root.right == null) {
            result = root.element;
        } else if (root.left == null) {
            result = root.element + new BinaryTree(root.right).reInOut();
        } else if (root.right == null) {
            result = new BinaryTree(root.left).reInOut() + root.element;
        } else {
            result = new BinaryTree(root.left).reInOut() + root.element + new BinaryTree(root.right).reInOut();
        }
        return result;
    }

    public String rePostOut() {
        String result = "";
        if (root.left == null && root.right == null) {
            result = root.element;
        } else if (root.left == null) {
            result = new BinaryTree(root.right).rePostOut() + root.element;
        } else if (root.right == null) {
            result = new BinaryTree(root.left).rePostOut() + root.element;
        } else {
            result = new BinaryTree(root.left).rePostOut() + new BinaryTree(root.right).rePostOut() + root.element;
        }
        return result;
    }

    public String irrePreOut() {
        Stack<BinaryTreeNode> stack = new Stack<>();
        String result = "";
        BinaryTreeNode theNode = root;
        while (true) {
            while (theNode != null) {
                result = result + theNode.element;
                stack.push(theNode);
                theNode = theNode.left;
            }
            if (!stack.empty()) {
                theNode = stack.pop();
                theNode = theNode.right;
            } else {
                return result;
            }
        }
    }

    public String irreInOut() {
        Stack<BinaryTreeNode> stack = new Stack<>();
        String result = "";
        BinaryTreeNode theNode = root;
        while (true) {
            while (theNode != null) {
                stack.push(theNode);
                theNode = theNode.left;
            }
            if (!stack.empty()) {
                theNode = stack.pop();
                result = result + theNode.element;
                theNode = theNode.right;
            } else {
                return result;
            }
        }
    }

    public String irrePostOut() {
        Stack<StackNode> stack = new Stack<>();
        String result = "";
        BinaryTreeNode theNode = root;
        StackNode stackNode;

        while (true) {
            while (theNode != null) {
                stack.push(new StackNode(0, theNode));
                theNode = theNode.left;
            }

            stackNode = stack.pop();
            theNode = stackNode.ptn;

            while (stackNode.tag == 1) {
                result = result + theNode.element;
                if (!stack.empty()) {
                    stackNode = stack.pop();
                    theNode = stackNode.ptn;
                } else {
                    return result;
                }
            }

            stackNode.tag = 1;
            stack.push(stackNode);
            theNode = theNode.right;
        }

    }

    public String levetOut() {
        String result = "";
        ArrayList<BinaryTreeNode> nodes = new ArrayList<>();
        nodes.add(root);

        while (true) {
            try {
                for (int i = 0; i < nodes.size(); i++) {
                    BinaryTreeNode theNode = nodes.get(0);
                    result = result + theNode.element;
                    nodes.remove(0);
                    if (theNode.left != null) {
                        nodes.add(theNode.left);
                    }
                    if (theNode.right != null) {
                        nodes.add(theNode.right);
                    }
                }
                if (nodes.isEmpty()) {
                    return result;
                } else {
                    continue;
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

    public void delete(){
        root = null;
    }

    public int height(){
        int result = -1;
        ArrayList<BinaryTreeNode> nodes = new ArrayList<>();
        nodes.add(root);

        while (true) {
            int length = nodes.size();
            for (int i = 0; i < length; i++) {
                BinaryTreeNode theNode = nodes.get(0);
                nodes.remove(0);

                if (theNode.left != null) {
                    nodes.add(theNode.left);
                }
                if (theNode.right != null) {
                    nodes.add(theNode.right);
                }
            }
            result = result + 1;
            if (nodes.isEmpty()) {
                return result;
            } else {
                continue;
            }

        }
    }

    public BinaryTreeNode search(String e){
        BinaryTreeNode result;
        ArrayList<BinaryTreeNode> nodes = new ArrayList<>();
        nodes.add(root);

        while (true) {
            int length = nodes.size();
            for(int i = 0; i<length; i++) {
                BinaryTreeNode theNode = nodes.get(0);
                nodes.remove(0);
                if(theNode.element.equals(e)){
                    return theNode;
                }
                if (theNode.left != null) {
                    nodes.add(theNode.left);
                }
                if (theNode.right != null) {
                    nodes.add(theNode.right);
                }
            }
            if(nodes.isEmpty()){
                return null;
            }else{
                continue;
            }
        }
    }
}

class StackNode {
    int tag;
    BinaryTreeNode ptn;

    public StackNode(int tag, BinaryTreeNode ptn) {
        this.tag = tag;
        this.ptn = ptn;
    }
}