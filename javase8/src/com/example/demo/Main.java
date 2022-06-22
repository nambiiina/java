package com.example.demo;

import com.example.demo.genericity.Couple;

public class Main {

    static <T> T hasard (T[] values) {
        int n = values.length;
        if (n == 0) return null;
        int i = (int) (n * Math.random());
        return values[i];
    }

    public static void main(String[] args) {
        System.out.println("Hello Java SE 8 !!!");

        /**
         * Generic class
         */
        Integer i1 = 3;
        Integer i2 = 7;
        Couple<Integer, Integer> ci = new Couple<Integer, Integer>(i1, i2);
        ci.show();

        Couple<Double, Double> cd = new Couple<Double, Double>(1.0, 3.5);
        cd.show();
        Double p = cd.getFirst();
        System.out.println("First element of couple cd = " + p);

        /**
         * Generic method
         */
        Integer[] tabi = {1, 5, 4, 9};
        System.out.println("hasard sur tabi = " + hasard(tabi));
        String[] tabs = {"bonjour", "salut", "hello"};
        System.out.println("hasard sur tabs = " + hasard(tabs));

        Test<Integer> test = new Test<>();
        test.f(1);
    }
}

class Test<T> {
    public <U> void f(U u) {
        System.out.println("Value of parameter : " + u);
    }
}
