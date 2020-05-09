import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private String StudentID;
    private List<Course> SelectCourse = new ArrayList<Course>();;
    public Student(){}
    public Student(String name, String IdNum, String ID) {
        super(name, IdNum);
        this.StudentID = ID;
    }
    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getBirthday() {
        return super.getBirthday();
    }

    @Override
    public void setBirthday(String birthday) {
        super.setBirthday(birthday);
    }

    @Override
    public char getSex() {
        return super.getSex();
    }

    @Override
    public void setSex(char sex) {
        super.setSex(sex);
    }

    public List<Course> getSelectCourse() {
        return SelectCourse;
    }

    public void setSelectCourse(List<Course> selectCourse) {
        SelectCourse = selectCourse;
    }

    public static boolean CheckStudentID(String ID) {
        if(ID.length() != 8) return  false;
        String regex = "\\d{8}";
        return ID.matches(regex);
    }

    @Override
    public String toString() {
        Person Temp = Person.newPerson(getName(),getId());
        String result;
        result = "Name:" + getName()+'\n'+"IDNum:"+getId().toUpperCase()+'\n'+"SID:"+getStudentID()+'\n'+"Sex:"+Temp.getSex()+'\n'+"Birthday:";
        char[] arr = Temp.getBirthday().toCharArray();
        result = result + arr[0] + arr[1] + arr[2] + arr[3] + '/' + arr[4] + arr[5] + '/' + arr[6] + arr[7];
        return result;
    }
}
