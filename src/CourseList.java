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

    public Course getCourseById(String cid) throws WrongInputException{
        for(Course result : courseList) {
            String temp = result.getCID().toUpperCase();
            if(temp.equals(cid.toUpperCase())) return result;
        }
        throw new WrongInputException("Course does not exist.");
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


    public void ModifyCourse(Course lesson, String choice, String Change) throws WrongInputException{
        if (choice.equals("-n")) {
            boolean flag = lesson.CheckCourseName(Change);
            if (flag) {
                lesson.setCourse_Name(Change);
                return;
            }
            throw new WrongInputException("Update fail.");
        }
        else if (choice.equals("-c")) {
            boolean flag = lesson.CheckNum(Change);
            if (flag) {
                int cap = Integer.parseInt(Change);
                lesson.setCapacity(cap);
                return;
            }
            else {
                String regex = "^-\\d+$";
                if (Change.matches(regex)) throw new WrongInputException("Update fail.");
                else throw new WrongInputException("Input illegal.");
            }
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
                return;
            }
            throw new WrongInputException("Update fail.");
        }
        throw new WrongInputException("Input illegal.");
    }


}
