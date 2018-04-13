import com.sg.baseballleague.controller.BaseballLeagueController;
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

        BaseballLeagueController controller = ctx.getBean("controller", BaseballLeagueController.class);

        controller.run();

    }
}
