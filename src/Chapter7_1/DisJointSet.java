package Chapter7_1;

public class DisJointSet {
    //only use through Integers
    private int[] parents;
    private boolean[] roots;

    public DisJointSet(int size){
        parents = new int[size + 1];
        roots = new boolean[size + 1];
        for(int i = 1; i <= size; i++){
            parents[i] = 0;
            roots[i] = true;
        }
    }

    public int find(int i){
        if(roots[i]){
            return i;
        }else {
            return parents[i] = find(parents[i]);
        }
    }

    public void union(int i, int j){
        // weight rule
        if(parents[i] > parents[j]){
            parents[i] = parents[i] + parents[j];
            roots[j] = false;
            parents[j] = i;
        }else{
            parents[j] = parents[i] + parents[j];
            roots[i] = false;
            parents[i] = j;
        }
    }
}
