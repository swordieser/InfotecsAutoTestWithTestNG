package com.swordie.commands;

import com.swordie.client.FTPClient;
import com.swordie.utils.Student;

import java.util.List;

public abstract class Command {
    protected int number;

    public int getNumber() {
        return number;
    }

    public abstract String execute(List<Student> students);
}
