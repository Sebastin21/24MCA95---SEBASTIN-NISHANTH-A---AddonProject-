import java.sql.*;

public class MarksOfCourses extends Student{

    public void addMarks(){
        StudentDetails.ViewUsingStudentId();
        System.out.print("Enter Mark For Student Enrolled Course : ");
        StudentMark = in.nextFloat();
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement ;
            preparedStatement = connection.prepareStatement("INSERT INTO jdbc.markdetails (id, marks) VALUES (?, ?)");

            preparedStatement.setInt(1, StudentId);
            preparedStatement.setFloat(2, StudentMark);
            preparedStatement.executeUpdate();

            connection.close();
            preparedStatement.close();
            System.out.println("\n\t STUDENT MARK ADDED SUCCESSFULLY\n");

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public boolean viewStudentMarksById(){
        System.out.print(" Enter Student ID : ");
        StudentId = in.nextInt();

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT sd.id, sd.name, sd.course_id, c.course_name," +
                    " md.marks FROM jdbc.studentdetails sd JOIN jdbc.course c ON sd.course_id = c.course_id " +
                    "JOIN jdbc.markdetails md ON sd.id = md.id WHERE sd.id = ?");
            ResultSet resultSet;

            preparedStatement.setInt(1,StudentId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {

                System.out.println("\n\t  STUDENT FOUND \n");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int courseID = resultSet.getInt("course_id");
                    String courseName = resultSet.getNString("course_name");
                    float courseMark = resultSet.getFloat("marks");

                    System.out.println("Student ID : " + id + ",  Student Name : " + name + ", Student Age : " +
                            ", Course Id : " + courseID + ", Course Name : " + courseName + ", MARKS Obtained : " + courseMark);
                }
                connection.close();
                resultSet.close();
                preparedStatement.close();
            }
            else {
                connection.close();
                resultSet.close();
                preparedStatement.close();
                System.out.println("\n\t  ! MARK YET ASSIGNED FOR THIS STUDENT (OR) STUDENT NOT EXIST\n");
                return false;
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return true;
    }

    public void viewStudentMarks(){
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT sd.id, sd.name, sd.course_id, c.course_name," +
                    " md.marks FROM jdbc.studentdetails sd JOIN jdbc.course c ON sd.course_id = c.course_id " +
                    "JOIN jdbc.markdetails md ON sd.id = md.id ");
            ResultSet resultSet;

            resultSet = preparedStatement.executeQuery();

            System.out.println("\n\t  STUDENT DETAILS");
            if (resultSet.isBeforeFirst()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int courseID = resultSet.getInt("course_id");
                    String courseName = resultSet.getNString("course_name");
                    float courseMark = resultSet.getFloat("marks");

                    System.out.println("Student ID : " + id + ",  Student Name : " + name + ", Student Age : " +
                            ", Course Id : " + courseID + ", Course Name : " + courseName + ", MARKS Obtained : " + courseMark);
                }
                resultSet.close();
                preparedStatement.close();
            }
            else {
                resultSet.close();
                preparedStatement.close();
                System.out.println("\n\t  ! NO DETAILS EXIST \n\n");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void updateMark(){
        if(viewStudentMarksById() == true) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement;
                System.out.print("Enter Student Mark to Update : ");
                StudentMark = in.nextFloat();
                preparedStatement = connection.prepareStatement("UPDATE jdbc.markdetails SET marks = ? WHERE id = ?");
                preparedStatement.setFloat(1, StudentMark);
                preparedStatement.setInt(2, StudentId);
                preparedStatement.executeUpdate();
                System.out.println(" Student MARK Updated ");
            } catch (SQLException e) {
                e.getMessage();
            }
        }
    }

    public void deleteMark(){
        if(viewStudentMarksById() == true) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement;
                in.nextLine();
                System.out.println("\nMATCH FOUND CONFIRM TO DELETE (Y/N)");
                System.out.print("\nCONFIRM (Y / N) : ");
                String op = in.nextLine();

                if (op.equalsIgnoreCase("Y")) {
                    String qry = "DELETE FROM jdbc.markdetails WHERE id = ? ";
                    preparedStatement = connection.prepareStatement(qry);

                    preparedStatement.setInt(1, StudentId);
                    preparedStatement.executeUpdate();
                    System.out.println("\n\t DELETED SUCCESSFULLY\n ");

                    preparedStatement.close();
                    connection.close();
                } else {
                    System.out.println("\n\t Delete Operation Cancelled \n");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
