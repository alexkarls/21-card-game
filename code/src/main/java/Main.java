import controller.ControllerFactory;
import controller.MenuController;
import view.MenuView;

public class Main {

    public static void main(String[] args) {
        MenuController controller = new MenuController(new ControllerFactory(), new MenuView());
        while (controller.run());
    }
}
