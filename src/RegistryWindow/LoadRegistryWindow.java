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
   // private static UserService userService;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Registry registry = LocateRegistry.getRegistry("localhost", 6789);

        //userService = (UserService) registry.lookup("service");
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistryWindow.fxml"));
        //Parent root = loader.load();

        Parent root = FXMLLoader.load(getClass().getResource("RegistryWindow.fxml"));
        //RegistryController registryController = loader.getController();
        //registryController.setUser();

        primaryStage.setTitle("Rejestracja");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    /*public static Parent execWindow() throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 6789);

        userService = (UserService) registry.lookup("service");
        FXMLLoader loader = new FXMLLoader(LoadRegistryWindow.class.getResource("RegistryWindow.fxml"));
        Parent root = loader.load();

        //RegistryController registryController = loader.getController();
        //registryController.setUser();

        return root;
    }*/

    //static UserService getUserService() {
      //  return userService;
    //}

    public static void main(String[] args) {
        launch(args);
    }
}
