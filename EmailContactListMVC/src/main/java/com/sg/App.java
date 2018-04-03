package com.sg;

import com.sg.controller.EmailListController;
import com.sg.dao.EmailContactDao;
import com.sg.dao.EmailContactInMemImpl;
import com.sg.io.UserIO;
import com.sg.io.UserIOConsoleImpl;
import com.sg.service.EmailListService;
import com.sg.service.EmailListServiceImpl;
import com.sg.view.EmailListView;

public class App {

    public static void main(String[] args) {

        //First we need an instance of userIO to inject into the view because we defined a constructor
        //in the view that accepts io as a parameter
        UserIO io = new UserIOConsoleImpl();
        //Inject io into the view through constructor dependeny injection
        EmailListView view = new EmailListView(io);
        //Create an instance of the DAO by calling its contstructor
        EmailContactDao dao = new EmailContactInMemImpl();
        //Inject that DAO into the service
        EmailListService service = new EmailListServiceImpl(dao);
        //inject both the view and the service into the controller
        EmailListController controller = new EmailListController(view, service);

        controller.run();
    }
}
