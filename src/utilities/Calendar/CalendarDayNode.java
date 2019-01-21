package utilities.Calendar;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import server.CalendarServices;

import java.rmi.RemoteException;
import java.time.LocalDate;

/**
 * Create an anchor pane that can store additional data.
 */
public class CalendarDayNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;
    private ObservableList<CalendarEvent> events;

    private void actionHandle(MouseEvent e){
        System.out.println("This pane's date is: " + date);
    }

    private void addDescriptionText(){
        Text txt = new Text(String.valueOf(date.getDayOfMonth()));
        setTopAnchor(txt, 5.0);
        setLeftAnchor(txt, 5.0);
        getChildren().add(txt);
        Text eventNumber = new Text("["+events.size()+"]");
        setTopAnchor(eventNumber, 5.0);
        setLeftAnchor(eventNumber, 20.0);
        eventNumber.setFill(Color.CRIMSON);
        getChildren().add(eventNumber);
    }

    private void addEventsText(){
        int displayedEvents = events.size();
        if (displayedEvents > CalendarServices.MAX_DISPLAYED_EVENTS)
            displayedEvents = CalendarServices.MAX_DISPLAYED_EVENTS;
        for (int i = 0; i < displayedEvents; i++) {
            Text tmp = new Text(CalendarServices.abbreviateToSize(events.get(i).getTitle(), CalendarServices.MAX_DISPLAYED_LEN));
            setTopAnchor(tmp, 20.0+i*20.0);
            setLeftAnchor(tmp, 5.0);
            getChildren().add(tmp);
        }
    }


    public CalendarDayNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        setOnMouseClicked(this::actionHandle);
    }


    public void fill(LocalDate date){
        if (getChildren().size() != 0) {
            getChildren().clear();
        }
        try {
            events = CalendarServices.getEventsForUserDate(1L, date);
            if (events == null){
                System.out.println("Pusta lista!");
            }
            setDate(date);
            addDescriptionText();
            addEventsText();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
