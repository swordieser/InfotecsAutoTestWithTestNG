package com.swordie.main;


import com.swordie.client.FTPClient;
import com.swordie.console.CommandManager;
import com.swordie.console.Console;
import com.swordie.utils.JSONParser;
import com.swordie.utils.Student;
import sun.net.ftp.FtpProtocolException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static LinkedList<Integer> id = new LinkedList<>();

    // 192.168.193.99
    // 172.28.111.196
    // 192.168.0.9

    public static void main(String[] args) {
        int maxId = 1000;
        for (int i = 1; i <= maxId; i++) {
            id.add(i);
        }
        Scanner scanner = new Scanner(System.in);
        List<Student> students;
        Console console = new Console();


        console.getConnectionInformation(scanner);

        FTPClient ftpClient = new FTPClient(console.getServer(), console.getLogin(), console.getPassword());
        CommandManager manager = new CommandManager(ftpClient);

        ftpClient.open();
        students = getAllInformationFromServer(ftpClient);

//        while (true) {
//            int commandId = console.mainMenu(scanner);
//
//            manager.setCommandId(commandId);
//            String answer = manager.executeCommand(students);
//
//            System.out.println(answer);
//
//            if (answer.equals("exit")){
//                exit(0);
//            }
//        }
        int commandId = console.mainMenu(scanner);

        manager.setCommandId(commandId);
        String answer = manager.executeCommand(students);

        System.out.println(answer);
    }

    private static List<Student> getAllInformationFromServer(FTPClient ftpClient) {
        try {
            File file = File.createTempFile("tempStudent", ".txt");
            FileOutputStream fos = new FileOutputStream(file);
            ftpClient.getFtp().getFile("students.json", fos);
            FileInputStream fis = new FileInputStream(file);
            Scanner scanner = new Scanner(fis);
            if (!scanner.hasNext()) {
                scanner.close();
                fos.close();
                fis.close();
                Files.deleteIfExists(file.toPath());
                return new ArrayList<>();
            }
            String json = scanner.useDelimiter("\\A").next();
            scanner.close();
            fos.close();
            fis.close();
            Files.deleteIfExists(file.toPath());
            List<Student> students = JSONParser.parseFromJsonToStudentsList(json);
            for (Student student : students) {
                if (Main.id.contains(student.getId())){
                    Main.id.remove((Integer) student.getId());
                }
            }
            return students;
        } catch (IOException | FtpProtocolException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
