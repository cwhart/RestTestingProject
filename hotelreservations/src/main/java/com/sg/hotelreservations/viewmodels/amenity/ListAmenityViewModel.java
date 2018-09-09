package com.sg.hotelreservations.viewmodels.amenity;

import java.util.List;

public class ListAmenityViewModel {

    private int[] pageNumbers;
    private int selectedPage;
    private List<AmenityViewModel> rooms;

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

    public List<AmenityViewModel> getRooms() {
        return rooms;
    }

    public void setRooms(List<AmenityViewModel> rooms) {
        this.rooms = rooms;
    }
}
