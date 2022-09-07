package com.swordie.commands;

import com.swordie.utils.CommandNumbers;
import com.swordie.utils.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetByNameCommand extends Command {
    public GetByNameCommand() {
        number = CommandNumbers.GET_BY_NAME.getNumber();
    }

    @Override
    public String execute(List<Student> students) {
        Scanner scanner = new Scanner(System.in);
        List<Student> foundStudents = new ArrayList<>();

        System.out.println("Type name of the student");
        String name = scanner.nextLine();
        scanner.close();

        for (Student student : students) {
            if (student.getName().equals(name)) {
                foundStudents.add(student);
            }
        }

        StringBuilder found = new StringBuilder();

        for (Student student : foundStudents) {
            found.append(student.toString()).append("\n");
        }
        return found.toString();
    }
}
