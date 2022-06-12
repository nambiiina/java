package com.example.demo;

import com.example.demo.genericity.Couple;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Java SE 8 !!!");

        /**
         * Genericty
         */
        Integer i1 = 3;
        Integer i2 = 7;
        Couple<Integer> ci = new Couple<Integer>(i1, i2);
        ci.show();

        Couple<Double> cd = new Couple<Double>(1.0, 3.5);
        cd.show();
        Double p = cd.getFirst();
        System.out.println("First element of couple cd = " + p);
    }
}
