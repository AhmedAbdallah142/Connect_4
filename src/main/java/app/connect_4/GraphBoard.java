package app.connect_4;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class GraphBoard {
    Accordion layout;

    public GraphBoard(Accordion layout){
        this.layout = layout;

    }

    public void addGraphLevel(ScrollPane panel){
        Platform.runLater(() -> {
            TitledPane pane = new TitledPane("LEVEL 0", panel);
            layout.getPanes().add(pane);
        });

    }
}
