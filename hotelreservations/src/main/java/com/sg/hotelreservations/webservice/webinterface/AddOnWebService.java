package com.sg.hotelreservations.webservice.webinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.viewmodels.addon.ListAddOnViewModel;
import com.sg.hotelreservations.viewmodels.room.ListRoomViewModel;

import java.time.LocalDate;

public interface AddOnWebService {

    public ListAddOnViewModel getListAddOnViewModel(Integer offset, Long billId);

    public AddOnBillDetail addAddOnToBill(Long addOnId, Long billId);

    public void deleteAddOn(Long id);
}
