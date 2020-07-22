import Chapter3.Stack.ExpressionEvaluation;
import Chapter4.*;
import Chapter4_1.BinaryNode;
import Chapter4_1.BinaryTree;
import Chapter6.MaxHeap;
import Chapter6.MinHeap;
import Chapter8_Chapter9.Adjacency_Matrix.DiGraph1;
import Chapter8_Chapter9.Adjacency_Matrix.Graph1;
import Chapter8_Chapter9.Linked_Adjacency_Lists.DiGraph2;
import extra1.CursorList;
import extra1.CursorListItr;
import org.junit.Test;

import java.util.Arrays;

import static Chapter7.Sort.*;

public class test {

    /**
     * 建立一棵AVL树，素材来源DataStructure.P403
     */
    @Test
    public void treeTest1() {
        BinaryNode node = new BinaryNode(12);
        BinaryTree tree = new BinaryTree(node);
        tree.insert(2);
        tree.insert(11);
        tree.insert(10);
        tree.insert(7);
        tree.insert(9);
        tree.insert(8);
        tree.insert(4);
        tree.insert(3);
        tree.insert(5);
        tree.insert(6);
        tree.insert(1);
        System.out.println("Done!");
    }

    /**
     * 广义表建树；前序和中序建树比去年各转成线索树；霍夫曼树
     */
    @Test
    public void treeTest2() {
        String string = "a(b(f,g),c,d(h,i,j),e)";
        Tree tree = new Tree(string);
        BinaryTreeNode binaryTreeNode1 = new BinaryTreeNode("k");
        TreeNode treeNode = new TreeNode("k");
        tree.insert(binaryTreeNode1, "i");
        tree.insert(treeNode, "i");
        System.out.println("Done!");

        String preOrder = "ABECDFGHIJ";
        String inOrder = "EBCDAFHIGJ";
        Chapter4.BinaryTree binaryTree1 = new Chapter4.BinaryTree(preOrder, inOrder, "PI");
        ThreadTree threadTree = new ThreadTree(binaryTree1);
        System.out.println(threadTree.threadOut());
        System.out.println("Done!");

        int[] weights = {5, 25, 3, 6, 10, 11, 36, 4};
        Chapter4.BinaryTreeNode binaryTreeNode2 = Huffman.huffman(weights);
        Chapter4.BinaryTree binaryTree2 = new Chapter4.BinaryTree(binaryTreeNode2);
        System.out.println("Done!");
    }

    /**
     * 最大堆排序1；排序输出从大到小;素材来源DataStructure.P670
     */
    @Test
    public void maxHeapTest1() {
        MaxHeap heap2 = new MaxHeap(new int[]{111, 453, 142, 434, 242, 123, 102, 65});
        heap2.heapSort();
        System.out.println(heap2.toString());
        System.out.println("Done!");
    }

    /**
     * 最大堆排序2；排序输出从小到大;素材来源DataStructure.P670
     */
    @Test
    public void maxHeapTest2() {
        MaxHeap heap2 = new MaxHeap(new int[]{111, 453, 142, 434, 242, 123, 102, 65});
        heap2.HeapSort();
        System.out.println(heap2.toString());
        System.out.println("Done!");
    }

    /**
     * 最小堆排序1；排序输出从小到大;素材来源DataStructure.P670
     */
    @Test
    public void minHeapTest1() {
        MinHeap heap2 = new MinHeap(new int[]{111, 453, 142, 434, 242, 123, 102, 65});
        heap2.heapSort();
        System.out.println(heap2.toString());
        System.out.println("Done!");
    }

    /**
     * 最小堆排序2；排序输出从大到小;素材来源DataStructure.P670
     */
    @Test
    public void minHeapTest2() {
        MinHeap heap2 = new MinHeap(new int[]{111, 453, 142, 434, 242, 123, 102, 65});
        heap2.HeapSort();
        System.out.println(heap2.toString());
        System.out.println("Done!");
    }

    @Test
    public void quickSortTest() {
        int[] sources = new int[]{46, 13, 55, 42, 94, 5, 17, 70, 82, 100};
        quickSort(sources, 0, sources.length - 1);
        System.out.println(Arrays.toString(sources));
        System.out.println("Done!");
    }

    @Test
    public void binarySortTest() {
        int[] sources = new int[]{46, 13, 55, 42, 94, 5, 17, 70, 82, 100};
        binarySort(sources);
        System.out.println(Arrays.toString(sources));
        System.out.println("Done!");
    }

    @Test
    public void shellSortTest() {
        int[] sources = new int[]{72, 73, 71, 23, 94, 16, 5, 68, 10};
        shellSort(sources);
        System.out.println(Arrays.toString(sources));
        System.out.println("Done!");
    }

    @Test
    public void graphKruskalTest() {
        Graph1 graph1 = new Graph1(5);
        graph1.insertEdge(1, 2, 2);
        graph1.insertEdge(1, 4, 10);
        graph1.insertEdge(1, 3, 12);
        graph1.insertEdge(2, 3, 8);
        graph1.insertEdge(4, 3, 6);
        graph1.insertEdge(4, 5, 7);
        graph1.insertEdge(5, 2, 9);
        graph1.insertEdge(3, 5, 3);
        Graph1 minTree = graph1.kruskal();
        minTree.print();
        System.out.println("Done!");
    }

    /**
     * 素材来源DataStructure.P743
     */
    @Test
    public void graphPrimTest() {
        Graph1 graph1 = new Graph1(7);
        graph1.insertEdge(1, 2, 28);
        graph1.insertEdge(1, 6, 10);
        graph1.insertEdge(2, 7, 14);
        graph1.insertEdge(2, 3, 16);
        graph1.insertEdge(6, 5, 25);
        graph1.insertEdge(7, 5, 24);
        graph1.insertEdge(5, 4, 22);
        graph1.insertEdge(4, 7, 18);
        graph1.insertEdge(3, 4, 12);
        Graph1 minTree = graph1.prim(1);
        minTree.print();
        System.out.println("Done!");
    }

    @Test
    public void graphDijkstraTest() {
        Graph1 graph1 = new Graph1(5);
        graph1.insertEdge(1, 2, 10);
        graph1.insertEdge(1, 4, 30);
        graph1.insertEdge(1, 5, 100);
        graph1.insertEdge(2, 3, 50);
        graph1.insertEdge(3, 5, 10);
        graph1.insertEdge(4, 3, 20);
        graph1.insertEdge(5, 4, 60);
        int[][] result = graph1.Dijkstra(1);
        System.out.println(Arrays.toString(result[0]));
        System.out.println("Done!");
    }

    @Test
    public void graphFloyedTest() {
        Graph1 graph1 = new Graph1(5);
        graph1.insertEdge(1, 2, 10);
        graph1.insertEdge(1, 4, 30);
        graph1.insertEdge(1, 5, 100);
        graph1.insertEdge(2, 3, 50);
        graph1.insertEdge(3, 5, 10);
        graph1.insertEdge(4, 3, 20);
        graph1.insertEdge(5, 4, 60);
        int[][] result = graph1.floyed();
        for (int[] nums : result) {
            System.out.println(Arrays.toString(nums));
        }
        System.out.println("Done!");
    }

    @Test
    public void TopTest() {
        DiGraph2 graph = new DiGraph2(9);
        graph.insertEdge(1, 3, 0);
        graph.insertEdge(1, 8, 0);
        graph.insertEdge(2, 3, 0);
        graph.insertEdge(2, 4, 0);
        graph.insertEdge(2, 5, 0);
        graph.insertEdge(3, 4, 0);
        graph.insertEdge(4, 6, 0);
        graph.insertEdge(4, 7, 0);
        graph.insertEdge(5, 6, 0);
        graph.insertEdge(8, 9, 0);
        graph.insertEdge(9, 7, 0);
        System.out.println(Arrays.toString(graph.topSort()));
        System.out.println("Done!");
    }

    /**
     * 三种一开始就都是回路，一种是没有回路，一种是开始时有回路然后拿走一个回路之后又没有回路了
     */
    @Test
    public void printCircleTest1() {
        DiGraph2 graph = new DiGraph2(4);
        graph.insertEdge(4, 1, 0);
        graph.insertEdge(1, 3, 0);
        graph.insertEdge(1, 2, 0);
        graph.insertEdge(2, 3, 0);
        graph.insertEdge(3, 1, 0);
        graph.insertEdge(3, 4, 0);
        graph.print();
        System.out.println(graph.printCircle());
        System.out.println("Done!");
    }

    @Test
    public void printCircleTest2() {
        DiGraph2 graph = new DiGraph2(7);
        graph.insertEdge(1, 3, 0);
        graph.insertEdge(2, 1, 0);
        graph.insertEdge(3, 5, 0);
        graph.insertEdge(3, 2, 0);
        graph.insertEdge(3, 6, 0);
        graph.insertEdge(4, 3, 0);
        graph.insertEdge(5, 4, 0);
        graph.insertEdge(6, 7, 0);
        graph.insertEdge(7, 3, 0);
        System.out.println(graph.printCircle());
        System.out.println("Done!");
    }

    @Test
    public void printCircleTest3() {
        DiGraph2 graph = new DiGraph2(9);
        graph.insertEdge(1, 3, 0);
        graph.insertEdge(1, 8, 0);
        graph.insertEdge(2, 3, 0);
        graph.insertEdge(2, 4, 0);
        graph.insertEdge(2, 5, 0);
        graph.insertEdge(3, 4, 0);
        graph.insertEdge(4, 6, 0);
        graph.insertEdge(4, 7, 0);
        graph.insertEdge(5, 6, 0);
        graph.insertEdge(8, 9, 0);
        graph.insertEdge(9, 7, 0);
        System.out.println(graph.printCircle());
        System.out.println("Done!");
    }

    @Test
    public void printCircleTest4() {
        DiGraph2 graph = new DiGraph2(5);
        graph.insertEdge(1, 2, 0);
        graph.insertEdge(2, 3, 0);
        graph.insertEdge(2, 1, 0);
        graph.insertEdge(3, 4, 0);
        graph.insertEdge(4, 5, 0);
        graph.insertEdge(5, 2, 0);
        System.out.println(graph.printCircle());
        System.out.println("Done!");
    }

    @Test
    public void printCircleTest5() {
        DiGraph2 graph = new DiGraph2(6);
        graph.insertEdge(1, 2, 0);
        graph.insertEdge(2, 3, 0);
        graph.insertEdge(3, 4, 0);
        graph.insertEdge(4, 1, 0);
        graph.insertEdge(4, 5, 0);
        graph.insertEdge(5, 6, 0);
        graph.insertEdge(6, 1, 0);
        System.out.println(graph.printCircle());
        System.out.println("Done!");
    }

    @Test
    public void diGraphDijkstraTest() {
        DiGraph1 diGraph1 = new DiGraph1(5);
        diGraph1.insertEdge(1, 2, 10);
        diGraph1.insertEdge(1, 3, 18);
        diGraph1.insertEdge(2, 4, 5);
        diGraph1.insertEdge(3, 2, 5);
        diGraph1.insertEdge(4, 3, 2);
        diGraph1.insertEdge(4, 5, 2);
        diGraph1.insertEdge(5, 3, 2);
        int[][] result = diGraph1.Dijkstra(1);
        System.out.println(Arrays.toString(result[0]));
        System.out.println("Done!");
    }

    @Test
    public void diGraphKruskalTest() {
        DiGraph1 diGraph1 = new DiGraph1(5);
        diGraph1.insertEdge(1, 2, 10);
        diGraph1.insertEdge(1, 3, 18);
        diGraph1.insertEdge(2, 4, 5);
        diGraph1.insertEdge(3, 2, 5);
        diGraph1.insertEdge(4, 3, 2);
        diGraph1.insertEdge(4, 5, 2);
        diGraph1.insertEdge(5, 3, 2);
        DiGraph1 mintree1 = diGraph1.kruskal();
        mintree1.print();
        DiGraph1 mintree2 = diGraph1.prim(1);
        mintree2.print();
        System.out.println("Done!");
    }

    @Test
    public void exerciseTest1() {
        Graph1 graph = new Graph1(6);
        graph.insertEdge(1, 3, 11);
        graph.insertEdge(1, 2, 7);
        graph.insertEdge(3, 2, 10);
        graph.insertEdge(4, 2, 9);
        graph.insertEdge(4, 3, 5);
        graph.insertEdge(5, 3, 7);
        graph.insertEdge(5, 6, 6);
        graph.insertEdge(3, 6, 8);
        Graph1 graph1 = graph.prim(1);
        graph1.print();
        System.out.println("Done!");
        Graph1 graph2 = graph.kruskal();
        graph2.print();
        System.out.println("Done!");
    }

    @Test
    public void exerciseTest3() {
        DiGraph1 graph = new DiGraph1(7);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(2, 3, 1);
        graph.insertEdge(2, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(3, 5, 1);
        graph.insertEdge(3, 4, 1);
        graph.insertEdge(4, 1, 1);
        graph.insertEdge(4, 6, 1);
        graph.insertEdge(5, 4, 1);
        graph.insertEdge(5, 6, 1);
        graph.insertEdge(7, 5, 1);
        int[][] result = graph.Dijkstra(2);
        System.out.println("Length: " + Arrays.toString(result[0]));
        System.out.println("PastVertice: " + Arrays.toString(result[1]));
        System.out.println("Done!");
    }

    @Test
    public void exerciseTest4() {
        DiGraph2 graph = new DiGraph2(11);
        graph.insertEdge(1, 2, 2);
        graph.insertEdge(1, 5, 2);
        graph.insertEdge(2, 3, 2);
        graph.insertEdge(4, 1, 3);
        graph.insertEdge(4, 5, 3);
        graph.insertEdge(5, 3, 2);
        graph.insertEdge(5, 6, 3);
        graph.insertEdge(5, 9, 3);
        graph.insertEdge(6, 3, 1);
        graph.insertEdge(7, 4, 2);
        graph.insertEdge(7, 5, 1);
        graph.insertEdge(7, 8, 6);
        graph.insertEdge(8, 5, 2);
        graph.insertEdge(8, 9, 6);
        graph.insertEdge(9, 6, 1);
        graph.insertEdge(10, 1, 1);
        graph.insertEdge(10, 4, 4);
        graph.insertEdge(10, 7, 6);
        graph.insertEdge(3, 11, 4);
        graph.insertEdge(6, 11, 3);
        graph.insertEdge(9, 11, 4);
        int[] result = graph.topSort();
        System.out.println(Arrays.toString(result));
        System.out.println("Done!");
    }

    @Test
    public void exerciseTest5(){
        Graph1 graph = new Graph1(10);
        graph.insertEdge(1,2,3);
        graph.insertEdge(1,4,4);
        graph.insertEdge(1,5,4);
        graph.insertEdge(2,3,10);
        graph.insertEdge(2,5,2);
        graph.insertEdge(2,6,3);
        graph.insertEdge(3,6,6);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,5,5);
        graph.insertEdge(4,8,6);
        graph.insertEdge(5,6,11);
        graph.insertEdge(5,8,2);
        graph.insertEdge(5,9,1);
        graph.insertEdge(6,7,2);
        graph.insertEdge(6,9,3);
        graph.insertEdge(6,10,11);
        graph.insertEdge(7,10,8);
        graph.insertEdge(8,9,4);
        graph.insertEdge(9,10,7);
        graph.kruskal().print();
        System.out.println("Done!");
        graph.prim(1).print();
        System.out.println("Done!");
        graph.prim(2).print();
        System.out.println("Done!");
        graph.prim(3).print();
        System.out.println("Done!");
        graph.prim(4).print();
        System.out.println("Done!");
        graph.prim(5).print();
        System.out.println("Done!");
        graph.prim(6).print();
        System.out.println("Done!");
        graph.prim(7).print();
        System.out.println("Done!");
        graph.prim(8).print();
        System.out.println("Done!");
        graph.prim(9).print();
        System.out.println("Done!");
        graph.prim(10).print();
        System.out.println("Done!");
    }

    /**
     * 中缀转后缀并且一步计算出来结果的测试
     */
    @Test
    public void stackTest(){
        ExpressionEvaluation alu = new ExpressionEvaluation();
        String s = "432/-5*42*5*3//#";
        System.out.println(alu.evaluate(s));
    }

    @Test
    public void cursorLinkedListTest(){
        CursorList cursorList = new CursorList();
        CursorListItr cursorListItr = cursorList.zeroth();
        cursorList.insert(1,cursorListItr);
        CursorListItr cursorListItr1 = cursorList.find(1);
        cursorList.insert(2,cursorListItr1);
        cursorList.remove(2);
        System.out.println("Done!");
    }
}
