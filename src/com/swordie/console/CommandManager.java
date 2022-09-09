package com.swordie.console;

import com.swordie.client.FTPClient;
import com.swordie.commands.*;
import com.swordie.utils.CommandNumbers;
import com.swordie.utils.JSONParser;
import com.swordie.utils.Student;
import jdk.jfr.events.FileWriteEvent;
import sun.net.ftp.FtpProtocolException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private int commandId;
    private final ArrayList<Command> commands;
    private final FTPClient ftpClient;

    public CommandManager(FTPClient ftpClient) {
        commands = new ArrayList<>();
        commands.add(new AddCommand());
        commands.add(new GetByIdCommand());
        commands.add(new GetByNameCommand());
        commands.add(new DeleteByIdCommand());
        commands.add(new StopCommand(ftpClient));
        this.ftpClient = ftpClient;
    }

    public String executeCommand(List<Student> students) {
        String answer = "";
        for (Command command : commands) {
            if (command.getNumber() == commandId) {
                answer = command.execute(students);
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
//            FileWriter fw = new FileWriter(file);
            FileOutputStream fos = new FileOutputStream(file);
//            fw.write(json);
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
