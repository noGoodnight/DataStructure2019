package Chapter3.Stack;

public class Operator {
    private String operator;
    int rank;

    public Operator(String operator, int rank){
        this.operator = operator;
        this.rank = rank;
    }

    public String toString(){
        return operator;
    }

    public boolean advanced(Operator another){
        return rank > another.rank;
    }
}
