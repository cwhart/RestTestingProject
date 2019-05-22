package com.sg.hotelreservations.webservice.webinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.viewmodels.addon.AddOnViewModel;
import com.sg.hotelreservations.viewmodels.room.ListRoomViewModel;

import java.time.LocalDate;
import java.util.List;

public interface AddOnWebService {

    public List<AddOnViewModel> getListAddOnViewModel(Integer offset);

    public AddOnBillDetail addAddOnToBill(Long addOnId, Long billId);

    public void deleteAddOn(Long id);
}
