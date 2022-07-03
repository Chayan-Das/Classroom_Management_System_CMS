package com.example.classroommanagementsystemcms.Student.Key;

public class Roommodel {

    public Roommodel() {
    }

    public String roomname,type,building,status,purchasedby;

    public Roommodel(String roomname, String type, String building, String status, String purchasedby) {
        this.roomname = roomname;
        this.type = type;
        this.building = building;
        this.status = status;
        this.purchasedby = purchasedby;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurchasedby() {
        return purchasedby;
    }

    public void setPurchasedby(String purchasedby) {
        this.purchasedby = purchasedby;
    }
}
