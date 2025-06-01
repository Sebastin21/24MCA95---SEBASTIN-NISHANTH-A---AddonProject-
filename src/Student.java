import java.util.Scanner;

public abstract class Student {
    protected static Scanner in = new Scanner(System.in);

    protected static int StudentId;
    protected String StudentName;
    protected int StudentAge;
    protected String StudentPlace;
    protected int CourseId;
    protected String StudentCourse;
    protected float StudentMark;

    static String url = "jdbc:mysql://localhost:3306/jdbc?characterEncoding-utf8";
    static String username = "root";
    static String password = "root";

}
