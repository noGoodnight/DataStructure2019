package Chapter8_Chapter9;

public class Edge implements Comparable{
    public int v1;
    public int v2;
    public int weight;

    public Edge(int i, int j, int w) {
        v1 = i;
        v2 = j;
        weight = w;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Edge){
            if(((Edge) o).weight == weight){
                return 0;
            }else if(((Edge) o).weight > weight){
                return -1;
            }else{
                return 1;
            }
        }else{
            return 0;
        }
    }

}
