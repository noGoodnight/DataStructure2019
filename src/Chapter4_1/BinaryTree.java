package Chapter4_1;

import java.util.ArrayList;
import java.util.Comparator;

public class BinaryTree {
    BinaryNode root;

    public BinaryTree(BinaryNode theNode) {
        root = theNode;
    }

    public void insert(BinaryNode addition) {
        BinaryNode node = root.add(addition);
        while (true) {
            if (new BinaryTree(node).isBalance()) {
                if (node.equals(root)) {
                    break;
                } else {
                    node = findParent(node.key);
                }
            } else {
                node = shift(node).root;
            }
        }
    }

    public void insert(int i) {
        BinaryNode node = new BinaryNode(i + "");
        insert(node);
    }

    public void insert(char c) {
        BinaryNode node = new BinaryNode(c);
        insert(node);
    }

    public BinaryNode find(int i) {
        BinaryNode node = root;
        while (true) {
            if (node.key == i) {
                return node;
            } else if (node.key < i) {
                node = node.right;
            } else if (node.key > i) {
                node = node.left;
            } else {
                continue;
            }
        }
    }

    public BinaryNode findParent(int i) {
        BinaryNode node = new BinaryNode("0");
        node.left = root;
        node.right = root;
        while (true) {
            if (node.left != null) {
                if (node.left.key == i) {
                    return node;
                } else if (i <= new BinaryTree(node.left).findMax().key) {
                    node = node.left;
                }
            }
            if (node.right != null) {
                if (node.right.key == i) {
                    return node;
                } else if (i >= new BinaryTree(node.right).findMin().key) {
                    node = node.right;
                }
            }
            continue;
        }
    }

    public BinaryNode findMax() {
        BinaryNode node = root;
        while (true) {
            if (node.right != null) {
                node = node.right;
            } else {
                return node;
            }
        }
    }

    public BinaryNode findMin() {
        BinaryNode node = root;
        while (true) {
            if (node.left != null) {
                node = node.left;
            } else {
                return node;
            }
        }
    }

    public BinaryTree delete(int i) {
        return delete(i, true);
    }

    public BinaryTree delete(int i, boolean needCheck) {
        BinaryNode node = find(i);
        BinaryNode parent = findParent(i);
        boolean isLeft = (parent.key > node.key);
        if (node.left == null && node.right == null) {
            if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (node.left == null && node.right != null) {
            if (isLeft) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
        } else if (node.left != null && node.right == null) {
            if (isLeft) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        } else {
            BinaryNode theNode = new BinaryTree(node.left).findMax();
            node = new BinaryTree(node).delete(theNode.key, false).root;
            if (isLeft) {
                parent.left = theNode;
            } else {
                parent.right = theNode;
            }
            theNode.left = node.left;
            theNode.right = node.right;
            node = theNode;
        }

        if (needCheck) {
            while (true) {
                if (new BinaryTree(node).isBalance()) {
                    if (node.equals(root)) {
                        break;
                    } else {
                        node = findParent(node.key);
                    }
                } else {
                    while (true) {
                        if (!new BinaryTree(node.left).isBalance() && node.left != null) {
                            node = node.left;
                        } else if (!new BinaryTree(node.right).isBalance() && node.right != null) {
                            node = node.right;
                        } else {
                            break;
                        }
                    }
                    node = shift(node).root;
                }
            }
        }
        return new BinaryTree(root);
    }

    public int getHeight() {
        int result = 0;
        ArrayList<BinaryNode> nodes = new ArrayList<>();
        if (root == null) {
            return result;
        } else {
            nodes.add(root);
            result++;
        }
        while (true) {
            int j = nodes.size();
            for (int i = 0; i < j; i++) {
                if (nodes.get(0).left != null) {
                    nodes.add(nodes.get(0).left);
                }
                if (nodes.get(0).right != null) {
                    nodes.add(nodes.get(0).right);
                }
                nodes.remove(0);
            }
            if (nodes.isEmpty()) {
                return result;
            } else {
                result++;
            }
        }
    }

    public boolean isBalance() {
        int gap = new BinaryTree(root.left).getHeight() - new BinaryTree(root.right).getHeight();
        if (gap > 1 || gap < -1) {
            return false;
        } else {
            return true;
        }
    }

    public BinaryTree leftShift() {
        BinaryNode LR = root.right.left;
        BinaryNode L = root;
        root = root.right;
        root.left = L;
        L.right = LR;
        return new BinaryTree(root);
    }

    public BinaryTree rightShift() {
        BinaryNode RL = root.left.right;
        BinaryNode R = root;
        root = root.left;
        root.right = R;
        R.left = RL;
        return new BinaryTree(root);
    }

    public BinaryTree shift(BinaryNode node) {
        if (new BinaryTree(node.left).getHeight() > new BinaryTree(node.right).getHeight()) {
            BinaryNode child = node.left;
            if (new BinaryTree(child.left).getHeight() > new BinaryTree(child.right).getHeight()) {
                BinaryNode parent = findParent(node.key);
                if (parent.key == 0) {
                    root = rightShift().root;
                } else {
                    if (parent.left.equals(node)) {
                        parent.left = new BinaryTree(node).rightShift().root;
                    } else {
                        parent.right = new BinaryTree(node).rightShift().root;
                    }
                }
            } else {
                BinaryNode parent = findParent(node.key);
                if (parent.key == 0) {
                    node.left = new BinaryTree(child).leftShift().root;
                    root = rightShift().root;
                } else {
                    if (parent.left.equals(node)) {
                        node.left = new BinaryTree(child).leftShift().root;
                        parent.left = new BinaryTree(node).rightShift().root;
                    } else {
                        node.left = new BinaryTree(child).leftShift().root;
                        parent.right = new BinaryTree(node).rightShift().root;
                    }
                }
            }
        } else {
            BinaryNode child = node.right;
            if (new BinaryTree(child.left).getHeight() > new BinaryTree(child.right).getHeight()) {
                BinaryNode parent = findParent(node.key);
                if (parent.key == 0) {
                    node.right = new BinaryTree(child).rightShift().root;
                    root = leftShift().root;
                } else {
                    if (parent.left.equals(node)) {
                        node.right = new BinaryTree(child).rightShift().root;
                        parent.left = new BinaryTree(node).leftShift().root;
                    } else {
                        node.right = new BinaryTree(child).rightShift().root;
                        parent.right = new BinaryTree(node).leftShift().root;
                    }
                }
            } else {
                BinaryNode parent = findParent(node.key);
                if (parent.key == 0) {
                    root = leftShift().root;
                } else {
                    if (parent.left.equals(node)) {
                        parent.left = new BinaryTree(node).leftShift().root;
                    } else {
                        parent.right = new BinaryTree(node).leftShift().root;
                    }
                }
            }
        }
        return new BinaryTree(root);
    }

    public String toString() {
        return "root " + root.toString();
    }

    static class NodeComparator implements Comparator<BinaryNode> {

        @Override
        public int compare(BinaryNode o1, BinaryNode o2) {
            if (o1 == null || o2 == null) {
                return 0;
            } else {
                return o1.compareTo(o2);
            }
        }
    }
}
