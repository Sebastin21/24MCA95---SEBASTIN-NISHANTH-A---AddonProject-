import java.sql.*;

public class CourseDetails extends Student {

    public void setCourse(){
        System.out.print("Enter The Course Name Needs To Be Added : ");
        StudentCourse = in.nextLine();
        insertCourse();
    }

    public void insertCourse(){
        try {

            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement ;
            preparedStatement = connection.prepareStatement("INSERT INTO jdbc.course (course_name) VALUES (?)");

            preparedStatement.setString(1, StudentCourse);
            preparedStatement.executeUpdate();

            connection.close();
            preparedStatement.close();

            System.out.println("\n\t NEW COURSE ADDED SUCCESSFULLY\n\n");

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void viewCourses(){
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM jdbc.course");
            ResultSet resultSet;

            resultSet = preparedStatement.executeQuery();

            System.out.println("\n\t  COURSE DETAILS");
            if (resultSet.isBeforeFirst()) {

                while (resultSet.next()) {
                    int courseId = resultSet.getInt("course_id");
                    String courseName = resultSet.getNString("course_name");

                    System.out.println("Course ID : " + courseId + " ,   Course Name : " + courseName  );
                }
                resultSet.close();
                preparedStatement.close();
//                System.out.println("\n\t  DETAILS LISTED\n\n");
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

    public void deleteCourse(){
        System.out.println("Enter Course ID to Delete : ");
        CourseId = in.nextInt();

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM jdbc.course where course_id = ?");
            ResultSet resultSet;

            preparedStatement.setInt(1,CourseId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                in.nextLine();
                System.out.println("\t  MATCH FOUND CONFIRM TO DELETE (Y/N)");

                int id = resultSet.getInt("course_id");
                String name = resultSet.getNString("course_name");

                System.out.println("Course ID : " + id + " ,   Course Name : " + name );

                System.out.print("\nCONFIRM (Y / N) : ");
                String op = in.nextLine();

                if (op.equalsIgnoreCase("Y")) {
                    String qry = "DELETE FROM jdbc.course WHERE course_id = ? ";
                    preparedStatement = connection.prepareStatement(qry);

                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                    System.out.println("\n\t DELETED SUCCESSFULLY\n\n ");

                    preparedStatement.close();
                    connection.close();
                } else {
                    System.out.println("\n\t Delete Operation Cancelled \n\n");
                }
            } else {
                preparedStatement.close();
                connection.close();
                System.out.println("\n\t NO SUCH COURSE FOUND FOR DELETE\n\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
