package live.ashish.cpjava.interview;

public class Mather<T> {

    int sum(int a, int b){
        return a + b;
    }


    double divide(int a, int b){
        if(b==0)
            throw new ArithmeticException("Divide by 0");
//        return ((float)a)/b;
        return (a*1.0)/b;
    }


    int multiple(int a,int b){
        return a*b;
    }
}
