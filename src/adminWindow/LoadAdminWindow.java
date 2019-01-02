package adminWindow;

import LoginWindow.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainWindowAdmin.UserAController;
import utilities.UserService;

import java.awt.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LoadAdminWindow extends Application {
    private static UserService userService;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Registry registry = LocateRegistry.getRegistry("localhost", 6789);

        userService = (UserService) registry.lookup("service");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminWindow.fxml"));
        Parent root = loader.load();

        AdminController adminController  = loader.getController();

        adminController.setUser(this);

        primaryStage.setTitle("Admin");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static UserService getUserService() {
        return userService;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

