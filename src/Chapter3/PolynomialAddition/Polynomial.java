package Chapter3.PolynomialAddition;

public class Polynomial {
    ListNode header;

    public Polynomial() {
        header = new ListNode();
    }

    public Iterator zeroth() {
        return new Iterator(header);
    }

    public Iterator first() {
        return new Iterator(header.next);
    }

    public Iterator find(int exponent) {
        for (Iterator iterator = first(); !iterator.isPastEnd(); iterator.advance()) {
            if (iterator.current.exponent == exponent) {
                return iterator;
            }
        }
        return null;
    }

    public void remove(int exponent) {
        Iterator iterator = findPrevious(exponent);
        iterator.current.next = iterator.current.next.next;
    }

    public Iterator findPrevious(int exponent) {
        for (Iterator iterator = zeroth(); !(iterator.current.next == null); iterator.advance()) {
            if (iterator.current.next.exponent == exponent) {
                return iterator;
            }
        }
        return null;
    }

    public void insert(ListNode theNode, Iterator iterator) {
        theNode.next = iterator.current.next;
        iterator.current.next = theNode;
    }

    public void sort() {
        // sort according to the exponent
        for (Iterator it = first(); !it.isPastEnd(); it.advance()) {
            for (Iterator iterator = zeroth(); iterator.current.next.next != null; iterator.advance()) {
                if (iterator.current.next.exponent < iterator.current.next.next.exponent) {
                    ListNode tmp1;
                    ListNode tmp2;
                    tmp1 = iterator.current.next;
                    tmp2 = iterator.current.next.next.next;
                    iterator.current.next = tmp1.next;
                    iterator.current.next.next = tmp1;
                    iterator.current.next.next.next = tmp2;
                }
            }
        }
    }

    public String toString() {
        String result = "";
        Iterator iterator = first();
        for (; !iterator.isPastEnd(); iterator.advance()) {
            result = iterator.current.toString() + result;
            result = " + " + result;
        }
        result = result.substring(3);
        return result;
    }

    public Polynomial add(Polynomial another) throws CloneNotSupportedException {
        Polynomial result = new Polynomial();
        Iterator tracer = result.zeroth();
        Iterator thisIterator = first();
        Iterator anotherIterator = another.first();
        while (!thisIterator.isPastEnd() && !anotherIterator.isPastEnd()) {
            if (thisIterator.current.exponent == anotherIterator.current.exponent) {
                if (thisIterator.current.significand + anotherIterator.current.significand != 0) {
                    result.insert(new ListNode(thisIterator.current.exponent, thisIterator.current.significand + anotherIterator.current.significand), tracer);
                    tracer.advance();
                    thisIterator.advance();
                    anotherIterator.advance();
                } else {
                    thisIterator.advance();
                    anotherIterator.advance();
                    continue;
                }
            } else if (thisIterator.current.exponent > anotherIterator.current.exponent){
                result.insert((ListNode) thisIterator.current.clone(),tracer);
                tracer.advance();
                thisIterator.advance();
            }else if(thisIterator.current.exponent < anotherIterator.current.exponent){
                result.insert((ListNode)anotherIterator.current.clone(),tracer);
                tracer.advance();
                anotherIterator.advance();
            }
        }
        if(thisIterator.isPastEnd()){
            while(!anotherIterator.isPastEnd()) {
                result.insert((ListNode) anotherIterator.current.clone(), tracer);
                tracer.advance();
                anotherIterator.advance();
            }
        }else{
            while(!thisIterator.isPastEnd()) {
                result.insert((ListNode) thisIterator.current.clone(), tracer);
                tracer.advance();
                thisIterator.advance();
            }
        }
        return result;
    }
}
