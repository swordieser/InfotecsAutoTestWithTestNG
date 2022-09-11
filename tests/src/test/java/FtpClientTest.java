import com.swordie.client.FTPClient;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class FtpClientTest {
    private String server = "192.168.193.99";
    private String login = "swordie";
    private String password = "swordie";

    @Test
    public void successfulConnectToServer() {
        FTPClient ftpClient = new FTPClient(server, login, password);
        ftpClient.open();
        Assert.assertTrue(ftpClient.getFtp().isConnected());
    }

    // Проверка на то, что программа не падает при некорректном вводе
    @Test
    public void noConnectWithWrongData() {
        FTPClient ftpClient = new FTPClient(server, "user", "user");
        Assert.assertNull(ftpClient.getFtp());
    }

    @Test
    public void successfulConnectAndSuccessfulDisconnect() throws IOException {
        FTPClient ftpClient = new FTPClient(server, login, password);
        ftpClient.open();
        ftpClient.close();
        Assert.assertFalse(ftpClient.getFtp().isConnected());
    }
}
