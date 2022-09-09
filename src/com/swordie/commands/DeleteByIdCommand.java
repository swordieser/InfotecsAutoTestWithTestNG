package com.swordie.commands;

import com.swordie.main.Main;
import com.swordie.utils.CommandNumbers;
import com.swordie.utils.Student;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DeleteByIdCommand extends Command {
    public DeleteByIdCommand() {
        number = CommandNumbers.DELETE_BY_ID.getNumber();
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
            }
        }

        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                Main.id.add(0, id);
                return "Student has been deleted";
            }
        }

        return "No student with this id";
    }
}
