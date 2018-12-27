package server;

import javafx.application.Application;

import javafx.stage.Stage;
import utilities.DatabaseConnection;
import utilities.UserService;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class CapitalizeServer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        DatabaseConnection.getConnection();

        Registry registry = LocateRegistry.createRegistry(6789);

        UserServiceImplements userServiceImplements = new UserServiceImplements();

        UserService userService = (UserService) UnicastRemoteObject.exportObject(userServiceImplements, 0);

        registry.rebind("service", userService);

        System.out.println("server is running");

    }

    public static void main(String[] args){
        launch(args);
    }
}
