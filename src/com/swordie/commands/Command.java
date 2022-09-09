package com.swordie.commands;

import com.swordie.utils.Student;

import java.util.List;
import java.util.Scanner;

public abstract class Command {
    protected int number;

    public int getNumber() {
        return number;
    }

    public abstract String execute(List<Student> students, Scanner scanner);
}
