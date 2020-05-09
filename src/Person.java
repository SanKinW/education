public class Person {
    private String id;
    private String name;
    private String password;
    private char sex;
    private String birthday;
    public Person() {}
    public Person(String name, String IdNum) {
        this.name = name;
        this.id = IdNum;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String toString() {
        String result;
        result = "Name:" + getName()+'\n'+"IDNum:"+getId()+'\n'+"Sex:"+getSex()+'\n'+"Birthday:";
        char[] arr = getBirthday().toCharArray();
        result = result + arr[0] + arr[1] + arr[2] + arr[3] + '/' + arr[4] + arr[5] + '/' + arr[6] + arr[7];
        return result;
    }

    public static Person newPerson(String name, String id) {
        boolean fg = IDNum.CheckIDNum(id);
        if(fg == false) return null;
        char[] arr = id.toCharArray();
        String birth = "";
        for(int i = 6; i < 14; ++i) {
            birth = birth + arr[i];
        }
        Person result = new Person();
        result.setBirthday(birth);
        int check = (int)arr[16]-(int)'0';
        if(check % 2 != 0) result.setSex('M');
        else result.setSex('F');
        result.setName(name);
        result.setId(id);
        return result;
    }

    public static boolean CheckPassWord(String PassWord) {
        if(PassWord.length() < 6 || PassWord.length() > 18) return false;
        boolean flag = true;
        int[] count = new int[4];
        int sum = 0;
        char[] arr = PassWord.toCharArray();
        for(int i = 0; i < arr.length; ++i) {
            if((int)arr[i] < 33 || (int)arr[i] > 126) flag = false;
            if (arr[i] >= 'A' && arr[i] <= 'Z') count[0] = 1;
            else if (arr[i] >= 'a' && arr[i] <= 'z') count[1] = 1;
            else if (arr[i] >= '0' && arr[i] <= '9') count[2] = 1;
            else count[4] = 1;
        }
        for(int i = 0; i < 4; ++i) {
            sum = sum + count[i];
        }
        return flag && sum > 1;
    }

    public static boolean CheckName(String Name) {
        String regex = "^[a-zA-Z]+$";
        return Name.matches(regex);
    }
}
