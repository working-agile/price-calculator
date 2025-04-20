package com.workingagile.acsd.example;

public class Calculator {

    private int value;


    public Calculator(int initialValue) {
        value = initialValue;
    }

    public int getValue() {
        return value;
    }

    public void add(int amount) {
        value+=amount;
    }

    public void subtract(int amount) {
        value-=amount;
    }

    public void clear() {
        value = 0;
    }
}
