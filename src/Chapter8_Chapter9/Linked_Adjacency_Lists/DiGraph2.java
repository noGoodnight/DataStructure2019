package Chapter8_Chapter9.Linked_Adjacency_Lists;

import java.util.ArrayList;
import java.util.Arrays;

import Chapter8_Chapter9.Edge;

public class DiGraph2 {
    // 讨论有向图

    private ArrayList<Edge>[] edge;
    private int currentEdge;
    private int maxVertice;
    private int[] vertices;
    private ArrayList<Edge> edges;
    private int currentVertice;
    private int[] count; // 记录每个顶点的入度

    /**
     * @param i
     */
    public DiGraph2(int i) {
        maxVertice = i;
        currentEdge = 0;
        currentVertice = 0;
        vertices = new int[maxVertice + 1];
        edge = new ArrayList[maxVertice + 1];
        edges = new ArrayList<>();
        count = new int[maxVertice + 1];

        for (int j = 0; j <= maxVertice; j++) {
            vertices[j] = -1;
            edge[j] = new ArrayList<>();
            edge[j].add(new Edge(-1, j, Integer.MAX_VALUE));
            count[j] = 0;
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
            edges.add(new Edge(v1, v2, weight));
            edge[v1].add(edges.get(currentEdge));
            currentEdge++;
            count[v2]++;
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
            for (int i = 1; i <= maxVertice; i++) {
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
        Edge edge1 = findEdge(v1,v2);

        if (edge1 != null) {
            edge[v1].remove(edge1);
            edges.remove(edge1);
            count[v2]--;
            currentEdge--;
        }
    }

    /**
     *
     * @param v1
     * @param v2
     * @return
     */
    public Edge findEdge(int v1,int v2){
        Edge edge1 = null;
        for (Edge e : edge[v1]) {
            if (e.v2 == v2 && e.v1 == v1) {
                edge1 = e;
                break;
            }
        }
        return edge1;
    }

    /**
     * @param v
     * @return
     */
    public int getFirstNeighbour(int v) {
        if (vertices[v] != -1) {
            try {
                if (edge[v].get(1).weight != Integer.MAX_VALUE) {
                    return edge[v].get(1).v2;
                }
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }

    /**
     * @param v
     * @return
     */
    public int getNextNeighbour(int v) {
        if (vertices[v] != -1) {
            try {
                if (edge[v].get(2).weight != Integer.MAX_VALUE) {
                    return edge[v].get(2).v2;
                }
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }

    /**
     * 通过拓扑排序实现AOV网络的排序
     * 前提：图内并没有回路
     *
     * @return
     */
    public int[] topSort() {
        ArrayList<Edge>[] edgeClone = edge.clone();
        for(int i = 1;i<edge.length;i++){
            edgeClone[i] = (ArrayList<Edge>) edge[i].clone();
        }
        int currentEdgeClone = currentEdge;
        int maxVerticeClone = maxVertice;
        int[] verticesClone = vertices.clone();
        ArrayList<Edge> edgesClone = (ArrayList<Edge>) edges.clone();
        int currentVerticeClone = currentVertice;
        int[] countClone = count.clone();

        int top;
        int[] result = new int[currentVertice + 1];
        int mark = 1;
        boolean[] ableToChange = new boolean[currentVertice + 1];

        for (int i = 1; i <= currentVertice; i++) {
            ableToChange[i] = true;
        }

        while (currentVertice != 0) {
            top = 0;
            for (int i = 1; i <= currentVerticeClone; i++) {
                if (count[i] == 0 && ableToChange[i]) {
                    top = i;
                    break;
                }
            }
            if (top == 0) {
                break;
            }
            result[mark] = top;
            removeVertice(top);
            ableToChange[top] = false;
            mark++;
        }

        edge = edgeClone;
        currentEdge = currentEdgeClone;
        maxVertice = maxVerticeClone;
        vertices = verticesClone;
        edges = edgesClone;
        currentVertice = currentVerticeClone;
        count = countClone;
        return result;
    }

    /**
     * AOE关键路径
     *
     * 我个人认为关键路径的定义和最短路径有异曲同工之妙
     * 寻找最短路径通过求最小值来判断，其中不直接连通的道路用的是Integer类的最大值
     * 所以可以把不直接连通的道路设置为Integer类的最小值，从而对路径求最大值
     *
     * 阿汤哥上课笔记：
     * 算法核心——
     * 求一个事件的最早开始时间，是事件的起点到源顶点的关键路径；
     * 求一个事件的最迟开始时间，是总事件的关键路径 - 事件的终点到目标顶点的关键路径
     * 迭代算法：求某个顶点到源的关键路径，是它的邻接点到源的关键路径当中的最小值，然后可以形成递归
     * 具体内容读者可以以为是笔者太懒，所以不想写这些奇怪的东西
     */

    /**
     * 判断是否有回路，如果有，将回路打印出来
     *
     * @return
     */
    public boolean printCircle() {
        ArrayList<Edge>[] edgeClone = edge.clone();
        for(int i = 1;i<edge.length;i++){
            edgeClone[i] = (ArrayList<Edge>) edge[i].clone();
        }
        int currentEdgeClone = currentEdge;
        int maxVerticeClone = maxVertice;
        int[] verticesClone = vertices.clone();
        ArrayList<Edge> edgesClone = (ArrayList<Edge>) edges.clone();
        int currentVerticeClone = currentVertice;
        int[] countClone = count.clone();

        //备份完原本数据之后，定义方法变量
        boolean result = false;
        int top = 0;
        int[] path = new int[currentVertice + 2];
        int pathLong = 0;
        int[] undoTimes = new int[currentVertice + 1];
        boolean undo = false;
        boolean[] ableToChange = new boolean[currentVertice + 1];

        //初始化
        for (int i = 1; i <= currentVertice; i++) {
            ableToChange[i] = true;
            undoTimes[i] = 0;
        }

        //拓扑排序里面清除掉非回路的部分
        while (currentVertice != 0) {
            top = 0;
            for (int i = 1; i <= currentVerticeClone; i++) {
                if (count[i] == 0 && ableToChange[i]) {
                    top = i;
                    break;
                }
            }
            if (top == 0) {
                result = true;
                break;
            }
            removeVertice(top);
            ableToChange[top] = false;
        }

        //find the circle and avoid complex circle existing
        int key;
        if (top == 0) {
            key = 0;
            //这说明count里有的值仍然大于0，因此表示仍然存在回路
            //但不管是单重回路还是多个回路重合，都可以选择一条边然后走完整个环路
            while (currentEdge != 0) {
                if (key == path[1] && pathLong != 1) {

                    // TODO 进来拿第一个顶点，顺便把之前的路径输出掉
                    for (int i = 1; i < pathLong; i++) {
                        System.out.print(path[i] + " --> ");
                        removeEdge(path[i], path[i+1]);
                    }
                    if(key!=0) {
                        System.out.println(key);
                    }

                    // TODO 初始化
                    for (int i = 1; i <= currentVerticeClone; i++) {
                        path[i] = 0;
                        undoTimes[i] = 0;
                    }
                    key = 0;

                    // TODO 确定第一个顶点
                    for (int i = 1; i <= currentVerticeClone; i++) {
                        if (edge[i].size() > 1) {
                            key = i;
                            pathLong = 1;
                            break;
                        }
                    }

                    if (key == 0) {
                        break;
                    }
                }

                // TODO 给路径上的顶点赋值
                path[pathLong] = key;

                // TODO 找到路径上的下一个顶点并且暂时增长路径长度
                int v = edgeClone[key].get(1 + undoTimes[key]).v2;
                pathLong++;
                // TODO 如果”贪吃蛇咬到了自己的身体“
                for (int i = 2; i < pathLong; i++) {
                    if (path[i] == v ) {
                        undo = true;
                        break;
                    }
                }
                // TODO 如果这条边对应的出度不为0，即有其他路可以走，而且选定的这条边已经被删了，那么也要”后撤步“
                if(edge[key].size() > 1 && !edge[key].contains(findEdge(key,v))){
                    undo = true;
                }

                if (undo) {
                    while (undo) {
                        // TODO 依次后撤直到找到可以被撤回的那条边
                        if (undoTimes[key] + 1 == edgeClone[key].size() - 1) {
                            pathLong--;
                            key = path[pathLong];
                        } else {
                            undoTimes[key]++;
                            pathLong--;
                            undo = false;
                        }
                    }
                } else {
                    // TODO 将刚刚暂时保存的边保留下来
                    key = v;
                    path[pathLong] = v;
                }

            }
        }


        edge = edgeClone;
        currentEdge = currentEdgeClone;
        maxVertice = maxVerticeClone;
        vertices = verticesClone;
        edges = edgesClone;
        currentVertice = currentVerticeClone;
        count = countClone;

        return result;
    }

    /**
     * 将 edge matrix 打印出来
     */
    public void print(){
        for(ArrayList<Edge> arrayList : edge){
            for(Edge e : arrayList){
                System.out.print(e.v1 + "-->"+e.v2+"  ");
            }
            System.out.println();
        }
    }
}