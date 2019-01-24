package server;

import com.jfoenix.controls.JFXComboBox;
import trainerWindow.TrainerController;
import utilities.*;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImplements extends UnicastRemoteObject implements UserService {

    public UserServiceImplements() throws RemoteException {
    }

    @Override
    public User insertUser(User user) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "insert into user(IdUser, Login, Password, Imie, Nazwisko, Plec, Pesel, Rola) values (NULL, ?, ?, ?, ?, ?, ?, ?)";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getImie());
            statement.setString(4, user.getNazwisko());
            statement.setString(5, user.getPlec());
            statement.setString(6, user.getPesel());
            statement.setString(7, user.getRola());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                user.setIdUser(result.getLong(1));
        }
        result.close();
        return user;

    } catch (SQLException e) {
        e.printStackTrace();
            return null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateUser(User user) throws RemoteException {
        PreparedStatement statement = null;
        String sql = "update user set Login = ?"
                +", Password = ?"
                +", Imie = ?"
                +", Nazwisko = ?"
                +", Plec = ?"
                +", Pesel = ?"
                +", rola = ?"
                +"where IdUser= ?";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getImie());
            statement.setString(4, user.getNazwisko());
            statement.setString(5, user.getPlec());
            statement.setString(6, user.getPesel());
            statement.setString(7, user.getRola());
            statement.setLong(8, user.getIdUser());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateTrainerProfile(TrainerProfile trainerProfile) throws RemoteException {
        PreparedStatement statement = null;
        String sql = "update trainer_profile set specjalizacja = ?"
                +", informacje= ?"
                +"where id_trainer= ?";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setString(1, trainerProfile.getSpecjalizacja());
            statement.setString(2, trainerProfile.getInformacje());
            statement.setLong(3, trainerProfile.getId_trainer());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deteleUser(Long IdUser) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "delete from user where IdUser = ?";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);

            statement.setLong(1, IdUser);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

   @Override
    public String getLogin(String Login) throws RemoteException{
        PreparedStatement statement = null;
        String sql = "select * from user where Login = ?";
       String login = null;
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setString(1, Login);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                login = result.getString("Login");
            }
            return login;
        } catch (SQLException e) {
            e.printStackTrace();
            return login;
        }
    }

    @Override
    public String setLogin(Long IdUser) throws RemoteException {
        PreparedStatement statement = null;
        String sql = "select * from user where IdUser = ?";
        String login = null;
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, IdUser);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                login = result.getString("Login");
            }
            return login;
        } catch (SQLException e) {
            e.printStackTrace();
            return login;
        }
    }

    @Override
    public String getFieldFromUser(Long IdUser, String Field) throws RemoteException {
        PreparedStatement statement = null;
        String sql = "select * from user where IdUser = ?";
        String field = null;
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, IdUser);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                field = result.getString(Field);
            }
            return field;
        } catch (SQLException e) {
            e.printStackTrace();
            return field;
        }
    }

    @Override
    public String getTrainerFieldFromUser(String Field) throws RemoteException {
        PreparedStatement statement = null;
        String sql = "select * from user where Rola = 'trainer' ";
        String field = null;
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                field = result.getString(Field);
            }
            return field;
        } catch (SQLException e) {
            e.printStackTrace();
            return field;
        }
    }

    @Override
    public String getFieldFromTrainerProfile(Long IdUser, String Field) throws RemoteException {
        PreparedStatement statement = null;
        String sql = "select * from trainer_profile where id_trainer = ?";
        String field = null;
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, IdUser);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                field = result.getString(Field);
            }
            return field;
        } catch (SQLException e) {
            e.printStackTrace();
            return field;
        }
    }

    @Override
    public String getPassword(String Login, String Password) throws RemoteException {
        PreparedStatement statement = null;
        String sql = "select * from user where Login = ? and Password = ?";
        String password = null;
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setString(1, Login);
            statement.setString(2, Password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                password = result.getString("Password");
            }
            return password;
        } catch (SQLException e) {
            e.printStackTrace();
            return password;
        }
    }

    @Override
    public Long getID(String Login, String Password) throws RemoteException {
        PreparedStatement statement = null;
        String sql = "select * from user where Login = ? and Password = ?";
        long id = 0;
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setString(1, Login);
            statement.setString(2, Password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                id = result.getLong("IdUser");
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return id;
        }
    }

    @Override
    public String getPesel(String Login, String Pesel) throws RemoteException {
        PreparedStatement statement = null;
        String sql = "select * from user where Login = ? and Pesel = ?";
        String age = null;
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setString(1, Login);
            statement.setString(2, Pesel);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                age = result.getString("Pesel");
            }
            return age;
        } catch (SQLException e) {
            e.printStackTrace();
            return age;
        }
    }

    @Override
    public String getRola(String Login,String Password) throws RemoteException {
        PreparedStatement statement = null;
        String sql = "select * from user where Login = ? and Password = ?";
        String rola = null;
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setString(1, Login);
            statement.setString(2, Password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                rola = result.getString("Rola");
            }
            return rola;
        } catch (SQLException e) {
            e.printStackTrace();
            return rola;
        }
    }

    @Override
    public User getUserById(Long IdUser) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "select * from user where IdUser= ?";

        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, IdUser);

            ResultSet result = statement.executeQuery();

            User user = null;
            if(result.next()){
                user = new User();
                user.setIdUser(result.getLong("IdUser"));
                user.setLogin(result.getString("Login"));
                user.setPassword(result.getString("Password"));
                user.setImie(result.getString("Imie"));
                user.setNazwisko(result.getString("Nazwisko"));
                user.setPlec(result.getString("Plec"));
                user.setPesel(result.getString("Pesel"));
                user.setRola(result.getString("rola"));
            }
            result.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User getUser(String Login, String Pesel) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "select * from user where Login= ? and Pesel = ?";

        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setString(1, Login);
            statement.setString(2, Pesel);

            ResultSet result = statement.executeQuery();

            User user = null;
            if(result.next()) {
                user = new User();
                user.setIdUser(result.getLong("IdUser"));
                user.setLogin(result.getString("Login"));
                user.setPassword(result.getString("Password"));
                user.setImie(result.getString("Imie"));
                user.setNazwisko(result.getString("Nazwisko"));
                user.setPlec(result.getString("Plec"));
                user.setPesel(result.getString("Pesel"));
                user.setRola(result.getString("rola"));
            }
            result.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User getTrainerById(Long IdUser) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "select * from user where IdUser= ? and rola = 'trainer'";

        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, IdUser);

            ResultSet result = statement.executeQuery();

            User user = null;
            if(result.next()){
                user = new User();
                user.setIdUser(result.getLong("IdUser"));
                user.setLogin(result.getString("Login"));
                user.setPassword(result.getString("Password"));
                user.setImie(result.getString("Imie"));
                user.setNazwisko(result.getString("Nazwisko"));
                user.setPlec(result.getString("Plec"));
                user.setPesel(result.getString("Pesel"));
            }
            result.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUser() throws RemoteException {
        Statement statement = null;

        String sql = "select * from user where rola = 'customer'";

        try {
            statement = DatabaseConnection.getConnection().createStatement();

            ResultSet result = statement.executeQuery(sql);

            List<User> list = new ArrayList<User>();

            while(result.next()){
                User user = new User();
                user.setIdUser(result.getLong("IdUser"));
                user.setLogin(result.getString("Login"));
                user.setPassword(result.getString("Password"));
                user.setImie(result.getString("Imie"));
                user.setNazwisko(result.getString("Nazwisko"));
                user.setPlec(result.getString("Plec"));
                user.setPesel(result.getString("Pesel"));
                user.setRola(result.getString("Rola"));
                list.add(user);
            }

            result.close();

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<User> getAllTrainer() throws RemoteException {
        Statement statement = null;

        String sql = "select * from user where rola = 'trainer'";

        try {
            statement = DatabaseConnection.getConnection().createStatement();

            ResultSet result = statement.executeQuery(sql);

            List<User> list = new ArrayList<User>();

            while(result.next()){
                User user = new User();
                user.setIdUser(result.getLong("IdUser"));
                user.setLogin(result.getString("Login"));
                user.setPassword(result.getString("Password"));
                user.setImie(result.getString("Imie"));
                user.setNazwisko(result.getString("Nazwisko"));
                user.setPlec(result.getString("Plec"));
                user.setPesel(result.getString("Pesel"));
                user.setRola(result.getString("Rola"));
                list.add(user);
            }

            result.close();

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<User> getAllTrainerSpecjalist(String Specjalizacja) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "select user.*, trainer_profile.* from user, trainer_profile where rola = 'trainer' and specjalizacja = ? and IdUser=id_trainer";

        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setString(1, Specjalizacja);

            ResultSet result = statement.executeQuery();

            List<User> list = new ArrayList<User>();

            while(result.next()){
                User user = new User();
                user.setLogin(result.getString("Login"));
                user.setImie(result.getString("Imie"));
                user.setNazwisko(result.getString("Nazwisko"));
                list.add(user);
            }

            result.close();

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public List<User> getTest() throws RemoteException {
        Statement statement = null;

        String sql = "select * from user";

        try {
            statement = DatabaseConnection.getConnection().createStatement();

            ResultSet result = statement.executeQuery(sql);

            List<User> list = new ArrayList<User>();

            while(result.next()){
                User user = new User();
                user.setLogin(result.getString("Login"));
                list.add(user);
            }

            result.close();

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public UserProfile insertUserProfile(UserProfile userProfile) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "insert into user_profile(ID, USER_ID, height, weight, neat, goal, other) values (NULL, ?, ?, ?, ?, ?, ?)";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, userProfile.getUSER_ID());
            statement.setString(2, userProfile.getHeight());
            statement.setString(3, userProfile.getWeight());
            statement.setString(4, userProfile.getNeat());
            statement.setString(5, userProfile.getGoal());
            statement.setString(6, userProfile.getOther());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                userProfile.setID(result.getLong(1));
            }
            result.close();
            return userProfile;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public TrainerProfile insertTrainerProfile(TrainerProfile trainerProfile) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "insert into trainer_profile(id_trainer_profile, id_trainer, specjalizacja, informacje) values (NULL, ?, ?, ?)";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, trainerProfile.getId_trainer());
            statement.setString(2, trainerProfile.getSpecjalizacja());
            statement.setString(3, trainerProfile.getInformacje());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                trainerProfile.setId_trainer(result.getLong(1));
            }
            result.close();
            return trainerProfile;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
