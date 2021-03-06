package utilities;

import com.jfoenix.controls.JFXComboBox;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserService extends Remote {
    User insertUser(User user) throws RemoteException;

    void updateUser(User user) throws RemoteException;

    void updateTrainerProfile(TrainerProfile trainerProfile) throws RemoteException;

    void deteleUser(Long IdUser) throws RemoteException;

    String getLogin(String Login) throws RemoteException;

    String setLogin(Long IdUser) throws RemoteException;

    String getFieldFromUser(Long IdUser, String Field) throws RemoteException;

    String getTrainerFieldFromUser(String Field) throws RemoteException;

    String getFieldFromTrainerProfile(Long IdUser, String Field) throws RemoteException;

    String getPassword(String Login, String Password) throws RemoteException;

    Long getID(String Login, String Password) throws RemoteException;

    String getPesel(String Login, String Pesel) throws RemoteException;

    String getRola(String Login, String Password) throws RemoteException;

    User getUserById(Long IdUser) throws  RemoteException;

    User getUser(String Login, String Pesel) throws  RemoteException;

    User getTrainerById(Long IdUser) throws  RemoteException;

    List<User> getAllUser() throws RemoteException;

    List<User> getAllTrainer() throws RemoteException;

    List<User> getAllTrainerSpecjalist(String Specjalizacja) throws RemoteException;

    List<User> getTest() throws RemoteException;

    UserProfile insertUserProfile(UserProfile userProfile) throws RemoteException;

    UserProfile getUserProfileById(Long UserID) throws RemoteException;

    TrainerProfile insertTrainerProfile(TrainerProfile trainerProfile) throws RemoteException;

    Training insertTraining(Training training) throws RemoteException;

    Training getTrainingByUserId(Long IdUser) throws RemoteException;

    Nutrition insertNutrition(Nutrition nutrition) throws RemoteException;

    Nutrition getNutritionByUserId(Long IdUser) throws RemoteException;

    void updateUserU(User trainer, Long UserID) throws RemoteException;

    UserProgress insertUserProgress(UserProgress userProgress) throws RemoteException;

    UserProgress getUserProgressById(Long IdUser) throws RemoteException;


}
