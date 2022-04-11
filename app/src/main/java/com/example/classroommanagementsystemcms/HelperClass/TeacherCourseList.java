package com.example.classroommanagementsystemcms.HelperClass;

public class TeacherCourseList {

    String course_code;
    String course_name;
    String year;
    String batch;
    String course_credit;
    String total_obtained_classes;
    String total_taken_classes;


    public TeacherCourseList() {

    }

    public TeacherCourseList(String course_code, String course_name, String year, String batch, String course_credit, String total_obtained_classes, String total_taken_classes) {
        this.course_code = course_code;
        this.course_name = course_name;
        this.year = year;
        this.batch = batch;
        this.course_credit = course_credit;
        this.total_obtained_classes = total_obtained_classes;
        this.total_taken_classes = total_taken_classes;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getCourse_credit() {
        return course_credit;
    }

    public void setCourse_credit(String course_credit) {
        this.course_credit = course_credit;
    }

    public String getTotal_obtained_classes() {
        return total_obtained_classes;
    }

    public void setTotal_obtained_classes(String total_obtained_classes) {
        this.total_obtained_classes = total_obtained_classes;
    }

    public String getTotal_taken_classes() {
        return total_taken_classes;
    }

    public void setTotal_taken_classes(String total_taken_classes) {
        this.total_taken_classes = total_taken_classes;
    }
}
