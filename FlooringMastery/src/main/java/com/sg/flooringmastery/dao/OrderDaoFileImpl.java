package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import org.aspectj.weaver.ast.Or;
import org.springframework.cglib.core.Local;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderDaoFileImpl implements OrderDao {

    public String orderFile;
    private final String DELIMITER = ",";
    private final String ID_FILE = "id.txt";
    private Map<LocalDate, Map<Integer, Order>> orderMap = new HashMap<LocalDate, Map<Integer, Order>>();
    private static int nextOrderNum;

    public OrderDaoFileImpl(String orderFile) {
        this.orderFile = orderFile;
    }

    public String getOrderFile() {
        return orderFile;
    }

    public void setOrderFile(String orderFile) {
        this.orderFile = orderFile;
    }

    @Override
    public Order createOrder(Order orderToCreate) throws OrderPersistenceException  {
        //Get the order date so we know which file to load from.
        LocalDate orderDate = orderToCreate.getOrderDate();
        //Generate the order number sequentially from the file.
        if(orderToCreate.getOrderNumber()==0) {
            orderToCreate.setOrderNumber(generateOrderNumber());
        }
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
            //Get the map of orders for this date.
            Map<Integer, Order> mapForThisDate = orderMap.get(orderDate);

            //Since orders are stored to the file without dates, need to set the order date when retrieved.
//            for (Order currentOrder : mapForThisDate.values()) {
//                currentOrder.setOrderDate(orderDate);
//            }
            //Now locate the order for the ID given and return it. If it doesn't exist, throw an exception.
            Order orderToReturn = mapForThisDate.get(orderId);
            if (orderToReturn != null) {
                return orderToReturn;
            } else throw new OrderPersistenceException("ERROR: Order not found!");

    }

    @Override
    public List<Order> retrieveOrdersByDate(LocalDate date) throws OrderPersistenceException  {
        loadOrdersByDate(date);
            //Get the orders for the date given and put then in a map, then a list.
            Map<Integer, Order> ordersForDate = orderMap.get(date);
            List<Order> orderList = new ArrayList<>(ordersForDate.values());

            //Populate the order dates since they are not stored in the file.
            for (Order currentOrder : orderList) {
                currentOrder.setOrderDate(date);
            }
            return orderList;

    }

    @Override
    public Order updateOrder(Order orderToUpdate) throws OrderPersistenceException {
        //Get the date for the given order and load orders for that date from the file and put them in a map.
        LocalDate thisDate = orderToUpdate.getOrderDate();
        loadOrdersByDate(thisDate);
        Map<Integer, Order> mapForThisDate = orderMap.get(thisDate);

        //replace the existing entry for this order ID with the new one. If not found, throw an exception.
        mapForThisDate.replace(orderToUpdate.getOrderNumber(), orderToUpdate);

        Order orderToReturn = mapForThisDate.get(orderToUpdate.getOrderNumber());
            if (orderToReturn != null) {
                return orderToReturn;
            } else throw new OrderPersistenceException("ERROR: Order not found!");

    }

    @Override
    public void removeOrder(LocalDate date, int id) throws OrderPersistenceException{
        //Get the date of the order and load orders from the corresponding file.
        //LocalDate thisDate = orderToRemove.getOrderDate();
        loadOrdersByDate(date);

        //Populate to a map, and then find the correct order by ID and remove it. If not found, throw an exception.
            Map<Integer, Order> ordersForThisDate = orderMap.get(date);
            if (ordersForThisDate.remove(id) == null) {
                throw new OrderPersistenceException("ERROR: Order not found!");
            }

    }

    @Override
    public void save() throws OrderPersistenceException {
        //Save to file.

        writeOrderFile();

    }

    private void loadOrdersByDate(LocalDate date) throws OrderPersistenceException {

        //If the order map contains the date given, execute the code.
        if(!orderMap.containsKey(date)) {
            Scanner scanner;

            //Format the date as MMddyyyy to be used in the filename to retrieve.
            String formatted = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));

            //Generate the filename.
            orderFile = "Orders/Orders_" + formatted + ".txt";


            //Read from the file. If file doesn't exist, create it.
            try {

                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(orderFile)));
            } catch (FileNotFoundException e) {
                Map<Integer, Order> newMap = new HashMap<>();

                orderMap.put(date, newMap);

                return;
            }

            //For each line in the file, split by comma delimiter and populate elements into an Order object.
            String currentLine;
            String[] currentTokens;
            Map<Integer, Order> todaysOrders = new HashMap<>();

            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);

                //Constructor takes in first token as Order ID.
                Order currentOrder = new Order();
                currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
                currentOrder.setCustomerLastName(currentTokens[1]);
                currentOrder.setState(currentTokens[2]);
                //For Tax and Product objects, create temporary variables and populate them into
                //objects below.

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
                currentOrder.setOrderDate(date);
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
                        currentOrder.getOrderTax().getTaxRate().setScale(2, RoundingMode.HALF_UP) + DELIMITER +
                        currentOrder.getOrderProduct().getProductType() + DELIMITER +
                        currentOrder.getArea().setScale(2, RoundingMode.HALF_UP) + DELIMITER +
                        currentOrder.getOrderProduct().getMaterialCostPerSquareFoot().setScale(2, RoundingMode.HALF_UP) + DELIMITER +
                        currentOrder.getOrderProduct().getLaborCostPerSquareFoot().setScale(2, RoundingMode.HALF_UP) + DELIMITER +
                        currentOrder.getCalculatedMaterialCost().setScale(2, RoundingMode.HALF_UP) + DELIMITER +
                        currentOrder.getCalculatedLaborCost().setScale(2, RoundingMode.HALF_UP) + DELIMITER +
                        currentOrder.getCalculatedTaxAmount().setScale(2, RoundingMode.HALF_UP) + DELIMITER +
                        currentOrder.getTotalOrderAmount().setScale(2, RoundingMode.HALF_UP));

                out.flush();
            }

            out.close();
        }
    }

    //Methods to generate the order ID = this loads the order ID file and increments it.
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

    //Write the updated order ID to the file.
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

    //....
}
