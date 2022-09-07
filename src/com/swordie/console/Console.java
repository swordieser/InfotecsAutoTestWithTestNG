package com.swordie.console;

import com.swordie.utils.CommandNumbers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    private String server;
    private String login;
    private String password;

    public Console() {
        System.out.println("Welcome to FTPClient!");
    }

    public void getConnectionInformation(Scanner scanner) {
        System.out.println("Please type server ip");
        server = scanner.nextLine();
        System.out.println("Please type your login");
        login = scanner.nextLine();
        System.out.println("Please type your password");
        password = scanner.nextLine();
    }

    public int mainMenu(Scanner scanner) {

        int commandId = -1;

        System.out.println("Main menu\n" +
                "Please choose command (type it's id):\n" +
                CommandNumbers.ADD.getNumber() + ". add student\n" +
                CommandNumbers.GET_BY_ID.getNumber() + ". get student by id\n" +
                CommandNumbers.GET_BY_NAME.getNumber() + ". get student by name\n" +
                CommandNumbers.DELETE_BY_ID.getNumber() + ". delete student by id\n" +
                CommandNumbers.STOP.getNumber() + ". exit (turns off)");

        while (commandId == -1) {
            try {
//                commandId = scanner.nextInt();
                commandId = Integer.parseInt(scanner.nextLine());
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input, please type number");
            }

            if (commandId < 1 || commandId > CommandNumbers.values().length) {
                System.out.println("Please type number from 1 to " + CommandNumbers.values().length);
                commandId = -1;
            }
        }

        return commandId;
    }

    public String getServer() {
        return server;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
