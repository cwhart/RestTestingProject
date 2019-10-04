package model;

public class SearchAvailableRoomsCommandModel {

    String startDate;
    String endDate;
    int numInParty;
    int roomNumber;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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
