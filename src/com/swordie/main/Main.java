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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int id = 1;

    // 192.168.193.99


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students;
        Console console = new Console();


        console.getConnectionInformation(scanner);

        FTPClient ftpClient = new FTPClient(console.getServer(), console.getLogin(), console.getPassword());
        CommandManager manager = new CommandManager(ftpClient);

        ftpClient.open();
        students = getAllInformationFromServer(ftpClient);

        int commandId = console.mainMenu(scanner);

        manager.setCommandId(commandId);
        manager.executeCommand(students);
    }

    private static List<Student> getAllInformationFromServer(FTPClient ftpClient) {
        try {
            File file = File.createTempFile("temp", ".txt");
            FileOutputStream fos = new FileOutputStream(file);
            ftpClient.getFtp().getFile("/students.json", fos);
            Scanner scanner = new Scanner(new FileInputStream(file));
            String json = scanner.useDelimiter("\\A").next();
            scanner.close();
            return JSONParser.parseFromJsonToStudentsList(json);
        } catch (IOException | FtpProtocolException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
