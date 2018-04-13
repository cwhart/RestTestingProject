import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.sg.vendingmachine.ui.UserIo;
import com.sg.vendingmachine.ui.UserIoConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        /*

        UserIo myIO = new UserIoConsoleImpl();
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineView myView = new VendingMachineView(myIO);
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao);
        VendingMachineController controller = new VendingMachineController(myService, myView);*/

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);

        controller.run();

    }

//Adding a comment for commit
}
