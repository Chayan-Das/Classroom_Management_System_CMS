package com.example.classroommanagementsystemcms.Staff.Last;

public class RecordModel {

    public RecordModel() {
    }

    public String getkeyid, studentname, roll, phoneno, date, purchasetime, roomno;

    public RecordModel(String getkeyid, String studentname, String roll, String phoneno, String date, String purchasetime, String roomno) {
        this.getkeyid = getkeyid;
        this.studentname = studentname;
        this.roll = roll;
        this.phoneno = phoneno;
        this.date = date;
        this.purchasetime = purchasetime;
        this.roomno = roomno;
    }

    public String getGetkeyid() {
        return getkeyid;
    }

    public void setGetkeyid(String getkeyid) {
        this.getkeyid = getkeyid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPurchasetime() {
        return purchasetime;
    }

    public void setPurchasetime(String purchasetime) {
        this.purchasetime = purchasetime;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }
}
