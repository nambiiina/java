package com.example.demo.genericity;

public class MethodGen<T> {

    public static <U> U hasard (U[] values) {
        int n = values.length;
        if (n == 0) return null;
        int i = (int) (n * Math.random());
        return values[i];
    }

    public static <V> V hasard (V e1, V e2) {
        double x = Math.random();
        if (x < 0.5) return e1;
        return e2;
    }
}
