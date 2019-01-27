package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.PromoRate;
import com.sg.hotelreservations.dto.PromoType;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.PromoRateService;
import com.sg.hotelreservations.service.serviceinterface.PromoTypeService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@Service(value="AddOnBillDetailServiceImpl")
public class AddOnBillDetailServiceImpl implements AddOnBillDetailService {

    @Inject
    AddOnBillDetailDAO addOnBillDetailDAO;

    @Inject
    PromoTypeService promoTypeService;

    @Inject
    PromoRateService promoRateService;

    @Override
    public AddOnBillDetail create(AddOnBillDetail addOnBillDetail) {

        if(addOnBillDetail.getPromo() != null) {
            PromoType promoType = new PromoType();
            PromoRate promoRate = new PromoRate();


            promoType = addOnBillDetail.getPromo().getPromoType();
            promoRate = promoType.getPromoRate();
            //promoType = promoTypeService.retrieve(addOnBillDetail.getPromo().getId());
            //promoRate = promoRateService.retrieve(promoType.getId());


            BigDecimal discountedRate = addOnBillDetail.getPrice();

            if (promoRate.getType().equals("%")) {
                BigDecimal decimalPercentage = promoRate.getRate().multiply(BigDecimal.valueOf(.01));
                BigDecimal discount = addOnBillDetail.getPrice().multiply(decimalPercentage);
                discountedRate = addOnBillDetail.getPrice().subtract(discount);
                addOnBillDetail.setPrice(discountedRate);
            } else if (promoRate.getType().equals("$")) {
                BigDecimal discount = promoRate.getRate();
                discountedRate = discountedRate.subtract(discount);
                addOnBillDetail.setPrice(discountedRate);
            }
        }
        return addOnBillDetailDAO.create(addOnBillDetail);
    }

    @Override
    public AddOnBillDetail retrieve(Long id) {
        return addOnBillDetailDAO.retrieve(id);
    }

    @Override
    public void update(AddOnBillDetail addOnBillDetail) {
        addOnBillDetailDAO.update(addOnBillDetail);
    }

    @Override
    public void delete(AddOnBillDetail addOnBillDetail) {
        addOnBillDetailDAO.delete(addOnBillDetail);
    }

    @Override
    public List<AddOnBillDetail> retrieveAll(int limit, int offset) {
        return addOnBillDetailDAO.retrieveAll(limit, offset);
    }

    @Override
    public List<AddOnBillDetail> retrieveByBillId(Long billId) {
        return addOnBillDetailDAO.retrieveByBillId(billId);
    }
}
