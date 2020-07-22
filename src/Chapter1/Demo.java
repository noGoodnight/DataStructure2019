package Chapter1;


class Link {
    class Node {
        private Node next;// 下一个节点
        private Object data;// 数据
        // 构造方法

        public Node(Object data) {
            this.data = data;
        }

        // 1.节点的增加
        // 第一次调用:this=Link.root
        // 第二次调用:this=Link.root.next
        public void addNode(Node newNode) {
            if (this.next == null) {// 如果下一节点为空,新节点为当前对象的下一节点
                this.next = newNode;
            } else {
                this.next.addNode(newNode);
            }
        }

        // 2.数据查询
        public boolean containsNode(Object data) {
            if (data.equals(this.data)) {
                return true;
            } else {
                if (this.next != null) {
                    return this.next.containsNode(data);
                } else {
                    return false;
                }
            }
        }

        // 3.修改节点数据
        public void setNode(int index, Object data) {
            if (Link.this.foot++ == index) {
                this.data = data;
            } else {
                this.next.setNode(index, data);
            }
        }

        // 4.节点数据查询
        public Object getNode(int index) {
            if (Link.this.foot++ == index) {
                return this.data;
            } else {

                return this.next.getNode(index);
            }
        }

        // 5.删除节点
        // 第一次调用 previous=Link.root;this=Link.root.next
        // 第二次调用 previous=Link.root;this=Link.root.next.next
        public void removeNode(Node previous, Object data) {
            if (data.equals(this.data)) {
                previous.next = this.next;
            } else {
                this.next.removeNode(this, data);
            }
        }

        // 6.对象数组
        public void toArrayNode() {
            Link.this.retArray[Link.this.foot++] = this.data;
            if (this.next != null) {
                this.next.toArrayNode();
            }
        }

    }

    // ==============以上是内部类==============
    private Node root;// 根节点
    private int count;// 计数器
    private int foot;// 定义脚标
    private Object[] retArray;// 设置对象数组

    // 1.增加数据
    public void add(Object data) {
        if (data == null) {// 如果数据为空，退出
            return;
        }
        Node newNode = new Node(data);// 数据打包
        if (this.root == null) {// 如果根节点为空
            this.root = newNode;// 新节点为根节点
        } else {
            this.root.addNode(newNode);
        }
        this.count++;
    }

    // 2.查询数据
    public boolean contains(Object data) {
        if (data == null || this.root == null) {
            return false;
        } else {
            return this.root.containsNode(data);
        }
    }

    // 3.数据修改
    public Object set(int index, Object newData) {
        if (index > this.count) {// 数组下标越界,退出
            return null;
        }
        this.foot = 0;// 每次修改，小标归零
        this.root.setNode(index, newData);
        return newData;// 修改成功返回修改后的数据

    }

    // 4.获取数据
    public Object get(int index) {
        if (index > this.count) {// 数组越界
            return null;
        }
        this.foot = 0;// 获取数据时候，下标归零
        return this.root.getNode(index);
    }

    // 5.数组长度
    public int length() {
        return this.count;
    }

    // 6.数组是否为空
    public boolean isEmpty() {
        return this.count == 0;
    }

    // 7.删除数据
    public void remove(Object data) {
        if (this.contains(data)) {// 当前对象是否包含被删除数据
            if (data.equals(this.root.data)) {// 删除是否根节点的数据
                this.root = this.root.next;// 根节点的下一节点是根节点
            } else {
                this.root.next.removeNode(this.root, data);// 不是根节点，从根节点的下一节点开始判断
            }
        }
        this.count--;
    }

    // 8.对象数组
    public Object[] toArray() {
        if (this.root == null) {// 根节点是否为空，为空则返回null
            return null;
        }
        this.foot = 0;// 脚标归零
        this.retArray = new Object[this.count];// 开辟数组
        this.root.toArrayNode();
        return this.retArray;
    }

    public static void print(Object[] data) {
        for (Object x : data) {
            System.out.print(x + " ");
        }
    }
}

public class Demo {
    public static void main(String[] args) {
        Link all = new Link();
        all.add(5);
        all.add(6);
        all.add(7);
        all.add(8);
        all.add(9);
        System.out.println("链表长度:" + all.length());
        System.out.println("链表是否为空:" + all.isEmpty());
        System.out.println("链表查询数字9:" + all.contains(9));
        System.out.println("下标为“1”修改后数据:" + all.set(1, 20));
        System.out.println("获取下标为“1”的数据:" + all.get(1));
        all.remove(9);// 删除数据
        System.out.println("删除数据后链表长度:" + all.length());
        System.out.println("输出链表所有数据:");
        Link.print(all.toArray());
    }
}

