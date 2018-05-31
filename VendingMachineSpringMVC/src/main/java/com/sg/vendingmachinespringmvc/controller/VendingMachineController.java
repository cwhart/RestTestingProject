package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.ItemDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.InsufficientItemQuantityException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class VendingMachineController {

    VendingMachineServiceLayer vendingMachineService;
    //VendingMachineView vendingMachineView;

    public VendingMachineController(VendingMachineServiceLayer vendingMachineServiceLayer) {
        this.vendingMachineService = vendingMachineServiceLayer;
    }

    //ItemDao dao;
    int itemSelected;
    BigDecimal moneyInMachine = BigDecimal.valueOf(0);
    String message = "";
    String changeToReturn = "";

    //@Inject
    //public VendingMachineController(ItemDao dao) {
    //    this.dao = dao;
    //}

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String startPage(Model model) throws VendingMachinePersistenceException {

       return "redirect:displayVendingMachine";
    }

    @RequestMapping(value="/displayVendingMachine", method = RequestMethod.GET)
    public String displayVendingMachine(Model model) throws VendingMachinePersistenceException {

        //List<Item> itemList = dao.retrieveAllItems();
        List<Item> itemList = vendingMachineService.retrieveListAllWithQuantityGTZero();

        model.addAttribute("itemList", itemList);
        model.addAttribute("item", itemSelected);
        model.addAttribute("totalMoneyIn", moneyInMachine.setScale(2));
        model.addAttribute("messages", message);
        model.addAttribute("change", changeToReturn);

        return "index";
    }

    @RequestMapping(value = "/selectItem", method = RequestMethod.GET)
    public String selectItem(HttpServletRequest request, Model model) throws VendingMachinePersistenceException{
        String itemIdParameter = request.getParameter("itemId");
        itemSelected = Integer.parseInt(itemIdParameter);
        //Item item = dao.retrieveSingleItem(itemSelected);
        model.addAttribute("item", itemSelected);
        message = "";
        changeToReturn = "";

        return "redirect:displayVendingMachine";

    }

    @RequestMapping(value = "/addDollar", method = RequestMethod.GET)
    public String addDollar(HttpServletRequest request, Model model){
        //changeTracker.setDollars(1);
        //changeTracker.setRunningTotal(changeTracker.getRunningTotal().add(new BigDecimal("1.00").setScale(2)));
        //moneyInMachine = moneyInMachine.add(BigDecimal.valueOf(1));
        vendingMachineService.addMoney(BigDecimal.valueOf(1));
        moneyInMachine = vendingMachineService.getRunningTotal();
        model.addAttribute("totalMoneyIn",(moneyInMachine));
        message = "";
        changeToReturn = "";
        return "redirect:displayVendingMachine";
    }

    @RequestMapping(value = "/addQuarter", method = RequestMethod.GET)
    public String addQuarter(HttpServletRequest request, Model model){
        //changeTracker.setDollars(1);
        //changeTracker.setRunningTotal(changeTracker.getRunningTotal().add(new BigDecimal("1.00").setScale(2)));
        vendingMachineService.addMoney(BigDecimal.valueOf(.25));
        moneyInMachine = vendingMachineService.getRunningTotal();
        model.addAttribute("totalMoneyIn",(moneyInMachine));
        message = "";
        changeToReturn = "";
        return "redirect:displayVendingMachine";
    }

    @RequestMapping(value = "/addDime", method = RequestMethod.GET)
    public String addDime(HttpServletRequest request, Model model){
        //changeTracker.setDollars(1);
        //changeTracker.setRunningTotal(changeTracker.getRunningTotal().add(new BigDecimal("1.00").setScale(2)));
        vendingMachineService.addMoney(BigDecimal.valueOf(.1));
        moneyInMachine = vendingMachineService.getRunningTotal();
        model.addAttribute("totalMoneyIn",(moneyInMachine));
        message = "";
        changeToReturn = "";
        return "redirect:displayVendingMachine";
    }

    @RequestMapping(value = "/addNickel", method = RequestMethod.GET)
    public String addNickel(HttpServletRequest request, Model model){
        //changeTracker.setDollars(1);
        //changeTracker.setRunningTotal(changeTracker.getRunningTotal().add(new BigDecimal("1.00").setScale(2)));
        vendingMachineService.addMoney(BigDecimal.valueOf(.05));
        moneyInMachine = vendingMachineService.getRunningTotal();
        model.addAttribute("totalMoneyIn",(moneyInMachine));
        message = "";
        changeToReturn = "";
        return "redirect:displayVendingMachine";
    }

    @RequestMapping(value = "/makePurchase", method=RequestMethod.GET)
    public String makePurchase(HttpServletRequest request) throws VendingMachinePersistenceException{

        try {
            message = vendingMachineService.purchaseItem(itemSelected);
            moneyInMachine = vendingMachineService.getRunningTotal();
        } catch (InsufficientItemQuantityException ex) {
            message="SOLD OUT!!";
        } catch (InsufficientFundsException ex) {
            //BigDecimal amountShort = itemPrice.subtract(moneyInMachine);
            message = ex.getMessage();
        }
        return "redirect:displayVendingMachine";
    }

    /*@RequestMapping(value = "/makePurchase", method=RequestMethod.GET)
    public String makePurchase(HttpServletRequest request) throws VendingMachinePersistenceException{

        if(itemSelected == 0) {
            message="Please select an item!";

            return "redirect:displayVendingMachine";
        }

        else{

            Item item = dao.retrieveSingleItem(itemSelected);
            BigDecimal itemPrice = item.getItemPrice();
            int itemQuantity = item.getItemQuantity();

            if (itemQuantity < 1) {
                message = "SOLD OUT!!";

                return "redirect: displayVendingMachine";
            } else if (itemPrice.compareTo(moneyInMachine) > 0) {
                BigDecimal amountShort = itemPrice.subtract(moneyInMachine);
                message = "Please deposit: $" + amountShort;

                return "redirect:displayVendingMachine";
            } else {

                moneyInMachine = moneyInMachine.subtract(itemPrice);
                item.setItemQuantity(itemQuantity - 1);
                dao.updateItem(item);
                message = "Thank you!!!";
                itemSelected = 0;
                return "redirect: displayVendingMachine";

            }
        }
    }*/



    @RequestMapping(value = "/returnChange", method = RequestMethod.GET)
    public String returnChange(HttpServletRequest request, Model model) {
        Change thisChange = new Change(moneyInMachine);
        int quarters = thisChange.getQuarters();
        int dimes = thisChange.getDimes();
        int nickels = thisChange.getNickels();
        int pennies = thisChange.getPennies();

        changeToReturn = "Quarters: " + quarters +
                " Dimes: " + dimes +
                " Nickels: " + nickels +
                " Pennies: " + pennies;

        moneyInMachine = BigDecimal.valueOf(0);


        return "redirect: displayVendingMachine";
    }

    //
}
