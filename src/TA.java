import java.io.PrintWriter;

public class TA extends Student {

    final String POSITION = "TA";


    public TA(String name, int studentID, int teacherID, String phone)
    {
        super(name, studentID, teacherID, phone);
    }

    public int getID()
    {
        if (super.studentID < super.teacherID)
        {
            return teacherID;
        } else {
            return studentID;
        }
    }

    @Override
    public void csvPrintln(PrintWriter out)
    {
        out.println(super.getName() + "," + getID() + "," + phone);
    }





}
