import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Test {
    private static String errorOut = "Input illegal.";
    private static String Error = "Error:";
    public static void main(String[] args) {
        CourseList courseList = CourseList.getCourse_List();
        String sign = "\\s+";
        Scanner reader = new Scanner(System.in);
        while (true) {
            String Content = reader.nextLine();
            String Choice[] = Content.split(sign);
            if (Content.equals("SUDO")) {
                while (true) {
                    Content = reader.nextLine();
                    Choice = Content.split(sign);
                    if (Choice[0].equals("np")) {
                        OutPut.addPerson(Choice);
                    }
                    else if (Choice[0].equals("nc")) {
                        OutPut.addCourse(Choice);
                    }
                    else if (Choice[0].equals("udc")) {
                        OutPut.modifyCourse(Choice);
                    }
                    else if (Choice[0].equals("clist")) {
                        if (Choice.length != 4) System.out.println(Error + errorOut);
                        else {
                            try {
                                Course lesson = courseList.getCourseById(Choice[1]);
                                List<String> studentList = lesson.getStudentList();
                                Collections.sort(studentList);
                                Course course = new Course();
                                boolean fg = course.CheckNum(Choice[2]) && course.CheckNum(Choice[3]);
                                if (!fg) System.out.println(Error + errorOut);
                                else {
                                    int N = Integer.parseInt(Choice[2]);
                                    int M = Integer.parseInt(Choice[3]);
                                    while (true) {
                                        if (N <= 0 || ((N - 1) * M) >= studentList.size()) {
                                            System.out.println(Error + "Record does not exist.");
                                        }
                                        System.out.println("Page:" + N);
                                        int End = N * M < studentList.size() ? N * M : studentList.size();
                                        OutPut.PrintSelectPerson(studentList, (N - 1) * M, End);
                                        String Instruction = reader.nextLine();
                                        if (Instruction.equals("n")) N++;
                                        else if (Instruction.equals("l")) N--;
                                        else if (Instruction.equals("q")) break;
                                        else if (Instruction.equals("QUIT")) return;
                                        else {
                                            System.out.println(Error + errorOut);
                                            break;
                                        }
                                    }
                                }
                            }catch (WrongInputException e) {
                                System.out.println(Error + e.getMessage());
                            }

                        }
                    }
                    else if (Content.equals("back")) break;
                    else if (Content.equals("QUIT")) return;
                    else System.out.println(Error + errorOut);
                }
            }
            else if (Choice[0].equals("login")) {
                Person ans = OutPut.Login(Choice);
                if (ans != null) {
                    while (true) {
                        Content = reader.nextLine();
                        Choice = Content.split(sign);
                        if (Choice[0].equals("chgpw")) OutPut.ChangePassword(ans, Choice);
                        else if (Content.equals("myinfo"))OutPut.PrintPersonInformation(ans);
                        else if (Choice[0].equals("gc") || Choice[0].equals("myc")) {
                            List<Course> list;
                            if (Choice[0].equals("gc")) list = OutPut.SearchCourse(Choice);
                            else list = OutPut.SearchMyCourse(Choice, ans);
                            if (list != null) {
                                Collections.sort(list);
                                int N;
                                int M;
                                if (Choice[0].equals("gc")) {
                                    if (Choice[1].equals("-key")) {
                                        N = Integer.parseInt(Choice[3]);
                                        M = Integer.parseInt(Choice[4]);
                                    } else {
                                        N = Integer.parseInt(Choice[2]);
                                        M = Integer.parseInt(Choice[3]);
                                    }
                                }
                                else {
                                    N = Integer.parseInt(Choice[1]);
                                    M = Integer.parseInt(Choice[2]);
                                }
                                while (true) {
                                    if(N <= 0 || ((N - 1) * M) >= list.size()) {
                                        System.out.println(Error + "Course does not exist.");
                                        break;
                                    }
                                    System.out.println("Page:"+N);
                                    int End = N * M < list.size() ? N * M : list.size();
                                    OutPut.PrintCourseInformation(list,(N - 1) * M, End);
                                    String Guide = reader.nextLine();
                                    if(Guide.equals("n")) N++;
                                    else if(Guide.equals("l")) N--;
                                    else if(Guide.equals("q")) break;
                                    else if (Guide.equals("QUIT")) return;
                                    else {
                                        System.out.println(Error + errorOut);
                                        break;
                                    }
                                }
                            }
                        }
                        else if (Choice[0].equals("clist")) {
                            if (Choice.length != 4) System.out.println(Error + errorOut);
                            else {
                                try {
                                    Course lesson = courseList.getCourseById(Choice[1]);
                                    List<String> studentList = lesson.getStudentList();
                                    Collections.sort(studentList);
                                    Course course = new Course();
                                    boolean fg = course.CheckNum(Choice[2]) && course.CheckNum(Choice[3]) && ans instanceof Teacher;
                                    if (!fg) System.out.println(Error + errorOut);
                                    else {
                                        int N = Integer.parseInt(Choice[2]);
                                        int M = Integer.parseInt(Choice[3]);
                                        while (true) {
                                            if (N <= 0 || ((N - 1) * M) >= studentList.size()) {
                                                System.out.println(Error + "Record does not exist.");
                                            }
                                            System.out.println("Page:" + N);
                                            int End = N * M < studentList.size() ? N * M : studentList.size();
                                            OutPut.PrintSelectPerson(studentList, (N - 1) * M, End);
                                            String Instruction = reader.nextLine();
                                            if (Instruction.equals("n")) N++;
                                            else if (Instruction.equals("l")) N--;
                                            else if (Instruction.equals("q")) break;
                                            else if (Instruction.equals("QUIT")) return;
                                            else {
                                                System.out.println(Error + errorOut);
                                                break;
                                            }
                                        }
                                    }
                                }catch (WrongInputException e){
                                    System.out.println(Error + e.getMessage());
                                }
                            }
                        }
                        else if (Choice[0].equals("getc")) {
                            if (Choice.length != 2) System.out.println(Error + errorOut);
                            else OutPut.GetCourse(Choice[1], ans);
                        }
                        else if (Choice[0].equals("dropc")) {
                            if (Choice.length != 2) System.out.println(Error + errorOut);
                            else OutPut.DropCourse(Choice[1], ans);
                        }
                        else if (Choice[0].equals("DROPOUT")) {
                            boolean test = OutPut.DropOut(Choice, ans);
                            if (test == true) break;
                        }
                        else if (Content.equals("back")) break;
                        else if (Content.equals("QUIT")) return;
                        else System.out.println(Error + errorOut);
                    }
                }
            }
            else if (Content.equals("QUIT")) return;
            else System.out.println(Error + errorOut);
        }
    }
}
