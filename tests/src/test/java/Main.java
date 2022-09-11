import org.testng.TestNG;

public class Main {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[]{FtpClientTest.class});
        testNG.run();
    }
}
