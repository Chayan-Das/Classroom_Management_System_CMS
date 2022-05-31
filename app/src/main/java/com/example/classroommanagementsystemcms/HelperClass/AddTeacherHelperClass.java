package com.example.classroommanagementsystemcms.HelperClass;

public class AddTeacherHelperClass {

    public String t_name;
    String t_designation;
    String t_id;
    String t_email;
    String t_phone;
    String departmnet;

    public AddTeacherHelperClass() {
    }

    public AddTeacherHelperClass(String t_name, String t_designation, String t_id, String t_email, String t_phone, String departmnet) {
        this.t_name = t_name;
        this.t_designation = t_designation;
        this.t_id = t_id;
        this.t_email = t_email;
        this.t_phone = t_phone;
        this.departmnet = departmnet;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_designation() {
        return t_designation;
    }

    public void setT_designation(String t_designation) {
        this.t_designation = t_designation;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_email() {
        return t_email;
    }

    public void setT_email(String t_email) {
        this.t_email = t_email;
    }

    public String getT_phone() {
        return t_phone;
    }

    public void setT_phone(String t_phone) {
        this.t_phone = t_phone;
    }

    public String getDepartmnet() {
        return departmnet;
    }

    public void setDepartmnet(String departmnet) {
        this.departmnet = departmnet;
    }
}
