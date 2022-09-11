import com.swordie.client.FTPClient;
import com.swordie.commands.*;
import com.swordie.main.Main;
import com.swordie.utils.Student;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CommandsTest {
    private Scanner scanner;


    @Test
    public void addCommandTest() {
        String name = "test";
        Main.id = new LinkedList<>();
        Main.id.add(1);
        List<Student> students = new ArrayList<>();

        System.setIn(new ByteArrayInputStream(name.getBytes()));
        scanner = new Scanner(System.in);

        Command command = new AddCommand();
        Assert.assertEquals(command.execute(students, scanner), "Successfully added student");
        Assert.assertEquals(students.size(), 1);
    }

    @Test
    public void deleteByIdCommandTest() {
        int id = 1;
        String name = "test";
        Student student = new Student(id, name);
        List<Student> students = new ArrayList<>();
        students.add(student);

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        scanner = new Scanner(System.in);


        Command command = new DeleteByIdCommand();
        Assert.assertEquals(command.execute(students, scanner), "Student has been deleted");
        Assert.assertEquals(students.size(), 0);
    }

    @Test
    public void tryToDeleteNotExistingStudentTest() {
        int id = 2;
        String name = "test";
        Student student = new Student(id, name);
        List<Student> students = new ArrayList<>();
        students.add(student);

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        scanner = new Scanner(System.in);


        Command command = new DeleteByIdCommand();
        Assert.assertEquals(command.execute(students, scanner), "No student with this id");
        Assert.assertEquals(students.size(), 1);
    }

    @Test
    public void getStudentByIdTest() {
        int id = 1;
        String name = "test";
        Student student = new Student(id, name);
        List<Student> students = new ArrayList<>();
        students.add(student);

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        scanner = new Scanner(System.in);


        Command command = new GetByIdCommand();
        Assert.assertEquals(command.execute(students, scanner), student.toString());
    }

    @Test
    public void tryToGetNotExistingStudentByIdTest() {
        int id = 1;
        String name = "test";
        Student student = new Student(id, name);
        List<Student> students = new ArrayList<>();
        students.add(student);

        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        scanner = new Scanner(System.in);


        Command command = new GetByIdCommand();
        Assert.assertEquals(command.execute(students, scanner), "No student with this id");
    }

    @Test
    public void getStudentsByNameTest() {
        String name = "test";
        Student student1 = new Student(1, name);
        Student student2 = new Student(2, name);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        System.setIn(new ByteArrayInputStream("test".getBytes()));
        scanner = new Scanner(System.in);

        StringBuilder studentsString = new StringBuilder();

        for (Student student : students) {
            studentsString.append(student.toString()).append("\n");
        }

        Command command = new GetByNameCommand();
        Assert.assertEquals(command.execute(students, scanner), studentsString.toString());
    }

    @Test
    public void tryToGetNotExistingStudentByNameTest() {
        String name = "test";
        Student student1 = new Student(1, name);
        Student student2 = new Student(2, name);
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        System.setIn(new ByteArrayInputStream("test2".getBytes()));
        scanner = new Scanner(System.in);

        Command command = new GetByNameCommand();
        Assert.assertEquals(command.execute(students, scanner), "There is no students with this name");
    }

    @Test
    public void stopCommandTest() {
        FTPClient ftpClient = new FTPClient("192.168.193.99", "swordie", "swordie");
        ftpClient.open();
        Command command = new StopCommand(ftpClient);

        Assert.assertEquals(command.execute(new ArrayList<>(), new Scanner(System.in)), "exit");
        Assert.assertFalse(ftpClient.getFtp().isConnected());
    }
}
