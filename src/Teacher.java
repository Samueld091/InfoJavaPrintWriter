import java.io.PrintWriter;

public class Teacher implements CSVPrintable {

    final String POSITION = "Teacher";
    String name;

    int studentID;

    int teacherID;

    int phone;

    public Teacher(String name, int studentID, int teacherID, String phone)
    {
        this.name = name;
        this.studentID = studentID;
        this.teacherID = teacherID;
        this.phone = Integer.parseInt(phone.trim().substring(6)) ;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return teacherID;
    }

    @Override
    public void csvPrintln(PrintWriter out) {
        out.println(getName() + "," + getID() + "," + phone);
    }
}
