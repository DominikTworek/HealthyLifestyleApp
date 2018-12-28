package server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import utilities.DatabaseConnection;
import utilities.User;
import utilities.UserService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class TestClient extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 6789);

        UserService userService = (UserService) registry.lookup("service");

        User p = new User();
        p.setLogin("trzcina");
        p.setPassword("to");
        p.setImie("pizda");
        p.setNazwisko("z farter");
        p.setPlec("1");
        p.setPesel("332342342");
        p.setRola("3");

        p = userService.insertUser(p);

        System.out.println(p.getIdUser());
        System.out.println(p.getLogin());
        System.out.println(p.getImie());
        System.out.println(p.getNazwisko());
        System.out.println(p.getPlec());
        System.out.println(p.getPesel());
        System.out.println(p.getRola());

        Platform.exit();
    }

    public static void main(String[] args){
        launch(args);
    }
}
