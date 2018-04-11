package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DvdController;
import com.sg.dvdlibrary.dao.DvdDao;
import com.sg.dvdlibrary.dao.DvdDaoFileImpl;
import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryAuditDaoFileImpl;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayer;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayerImpl;
import com.sg.dvdlibrary.ui.DvdView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        /*UserIO myIO = new UserIOConsoleImpl();
        DvdDao myDao = new DvdDaoFileImpl();
        DvdView myView = new DvdView(myIO);
        DvdLibraryAuditDao myAuditDao = new DvdLibraryAuditDaoFileImpl();
        DvdLibraryServiceLayer myService = new DvdLibraryServiceLayerImpl(myDao, myAuditDao);
        DvdController controller = new DvdController(myService, myView);*/

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DvdController controller = ctx.getBean("controller", DvdController.class);


        controller.run();

    }
    //Comment
}
