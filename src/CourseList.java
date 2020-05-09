import java.util.ArrayList;
import java.util.List;

public class CourseList {
    private static CourseList Course_List = new CourseList();
    private List<Course> courseList;
    private CourseList(){
        courseList  = new ArrayList<Course>();
    }
    public static CourseList getCourse_List() {
        return Course_List;
    }
    public List<Course> getList() {
        return courseList;
    }

    public Course getCourseById(String cid) {
        for(Course result : courseList) {
            String temp = result.getCID().toUpperCase();
            if(temp.equals(cid.toUpperCase())) return result;
        }
        return null;
    }

    public List<Course> getCourseByKeyword(String Keyword) {
        List<Course> result = new ArrayList<Course>();
        for(Course temp : courseList) {
            String pass = temp.getCourse_Name().toUpperCase();
            if(pass.indexOf(Keyword.toUpperCase()) != -1) {
                result.add(temp);
            }
        }
        return result;
    }

    public boolean AddNewCourse(String cid, String name, String teacher, String cap, String time) {
        Course course = new Course();
        boolean flag = course.CheckNum(cap);
        if (flag) {
            int Cap = Integer.parseInt(cap);
            Course T = new Course(cid, name, teacher, Cap, time);
            courseList.add(T);
            return true;
        }
        else return false;
    }

    public boolean ModifyCourse(Course lesson, String choice, String Change) {
        if (choice.equals("-n")) {
            boolean flag = lesson.CheckCourseName(Change);
            if (flag) {
                lesson.setCourse_Name(Change);
                return true;
            }
            return false;
        }
        else if (choice.equals("-c")) {
            boolean flag = lesson.CheckNum(Change);
            if (flag) {
                int cap = Integer.parseInt(Change);
                lesson.setCapacity(cap);
                return true;
            }
            return false;
        }
        else if (choice.equals("-t")) {
            boolean flag = lesson.CheckTeacherName(Change);
            if (flag) {
                lesson.setTeacherList(Change);
                List<String> list = lesson.getTeacherList();
                for(int i = 0; i < list.size(); ++i) {
                    Teacher teacher = PersonList.getPersonList().Search_Teacher(list.get(i));
                    if (teacher != null) {
                        if (!teacher.CheckCourse(lesson))teacher.getTeachingCourse().add(lesson);
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public String PrintCourse(List<Course> ans, int n, int m) {
        String result = "";
        for(int i = n; i < m; ++i) {
            Course temp = ans.get(i);
            int num = i - n + 1;
            result = result + num + "." + temp.toString() + '\n';
        }
        return result;
    }

}
