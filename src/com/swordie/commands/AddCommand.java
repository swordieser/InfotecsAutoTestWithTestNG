package com.swordie.commands;

import com.swordie.main.Main;
import com.swordie.utils.CommandNumbers;
import com.swordie.utils.Student;

import java.util.List;
import java.util.Scanner;

public class AddCommand extends Command {
    public AddCommand(){
        number = CommandNumbers.ADD.getNumber();
    }
    @Override
    public String execute(List<Student> students) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type name of the student");
        String name = scanner.nextLine();
        int id = Main.id.pop();
        students.add(new Student(id, name));
        return "Successfully added student";
    }
}
