package com.swordie.utils;

public enum CommandNumbers {
    ADD(1),
    GET_BY_ID(2),
    GET_BY_NAME(3),
    DELETE_BY_ID(4),
    STOP(5);

    int number;

    CommandNumbers(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
