package com.loga.ui;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import java.time.LocalDate;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;

    private Popup popup;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        popup = new Popup();
        popup.getContent().add(new Text());
        popup.setAutoHide(true);

        this.setOnMouseClicked(e -> {
            System.out.println("This pane's date is: " + date);
            /*Pane pop = new Pane();
            Text text = new Text();
            text.setText("Calendrier :"+ date);
            pop.getChildren().add(text);
            popup.show(View.getInstance().getGui());*/
        });
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
