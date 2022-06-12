package com.example.demo.genericity;

public class Couple<T> {
    private T first, second;

    public Couple(T first, T second) {
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
