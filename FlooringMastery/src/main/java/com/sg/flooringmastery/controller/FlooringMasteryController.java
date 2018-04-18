package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.sg.flooringmastery.ui.FlooringMasteryView;

import java.time.LocalDate;

public class FlooringMasteryController {

    FlooringMasteryServiceLayerImpl service;
    FlooringMasteryView view;

    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayerImpl service) {
        this.service = service;
        this.view = view;
    }

    public void run() {

        service.retrieveAllOrdersByDate(LocalDate.now());

    }
}
