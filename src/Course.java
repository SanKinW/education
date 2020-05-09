import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course implements Comparable<Course>{
    private String CID;
    private String Course_Name;
    private List<String> TeacherList;
    private int Capacity;
    private List<String> StudentList = new ArrayList<String>();
    private String ClassTime;
    public Course() {}
    public Course(String CID, String Course_Name, String TeacherName, int Capacity, String ClassTime) {
        this.CID = CID;
        this.Course_Name = Course_Name;
        this.Capacity = Capacity;
        this.ClassTime = ClassTime;
        String sign = "\\[|,|\\]";
        String[] content = TeacherName.split(sign);
        int len = content.length;
        List<String> list = new ArrayList<>();
        for (int i = 1; i < len; ++i) {
            list.add(content[i]);
        }
        TeacherList = list;
        Collections.sort(TeacherList);
    }

    public Course(String CID) {
        this.CID = CID;
    }

    @Override
    public int compareTo(Course o) {
        if(CID.compareTo(o.getCID()) > 0) return 1;
        return -1;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public List<String> getStudentList() {
        return StudentList;
    }

    public void setStudentList(List<String> studentList) {
        StudentList = studentList;
    }

    public List<String> getTeacherList() {
        return TeacherList;
    }

    public void setTeacherList(String name) {
        String sign = "\\[|,|\\]";
        String[] content = name.split(sign);
        List<String> list = new ArrayList<String>();
        for (int i = 1; i < content.length; ++i) {
            list.add(content[i]);
        }
        TeacherList = list;
        Collections.sort(TeacherList);
    }

    public String getClassTime() {
        return ClassTime;
    }

    public void setClassTime(String classTime) {
        ClassTime = classTime;
    }

    public  boolean CheckNum(String cap) {
        String regex = "^\\d+$";
        return cap.matches(regex);
    }

    public  boolean CheckTeacherName(String name) {
        if (name.equals("[]")) return true;
        else {
            String regex = "^\\[(\\d{5},)*\\d{5}\\]$";
            if (name.matches(regex)) {
                String sign = "\\[|,|\\]";
                String[] content = name.split(sign);
                int len = content.length;
                int[] fg = new int[1000001];
                for (int i = 1; i < len; ++i) {
                    int num = Integer.parseInt(content[i]);
                    if (fg[num] != 0) return false;
                    fg[num]++;
                }
                return true;
            }
            return false;
        }
    }

    public  boolean CheckCourseName(String CName) {
        String regex = "^\\d*[a-zA-Z]*\\d*[a-zA-Z]*$";
        return CName.matches(regex);
    }

    public  boolean CheckCid(String cid) {
        String regex = "^[bB][hH]\\d{8}$";
        return cid.matches(regex);
    }

    public boolean CheckTime(String time) {
        String regex = "^\\[\\d{1,2}-\\d{1,2}\\]\\d,\\d{1,2}$";
        if (time.matches(regex)) {
            String sign = "\\[|\\]|-|,";
            String[] num = time.split(sign);
            int[] Num = new int[4];
            for(int i = 1; i < num.length; ++i) {
                Num[i-1] = Integer.parseInt(num[i]);
            }
            if (Num[0] <= Num[1] && Num[0] <= 18 && Num[1] <= 18 && (Num[2] <= 7 && Num[2] >= 1) && (Num[3] >= 1 && Num[3] <= 10)) return true;
        }
        return false;
    }

    public boolean CheckConflict(Course newCourse) {
        int[] newTime = newCourse.GetTime();
        int[] nowTime = GetTime();
        if (newTime[2] == nowTime[2] && newTime[3] == nowTime[3]) {
            if (!(newTime[1] < nowTime[0] || newTime[0] > nowTime[1])) return true;
        }
        return false;
    }

    public String GetTeacher() {
        PersonList personList = PersonList.getPersonList();
        String result = "[";
        int len = TeacherList.size();
        for(int i = 0; i < len; ++i) {
            String TID = TeacherList.get(i);
            String Name = personList.SearchTeacher(TID);
            if (Name != null) result = result + Name;
            else result = result + TID;
            if (i != len - 1) result = result + ",";
        }
        result = result + "]";
        return result;
    }

    public boolean IfTeach(String TeacherID) {
        for (String id : TeacherList) {
            if (TeacherID.equals(id)) return true;
        }
        return false;
    }

    public int[] GetTime() {
        String sign = "\\[|\\]|-|,";
        String[] num = ClassTime.split(sign);
        int[] Num = new int[4];
        for(int i = 1; i < num.length; ++i) {
            Num[i-1] = Integer.parseInt(num[i]);
        }
        return Num;
    }


    public String toString() {
        String output = "CID:" + getCID() + ",Name:" + getCourse_Name() + ",Teachers:" + GetTeacher()
                + ",Capacity:" + getStudentList().size()+ "/" + getCapacity()+",Time:"+getClassTime();
        return output;
    }
}
