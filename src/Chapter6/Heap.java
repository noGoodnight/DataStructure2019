package Chapter6;

import Chapter4_1.BinaryNode;

public class Heap {
    // 数组实现  与Chapter4_1不同
    protected BinaryNode[] nodes;
    protected int maxHeapSize;
    protected int currentSize;
    protected final int defaultSize = 100;

    public BinaryNode[] getNodes() {
        return nodes;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int getMaxHeapSize() {
        return maxHeapSize;
    }

    protected Heap(int MAX) {
        maxHeapSize = MAX;
        nodes = new BinaryNode[maxHeapSize + 1];
        currentSize = 0;
    }

    protected Heap(int[] nums) {
    }

    protected Heap() {
    }

    public void insert(BinaryNode node) {
    }

    public void delete() {
    }

    private void percolateDown(int hole) {
    }

    public int leftChild(int i) {
        return 2 * i;
    }

    public int rightChild(int i) {
        return 2 * i + 1;
    }

    public void heapSort(){}

    public void HeapSort(){
        //Teached
    }

    public String toString(){
        String result = "--  ";
        for(int i = 1;i <=currentSize;i++){
            result = result + nodes[i].toString();
            result = result + " ";
        }
        result = result + " --";
        return result;
    }
}
