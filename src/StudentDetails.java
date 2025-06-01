import java.sql.*;
public class StudentDetails extends Student {

    public void SetStudentDetails() {
        System.out.println("Enter Student Name : ");
        StudentName = in.nextLine();
        System.out.println("Enter Student Age : ");
        StudentAge = in.nextInt();
        in.nextLine();
        System.out.println("Enter Student Place : ");
        StudentPlace = in.nextLine();
        System.out.println("Available COURSES are Listed below");
        CourseDetails cd = new CourseDetails();
        cd.viewCourses();
        System.out.println("\nEnter The Course Id You Want To Enroll : ");
        CourseId = in.nextInt();
        InsertDetails();
    }

    public void InsertDetails() {
        try {

            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO jdbc.studentdetails (name, age, place, course_id) VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, StudentName);
            preparedStatement.setInt(2, StudentAge);
            preparedStatement.setString(3, StudentPlace);
            preparedStatement.setInt(4, CourseId);

            preparedStatement.executeUpdate();

            connection.close();
            preparedStatement.close();

            System.out.println("\n\t STUDENT DETAILS ADDED SUCCESSFULLY\n\n");

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void updateDetails() {
        try {
            ViewUsingStudentId();
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement;
            System.out.println("Select Option For Which Detail You Need to Update \n 1. Student Name \n 2. Student Age \n 3. Student Place \n 4. Student Course ID");
            int option = in.nextInt();
            in.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Enter Student Name to Update : ");
                    StudentName = in.nextLine();
                    preparedStatement = connection.prepareStatement("UPDATE jdbc.studentdetails SET name = ? WHERE id = ?");
                    preparedStatement.setString(1, StudentName);
                    preparedStatement.setInt(2, StudentId);
                    preparedStatement.executeUpdate();
                    System.out.println(" Student Name Updated ");
                    connection.close();
                    preparedStatement.close();
                    break;
                case 2:
                    System.out.println("Enter Student Age to Update : ");
                    StudentAge = in.nextInt();
                    preparedStatement = connection.prepareStatement("UPDATE jdbc.studentdetails SET age = ? WHERE id = ?");
                    preparedStatement.setInt(1, StudentAge);
                    preparedStatement.setInt(2, StudentId);
                    preparedStatement.executeUpdate();
                    System.out.println(" Student Age Updated ");
                    connection.close();
                    preparedStatement.close();
                    break;
                case 3:
                    System.out.println("Enter Student Place to Update : ");
                    StudentPlace = in.nextLine();
                    preparedStatement = connection.prepareStatement("UPDATE jdbc.studentdetails SET place = ? WHERE id = ?");
                    preparedStatement.setString(1, StudentPlace);
                    preparedStatement.setInt(2, StudentId);
                    preparedStatement.executeUpdate();
                    System.out.println(" Student Place Updated ");
                    connection.close();
                    preparedStatement.close();
                    break;
                case 4:
                    System.out.println("Available COURSES are Listed below");
                    CourseDetails cd = new CourseDetails();
                    cd.viewCourses();
                    System.out.println("\nEnter The Course Id You Want Change : ");
                    CourseId = in.nextInt();
                    preparedStatement = connection.prepareStatement("UPDATE jdbc.studentdetails SET course_id = ? WHERE id = ?");
                    preparedStatement.setInt(1, CourseId);
                    preparedStatement.setInt(2, StudentId);
                    preparedStatement.executeUpdate();
                    System.out.println(" Student Course Changed ");
                    connection.close();
                    preparedStatement.close();
                    break;
                default:
                    System.out.println("\t !! Invalid Choice");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }


    public static void ViewUsingStudentId() {

        System.out.print(" Enter Student ID : ");
        StudentId = in.nextInt();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT sd.id, sd.name, sd.age, sd.place, " +
                    "sd.course_id, c.course_name FROM jdbc.studentdetails sd JOIN jdbc.course c ON sd.course_id = " +
                    "c.course_id WHERE sd.id = ?");
            ResultSet resultSet;

            preparedStatement.setInt(1, StudentId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {

                System.out.println("\n\t  STUDENT FOUND \n");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String place = resultSet.getString("place");
                    int courseID = resultSet.getInt("course_id");
                    String courseName = resultSet.getNString("course_name");


                    System.out.println("Student ID : " + id + ",  Student Name : " + name + ", Student Age : " + age +
                            ", Student Place : " + place + ", Course Id : " + courseID + ", Course Name : " + courseName);
                }
                resultSet.close();
                connection.close();
                preparedStatement.close();
                System.out.println("\n\t  DETAILS LISTED\n");
            } else {
                resultSet.close();
                connection.close();
                preparedStatement.close();
                System.out.println("\n\t  ! STUDENT DOESN'T EXIST \n");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void ViewStudent() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT sd.id, sd.name, sd.age, sd.place, " +
                    "sd.course_id, c.course_name FROM jdbc.studentdetails sd JOIN jdbc.course c ON sd.course_id = c.course_id order by sd.id");
            ResultSet resultSet;

            resultSet = preparedStatement.executeQuery();

            System.out.println("\n\t  STUDENT DETAILS");
            if (resultSet.isBeforeFirst()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getNString("name");
                    int age = resultSet.getInt("age");
                    String place = resultSet.getString("place");
                    int courseID = resultSet.getInt("course_id");
                    String courseName = resultSet.getNString("course_name");

                    System.out.println("Student ID : " + id + ", Student Name : " + name + ", Student Age : " + age +
                            ", Student Place : " + place + ", Enrolled Course Id : " + courseID + ", Course Name : " + courseName);
                }
                resultSet.close();
                connection.close();
                preparedStatement.close();
            } else {
                resultSet.close();
                connection.close();
                preparedStatement.close();
                System.out.println("\n\t  ! NO DETAILS EXIST \n\n");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void DeleteStudent() {
        ViewUsingStudentId();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement;
            in.nextLine();
            System.out.println("\nMATCH FOUND CONFIRM TO DELETE");

            System.out.print("\n  CONFIRM (Y / N) : ");
            String op = in.nextLine();

            if (op.equalsIgnoreCase("Y")) {
                String qry = "DELETE FROM jdbc.studentdetails WHERE id = ? ";
                preparedStatement = connection.prepareStatement(qry);

                preparedStatement.setInt(1, StudentId);
                preparedStatement.executeUpdate();
                System.out.println("\n\t DELETED SUCCESSFULLY\n\n ");

                preparedStatement.close();
                connection.close();
            } else {
                System.out.println("\n\t Delete Operation Cancelled \n\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}