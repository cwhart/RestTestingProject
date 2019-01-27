package com.sg.hotelreservations.viewmodels.bill;

import java.util.List;

public class BillViewModel {

    String reservationHolderName;

    List<AddOnBillDetailViewModel> addOnBillDetailViewModels;
    List<RoomBillDetailViewModel> roomBillDetailViewModels;
    String total;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getReservationHolderName() {
        return reservationHolderName;
    }

    public void setReservationHolderName(String reservationHolderName) {
        this.reservationHolderName = reservationHolderName;
    }

    public List<AddOnBillDetailViewModel> getAddOnBillDetailViewModels() {
        return addOnBillDetailViewModels;
    }

    public void setAddOnBillDetailViewModels(List<AddOnBillDetailViewModel> addOnBillDetailViewModels) {
        this.addOnBillDetailViewModels = addOnBillDetailViewModels;
    }

    public List<RoomBillDetailViewModel> getRoomBillDetailViewModels() {
        return roomBillDetailViewModels;
    }

    public void setRoomBillDetailViewModels(List<RoomBillDetailViewModel> roomBillDetailViewModels) {
        this.roomBillDetailViewModels = roomBillDetailViewModels;
    }
}
