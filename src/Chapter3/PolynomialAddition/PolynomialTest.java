package Chapter3.PolynomialAddition;

import Chapter3.PolynomialAddition.ListNode;
import Chapter3.PolynomialAddition.Polynomial;

public class PolynomialTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        int[] exponents = {0,1,6,3,8,5};
        int[] exponent = {2,1,6,4,9,5};
        int[] significands = {1,1,6,3,8,5};
        int[] significand = {3,1,-6,7,8,11};
        Polynomial polynomial = new Polynomial();
        Polynomial polynomial1 = new Polynomial();

        for(int i = 0;i<exponents.length;i++){
            polynomial.insert(new ListNode(exponents[i],significands[i]),polynomial.zeroth());
            polynomial1.insert(new ListNode(exponent[i],significand[i]),polynomial1.zeroth());
        }

        polynomial.sort();
        polynomial1.sort();

        System.out.println(polynomial.add(polynomial1).toString());
    }
}
