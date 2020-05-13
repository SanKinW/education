import java.util.List;

public class OutPut {
    private static PersonList personList = PersonList.getPersonList();
    private static CourseList courseList = CourseList.getCourse_List();
    private static String errorOut = "Input illegal.";
    private static String Error = "Error:";
    public static void addPerson(String Choice[]) {
        if (Choice.length != 5) {
            System.out.println(Error+errorOut);
        }
        else {
            if (Choice[1].equals("-t")) {
                try {
                    personList.Add(Choice[2], Choice[3], Choice[4], 1);
                    System.out.println("Add teacher success.");
                    int len = personList.getPersonnelList().size();
                    Teacher teacher = (Teacher) personList.getPersonnelList().get(len-1);
                    List<Course> list = courseList.getList();
                    for (Course test : list) {
                        if (test.IfTeach(Choice[4])) {
                            teacher.getTeachingCourse().add(test);
                        }
                    }
                }catch (WrongInputException e) {
                    System.out.println(Error + e.getMessage());
                }
            }
            else if (Choice[1].equals("-s")) {
                try {
                    personList.Add(Choice[2], Choice[3], Choice[4], 0);
                    System.out.println("Add student success.");
                }catch (WrongInputException e) {
                    System.out.println(Error + e.getMessage());
                }
            }
            else System.out.println(Error + errorOut);
        }
    }

    public static void addCourse(String Choice[]) {
        if (Choice.length != 6) System.out.println(Error + errorOut);
        else {
            try {
                courseList.getCourseById(Choice[1]);
                System.out.println(Error + "Course exists.");
            }catch (WrongInputException e) {
                Course course = new Course();
                boolean fg = course.CheckNum(Choice[4]) && course.CheckTeacherName(Choice[3]) && course.CheckCourseName(Choice[2]) && course.CheckCid(Choice[1]) && course.CheckTime(Choice[5]);
                if (fg) {
                    int cap = Integer.parseInt(Choice[4]);
                    Course lesson = new Course(Choice[1], Choice[2], Choice[3], cap, Choice[5]);
                    courseList.getList().add(lesson);
                    List<String> list = lesson.getTeacherList();
                    for(String string : list) {
                        Teacher teacher = personList.Search_Teacher(string);
                        if (teacher != null) teacher.getTeachingCourse().add(lesson);
                    }
                }
                else {
                    if(!course.CheckTeacherName(Choice[3])) {
                        String regex = "^\\[.*\\]$";
                        if (!(Choice[3].matches(regex))) {
                            System.out.println(Error + errorOut);
                            return;
                        }
                    }
                    if (!course.CheckNum(Choice[4])) {
                        String regex = "^-\\d+$";
                        if (!(Choice[4].matches(regex))) {
                            System.out.println(Error + errorOut);
                            return;
                        }
                    }
                    if (!course.CheckTime(Choice[5])) {
                        String regex = "^\\[\\d{1,2}-\\d{1,2}\\]\\d,\\d{1,2}$";
                        if (!(Choice[5].matches(regex))) {
                            System.out.println(Error + errorOut);
                            return;
                        }
                    }
                    System.out.println(Error + "Course add illegal.");
                }
            }
        }
    }

    public static void modifyCourse(String Choice[]) {
        if (Choice.length != 4) {
            System.out.println(Error + errorOut);
        }
        else {
            try {
                Course lesson = courseList.getCourseById(Choice[1]);
                try {
                    courseList.ModifyCourse(lesson, Choice[2], Choice[3]);
                    System.out.println("Update success.");
                }catch (WrongInputException e) {
                    System.out.println(Error + e.getMessage());
                }

            }catch (WrongInputException e) {
                System.out.println(Error + e.getMessage());
            }
        }
    }

    public static void PrintSelectPerson(List<String> list, int n, int m) {
        for(int i = n; i < m; ++i) {
            String SID = list.get(i);
            String Name = personList.SearchStudent(SID);
            int order = i - n + 1;
            System.out.println(order + "." + SID + "," + Name);
        }
        System.out.println("n-next page, l-last page, q-quit");
    }

    public static Person Login(String Choice[]) {
        if (Choice.length == 4 && (Choice[1].equals("-t") || Choice[1].equals("-s"))) {
            Person ans;
            try {
                if (Choice[1].equals("-t")) {
                    ans = personList.Log(Choice[2], Choice[3], true);
                } else {
                    ans = personList.Log(Choice[2], Choice[3], false);
                }
                System.out.println("Login success.");
                return ans;
            }catch (WrongInputException e) {
                System.out.println(Error + e.getMessage());
            }
        }
        else System.out.println(Error + errorOut);
        return null;
    }

    public static void ChangePassword(Person person, String Choice[]) {
        if (Choice.length != 3) System.out.println(errorOut);
        else if (!Person.CheckPassWord(Choice[1])) {
            System.out.println(Error + "Password illegal.");
        }
        else if (!Choice[1].equals(Choice[2]))
            System.out.println(Error + "The password you entered must be the same as the former one.");
        else {
            person.setPassword(Choice[1]);
            System.out.println("Password changed successfully.");
        }
    }

    public static void PrintPersonInformation(Person person) {
        boolean fg = Teacher.class.isInstance(person);
        if (fg) {
            Teacher Temp = (Teacher) person;
            System.out.println(Temp.toString());
        }
        else {
            Student Temp = (Student) person;
            System.out.println(Temp.toString());
        }
    }

    public static void PrintCourseInformation(List<Course> courseList, int st, int end) {
        for (int i = st; i < end; ++i) {
            Course temp = courseList.get(i);
            int num = i - st + 1;
            System.out.println(num + "." + temp);
        }
        System.out.println("n-next page, l-last page, q-quit");
    }

    public static List<Course> SearchCourse(String[] Choice) {
        Course course = new Course();
        if (Choice[1].equals("-id")) {
            if (Choice.length != 3) {
                System.out.println(Error + errorOut);
                return null;
            }
            try {
                Course check = courseList.getCourseById(Choice[2]);
                System.out.println(check);
            }catch (WrongInputException e) {
                System.out.println(Error + e.getMessage());
            }
            return null;
        }
        else if (Choice[1].equals("-key")) {
            if (Choice.length != 5) {
                System.out.println(Error + errorOut);
                return null;
            }
            boolean fg = course.CheckNum(Choice[3]) && course.CheckNum(Choice[4]);
            if (!fg) {
                String regex = "^-\\d+$";
                if (Choice[3].matches(regex) && Choice[4].matches(regex)) System.out.println(Error + "Course does not exist.");
                else System.out.println(Error + errorOut);
                return null;
            }
            return courseList.getCourseByKeyword(Choice[2]);
        }
        else if (Choice[1].equals("-all")) {
            if (Choice.length != 4) {
                System.out.println(Error + errorOut);
                return null;
            }
            boolean fg = course.CheckNum(Choice[2]) && course.CheckNum(Choice[3]);
            if (!fg) {
                String regex = "^-\\d+$";
                if (Choice[2].matches(regex) && Choice[3].matches(regex)) System.out.println(Error +"Course does not exist.");
                System.out.println(Error + errorOut);
                return null;
            }
            return courseList.getList();
        }
        System.out.println(Error + errorOut);
        return null;
    }

    public static List<Course> SearchMyCourse(String[] Choice, Person ans) {
        if (Choice.length != 3) {
            System.out.println(Error + errorOut);
        }
        else {
            Course course = new Course();
            boolean fg = course.CheckNum(Choice[1]) && course.CheckNum(Choice[2]);
            if (!fg) {
                System.out.println(Error + errorOut);
            } else {
                boolean test = ans instanceof Teacher;
                if (test) {
                    Teacher temp = (Teacher) ans;
                    return temp.getTeachingCourse();
                } else {
                    Student temp = (Student) ans;
                    return temp.getSelectCourse();
                }
            }
        }
        return null;
    }

    public static void GetCourse(String Cid,Person person) {
        if (!(person instanceof Student)) System.out.println(Error + errorOut);
        else {
            try {
                Course aim = courseList.getCourseById(Cid);
                Student student = (Student) person;
                List<Course> list = student.getSelectCourse();
                for (Course test : list) {
                    if (test == aim) {
                        System.out.println(Error + "The course has been selected.");
                        return;
                    }
                }
                for (Course course:list) {
                    if (course.CheckConflict(aim)) {
                        System.out.println(Error + "Course time conflict.");
                        return;
                    }
                }
                if (aim.getStudentList().size() == aim.getCapacity()) System.out.println(Error + "The course is full.");
                else {
                    aim.getStudentList().add(student.getStudentID());
                    student.getSelectCourse().add(aim);
                    System.out.println("Course chosen success.");
                }
            }catch (WrongInputException e) {
                System.out.println(Error + e.getMessage());
            }

        }
    }

    public static void DropCourse(String Cid, Person person) {
        if (!(person instanceof Student)) System.out.println(Error + errorOut);
        else {
            try {
                Course aim = courseList.getCourseById(Cid);
                Student student = (Student) person;
                List<Course> list = student.getSelectCourse();
                for (Course test : list) {
                    if (test == aim) {
                        list.remove(test);
                        aim.getStudentList().remove(student.getStudentID());
                        System.out.println("Drop out successful.");
                        return;
                    }
                }
                System.out.println(Error + "The course has not been selected.");
            }catch (WrongInputException e) {
                System.out.println(Error + e.getMessage());
            }
        }
    }

    public static boolean DropOut(String[] choice, Person ans) {
        if (choice.length != 3 || !(ans instanceof Student)) {
            System.out.println(Error + errorOut);
            return false;
        }
        else {
            if (!choice[1].equals(choice[2])) {
                System.out.println(Error + "The password you entered must be the same as the former one.");
                return false;
            }
            else if (!choice[1].equals(ans.getPassword())) {
                System.out.println(Error + "Password illegal.");
                return false;
            }
            else {
                Student student = (Student) ans;
                List<Course> list = student.getSelectCourse();
                for (Course course:list) {
                    List<String> STDs = course.getStudentList();
                    STDs.remove(student.getStudentID());
                }
                List<Person> people = personList.getPersonnelList();
                people.remove(student);
                System.out.println("Congratulations, drop out successfully.");
                return true;
            }
        }
    }
}
