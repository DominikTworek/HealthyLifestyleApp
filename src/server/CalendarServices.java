package server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.Calendar.CalendarEvent;
import utilities.DatabaseConnection;

import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarServices {
    public static final int MAX_TITLE_LEN = 250;
    public static final int MAX_DESC_LEN = 250;
    public static final int MAX_DISPLAYED_LEN = 10;
    public static final int MAX_DISPLAYED_EVENTS = 3;

    private static CalendarEvent emptyEvent;

    private static ObservableList<Integer> hours;

    public static CalendarEvent getEmptyEvent() {
        if (emptyEvent == null) {
            emptyEvent = new CalendarEvent();
            emptyEvent.setIdEvent(-1L);
            emptyEvent.setIdUser(-1L);
            emptyEvent.setDay(0);
            emptyEvent.setMonth(0);
            emptyEvent.setYear(0);
            emptyEvent.setHour(getHours().get(0));
            emptyEvent.setTitle("Dodaj nowy");
            emptyEvent.setDescription("");
        }
        return emptyEvent;
    }

    public static String abbreviateToSize(String s, int limit){
        return s.substring(0, Math.min(s.length(), limit))+"...";
    }

    public static ObservableList<Integer> getHours() {
        if (hours == null) {
            List<Integer> hourList = new ArrayList<>(Arrays.asList(10, 11, 12, 13, 14, 15, 16, 17, 18, 19));
            hours = FXCollections.observableArrayList(hourList);
        }
        return hours;
    }

    public static ObservableList<CalendarEvent> getEventsForUserDate(Long userID, LocalDate date) throws RemoteException {
        PreparedStatement statement = null;

        String sql = "select * from calendarevent where IdUser=? and day=? and month=? and year=?;";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, userID);
            statement.setInt(2, date.getDayOfMonth());
            statement.setInt(3, date.getMonthValue());
            statement.setInt(4, date.getYear());

            ResultSet result = statement.executeQuery();
            List<CalendarEvent> events = new ArrayList<>();
            while (result.next()) {
                CalendarEvent e = new CalendarEvent();
                e.setIdEvent(result.getLong("IdEvent"));
                e.setIdUser(result.getLong("IdUser"));
                e.setDay(result.getInt("day"));
                e.setMonth(result.getInt("month"));
                e.setYear(result.getInt("year"));
                e.setHour(result.getInt("hour"));
                e.setTitle(result.getString("title"));
                e.setDescription(result.getString("description"));
                events.add(e);
            }
            result.close();

            return FXCollections.observableArrayList(events);
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

    public static void deleteEvent(Long eventID) {
        PreparedStatement statement = null;

        String sql = "delete * from calendarevent where IdEvent=?;";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, eventID);

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
            return;
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

    public static void updateEvent(Long eventID, String title, String description, int hour) {
        PreparedStatement statement = null;
        String sql = "update calendarevent set hour=?, title=?, description=? where IdEvent=?;";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setInt(1, hour);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setLong(4, eventID);

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
            return;
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

    public static void insertEvent(Long userID, LocalDate date, int hour, String title, String desc) {
        PreparedStatement statement = null;

        String sql = "insert into calendarevent(IdEvent, IdUser, day, month, year, hour, title, description) values (NULL,?,?,?,?,?,?,?);";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, userID);
            statement.setInt(2, date.getDayOfMonth());
            statement.setInt(3, date.getMonthValue());
            statement.setInt(4, date.getYear());
            statement.setInt(5, hour);
            statement.setString(6, title);
            statement.setString(7, title);

            System.out.println(statement.executeUpdate());

        } catch (SQLException e) {
            e.printStackTrace();
            return;
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
