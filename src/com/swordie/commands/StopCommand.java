package com.swordie.commands;

import com.swordie.client.FTPClient;
import com.swordie.utils.CommandNumbers;
import com.swordie.utils.Student;

import java.io.IOException;
import java.util.List;

import static java.lang.System.exit;

public class StopCommand extends Command {
    private final FTPClient ftpClient;

    public StopCommand(FTPClient ftpClient) {
        number = CommandNumbers.STOP.getNumber();
        this.ftpClient = ftpClient;
    }

    @Override
    public String execute(List<Student> students) {
        try {
            ftpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Closed connection with ftp server");
        exit(0);
        return "";
    }
}
