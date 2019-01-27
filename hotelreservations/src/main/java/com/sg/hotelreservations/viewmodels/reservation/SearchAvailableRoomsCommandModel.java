package com.sg.hotelreservations.viewmodels.reservation;

public class SearchAvailableRoomsCommandModel {

    String startDate;
    String endDate;
    int numInParty;
    int roomNum;

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getNumInParty() {
        return numInParty;
    }

    public void setNumInParty(int numInParty) {
        this.numInParty = numInParty;
    }
}
