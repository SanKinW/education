import java.util.ArrayList;
import java.util.List;

public class PersonList {
    private static PersonList personList = new PersonList();
    private List<Person> PersonnelList;
    private PersonList() {
        PersonnelList = new ArrayList<Person>();
    }
    public static PersonList getPersonList() {
        return personList;
    }
    public List<Person> getPersonnelList() {
        return PersonnelList;
    }

    public void Add(String Name, String IdNum, String ID, int fg) throws WrongInputException{
        boolean flag = IDNum.CheckIDNum(IdNum);
        if (!flag) throw new WrongInputException("ID illegal.");
        List<Person> list = getPersonnelList();
        for(Person temp : list) {
            if (temp.getId().equals(IdNum)) throw new WrongInputException("ID exists.");
        }
        flag = Person.CheckName(Name);
        if (!flag) throw new WrongInputException("Name illegal.");
        Person add;
        if (fg == 1) {
            flag = Teacher.CheckTeacherID(ID);
            if (!flag) throw new WrongInputException("TID illegal.");
            for (Person temp : list) {
                boolean test = Teacher.class.isInstance(temp);
                if (test) {
                    Teacher ans = (Teacher) temp;
                    if (ans.getTeacherID().equals(ID)) throw new WrongInputException("TID exists.");
                }
            }
            add = new Teacher(Name, IdNum, ID);
        }
        else{
            flag = Student.CheckStudentID(ID);
            if (!flag) throw new WrongInputException("SID illegal.");
            for (Person temp : list) {
                boolean test = Student.class.isInstance(temp);
                if (test) {
                    Student ans = (Student) temp;
                    if (ans.getStudentID().equals(ID)) throw new WrongInputException("SID exists.");
                }
            }
            add = new Student(Name, IdNum, ID);
        }
        add.setPassword("a12345");
        getPersonnelList().add(add);
    }

    public Person Log(String IdNum, String Password, boolean fg) throws WrongInputException{
        List<Person> list = getPersonnelList();
        for(Person Temp : list) {
            boolean flag = Teacher.class.isInstance(Temp);
            if (fg == flag && Temp.getId().equals(IdNum) && Temp.getPassword().equals(Password)) return Temp;

        }
        throw new WrongInputException("Login Error. Your ID or password maybe wrong.");
    }


    public String SearchStudent(String SID) {
        List<Person> list = getPersonnelList();
        for(Person temp : list) {
            if(temp instanceof Student) {
                Student test = (Student) temp;
                if (test.getStudentID().equals(SID)) return test.getName();
            }
        }
        return null;
    }

    public String SearchTeacher(String TID) {
        List<Person> list = getPersonnelList();
        for(Person temp : list) {
            if(temp instanceof Teacher) {
                Teacher test = (Teacher) temp;
                if (test.getTeacherID().equals(TID)) return test.getName();
            }
        }
        return null;
    }

    public Teacher Search_Teacher(String TID) {
        List<Person> list = getPersonnelList();
        for(Person temp : list) {
            if(temp instanceof Teacher) {
                Teacher test = (Teacher) temp;
                if (test.getTeacherID().equals(TID)) return test;
            }
        }
        return null;
    }
}
