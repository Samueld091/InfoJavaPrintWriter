import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static final String OUT = System.getProperty("user.dir")+"/t/";
    public static void main (String[] args)
    {

        //real stuff
        Scanner in = new Scanner(System.in);
        boolean keepAsking = true;

        int n = 0;
        do
        {
            try
            {
                System.out.print("Enter an integer:");
                n = in.nextInt();

                if (n < 0)
                    throw new InputMismatchException();
                in.nextLine();
                keepAsking = false;
            } catch (InputMismatchException ex)
            {
                System.out.println("Try again. Incorrect input: an integer is required");
                in.nextLine();
            }
        } while (keepAsking);

        PrintWriter out;
        try {
            out = new PrintWriter("out.csv");
        } catch (FileNotFoundException e) {
            System.out.println("Fatal Error: Either the out.csv file can't be accessed or it doesn't exists on your computer's file system");
            e.printStackTrace();
            return;
        }


        System.out.println("Enter the information for " + n + " Students/TAs/Teachers... (Format: Position First,Last StudentID TeacherID PhoneNumber)");
        for (int i = 0; i < n; i++)
        {
            String position = "";
            String name = "";
            int studentID = 0;
            int teacherID = 0;
            String phone = "";

            boolean continueAskingForInfo = true;

            while (continueAskingForInfo) {
                boolean error = false;
                String line = in.nextLine();

                try {
                    if (line.isBlank())
                        throw new InputMismatchException();
                } catch (InputMismatchException ex) {
                    System.out.println("Please enter information for a Student/TA/Teacher");
                    error = true;
                    in.nextLine();
                }

                int q = 0;


                try {

                    while (!Character.isWhitespace(line.charAt(q))) {q++;}
                    position = line.substring(0, q).trim();
                    if (!position.equals("Student") && !position.equals("Teacher") && !position.equals("TA"))
                        throw new InputMismatchException();
                } catch (InputMismatchException ex) {
                    System.out.println("Try Again: (Incorrect Input: Position needs to be either Student, Teacher, or TA)");
                    error = true;
                }


                int w = q + 1;


                try {
                    while (!Character.isWhitespace(line.charAt(w))) {
                        w++;
                    }
                    name = line.substring(q + 1, w).trim();

                    if (!name.contains(","))
                        throw new InputMismatchException();

                    name = name.replace(',', ' ');

                } catch (InputMismatchException ex) {
                    System.out.println("Try again: (Incorrect Input: Name needs to be two words separated by a comma only)");
                    error = true;
                }

                int e = w + 1;


                try {
                    while (!Character.isWhitespace(line.charAt(e))) {
                        e++;
                    }
                    studentID = Integer.parseInt(line.substring(w + 1, e).trim());

                    if (((position.equals("Student") || position.equals("TA")) && !(Integer.toString(studentID).length() == 5)) || (position.equals("Teacher") && studentID != 0))
                        throw new InputMismatchException();

                } catch (InputMismatchException ex) {
                    System.out.println("Try again: (Incorrect Input: Students and TAs have a studentID of 5 digits, and teachers have a studentsID of 0)");
                    error = true;
                }


                int r = e + 1;


                try {
                    while (!Character.isWhitespace(line.charAt(r))) {
                        r++;
                    }
                    teacherID = Integer.parseInt(line.substring(e + 1, r).trim());

                    if (((position.equals("Teacher") || position.equals("TA")) && !(Integer.toString(teacherID).length() == 5)) || (position.equals("Student") && teacherID != 0))
                        throw new InputMismatchException();

                } catch (InputMismatchException ex) {
                    System.out.println("Try again: (Incorrect Input: Teachers and TAs have a teacherID of 5 digits, and students have a teacherID of 0)");
                    error = true;
                }


                try {
                    phone = line.substring(r + 1).trim();

                    if (phone.length() != 10)
                        throw new InputMismatchException();

                    int digitToCheck = 0;
                    while ((digitToCheck < 10) && (Character.isDigit(phone.charAt(digitToCheck)))) {
                        digitToCheck++;
                    }

                    if (digitToCheck != 10)
                        throw new InputMismatchException();

                } catch (InputMismatchException ex) {
                    System.out.println("Try again: (Incorrect Input: Phone number needs to be a 10-digit number)");
                    error = true;
                }

                continueAskingForInfo = error;
            }

            if (position.equals("Student"))
            {
                CSVPrintable newPerson = new Student(name, studentID, teacherID, phone);
                newPerson.csvPrintln(out);
            } else if (position.equals("Teacher"))
            {
                CSVPrintable newPerson = new Teacher(name, studentID, teacherID, phone);
                newPerson.csvPrintln(out);
            } else if (position.equals("TA"))
            {
                CSVPrintable newPerson = new TA(name, studentID, teacherID, phone);
                newPerson.csvPrintln(out);
            }
            out.flush();
        }
        out.close();



    }





}
