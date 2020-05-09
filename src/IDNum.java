public class IDNum {
    private String identity_number;
    public IDNum() {}
    public IDNum (String id) {
        this.identity_number = id;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    private boolean CheckDate(char[] BirthDay) {
        String temp = "";
        int test[] = new int[]{0, 31,28,31,30,31,30,31,31,30,31,30,31};
        for(int i = 0; i < 4; ++i) {
            temp = temp + BirthDay[i];
        }
        int year = Integer.parseInt(temp);
        temp = "";
        if(BirthDay[4] != '0') temp = temp + BirthDay[4] + BirthDay[5];
        else temp = temp + BirthDay[5];
        int month = Integer.parseInt(temp);
        temp = "";
        if(BirthDay[6] != '0') temp = temp + BirthDay[6] + BirthDay[7];
        else temp = temp + BirthDay[7];
        int day = Integer.parseInt(temp);
        if((year % 400 == 0) || (year % 100 != 0 && year % 4 == 0)) test[2] = 29;
        if(month < 1 || month > 12) return false;
        if(day > test[month]) return false;
        return true;
    }

    private boolean CheckCode(char[] number) {
        int w = 1;
        int Weighted = 0;
        for(int i = 17; i >= 0; --i) {
            int Num = (int)number[i]-(int)'0';
            if(number[i] == 'x' || number[i] == 'X') Num = 10;
            Weighted += (w % 11) * Num;
            w = w * 2;
        }
        if(Weighted % 11 == 1) return true;
        return false;
    }

    public static boolean CheckIDNum(String id) {
        if(id.length() != 18) return false;
        char[] arr = id.toCharArray();
        for(int i = 0; i < 17; ++i) {
            if(arr[i] < '0' || arr[i] > '9') return false;
        }
        if((arr[17] < '0' || arr[17] > '9') && arr[17] != 'x' && arr[17] != 'X') return false;
        char[] Birth = new char[8];
        boolean result = true;
        for(int i = 6; i < 14; ++i) {
            Birth[i-6] = arr[i];
        }
        IDNum obj = new IDNum();
        result = result & obj.CheckDate(Birth) & obj.CheckCode(arr);
        return result;
    }

    public String toString() {
        String result = getIdentity_number();
        result.toUpperCase();
        return result;
    }
}
