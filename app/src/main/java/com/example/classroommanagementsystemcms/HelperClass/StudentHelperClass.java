package com.example.classroommanagementsystemcms.HelperClass;

public class StudentHelperClass {

    public String fullname;
    String email;
    String password;
    String roll;

    public StudentHelperClass(){

    }

    public StudentHelperClass(String fullname, String email, String password, String roll) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.roll = roll;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
