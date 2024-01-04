package live.ashish.cpjava.test;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
//        String testString = "ExaMple";
        System.out.println("Hi, please enter your name!");
        Scanner sc =new Scanner(System.in);

        String testString = sc.nextLine();
        System.out.println("Hello "+testString);
    }
}
