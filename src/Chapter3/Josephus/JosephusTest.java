package Chapter3.Josephus;

import java.util.ArrayList;

public class JosephusTest {
    public static void main(String[] args) {
        int n = 8;
        int m = 3;
        LinkedCircleList linkedCircleList = new LinkedCircleList(n, m);

        System.out.println(solution2(n, m));
    }

    public static String solution2(int n, int m) {
        ArrayList<String> list = new ArrayList<>();
        int rank = 1;
        int sign = 0;

        for (int i = 1; i <= n; i++) {
            list.add(i + "");
        }
        while (list.size() != 1) {
            if (rank == m) {
                list.remove(sign);
                rank = 1;

            } else {
                rank++;
                sign++;
            }
            if (sign >= list.size()) {
                sign = sign - list.size();
            }
        }
        return list.get(sign);
    }
}
