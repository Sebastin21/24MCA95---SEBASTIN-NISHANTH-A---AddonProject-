import java.util.Scanner;

public class StudentMenu {

    Scanner in = new Scanner(System.in);

    private void displayHeader(){
        System.out.println("\n\t STUDENTS INFORMATION MANAGEMENT SYSTEM \n");
    }

    public void displayMainMenu(){
        int select = 0;
        while (select != 9) {
        displayHeader();
        System.out.println(" Select Your Choice for Process");
        System.out.println("1. Student Process");
        System.out.println("2. Course Process");
        System.out.println("3. Marks Process");
        System.out.println("9. Exit");
        System.out.println("\n Enter your choice : ");
        select = in.nextInt();

        switch (select){
            case 1:
                displayStudentMenu();
                break;
            case 2:
                displayCourseMenu();
                break;
            case 3:
                displayMarksMenu();
                break;
            case 9:
                break;
            default:
                System.out.println("\tInvalid Choice !!");
        }
      }
    }

    public void displayStudentMenu(){
        StudentDetails SD = new StudentDetails();
        int select = 0;
        while (select != 9){
            displayHeader();
            System.out.println(" Select Your Choice for Process");
            System.out.println("1. Add Student Details");
            System.out.println("2. Update Student Details");
            System.out.println("3. View student Details Using Student Id");
            System.out.println("4. List All Students");
            System.out.println("5. Delete Student");
            System.out.println("9. Exit");
            System.out.println("\n Enter your choice : ");
            select = in.nextInt();
            in.nextLine();

            switch (select){
                case 1:
                    SD.SetStudentDetails();
                    break;
                case 2:
                    SD.updateDetails();
                    break;
                case 3:
                    SD.ViewUsingStudentId();
                    break;
                case 4:
                    SD.ViewStudent();
                    break;
                case 5:
                    SD.DeleteStudent();
                    break;
                case 9:
                    break;
                default:
                    System.out.println("\tInvalid Choice !!");
            }
        }
    }

    public void displayCourseMenu(){
        CourseDetails CD = new CourseDetails();
        int select = 0;
        while (select != 9){
            displayHeader();
            System.out.println(" Select Your Choice for Process");
            System.out.println("1. Add Course");
            System.out.println("2. List All Courses Available");
            System.out.println("3. Delete Course");
            System.out.println("9. Exit");
            System.out.println("\n Enter your choice : ");
            select = in.nextInt();

            switch (select){
                case 1:
                    CD.setCourse();
                    break;
                case 2:
                    CD.viewCourses();
                    break;
                case 3:
                    CD.deleteCourse();
                    break;
                case 9:
                    break;
                default:
                    System.out.println("\tInvalid Choice !!");
            }
        }
    }

    public void displayMarksMenu(){
        int select = 0;
        MarksOfCourses MC = new MarksOfCourses();
        while (select != 9){
            displayHeader();
            System.out.println(" Select Your Choice for Process");
            System.out.println("1. Add Marks");
            System.out.println("2. View Student Marks Student Student ID");
            System.out.println("3. View All Student Marks ");
            System.out.println("4. Update Marks ");
            System.out.println("5. Delete Marks ");
            System.out.println("9. Exit");
            System.out.println("\n Enter your choice : ");
            select = in.nextInt();

            switch (select){
                case 1:
                    MC.addMarks();
                    break;
                case 2:
                    MC.viewStudentMarksById();
                    break;
                case 3:
                    MC.viewStudentMarks();
                    break;
                case 4:
                    MC.updateMark();
                    break;
                case 5:
                    MC.deleteMark();
                    break;
                case 9:
                    break;
                default:
                    System.out.println("\tInvalid Choice !!");
            }
        }
    }
}
