package com.sg.hotelreservations.webservice.impl;

import com.sg.hotelreservations.dto.*;
import com.sg.hotelreservations.service.serviceinterface.*;
import com.sg.hotelreservations.viewmodels.bill.AddOnBillDetailViewModel;
import com.sg.hotelreservations.viewmodels.bill.BillViewModel;
import com.sg.hotelreservations.viewmodels.bill.RoomBillDetailViewModel;
import com.sg.hotelreservations.webservice.webinterface.BillWebService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillWebServiceImpl implements BillWebService {

    AddOnBillDetailService addOnBillDetailService;
    RoomBillDetailService roomBillDetailService;
    BillService billService;
    RoomRateService roomRateService;
    RoomService roomService;
    AddOnRateService addOnRateService;
    AddOnService addOnService;

    public BillWebServiceImpl(AddOnBillDetailService addOnBillDetailService, RoomBillDetailService roomBillDetailService, BillService billService, RoomService roomService,
                              RoomRateService roomRateService, AddOnRateService addOnRateService, AddOnService addOnService) {
        this.addOnBillDetailService = addOnBillDetailService;
        this.roomBillDetailService = roomBillDetailService;
        this.billService = billService;
        this.roomService = roomService;
        this.roomRateService = roomRateService;
        this.addOnRateService = addOnRateService;
        this.addOnService = addOnService;
    }

    @Override
    public List<AddOnBillDetailViewModel> getAddOnBillDetailViewModels(Long billId) {
        List<AddOnBillDetail> addOnBillDetails = addOnBillDetailService.retrieveByBillId(billId);
        List<AddOnBillDetailViewModel> addOnBillDetailViewModels = new ArrayList<>();

        if (addOnBillDetails != null && addOnBillDetails.size() != 0) {

            for (AddOnBillDetail currentAddOnBillDetail : addOnBillDetails) {
                AddOnBillDetailViewModel addOnBillDetailViewModel = new AddOnBillDetailViewModel();
                addOnBillDetailViewModel.setDiscountedPrice(currentAddOnBillDetail.getPrice().toString());
                addOnBillDetailViewModel.setTaxAmount(currentAddOnBillDetail.getTaxAmount().toString());
                addOnBillDetailViewModel.setTransactionDate(currentAddOnBillDetail.getTransactionDate().toString());
                AddOnRate addOnRate = addOnRateService.retrieve(currentAddOnBillDetail.getAddOnRate().getId());
                addOnBillDetailViewModel.setBasePrice(addOnRate.getPrice().toString());
                AddOn addOn = addOnService.retrieve(addOnRate.getAddOn().getId());
                addOnBillDetailViewModel.setDescription(addOn.getType());
                addOnBillDetailViewModels.add(addOnBillDetailViewModel);
            }
        }

        return addOnBillDetailViewModels;

    }

    @Override
    public List<RoomBillDetailViewModel> getRoomBillDetailViewModels(Long billId) {
        List<RoomBillDetail> roomBillDetails = roomBillDetailService.retrieveByBillId(billId);
        List<RoomBillDetailViewModel> roomBillDetailViewModels = new ArrayList<>();

        if (roomBillDetails != null && roomBillDetails.size() != 0) {

            for (RoomBillDetail currentRoomBillDetail : roomBillDetails) {
                RoomBillDetailViewModel roomBillDetailViewModel = new RoomBillDetailViewModel();
                roomBillDetailViewModel.setDiscountedPrice(currentRoomBillDetail.getPrice().toString());
                roomBillDetailViewModel.setTaxAmount(currentRoomBillDetail.getTaxAmount().toString());
                roomBillDetailViewModel.setTransactionDate(currentRoomBillDetail.getTransactionDate().toString());
                //Have the roomRate id, need to retrieve the room and type.
                RoomRate roomRate = roomRateService.retrieve(currentRoomBillDetail.getRoomRate().getId());
                roomBillDetailViewModel.setBasePrice(roomRate.getPrice().toString());
                Room room = roomService.retrieve(roomRate.getRoom().getId());
                roomBillDetailViewModel.setDescription(room.getType());
                roomBillDetailViewModels.add(roomBillDetailViewModel);
            }
        }

        return roomBillDetailViewModels;
    }

    @Override
    public BillViewModel getBillViewModel(Long reservationId) {
        Bill bill = billService.retrieveByReservationId(reservationId);
        Long billId = bill.getId();
        BillViewModel billViewModel = new BillViewModel();
        if (getRoomBillDetailViewModels(billId) != null && getRoomBillDetailViewModels(billId).size() != 0) {
            billViewModel.setRoomBillDetailViewModels(getRoomBillDetailViewModels(billId));
        }
        if(getAddOnBillDetailViewModels(billId) != null && getAddOnBillDetailViewModels(billId).size() != 0) {
            billViewModel.setAddOnBillDetailViewModels(getAddOnBillDetailViewModels(billId));
        }
        billViewModel.setReservationHolderName("placeholder");
        billViewModel.setTotal(bill.getTotal().toString());

        return billViewModel;
    }
}
