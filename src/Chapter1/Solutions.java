package Chapter1;

import java.util.ArrayList;


public class Solutions {
    array<array<Integer>> results = new array<array<Integer>>();
    int sum = 6;

    public void calculate(int n, int r, ArrayList<Integer> ctx){
        array<Integer> next = (array<Integer>) ctx.clone();

        if(n==r){
            for(int i = r;i>0;i--){
                next.add(i);
            }
            results.add(next);
            next.print();
        }else{
            if(r==1){
                for(int i = n;i>0;i--) {
                    array<Integer> temp = (array<Integer>) next.clone();
                    temp.add(i);
                    results.add(temp);
                    temp.print();
                }

            }else{
                calculate(n-1,r,next);
                next.add(n);
                calculate(n-1,r-1,next);
            }
        }
    }

    public void process(int location,int destination,int floors){
        if(floors>1){
            process(location,sum-destination-location,floors-1);
            change(location,destination,floors);
            process(sum-destination-location,destination,floors-1);
        }else{
            change(location,destination,floors);
        }
    }

    public void change(int location,int destination,int floor){
        System.out.println("Change plate with the size of "+floor+" from "+location+" to "+destination);
    }

    public int get1InBinaryRepresentation(int n , int sum){
        if(n == 0){
            return 0 + sum;
        }else if(n == 1){
            return 1 + sum;
        }else{
            if(n%2 == 1) {
                return get1InBinaryRepresentation(n / 2, sum + 1);
            }else{
                return get1InBinaryRepresentation(n/2,sum);
            }
        }
    }

    public int getMax(int[] a, int left, int right){
        if(right == left + 1){
            return a[left];
        }else{
            if(a[left] > getMax(a, left+1, right)){
                return a[left];
            }else{
                return getMax(a, left+1, right);
            }
        }
    }

    public double getAverage(int[] a, int left, int right){
        if(right == left + 1){
            return (double)a[left];
        }else{
            return (a[left] + getAverage(a, left+1, right) * (right - left - 1))/(right - left);
        }
    }

    public int getLengthOfLinkedList(ElementOfLinkedList startingPoint){
        if(startingPoint.hasNext()){
            return 1 + getLengthOfLinkedList(startingPoint.getNext());
        }else{
            return 1;
        }
    }

    public boolean isHUIWEN(String s, int left, int right){
        s = s.toLowerCase();
        String string = "";
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)<='z' && s.charAt(i)>='a'){
                string = string + s.charAt(i);
            }else{
                right--;
            }
        }
        if(right - left < 2){
            return true;
        }else{
            if(string.charAt(left) == string.charAt(right)){
                return true&isHUIWEN(string,left+1,right-1);
            }else{
                return false;
            }
        }
    }

    public String negative(int num){
        String temp = "";
        int addition = 1;
        while(num > 1){
            temp = (1 - num%2 + addition)%2 + temp;
            addition = (1 - num%2 + addition)/2;
            num = num/2;
        }
        temp = (1 - num%2 + addition)%2 + temp;
        return temp;
    }

    public String point(float f){
        String temp = "";
        while(f != 0){
            f = f * 2;
            if(f >= 1.0){
                temp = temp + "1";
                f = (float) (f - 1.0);
            }else{
                temp = temp + "0";
            }
        }
        return temp;
    }

}

