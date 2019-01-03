package LoginWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.UserService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LoadLoginWindow extends Application {
    private static UserService userService;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Registry registry = LocateRegistry.getRegistry("localhost", 6789);

        userService = (UserService) registry.lookup("service");

        Parent root = FXMLLoader.load(getClass().getResource("LoginWindow.fxml"));

        primaryStage.setTitle("Login Window");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.show();
    }

    public static UserService getUserService() {
        return userService;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
