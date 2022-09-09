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
    public String execute(List<Student> students) {
        Scanner scanner = new Scanner(System.in);
        int id = -1;

        System.out.println("Type id of the student");

        while (id == -1) {
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input, please type number");
            }
        }

        scanner.close();

        for (Student student : students) {
            if (student.getId() == id) {
                students.remove(student);
                Main.id.add(id);
                return "Student has been deleted";
            }
        }

        return "No student with this id";
    }
}
