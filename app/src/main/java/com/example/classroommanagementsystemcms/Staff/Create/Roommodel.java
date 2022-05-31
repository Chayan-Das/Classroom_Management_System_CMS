package com.example.classroommanagementsystemcms.Staff.Create;

public class Roommodel {

    public Roommodel() {
    }

    public String roomname,type,building,status;

    public Roommodel(String roomname, String type, String building, String status) {
        this.roomname = roomname;
        this.type = type;
        this.building = building;
        this.status = status;
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
}
