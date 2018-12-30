package RegistryWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.UserService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LoadRegistryWindow extends Application {
    private UserService userService;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 6789);

        userService = (UserService) registry.lookup("service");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistryWindow.fxml"));
        Parent root = loader.load();

        RegistryController registryController = loader.getController();

        registryController.setUser(this);

        primaryStage.setTitle("Rejestracja");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public UserService getUserService() {
        return userService;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
