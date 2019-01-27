package com.sg.hotelreservations.webservice.webinterface;

import com.sg.hotelreservations.viewmodels.bill.AddOnBillDetailViewModel;
import com.sg.hotelreservations.viewmodels.bill.BillViewModel;
import com.sg.hotelreservations.viewmodels.bill.RoomBillDetailViewModel;

import java.util.List;

public interface BillWebService {

    public List<AddOnBillDetailViewModel> getAddOnBillDetailViewModels(Long billId);
    public List<RoomBillDetailViewModel> getRoomBillDetailViewModels (Long billId);
    public BillViewModel getBillViewModel(Long billId);

}
