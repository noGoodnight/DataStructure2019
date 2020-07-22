package Chapter3.Stack;

import java.util.ArrayList;

public class ExpressionEvaluation {
    ArrayList<Object> signs;
    ArrayList<Double> integers;
    Operator bottle;
    Operator add;
    Operator sub;
    Operator mul;
    Operator div;
    ArrayList<Operator> operators;

    public ExpressionEvaluation() {
        signs = new ArrayList<>();
        integers = new ArrayList<>();
        operators = new ArrayList<>();
        bottle = new Operator("#", 0);
        signs.add(bottle);
        add = new Operator("+", 1);
        sub = new Operator("-", 1);
        mul = new Operator("*", 2);
        div = new Operator("/", 2);
        operators.add(add);
        operators.add(bottle);
        operators.add(sub);
        operators.add(mul);
        operators.add(div);
    }

    public double evaluate(String expression) {
        /**
         * 我这里写的并不是中缀到后缀再到计算结果的代码
         * 我传进来的是后缀，这里是我的错误
         * 中缀后缀求结果一边做的算法：
         * 1.建立放值的栈和放运算符的栈，并且在运算符栈底放一个优先级最低的#
         * 2.读中缀表达式
         *      1）左括号：左括号的值上升，运用左右括号对应的栈算法
         *      2）右括号：把此时在运算符栈里面的除#运算符统统弹出，并且计算数字栈中的值；左括号的值下降
         *      3）运算符：判断运算符栈顶的运算符和读到的运算符的优先级。栈顶运算符低则压栈，否则将栈顶运算符弹出直到栈顶运算符优先级不低于读到的运算符
         *      4）数字：把数字压栈进入数字栈
         * 3.没事弹出一个运算符都要执行一次计算，把数字栈栈顶的两个出栈并运算，然后把结果再压栈回去
         * 4.最后数字栈只剩下一个数字，那就是结果
         */
        double result;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                signs.add("(");

            } else if (expression.charAt(i) == ')') {
                while (!signs.get(signs.size() - 1).equals("(")) {
                    double tmp = 0;
                    if (signs.get(signs.size() - 1).equals(add)) {
                        tmp = integers.get(integers.size() - 2) + integers.get(integers.size() - 1);
                    } else if (signs.get(signs.size() - 1).equals(sub)) {
                        tmp = integers.get(integers.size() - 2) - integers.get(integers.size() - 1);
                    } else if (signs.get(signs.size() - 1).equals(mul)) {
                        tmp = integers.get(integers.size() - 2) * integers.get(integers.size() - 1);
                    } else if (signs.get(signs.size() - 1).equals(div)) {
                        tmp = integers.get(integers.size() - 2) / integers.get(integers.size() - 1);
                    }
                    integers.remove(integers.size() - 1);
                    integers.remove(integers.size() - 1);
                    integers.add(tmp);
                    signs.remove(signs.size() - 1);
                }
                signs.remove(signs.size() - 1);
            } else if (expression.charAt(i) >= '0' && '9' >= expression.charAt(i)) {
                integers.add(Double.parseDouble(expression.substring(i, i + 1)));
            } else if (expression.charAt(i) == '#') {
                while (signs.size() != 1) {
                    double tmp = 0;
                    if (signs.get(signs.size() - 1).equals(add)) {
                        tmp = integers.get(integers.size() - 2) + integers.get(integers.size() - 1);
                    } else if (signs.get(signs.size() - 1).equals(sub)) {
                        tmp = integers.get(integers.size() - 2) - integers.get(integers.size() - 1);
                    } else if (signs.get(signs.size() - 1).equals(mul)) {
                        tmp = integers.get(integers.size() - 2) * integers.get(integers.size() - 1);
                    } else if (signs.get(signs.size() - 1).equals(div)) {
                        tmp = integers.get(integers.size() - 2) / integers.get(integers.size() - 1);
                    }
                    integers.remove(integers.size() - 1);
                    integers.remove(integers.size() - 1);
                    integers.add(tmp);
                    signs.remove(signs.size() - 1);
                }
            } else {
                Operator operator = search(expression.substring(i, i + 1));
                if (signs.get(signs.size() - 1) instanceof Operator) {
                    try {
                        while (operator.rank <= ((Operator) signs.get(signs.size() - 1)).rank) {
                            double tmp = 0;
                            if (signs.get(signs.size() - 1).equals(add)) {
                                tmp = integers.get(integers.size() - 2) + integers.get(integers.size() - 1);
                            } else if (signs.get(signs.size() - 1).equals(sub)) {
                                tmp = integers.get(integers.size() - 2) - integers.get(integers.size() - 1);
                            } else if (signs.get(signs.size() - 1).equals(mul)) {
                                tmp = integers.get(integers.size() - 2) * integers.get(integers.size() - 1);
                            } else if (signs.get(signs.size() - 1).equals(div)) {
                                tmp = integers.get(integers.size() - 2) / integers.get(integers.size() - 1);
                            }
                            integers.remove(integers.size() - 1);
                            integers.remove(integers.size() - 1);
                            integers.add(tmp);
                            signs.remove(signs.size() - 1);
                        }
                    } catch (Exception e) {
                    }finally {
                        signs.add(operator);
                    }
                } else {
                    signs.add(operator);
                }
            }
        }
        result = integers.get(0);
        return result;
    }

    public Operator search(String s) {
        if (s.equals("+")) {
            return add;
        } else if (s.equals("-")) {
            return sub;
        } else if (s.equals("*")) {
            return mul;
        } else if (s.equals("/")) {
            return div;
        }
        return null;
    }
}