package utilities;

import com.jfoenix.controls.JFXComboBox;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserService extends Remote {
    User insertUser(User user) throws RemoteException;

    void updateUser(User user) throws RemoteException;

    void deteleUser(Long IdUser) throws RemoteException;

    String getLogin(String Login) throws RemoteException;

    String getPassword(String Login, String Password) throws RemoteException;

    User getUserById(Long IdUser) throws  RemoteException;

    User getTrainerById(Long IdUser) throws  RemoteException;

    List<User> getAllUser() throws RemoteException;

    List<User> getTest() throws RemoteException;

}
