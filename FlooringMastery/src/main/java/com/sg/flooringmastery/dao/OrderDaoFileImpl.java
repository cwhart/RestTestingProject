package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import org.aspectj.weaver.ast.Or;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderDaoFileImpl implements OrderDao {

    private String orderFile;
    private final String DELIMITER = ",";
    private final String ID_FILE = "id.txt";
    private Map<LocalDate, Map<Integer, Order>> orderMap = new HashMap<LocalDate, Map<Integer, Order>>();
    private static int nextOrderNum;

    @Override
    public Order createOrder(Order orderToCreate) throws OrderPersistenceException  {
        LocalDate orderDate = orderToCreate.getOrderDate();
        orderToCreate.setOrderNumber(generateOrderNumber());
        loadOrdersByDate(orderDate);
        boolean dateFound = false;
        Map <Integer,Order> thisMap = new HashMap<>();
        //Check to see if there is already an entry in the map for this date. If not, create it.
        for(LocalDate currentDate : orderMap.keySet()) {

            if(currentDate.equals(orderDate)) {
                thisMap = orderMap.get(orderDate);

                dateFound = true;
            }
        } if(dateFound == false) {
            thisMap = orderMap.put(orderDate, new HashMap<Integer, Order>());

        }
        thisMap.put(orderToCreate.getOrderNumber(), orderToCreate);
        orderMap.put(orderDate, thisMap);

        return thisMap.get(orderToCreate.getOrderNumber());
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, Integer orderId) throws OrderPersistenceException {
        loadOrdersByDate(orderDate);
        //if(orderMap.containsKey(orderDate)) {
            Map<Integer, Order> mapForThisDate = orderMap.get(orderDate);

            //Since orders are stored without dates, need to set the order date when retrieved.
            for (Order currentOrder : mapForThisDate.values()) {
                currentOrder.setOrderDate(orderDate);
            }
            Order orderToReturn = mapForThisDate.get(orderId);
            if (orderToReturn != null) {
                return orderToReturn;
            } else throw new OrderPersistenceException("ERROR: Order not found!");
        //} else throw new OrderPersistenceException("ERROR: Date not found!");
    }

    @Override
    public List<Order> retrieveOrdersByDate(LocalDate date) throws OrderPersistenceException  {
        loadOrdersByDate(date);
        //if(orderMap.containsKey(date)) {
            Map<Integer, Order> ordersForDate = orderMap.get(date);
            List<Order> orderList = new ArrayList<>(ordersForDate.values());

            for (Order currentOrder : orderList) {
                currentOrder.setOrderDate(date);
            }
            return orderList;
        //} else throw new OrderPersistenceException("ERROR: Date not found!");
    }

    @Override
    public Order updateOrder(Order orderToUpdate) throws OrderPersistenceException {
        LocalDate thisDate = orderToUpdate.getOrderDate();
        loadOrdersByDate(thisDate);
        //if(orderMap.containsKey(thisDate)) {
            Map<Integer, Order> mapForThisDate = orderMap.get(thisDate);
            mapForThisDate.replace(orderToUpdate.getOrderNumber(), orderToUpdate);

            Order orderToReturn = mapForThisDate.get(orderToUpdate.getOrderNumber());
            if (orderToReturn != null) {
                return orderToReturn;
            } else throw new OrderPersistenceException("ERROR: Order not found!");
       // } else throw new OrderPersistenceException("ERROR: Date not found!");



    }

    @Override
    public void removeOrder(Order orderToRemove) throws OrderPersistenceException{
        LocalDate thisDate = orderToRemove.getOrderDate();
        loadOrdersByDate(thisDate);
       // if(orderMap.containsKey(thisDate)) {
            Map<Integer, Order> ordersForThisDate = orderMap.get(thisDate);
            if (ordersForThisDate.remove(orderToRemove.getOrderNumber()) == null) {
                throw new OrderPersistenceException("ERROR: Order not found!");
            }
            //orderMap.put(thisDate, ordersForThisDate);
        //} else throw new OrderPersistenceException("ERROR: Date not found!");
    }

    @Override
    public void save() throws OrderPersistenceException {
      writeOrderFile();

    }

    private void loadOrdersByDate(LocalDate date) throws OrderPersistenceException {
        if(!orderMap.containsKey(date)) {
            Scanner scanner;

            String formatted = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));

            orderFile = "Orders/Orders_" + formatted + ".txt";


            try {

                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(orderFile)));
            } catch (FileNotFoundException e) {
                Map<Integer, Order> newMap = new HashMap<>();

                orderMap.put(date, newMap);
                //throw new NewFileCreatedException("");

                return;
            }

            String currentLine;
            String[] currentTokens;
            Map<Integer, Order> todaysOrders = new HashMap<>();

            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);

                Order currentOrder = new Order(Integer.parseInt(currentTokens[0]));

                currentOrder.setCustomerLastName(currentTokens[1]);
                currentOrder.setState(currentTokens[2]);
                BigDecimal taxRate = new BigDecimal(currentTokens[3]);
                String productType = currentTokens[4];
                currentOrder.setArea(new BigDecimal(currentTokens[5]));
                BigDecimal materialCostPerSquareFoot = new BigDecimal(currentTokens[6]);
                BigDecimal laborCostPerSquareFoot = new BigDecimal(currentTokens[7]);
                currentOrder.setCalculatedMaterialCost(new BigDecimal(currentTokens[8]));
                currentOrder.setCalculatedLaborCost(new BigDecimal(currentTokens[9]));
                currentOrder.setCalculatedTaxAmount(new BigDecimal(currentTokens[10]));
                currentOrder.setTotalOrderAmount(new BigDecimal(currentTokens[11]));

                Tax orderTax = new Tax(currentOrder.getState(), taxRate);
                currentOrder.setOrderTax(orderTax);
                Product orderProduct = new Product(productType, materialCostPerSquareFoot, laborCostPerSquareFoot);
                currentOrder.setOrderProduct(orderProduct);

                todaysOrders.put(currentOrder.getOrderNumber(), currentOrder);

            }
            orderMap.put(date, todaysOrders);

            scanner.close();
        }

    }

    private void writeOrderFile() throws OrderPersistenceException{

        Set<LocalDate> orderDatesToBeSaved = orderMap.keySet();

        for (LocalDate currentDate : orderDatesToBeSaved) {

            Map<Integer,Order> currentMap = orderMap.get(currentDate);

            String formatted = currentDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));
            orderFile = "Orders/Orders_" + formatted + ".txt";

            PrintWriter out;

            try {
                out = new PrintWriter(new FileWriter(orderFile));
            } catch (IOException e) {

                throw new OrderPersistenceException("Could not save Order data.", e);
            }

            /*  Order of fields in file: OrderNumber,CustomerName,State,TaxRate,ProductType,
            Area,CostPerSquareFoot,LaborCost
            PerSquareFoot,MaterialCost,LaborCost,Tax,Total*/


            List<Order> orderList = new ArrayList<Order>(currentMap.values());
            for (Order currentOrder : orderList) {
                out.println(currentOrder.getOrderNumber() + DELIMITER +
                        currentOrder.getCustomerLastName() + DELIMITER +
                        currentOrder.getState() + DELIMITER +
                        currentOrder.getOrderTax().getTaxRate().setScale(2) + DELIMITER +
                        currentOrder.getOrderProduct().getProductType() + DELIMITER +
                        currentOrder.getArea().setScale(2) + DELIMITER +
                        currentOrder.getOrderProduct().getMaterialCostPerSquareFoot().setScale(2) + DELIMITER +
                        currentOrder.getOrderProduct().getLaborCostPerSquareFoot().setScale(2) + DELIMITER +
                        currentOrder.getCalculatedMaterialCost().setScale(2) + DELIMITER +
                        currentOrder.getCalculatedLaborCost().setScale(2) + DELIMITER +
                        currentOrder.getCalculatedTaxAmount().setScale(2) + DELIMITER +
                        currentOrder.getTotalOrderAmount().setScale(2));

                out.flush();
            }

            out.close();
        }
    }

    private int loadOrderIdFromFile() throws OrderPersistenceException {
        Scanner scanner;
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(ID_FILE)));
        } catch (FileNotFoundException e) {
            throw new OrderPersistenceException("-_- Could not load ID data into memory", e);
        }
        String currentLine = scanner.nextLine();
        int id = Integer.parseInt(currentLine);
        scanner.close();
        id++;
        return id;
    }

    private void writeOrderIdToFile(int idNumber) throws OrderPersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ID_FILE));
        } catch (IOException e) {
            throw new OrderPersistenceException("-_- Could not save ID data.", e);
        }
        out.println(idNumber);
        out.flush();
        out.close();
    }



    private int generateOrderNumber() throws OrderPersistenceException {
        nextOrderNum = loadOrderIdFromFile();
        writeOrderIdToFile(nextOrderNum);

        return nextOrderNum;
    }
}
