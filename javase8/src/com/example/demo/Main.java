package com.example.demo;

import com.example.demo.genericity.Couple;
import com.example.demo.genericity.MethodGen;

public class Main {

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
         * Generic method with one argument
         */
        Integer[] tabi = {1, 5, 4, 9};
        System.out.println("hasard sur tabi = " + MethodGen.hasard(tabi));
        String[] tabs = {"bonjour", "salut", "hello"};
        System.out.println("hasard sur tabs = " + MethodGen.hasard(tabs));

        /**
         * Generic method with two arguments
         */
        Integer n1 = 2;
        Integer n2 = 5;

        System.out.println("hasard (n1, n2) : " + MethodGen.hasard(n1, n2));

        Double x1 = 2.5;
        System.out.println("hasard (n1, x1) : " + MethodGen.hasard(n1, x1)); // erasure (n1 = n2 = Object)

        // Forcera le compilateur à vérifier que les arguments (n1 et x1) sont bien d'un type compatible avec Double
        //MethodGen.<Double>hasard(n1, x1); // compilation error

        // (n1 et x1) sont d'un type compatible avec Number
        System.out.println("hasard (n1, x1) with type verification : " + MethodGen.<Number>hasard(n1, x1)); // worked

    }
}

class Test<T> {
    public <U> void f(U u) {
        System.out.println("Value of parameter : " + u);
    }
}
