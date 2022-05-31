package com.example.classroommanagementsystemcms.Staff.Create;

public class Batchmodel {
    String Batchid;
    String Batchname;


    public Batchmodel(){

    }

    public Batchmodel(String batchid, String batchname ) {
        Batchid = batchid;
        Batchname = batchname;

    }

    public String getBatchid() {
        return Batchid;
    }

    public void setBatchid(String batchid) {
        Batchid = batchid;
    }

    public String getBatchname() {
        return Batchname;
    }

    public void setBatchname(String batchname) {
        Batchname = batchname;
    }


}
