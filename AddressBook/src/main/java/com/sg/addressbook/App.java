package com.sg.addressbook;

import com.sg.addressbook.controller.AddressBookController;
import com.sg.addressbook.dao.AddressBookDao;
import com.sg.addressbook.dao.AddressBookDaoFileImpl;
import com.sg.addressbook.ui.AddressBookView;
import com.sg.addressbook.ui.UserIO;
import com.sg.addressbook.ui.UserIoConsoleImpl;

public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIoConsoleImpl();
        AddressBookView myView = new AddressBookView(myIo);
        AddressBookDao myDao = new AddressBookDaoFileImpl();

        AddressBookController controller = new AddressBookController(myDao, myView);
        controller.run();
    }
}
