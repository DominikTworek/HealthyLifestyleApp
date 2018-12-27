package server;

import utilities.DatabaseConnection;
import utilities.User;
import utilities.UserService;

import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImplements implements UserService {
    @Override
    public User insertUser(User user) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "insert into user(IdUser, Login, Password, Imie, Nazwisko, Plec, Pesel, rola) values (NULL, ?, ?, ?, ?, ?, ?, ?)";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getImie());
            statement.setString(4, user.getNazwisko());
            statement.setLong(5, user.getPlec());
            statement.setLong(6, user.getPesel());
            statement.setLong(7, user.getRola());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                user.setIdUser(result.getLong(0));
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
            statement.setLong(5, user.getPlec());
            statement.setLong(6, user.getPesel());
            statement.setLong(7, user.getRola());
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
    public User getUserById(Long IdUser) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "select * from user where IdUser= ? and rola = 3";

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
                user.setPlec(result.getLong("Plec"));
                user.setPesel(result.getLong("Pesel"));
                user.setRola(result.getLong("rola"));
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

        String sql = "select * from user where IdUser= ? and rola = 2";

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
                user.setPlec(result.getLong("Plec"));
                user.setPesel(result.getLong("Pesel"));
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

        String sql = "select * from user";

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
                user.setPlec(result.getLong("Plec"));
                user.setPesel(result.getLong("Pesel"));
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
}
