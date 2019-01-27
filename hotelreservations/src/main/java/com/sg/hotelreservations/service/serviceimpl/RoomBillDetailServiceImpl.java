package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomBillDetailDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.PromoRate;
import com.sg.hotelreservations.dto.PromoType;
import com.sg.hotelreservations.dto.RoomBillDetail;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.PromoRateService;
import com.sg.hotelreservations.service.serviceinterface.PromoTypeService;
import com.sg.hotelreservations.service.serviceinterface.RoomBillDetailService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@Service(value = "RoomBillDetailServiceImpl")
public class RoomBillDetailServiceImpl implements RoomBillDetailService {

    @Inject
    RoomBillDetailDAO roomBillDetailDAO;

    @Inject
    PromoTypeService promoTypeService;

    @Inject
    PromoRateService promoRateService;

    @Override
    public RoomBillDetail create(RoomBillDetail roomBillDetail) {

        PromoType promoType = new PromoType();
        PromoRate promoRate = new PromoRate();

        if(roomBillDetail.getPromo() != null && roomBillDetail.getPromo().getId() != null) {
            promoType = promoTypeService.retrieve(roomBillDetail.getPromo().getPromoType().getId());
            promoRate = promoRateService.retrieve(promoType.getPromoRate().getId());

            BigDecimal discountedRate = roomBillDetail.getPrice();

            if (promoRate.getType().equals("%")) {
                BigDecimal decimalPercentage = promoRate.getRate().multiply(BigDecimal.valueOf(.01));
                BigDecimal discount = roomBillDetail.getPrice().multiply(decimalPercentage);
                discountedRate = roomBillDetail.getPrice().subtract(discount);
                roomBillDetail.setPrice(discountedRate);
            } else if (promoRate.getType().equals("$")) {
                BigDecimal discount = promoRate.getRate();
                discountedRate = discountedRate.subtract(discount);
                roomBillDetail.setPrice(discountedRate);
            }
        }


        return roomBillDetailDAO.create(roomBillDetail);
    }

    @Override
    public RoomBillDetail retrieve(Long id) {
        return roomBillDetailDAO.retrieve(id);
    }

    @Override
    public void update(RoomBillDetail roomBillDetail) {
        roomBillDetailDAO.update(roomBillDetail);
    }

    @Override
    public void delete(RoomBillDetail roomBillDetail) {
        roomBillDetailDAO.delete(roomBillDetail);
    }

    @Override
    public List<RoomBillDetail> retrieveAll(int limit, int offset) {
        return roomBillDetailDAO.retrieveAll(limit, offset);
    }

    @Override
    public List<RoomBillDetail> retrieveByBillId(Long billId) {
        return roomBillDetailDAO.retrieveByBillId(billId);
    }
}
