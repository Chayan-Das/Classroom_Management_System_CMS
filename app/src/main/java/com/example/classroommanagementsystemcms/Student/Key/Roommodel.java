package com.example.classroommanagementsystemcms.Student.Key;

public class Roommodel {

    public Roommodel() {
    }

    public String roomname,type,building,status,purchasedby,id;

    public Roommodel(String roomname, String type, String building, String status, String purchasedby, String id) {
        this.roomname = roomname;
        this.type = type;
        this.building = building;
        this.status = status;
        this.purchasedby = purchasedby;
        this.id = id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
