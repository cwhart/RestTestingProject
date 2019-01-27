package com.sg.hotelreservations.webservice.webinterface;

import com.sg.hotelreservations.dto.Promo;
import com.sg.hotelreservations.viewmodels.promo.*;

import java.util.List;

public interface PromoWebService {

    public CreatePromoViewModel getCreatePromoViewModel();
    public Promo saveCreatePromoCommandModel(CreatePromoCommandModel commandModel);
    public List<ListPromoViewModel> getPromoListViewModel();
    public EditPromoViewModel getEditPromoViewModel(Long id);
    public void saveEditPromoCommandModel(EditPromoCommandModel commandModel);
}
