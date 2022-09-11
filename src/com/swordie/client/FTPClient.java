package com.swordie.client;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class FTPClient {
    private String server;
    private final int port = 21;
    private String login;
    private String password;
    private FtpClient ftp;

    public FTPClient(String server, String user, String password) {
        this.server = server;
        this.login = user;
        this.password = password;
    }

    public void open() {
        ftp = FtpClient.create();
        SocketAddress dest = new InetSocketAddress(server, port);

        try {
            ftp.connect(dest);
        } catch (IOException e) {
            System.out.println("Connection failed");
        } catch (FtpProtocolException e) {
            System.out.println("FtpProtocolException");
        }

        try {
            ftp.login(login, password.toCharArray());
        } catch (FtpProtocolException e) {
            System.out.println("Incorrect login");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public void close() throws IOException {
        ftp.close();
    }

    public FtpClient getFtp() {
        return ftp;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
