package Chapter8_Chapter9.Adjacency_Matrix;

import Chapter7_1.DisJointSet;
import Chapter8_Chapter9.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Graph1 {
    /* 讨论无向图
     * 无向图和有向图区别： 插入、删除一条边的时候对链表进行的处理，其余无；
     *
     * 一个图当中如果有多个非连通部分，我觉得求最小生成树也是没什么意义的事情
     * 所以这个类里的图默认为一个连通图；
     */

    private int[][] edgeMatrix; // matrix
    private int currentEdge; // number of edges
    private int maxVertice; // maximum
    private int[] vertices; // collection of vertices
    private ArrayList<Edge> edges; // collection of edges
    private int currentVertice; // number of edges


    public int getMaxVertice() {
        return maxVertice;
    }

    public Graph1(int i) {
        maxVertice = i;
        edgeMatrix = new int[maxVertice + 1][maxVertice + 1];
        currentEdge = 0;
        vertices = new int[maxVertice + 1];
        currentVertice = 0;
        edges = new ArrayList<>();

        for (int j = 0; j <= maxVertice; j++) {
            for (int k = 0; k <= maxVertice; k++) {
                edgeMatrix[j][k] = Integer.MAX_VALUE;
            }
        }

        for (int j = 0; j <= maxVertice; j++) {
            vertices[j] = -1;
        }

    }

    /**
     * @param v
     */
    public void insert(int v) {
        try {
            if (vertices[v] == -1) {
                vertices[v] = v;
                currentVertice++;
            }
        } catch (Exception e) {
            System.out.println("Invalid Insert!");
        }
    }

    /**
     * @param v1
     * @param v2
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        try {
            insert(v1);
            insert(v2);
            if (edgeMatrix[v1][v2] == Integer.MAX_VALUE) {
                edgeMatrix[v1][v2] = weight;
                edges.add(new Edge(v1, v2, weight));
                currentEdge++;
                insertEdge(v2, v1, weight);
            }
        } catch (Exception e) {
            System.out.println("Invalid Insert!");
        }
    }

    /**
     * @param v
     */
    public void removeVertice(int v) {
        if (vertices[v] == -1) {
            // do nothing
        } else {
            vertices[v] = -1;
            for (int i = 1; i <= currentVertice; i++) {
                removeEdge(i, v);
                removeEdge(v, i);
            }
            currentVertice--;
        }
    }

    /**
     * @param v1
     * @param v2
     */
    public void removeEdge(int v1, int v2) {
        if (edgeMatrix[v1][v2] == Integer.MAX_VALUE) {
            // do nothing
        } else {
            edgeMatrix[v1][v2] = Integer.MAX_VALUE;
            currentEdge--;
            removeEdge(v2, v1);
        }
    }

    /**
     * @param v
     * @return
     */
    public int getFirstNeighbour(int v) {
        if (vertices[v] != -1) {
            for (int i = 1; i <= maxVertice; i++) {
                if (edgeMatrix[v][i] != Integer.MAX_VALUE) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbour(int v1, int v2) {
        if (vertices[v1] != -1) {
            for (int i = 1; i <= maxVertice; i++) {
                if (edgeMatrix[v1][i] != Integer.MAX_VALUE && i != v2) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * kruskal算法求最小生成树
     *
     * @return
     */
    public Graph1 kruskal() {
        Graph1 result = new Graph1(currentVertice);
        ArrayList<Edge> edgeArrayList = (ArrayList<Chapter8_Chapter9.Edge>) edges.clone();
        DisJointSet disJointSet = new DisJointSet(currentVertice);

        edgeArrayList.sort(new Comparator<Chapter8_Chapter9.Edge>() {
            @Override
            public int compare(Chapter8_Chapter9.Edge o1, Chapter8_Chapter9.Edge o2) {
                if (o1 == null || o2 == null) {
                    return 0;
                } else {
                    return o1.compareTo(o2);
                }
            }
        });

        for (int i = 1; i < result.getMaxVertice(); i++) {
            Edge e = edgeArrayList.get(0);
            int h1 = disJointSet.find(e.v1);
            int h2 = disJointSet.find(e.v2);

            if (h1 != h2) {
                disJointSet.union(h1, h2);
                result.insertEdge(e.v1, e.v2, e.weight);
                edgeArrayList.remove(0);
                edgeArrayList.remove(0);
            } else {
                result.removeEdge(e.v1, e.v2);
                edgeArrayList.remove(0);
                edgeArrayList.remove(0);
                i--;
            }
        }

        return result;
    }

    /**
     * 通过参数v1和v2找到那条边，如果不存在就返回null
     *
     * @param v1
     * @param v2
     * @return
     */
    public Edge find(int v1, int v2) {
        if (edgeMatrix[v1][v2] == Integer.MAX_VALUE) {
            return null;
        } else {
            return new Edge(v1, v2, edgeMatrix[v1][v2]);
        }
    }

    /**
     * prim算法求最小生成树
     *
     * @param starting
     * @return
     */
    public Graph1 prim(int starting) {
        Graph1 result = new Graph1(currentVertice);
        boolean[] booleans = new boolean[currentVertice + 1];
        int[] nearvex = new int[currentVertice + 1];
        int[] lowcost = new int[currentVertice + 1];

        for (int i = 1; i <= currentVertice; i++) {
            nearvex[i] = starting;
            booleans[i] = true;
        }

        nearvex[starting] = -1;
        booleans[starting] = false;
        result.insert(starting);
        for (int i = 1; i <= currentVertice; i++) {
            lowcost[i] = edgeMatrix[starting][i];
        }

        for (int i = 1; i < result.getMaxVertice(); i++) {
            int next = 0;

            for (int j = 1; j <= currentVertice; j++) {
                if (booleans[j]) {
                    next = j;
                    break;
                }
            }

            for (int j = next + 1; j <= currentVertice; j++) {
                if (booleans[j] && (lowcost[next] > lowcost[j])) {
                    next = j;
                }
            }

            result.insertEdge(nearvex[next], next, lowcost[next]);
            booleans[next] = false;

            for (int j = 1; j <= currentVertice; j++) {
                if (booleans[j] && (edgeMatrix[next][j] < lowcost[j])) {
                    lowcost[j] = edgeMatrix[next][j];
                    nearvex[j] = next;
                }
            }
        }

        return result;
    }

    /**
     * 通过Dijkstra算法算出v到所有顶点的最短路径
     *
     * @param v
     * @return
     */
    public int[][] Dijkstra(int v) {
        int past = v;
        int[] vToAll = new int[currentVertice + 1];
        int[] pastVertice = new int[currentVertice + 1];
        boolean[] ableToChange = new boolean[currentVertice + 1];

        for (int i = 1; i <= currentVertice; i++) {
            vToAll[i] = edgeMatrix[past][i];
            ableToChange[i] = true;
            pastVertice[i] = v;
        }

        vToAll[past] = 0;
        pastVertice[past] = 0;
        ableToChange[past] = false;

        for (int i = 1; i < currentVertice; i++) {
            int next = 0;

            for (int j = 1; j <= currentVertice; j++) {
                if (ableToChange[j]) {
                    next = j;
                    break;
                }
            }

            for (int j = next + 1; j <= currentVertice; j++) {
                if (ableToChange[j] && (vToAll[next] > vToAll[j])) {
                    next = j;
                }
            }

            ableToChange[next] = false;
            past = next;

            for (int j = 0; j <= currentVertice; j++) {
                if (ableToChange[j] && (vToAll[past] + edgeMatrix[past][j]) < vToAll[j] && edgeMatrix[past][j] != Integer.MAX_VALUE) {
                    vToAll[j] = vToAll[past] + edgeMatrix[past][j];
                    pastVertice[j] = past;
                }
            }

        }

        return new int[][]{vToAll, pastVertice};
    }

    /**
     * 通过Floyed算法算出所有顶点到其他顶点的最短路径
     * @return
     */
    public int[][] floyed(){
        int[][] allPath = edgeMatrix.clone();
        for(int i = 1;i<=currentVertice;i++){
            for(int j = 1;j<=currentVertice;j++){
                for(int k = 1;k<=currentVertice;k++){
                    if(allPath[j][i] + allPath[i][k] < allPath[j][k] && allPath[j][i] != Integer.MAX_VALUE && allPath[i][k] != Integer.MAX_VALUE){
                        allPath[j][k] = allPath[j][i] + allPath[i][k];
                    }
                }
            }
        }
        return allPath;
    }

    /**
     * 通过BellmanFord算法算出v到所有顶点的最短路径
     * 真尼玛的不想写第三个算法了，大概口述：
     * 第一步：起点到终点路径为1的长度
     * 第二步：以第一步的终点为中转站，在比较中确定最小的到终点路径为2的中转站
     * ······
     * 第n步：在无数次的迭代之后，起点到其余顶点的最小距离都确定下来了
     * 这条基本路径的长度上一定不会有重复的点，所以说，在一定次数的迭代之后就会有结果
     */

    /**
     * 将 edge matrix 打印出来
     */
    public void print(){
        for(int[] nums : edgeMatrix){
            int[] numsClone = nums;
            for(int i = 0;i<nums.length;i++){
                if(nums[i] == Integer.MAX_VALUE){
                    numsClone[i] = -1;
                }
            }
            System.out.println(Arrays.toString(numsClone));
        }
    }
}
