import java.io.PrintWriter;

public class Student implements CSVPrintable {

    final String POSITION = "Student";

    protected String name;

    protected int studentID;

    protected int teacherID;

    protected long phone;

    public Student(String name, int studentID, int teacherID, String phone)
    {
        this.name = name;
        this.studentID = studentID;
        this.teacherID = teacherID;
        this.phone = Long.parseLong(phone.trim());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return studentID;
    }

    @Override
    public void csvPrintln(PrintWriter out) {
        out.println(getName() + "," + getID() + "," + phone);
    }
}
