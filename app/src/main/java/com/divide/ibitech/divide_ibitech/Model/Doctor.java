package com.divide.ibitech.divide_ibitech.Model;

/**
 * Created by s216100801 on 2018/08/27.
 */

public class Doctor {
    String Fullname;
    String Surname;
    String TellNo;
    public Doctor(String fullname, String surname, String tellNo) {
        Fullname = fullname;
        Surname = surname;
        TellNo = tellNo;
    }

    public String getFullname() {
        return Fullname;
    }

    public String getSurname() {
        return Surname;
    }

    public String getTellNo() {
        return TellNo;
    }
}
