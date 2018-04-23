package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryView {

    UserIO userIO;

    public FlooringMasteryView(UserIO userIO) {
        this.userIO = userIO;
    }

    public int displayMenuAndGetSelection() {

        userIO.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        userIO.print("* 1. Display Orders");
        userIO.print("* 2. Add an Order");
        userIO.print("* 3. Edit an Order");
        userIO.print("* 4. Remove an Order");
        userIO.print("* 5. Save Current Work");
        userIO.print("* 6. Quit");
        userIO.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        return userIO.readInt("Please enter your selection: ");

    }

    public LocalDate promptUserForDate() {
        return userIO.readLocalDate("Please enter the date you wish to search on in YYYY-MM-DD format: ");
    }

    public void displayOrders(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            userIO.print(currentOrder.toString());
        }
    }

    public void displayErrorMessage(String message) {
        userIO.print(message);
    }

    public Order promptUserForOrderInfo() {
        Order newOrder = new Order(1);
        newOrder.setCustomerLastName(userIO.readString("Please enter the customer's Last Name: "));
        newOrder.setOrderDate(userIO.readLocalDate("Please enter the order date in YYYY-MM-DD format: "));
        newOrder.setState(userIO.readString("Please enter the customer's state: "));
        String productName = userIO.readString("Please enter the product the customer has selected: ");
        newOrder.setOrderProduct(new Product(productName));
        newOrder.setArea(userIO.readBigDecimal("Please enter the floor area: "));

        return newOrder;
    }

    public boolean displayProcessedOrderAndConfirm(Order newOrder) {
        userIO.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        userIO.print("Your order information: ");
        userIO.print("Customer's name: " + newOrder.getCustomerLastName());
        userIO.print("Order date: " + newOrder.getOrderDate());
        userIO.print("Floor area: " + newOrder.getArea());
        userIO.print("Customer's selected flooring: " + newOrder.getOrderProduct().getProductType());
        userIO.print("Material cost per square foot: $" + newOrder.getOrderProduct().getMaterialCostPerSquareFoot());
        userIO.print("Labor cost per square foot: $" + newOrder.getOrderProduct().getLaborCostPerSquareFoot());
        userIO.print("Customer's state: " + newOrder.getOrderTax().getState());
        userIO.print("Tax rate: " + newOrder.getOrderTax().getTaxRate() + "%");
        userIO.print("Total Material cost: $" + newOrder.getCalculatedMaterialCost().setScale(2, RoundingMode.HALF_UP));
        userIO.print("Total Labor cost: $" + newOrder.getCalculatedLaborCost().setScale(2, RoundingMode.HALF_UP));
        userIO.print("Total taxes: $" + newOrder.getCalculatedTaxAmount().setScale(2, RoundingMode.HALF_UP));
        userIO.print("Order total: $" + newOrder.getTotalOrderAmount().setScale(2, RoundingMode.HALF_UP));
        String confirm = userIO.readString("Continue with order? Y/N");
        if (confirm.toUpperCase().equals("Y")) {
            return true;
        } else return false;
    }

    public Order promptUserForOrderNumberAndDate() {
        Order orderToUpdate = new Order(1);
        orderToUpdate.setOrderDate(userIO.readLocalDate("Please enter the order date in YYYY-MM-DD format: "));
        orderToUpdate.setOrderNumber(userIO.readInt("Please enter the order number: "));

        return orderToUpdate;
    }

    public Order promptUserForUpdatedOrderInfo(Order orderToUpdate) {
        Order updatedOrder = orderToUpdate;

        userIO.print("Current customer name is: " + orderToUpdate.getCustomerLastName());
        String newName = userIO.readString("Enter new name or hit <Enter> to continue without editing: ");
        if (!newName.equals("")) {
            updatedOrder.setCustomerLastName(newName);
        }

        userIO.print("Current customer State is: " + orderToUpdate.getState());
        String newState = userIO.readString("Enter new State or hit <Enter> to continue without editing: ");
        if (!newState.equals("")) {
            updatedOrder.setState(newState);
        }

        userIO.print("Current order Date is: " + orderToUpdate.getOrderDate());
        String newDateStr = userIO.readString(("Enter new date or hit <Enter> to continue without editing: "));
        if (!newDateStr.equals("")) {
            LocalDate newDate = LocalDate.parse(newDateStr);
            updatedOrder.setOrderDate(newDate);
        }

        userIO.print("Current selected product is: " + orderToUpdate.getOrderProduct().getProductType());
        String newProduct = userIO.readString("Enter new product or hit <Enter> to continue without editing: ");
        if (!newProduct.equals("")) {
            updatedOrder.setOrderProduct(new Product(newProduct));
        }

        userIO.print("Current floor area is: " + orderToUpdate.getArea());
        String newAreaStr = userIO.readString("Enter new area or hit <Enter> to continue without editing: ");
        if (!newAreaStr.equals("")) {
            BigDecimal newArea = new BigDecimal(newAreaStr);
            updatedOrder.setArea(newArea);
        }


        return updatedOrder;
    }


}
