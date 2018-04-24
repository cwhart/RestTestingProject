package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryView {

    UserIO userIO;

    public FlooringMasteryView(UserIO userIO) {
        this.userIO = userIO;
    }

    public int displayMenuAndGetSelection(boolean mode) {

        userIO.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        if(mode==false) {
            userIO.print("*****YOU ARE CURRENTLY IN TRAINING MODE*****");
        }
        userIO.print("* 1. Display Orders");
        userIO.print("* 2. Add an Order");
        userIO.print("* 3. Edit an Order");
        userIO.print("* 4. Remove an Order");
        userIO.print("* 5. Save Current Work");
        userIO.print("* 6. Change mode");
        userIO.print("* 7. Quit");
        userIO.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        return userIO.readInt("Please enter your selection: ", 1, 7);

    }

    public LocalDate promptUserForDate() {
        boolean tryAgain = true;
        LocalDate date = null;

        do {
            try {
                date = userIO.readLocalDate("Please enter the order date in YYYY-MM-DD format: ");
                tryAgain = false;
            } catch (DateTimeException e) {
                displayErrorMessage("ERROR: Date needs to be in YYYY-MM-DD format!");

            }
        } while (tryAgain);
        return date;
    }

    public void displayOrders(List<Order> orderList, LocalDate orderDate) {
        if(orderList.size() == 0) {
            displayErrorMessage("No orders found for " + orderDate);
        } else {
            for (Order currentOrder : orderList) {

                userIO.print("\n* * * * * * ORDERS FOR " + orderDate + "* * * * * *");
                userIO.print("Order number: " + currentOrder.getOrderNumber());
                displayOrder(currentOrder);
                userIO.print("\n");
            }
        }
    }

    public void displayErrorMessage(String message) {
        userIO.print(message);
    }

    public Order promptUserForOrderInfo(List<Product> productList, List<Tax> taxList) {
        Order newOrder = new Order();
        newOrder.setCustomerLastName(userIO.readString("Please enter the customer's Last Name: "));
        newOrder.setOrderDate(promptUserForDate());
        displayTaxes(taxList);
        newOrder.setState(userIO.readString("Please enter the customer's state: "));
        displayProducts(productList);
        String productName = userIO.readString("Please enter the product the customer has selected: ");
        newOrder.setOrderProduct(new Product(productName));
        newOrder.setArea(userIO.readBigDecimal("Please enter the floor area: "));

        return newOrder;
    }

    public boolean displayProcessedOrderAndConfirm(Order newOrder) {
        userIO.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        userIO.print("Your order information: ");
        displayOrder(newOrder);
        String confirm = userIO.readString("\nContinue with order? Y/N");
        if (confirm.toUpperCase().equals("Y")) {
            return true;
        } else return false;
    }

    public Order promptUserForOrderNumberAndDate() {
        Order orderToUpdate = new Order();
        orderToUpdate.setOrderDate(promptUserForDate());
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

    public void displayOrder(Order order) {
        userIO.print("Customer's name: " + order.getCustomerLastName());
        userIO.print("Order date: " + order.getOrderDate());
        userIO.print("Floor area: " + order.getArea());
        userIO.print("Customer's selected flooring: " + order.getOrderProduct().getProductType());
        userIO.print("Material cost per square foot: $" + order.getOrderProduct().getMaterialCostPerSquareFoot());
        userIO.print("Labor cost per square foot: $" + order.getOrderProduct().getLaborCostPerSquareFoot());
        userIO.print("Customer's state: " + order.getOrderTax().getState());
        userIO.print("Tax rate: " + order.getOrderTax().getTaxRate() + "%");
        userIO.print("Total Material cost: $" + order.getCalculatedMaterialCost().setScale(2, RoundingMode.HALF_UP));
        userIO.print("Total Labor cost: $" + order.getCalculatedLaborCost().setScale(2, RoundingMode.HALF_UP));
        userIO.print("Total taxes: $" + order.getCalculatedTaxAmount().setScale(2, RoundingMode.HALF_UP));
        userIO.print("Order total: $" + order.getTotalOrderAmount().setScale(2, RoundingMode.HALF_UP));
    }

    public void displayAddOrderConfirmation(Order addedOrder) {
        userIO.print("Order #" + addedOrder.getOrderNumber() + " has been successfully added.\n");
    }

    public void displayEditOrderConfirmation(Order updatedOrder) {
        userIO.print("Order #" + updatedOrder.getOrderNumber() + " has been successfully updated.\n");
    }

    public void displayRemoveOrderConfirmation(Order removedOrder) {
        userIO.print("Order #" + removedOrder.getOrderNumber() + " has been successfully removed.\n");
    }

    public boolean promptForConfirmation() {
        String confirm = userIO.readString("Confirm exit: have all changes been saved? Y/N: ");

        if (confirm.toUpperCase().equals("Y")) {
            return true;
        } else return false;
    }

    public void displayTaxes(List<Tax> taxList) {
        userIO.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        for (Tax currentTax : taxList) {
            userIO.print("State: " + currentTax.getState() + " | " + "Tax rate: " + currentTax.getTaxRate());
            }
        userIO.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }

    public void displayProducts(List<Product> productList) {
        userIO.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        for (Product currentProduct : productList) {
            userIO.print("Product: " + currentProduct.getProductType()
                    + " | " + "Material cost/sqft: " + currentProduct.getMaterialCostPerSquareFoot()
                    + " | " + "Labor cost/sqft: " + currentProduct.getLaborCostPerSquareFoot());
        }
        userIO.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }

    public void displaySaveSuccessMessage() {
        userIO.print("\nChanges have been saved!");
    }

    public String errorPrompt(String error) {
        return userIO.readString(error);
    }

    public boolean displaySwitchMode(boolean mode) {
        if(mode ==true) {
            String userInput = (userIO.readString("Current mode is Production. " +
                    "Do you want to switch to Training? Y/N")).toUpperCase();
            if(userInput.equals("Y")) {
                mode = false;
            }
        }
        else if(mode == false) {
            String userInput = (userIO.readString("Current mode is Training. " +
                    "Do you want to switch to Production? Y/N")).toUpperCase();
            if(userInput.equals("Y")) {
                mode = true;
            }
        }

        return mode;
    }//..
}
