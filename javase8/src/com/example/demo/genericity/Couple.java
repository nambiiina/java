package com.example.demo.genericity;

public class Couple<T, U> {
    private T first;
    private U second;

    public Couple(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public void show() {
        System.out.println("first value : " + first + " - second value : " + second);
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}
