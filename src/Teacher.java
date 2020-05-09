import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person{
    private String TeacherID;
    private List<Course> TeachingCourse = new ArrayList<Course>();
    public Teacher(String name, String IdNum, String ID) {
        super(name,IdNum);
        this.TeacherID = ID;
    }
    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    public String getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(String teacherID) {
        TeacherID = teacherID;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
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

    public List<Course> getTeachingCourse() {
        return TeachingCourse;
    }

    public void setTeachingCourse(List<Course> teachingCourse) {
        TeachingCourse = teachingCourse;
    }

    @Override
    public char getSex() {
        return super.getSex();
    }

    @Override
    public void setSex(char sex) {
        super.setSex(sex);
    }

    public static boolean CheckTeacherID(String ID) {
        if(ID.length() != 5) return  false;
        String regex = "\\d{5}";
        return ID.matches(regex);
    }

    public boolean CheckCourse(Course test) {
        for(int i = 0; i < TeachingCourse.size(); ++i) {
            Course temp = TeachingCourse.get(i);
            if (temp == test) return true;
        }
        return  false;
    }
    @Override
    public String toString() {
        Person Temp = Person.newPerson(getName(),getId());
        String result;
        result = "Name:" + getName()+'\n'+"IDNum:"+getId().toUpperCase()+'\n'+"TID:"+getTeacherID()+'\n'+"Sex:"+Temp.getSex()+'\n'+"Birthday:";
        char[] arr = Temp.getBirthday().toCharArray();
        result = result + arr[0] + arr[1] + arr[2] + arr[3] + '/' + arr[4] + arr[5] + '/' + arr[6] + arr[7];
        return result;
    }
}
