package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DvdController;
import com.sg.dvdlibrary.dao.DvdDao;
import com.sg.dvdlibrary.dao.DvdDaoFileImpl;
import com.sg.dvdlibrary.ui.DvdView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {

        UserIO myIO = new UserIOConsoleImpl();
        DvdDao myDao = new DvdDaoFileImpl();
        DvdView myView = new DvdView(myIO);


        DvdController controller = new DvdController(myDao, myView);
        controller.run();
  
    }

}
