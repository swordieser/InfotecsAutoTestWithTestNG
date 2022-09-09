package com.swordie.console;

import com.swordie.client.FTPClient;
import com.swordie.commands.*;
import com.swordie.utils.CommandNumbers;
import com.swordie.utils.JSONParser;
import com.swordie.utils.Student;
import sun.net.ftp.FtpProtocolException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandManager {
    private final ArrayList<Command> commands;
    private final FTPClient ftpClient;
    private int commandId;

    public CommandManager(FTPClient ftpClient) {
        commands = new ArrayList<>();
        commands.add(new AddCommand());
        commands.add(new GetByIdCommand());
        commands.add(new GetByNameCommand());
        commands.add(new DeleteByIdCommand());
        commands.add(new StopCommand(ftpClient));
        this.ftpClient = ftpClient;
    }

    public String executeCommand(List<Student> students, Scanner scanner) {
        String answer = "";

        for (Command command : commands) {
            if (command.getNumber() == commandId) {
                answer = command.execute(students, scanner);

                if (commandId == CommandNumbers.ADD.getNumber() || commandId == CommandNumbers.DELETE_BY_ID.getNumber()) {
                    updateServerFile(students, ftpClient);
                }

                break;
            }
        }
        return answer;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public boolean updateServerFile(List<Student> students, FTPClient ftpClient) {
        String json = JSONParser.parseFromStudentsListToJson(students);
        try {
            File file = File.createTempFile("students", ".json");
            FileOutputStream fos = new FileOutputStream(file);

            fos.write(json.getBytes(StandardCharsets.UTF_8));

            FileInputStream fis = new FileInputStream(file);

            ftpClient.getFtp().putFile("/students.json", fis);

            fos.close();
            fis.close();
            Files.deleteIfExists(file.toPath());

            return true;
        } catch (IOException | FtpProtocolException e) {
            System.out.println("Something went wrong, didn't update server file");

            return false;
        }
    }
}
