package com.divide.ibitech.divide_ibitech.Model;

/**
 * Created by s216100801 on 2018/08/27.
 */

public class Patient {
    String Fullname;
    String Surname;
    String TellNo;
    public Patient(String fullname, String Surname, String tellNo) {
        Fullname = fullname;
        Surname = Surname;
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
