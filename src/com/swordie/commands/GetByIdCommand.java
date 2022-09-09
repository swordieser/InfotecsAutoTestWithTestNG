package com.swordie.commands;

import com.swordie.utils.CommandNumbers;
import com.swordie.utils.Student;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GetByIdCommand extends Command {
    public GetByIdCommand() {
        number = CommandNumbers.GET_BY_ID.getNumber();
    }

    @Override
    public String execute(List<Student> students, Scanner scanner) {
        int id = -1;

        System.out.println("Type id of the student");

        while (id == -1) {
            try {
                id = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input, please type number");

                id = -1;
            }
        }

        for (Student student : students) {
            if (student.getId() == id) {
                return student.toString();
            }
        }

        return "No student with this id";
    }
}
