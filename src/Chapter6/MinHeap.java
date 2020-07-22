package Chapter6;

import Chapter4_1.BinaryNode;

public class MinHeap extends Heap {

    public MinHeap(int[] nums) {
        maxHeapSize = defaultSize;
        nodes = new BinaryNode[maxHeapSize + 1];
        assert nums.length <= maxHeapSize;
        currentSize = nums.length;
        for (int i = 0; i < currentSize; i++) {
            nodes[i + 1] = new BinaryNode(nums[i]);
        }
        while (true) {
            boolean isChanged = false;
            for (int i = currentSize / 2; i > 0; i--) {
                try {
                    if (nodes[2 * i].compareTo(nodes[2 * i + 1]) < 0) {
                        if (nodes[2 * i].compareTo(nodes[i]) < 0) {
                            BinaryNode theNode = nodes[2 * i];
                            nodes[2 * i] = nodes[i];
                            nodes[i] = theNode;
                            isChanged = true;
                        }
                    } else {
                        if (nodes[2 * i + 1].compareTo(nodes[i]) < 0) {
                            BinaryNode theNode = nodes[2 * i + 1];
                            nodes[2 * i + 1] = nodes[i];
                            nodes[i] = theNode;
                            isChanged = true;
                        }
                    }
                } catch (Exception e) {
                    if (nodes[2 * i].compareTo(nodes[i]) < 0) {
                        BinaryNode theNode = nodes[2 * i];
                        nodes[2 * i] = nodes[i];
                        nodes[i] = theNode;
                        isChanged = true;
                    }
                }
            }
            if (!isChanged) {
                break;
            }
        }
    }

    public void insert(BinaryNode node) {
        if (currentSize == maxHeapSize) {
            throw new IndexOutOfBoundsException();
        } else {
            currentSize++;
            int temp = currentSize;
            while (temp != 1 && node.compareTo(nodes[temp / 2]) < 0) {
                nodes[temp] = nodes[temp / 2];
                temp = temp / 2;
            }
            nodes[temp] = node;
        }
    }

    public void delete() {
        //偷懒：这里的删除默认是删除第一个元素
//        int temp = 1;
//        nodes[temp] = nodes[currentSize];
//        nodes[currentSize] = null;
//        currentSize--;
//        try{
//            while(true){
//                if(nodes[temp].compareTo(nodes[2 * temp]) == 1 && nodes[temp].compareTo(nodes[2 * temp + 1]) == 1){
//                    break;
//                }else {
//                    if(nodes[2 * temp].compareTo(nodes[2 * temp + 1]) == 1){
//                        BinaryNode theNode = nodes[2 * temp];
//                        nodes[2 * temp] = nodes[temp];
//                        nodes[temp] = theNode;
//                        temp = 2 * temp;
//                    }else{
//                        BinaryNode theNode = nodes[2 * temp + 1];
//                        nodes[2 * temp + 1] = nodes[temp];
//                        nodes[temp] = theNode;
//                        temp = 2 * temp + 1;
//                    }
//                }
//            }
//        }catch (NullPointerException e){
//            if(nodes[2 * temp] == null){
//
//            }else{
//                if(nodes[temp].compareTo(nodes[2 * temp]) == -1){
//                    BinaryNode theNode = nodes[2 * temp];
//                    nodes[2 * temp] = nodes[temp];
//                    nodes[temp] = theNode;
//                }
//            }
//        }catch (IndexOutOfBoundsException e){
//
//        }
        nodes[1] = nodes[currentSize--];
        percolateDown(1);
    }

    private void percolateDown(int hole) {
        int child;
        BinaryNode tmp = nodes[hole];
        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && nodes[child + 1].compareTo(nodes[child]) < 0)
                child++;
            if (nodes[child].compareTo(tmp) < 0)
                nodes[hole] = nodes[child];
            else
                break;
        }
        nodes[hole] = tmp;
    }

    @Override
    public void heapSort() {
        // sort from smallest to largest
        if (currentSize > 1) {
            BinaryNode[] binaryNodes = new BinaryNode[currentSize];
            MinHeap maxHeap;
            int[] nums = new int[binaryNodes.length - 1];

            for (int i = 1; i < currentSize; i++) {
                binaryNodes[i] = nodes[i + 1];
            }

            for (int i = 1; i < binaryNodes.length; i++) {
                nums[i - 1] = binaryNodes[i].getKey();
            }

            maxHeap = new MinHeap(nums);
            maxHeap.heapSort();

            for (int i = 1; i < currentSize; i++) {
                nodes[i + 1] = maxHeap.nodes[i];
            }

        } else {

        }
    }

    @Override

    public void HeapSort() {
        int tempi = currentSize;
        BinaryNode tempb;

        for (int i = 1; i < tempi; i++) {
            tempb = nodes[1];
            nodes[1] = nodes[currentSize--];
            nodes[currentSize + 1] = tempb;
            percolateDown(1);
        }

        currentSize = tempi;
    }

}