package com.sg.hotelreservations.viewmodels.room.list;

import java.util.List;

public class ListRoomViewModel {

    private int[] pageNumbers;
    private int selectedPage;
    private List<RoomViewModel> rooms;

    public int[] getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(int[] pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public int getSelectedPage() {
        return selectedPage;
    }

    public void setSelectedPage(int selectedPage) {
        this.selectedPage = selectedPage;
    }

    public List<RoomViewModel> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomViewModel> rooms) {
        this.rooms = rooms;
    }
}
