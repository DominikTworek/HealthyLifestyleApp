package server;

import utilities.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImplements extends UnicastRemoteObject implements UserService {

    public UserServiceImplements() throws RemoteException {
    }

    @Override
    public User insertUser(User user) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "insert into USERS(IDUSER, LOGIN, PASSWORD, IMIE, NAZWISKO, PLEC, PESEL, ROLA) values (USERS_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?)";
        //String sql = "insert into students (STUDENT_ID, LOGIN, PASSWORD, IMIE, NAZWISKO, PLEC, PESEL, ROLA) values (student_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql,new String[] {"iduser"});
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
        String sql = "update USERS set Login = ?"
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

        String sql = "delete from USERS where IdUser = ?";
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
        String sql = "select * from USERS where Login = ?";
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
        String sql = "select * from USERS where IdUser = ?";
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
        String sql = "select * from USERS where IdUser = ?";
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
        String sql = "select * from USERS where Rola = 'trainer' ";
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
        String sql = "select * from USERS where Login = ? and Password = ?";
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
        String sql = "select * from USERS where Login = ? and Password = ?";
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
        String sql = "select * from USERS where Login = ? and Pesel = ?";
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
        String sql = "select * from USERS where Login = ? and Password = ?";
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

        String sql = "select * from USERS where IdUser= ?";

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
                user.setIdUser_U(result.getLong("IDUSER_U"));
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

        String sql = "select * from USERS where Login= ? and Pesel = ?";

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

        String sql = "select * from USERS where IdUser= ? and rola = 'trainer'";

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

        String sql = "select * from USERS where rola = 'customer'";

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

        String sql = "select * from USERS where rola = 'trainer'";

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
                user.setIdUser_U(result.getLong("IDUSER_U"));
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

        String sql = "select USERS.*, trainer_profile.* from USERS, trainer_profile where rola = 'trainer' and specjalizacja = ? and IdUser=id_trainer";

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

        String sql = "select * from USERS";

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

        String sql = "insert into user_profile(ID, USER_ID, height, weight, neat, goal, other) values (USER_PROFILE_SEQ.nextval, ?, ?, ?, ?, ?, ?)";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql, new String[]{"ID"});
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

        String sql = "insert into trainer_profile(id_trainer_profile, id_trainer, specjalizacja, informacje) values (TRAINER_PROFILE_SEQ.nextval, ?, ?, ?)";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql, new String[]{"id_trainer_profile"});
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

    @Override
    public Training insertTraining(Training training) throws  RemoteException {
        PreparedStatement statement = null;

        String sql = "insert into training(ID_training, chest, shoulders, biceps, triceps, back, abs, legs, " +
                "chest_s, shoulders_s, biceps_s, triceps_s, back_s, abs_s, legs_s," +
                "chest_p, shoulders_p, biceps_p, triceps_p, back_p, abs_p, legs_p, ID_user) " +
                "values (TRAINING_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql, new String[]{"id_training"});
            statement.setString(1, training.getChest());
            statement.setString(2, training.getShoulders());
            statement.setString(3, training.getBiceps());
            statement.setString(4, training.getTriceps());
            statement.setString(5, training.getBack());
            statement.setString(6, training.getAbs());
            statement.setString(7, training.getLegs());

            statement.setInt(8, training.getChest_s());
            statement.setInt(9, training.getShoulders_s());
            statement.setInt(10, training.getBiceps_s());
            statement.setInt(11, training.getTriceps_s());
            statement.setInt(12, training.getBack_s());
            statement.setInt(13, training.getAbs_s());
            statement.setInt(14, training.getLegs_s());

            statement.setInt(15, training.getChest_p());
            statement.setInt(16, training.getShoulders_p());
            statement.setInt(17, training.getBiceps_p());
            statement.setInt(18, training.getTriceps_p());
            statement.setInt(19, training.getBack_p());
            statement.setInt(20, training.getAbs_p());
            statement.setInt(21, training.getLegs_p());

            statement.setLong(22, training.getID_user());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                training.setID_training(result.getLong(1));
            }
            result.close();
            return training;

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
    public Training getTrainingByUserId(Long IdUser) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "select * from training where ID_user = ?";

        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, IdUser);

            ResultSet result = statement.executeQuery();

            Training training = null;
            while(result.next()){
                training = new Training.Builder()
                        .Chest(result.getString("chest"))
                        .Chest_s(result.getInt("chest_s"))
                        .Chest_p(result.getInt("chest_p"))
                        .Shoulders(result.getString("shoulders"))
                        .Shoulders_s(result.getInt("shoulders_s"))
                        .Shoulders_p(result.getInt("shoulders_p"))
                        .Biceps(result.getString("biceps"))
                        .Biceps_s(result.getInt("biceps_s"))
                        .Biceps_p(result.getInt("biceps_p"))
                        .Triceps(result.getString("triceps"))
                        .Triceps_s(result.getInt("triceps_s"))
                        .Triceps_p(result.getInt("triceps_p"))
                        .Back(result.getString("back"))
                        .Back_s(result.getInt("back_s"))
                        .Back_p(result.getInt("back_p"))
                        .Abs(result.getString("abs"))
                        .Abs_s(result.getInt("abs_s"))
                        .Abs_p(result.getInt("abs_p"))
                        .Legs(result.getString("legs"))
                        .Legs_s(result.getInt("legs_s"))
                        .Legs_p(result.getInt("legs_p"))
                        .build();
            }

            result.close();

            return training;
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
    public Nutrition insertNutrition(Nutrition nutrition) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "insert into nutrition(ID_nutrition, calories, protein, carbs, fat, sugars, saturedfat, unsaturedfat, ID_user) values (NUTRITION_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql, new String[]{"ID_nutrition"});
            statement.setInt(1, nutrition.getCalories());
            statement.setInt(2, nutrition.getProtein());
            statement.setInt(3, nutrition.getCarbs());
            statement.setInt(4, nutrition.getFat());
            statement.setInt(5, nutrition.getSugars());
            statement.setInt(6, nutrition.getSaturedfat());
            statement.setInt(7, nutrition.getUnsaturedfat());
            statement.setLong(8, nutrition.getID_user());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next())
                nutrition.setID_user(result.getLong(1));

            result.close();
            return nutrition;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }finally {
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
    public Nutrition getNutritionByUserId(Long IdUser) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "select * from nutrition where ID_user = ?";

        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, IdUser);

            ResultSet result = statement.executeQuery();

            Nutrition nutrition = null;
            while(result.next()){
               nutrition = new Nutrition();
               nutrition.setID_nutrition(result.getLong("ID_nutrition"));
               nutrition.setCalories(result.getInt("calories"));
               nutrition.setProtein(result.getInt("protein"));
               nutrition.setCarbs(result.getInt("carbs"));
               nutrition.setFat(result.getInt("fat"));
               nutrition.setSugars(result.getInt("sugars"));
               nutrition.setSaturedfat(result.getInt("saturedfat"));
               nutrition.setUnsaturedfat(result.getInt("unsaturedfat"));
               nutrition.setID_user(result.getLong("ID_user"));
            }

            result.close();

            return nutrition;
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
    public void updateUserU(User trainer, Long UserID) throws RemoteException {
        PreparedStatement statement = null;
        String sql = "update USERS set Login = ?"
                +", Password = ?"
                +", Imie = ?"
                +", Nazwisko = ?"
                +", Plec = ?"
                +", Pesel = ?"
                +", rola = ?"
                +", IDUSER_U = ?"
                +"where IdUser= ?";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setString(1, trainer.getLogin());
            statement.setString(2, trainer.getPassword());
            statement.setString(3, trainer.getImie());
            statement.setString(4, trainer.getNazwisko());
            statement.setString(5, trainer.getPlec());
            statement.setString(6, trainer.getPesel());
            statement.setString(7, trainer.getRola());
            statement.setLong(8, UserID);
            statement.setLong(9, trainer.getIdUser());

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
}
