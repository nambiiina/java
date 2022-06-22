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
        Couple<Integer, Integer> ci = new Couple<Integer, Integer>(i1, i2);
        ci.show();

        Couple<Double, Double> cd = new Couple<Double, Double>(1.0, 3.5);
        cd.show();
        Double p = cd.getFirst();
        System.out.println("First element of couple cd = " + p);
    }
}

class Test<T> {
    private T first, second;

    public Test(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public void show() {
        System.out.println("first value : " + first + " - second value : " + second);
    }

    public T getFirst() {
        return first;
    }
}
