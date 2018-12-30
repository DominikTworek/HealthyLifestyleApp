package trainerWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.UserService;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LoadTrainerWindow extends Application {

    private UserService userService;
    @Override
    public void start(Stage primaryStage) throws Exception{

        Registry registry = LocateRegistry.getRegistry("localhost", 6789);

        userService = (UserService) registry.lookup("service");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("TrainerWindow.fxml"));

        Parent root = loader.load();

        TrainerController trainerController = loader.getController();
        
        trainerController.setUser(this);
        
        primaryStage.setTitle("Trainer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public UserService getUserService() {
        return userService;
    }

    public static void main(String[] args) {

        launch(args);
    }
}