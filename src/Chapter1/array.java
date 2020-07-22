package Chapter1;

import java.util.ArrayList;

public class array<E> extends ArrayList<E> {
    String string = "";

    public String toString() {
        for (E i : this) {
            string = string + i.toString();
        }
        return string;
    }

    public void print() {
        System.out.println(this.toString());
    }

}
