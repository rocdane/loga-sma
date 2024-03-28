package com.loga.ui;

import com.loga.app.controller.AgendaController;
import com.loga.app.controller.GuiController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;

public class FullCalendarView {
    private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>();
    private VBox view;
    private Text calendarTitle;
    private YearMonth currentYearMonth;

    /**
     * Create a calendar view
     * @param yearMonth year month to create the calendar of
     */
    public FullCalendarView(YearMonth yearMonth) {
        /*CalendrierRepository calendrierRepository = new CalendrierRepository();
        List<Calendrier> calendriers = calendrierRepository.findAll();*/

        currentYearMonth = yearMonth;

        // Create the calendar grid pane
        GridPane calendar = new GridPane();
        calendar.setGridLinesVisible(true);

        // Create rows and columns with anchor panes for the calendar
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode ap = new AnchorPaneNode();
                ap.setPrefSize(GuiController.getInstance().getContent().getWidth()/7,GuiController.getInstance().getContent().getHeight()/7);
                calendar.add(ap,j,i);
                allCalendarDays.add(ap);
            }
        }

        // Days of the week labels
        Text[] dayNames = new Text[]{new Text("Dimanche"), new Text("Lundi"), new Text("Mardi"),
                                        new Text("Mercredi"), new Text("Jeudi"), new Text("Vendredi"),
                                        new Text("Samedi")};
        GridPane dayLabels = new GridPane();
        dayLabels.setPrefSize(GuiController.getInstance().getContent().getWidth()/7,GuiController.getInstance().getContent().getHeight()/7);
        Integer col = 0;
        for (Text txt : dayNames) {
            AnchorPane ap = new AnchorPane();
            ap.setPrefSize(GuiController.getInstance().getContent().getWidth()/7, GuiController.getInstance().getContent().getHeight()/7);
            ap.setBottomAnchor(txt,5.0);
            ap.getChildren().add(txt);
            dayLabels.add(ap, col++, 0);
        }

        // Create calendarTitle and buttons to change current month
        calendarTitle = new Text();
        Button previousMonth = new Button("<<");
        previousMonth.setOnAction(e -> previousMonth());
        Button nextMonth = new Button(">>");
        nextMonth.setOnAction(e -> nextMonth());
        HBox titleBar = new HBox(previousMonth, calendarTitle, nextMonth);
        titleBar.setSpacing(100);
        titleBar.setAlignment(Pos.BASELINE_CENTER);

        // Populate calendar with the appropriate day numbers
        populateCalendar(yearMonth);

        // Create the calendar view
        view = AgendaController.getInstance().getCalendar();
        view.getChildren().addAll(titleBar,dayLabels,calendar);
    }

    /**
     * Set the days of the calendar to correspond to the appropriate date
     * @param yearMonth year and month of month to render
     */
    public void populateCalendar(YearMonth yearMonth) {
        // Get the date we want to start with on the calendar
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);

        ZoneId systemTimeZone = ZoneId.systemDefault();

        calendarDate.atStartOfDay(systemTimeZone);

        // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
        while (!calendarDate.getDayOfWeek().toString().equals("DIMANCHE") ) {
            calendarDate = calendarDate.minusDays(1);
        }
        // Populate the calendar with day numbers
        for (AnchorPaneNode ap : allCalendarDays) {
            if (ap.getChildren().size() != 0) {
                ap.getChildren().remove(0);
            }
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            ap.setDate(calendarDate);
            ap.setTopAnchor(txt, 5.0);
            ap.setLeftAnchor(txt, 5.0);
            ap.setBottomAnchor(txt,5.0);
            ap.setRightAnchor(txt,5.0);
            ap.getChildren().add(txt);

            calendarDate = calendarDate.plusDays(1);
        }
        // Change the title of the calendar
        calendarTitle.setText(yearMonth.getMonth().toString() + " " + String.valueOf(yearMonth.getYear()));
    }

    /**
     * Move the month back by one. Repopulate the calendar with the correct dates.
     */
    private void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    /**
     * Move the month forward by one. Repopulate the calendar with the correct dates.
     */
    private void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    public VBox getView() {
        return view;
    }

    public ArrayList<AnchorPaneNode> getAllCalendarDays() {
        return allCalendarDays;
    }

    public void setAllCalendarDays(ArrayList<AnchorPaneNode> allCalendarDays) {
        this.allCalendarDays = allCalendarDays;
    }
}
