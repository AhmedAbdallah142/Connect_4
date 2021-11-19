package app.connect_4;

import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class GraphBoard {
    private Scene scene = AppLayout.scene;

    public void addGraphLevel(){
        Accordion a = (Accordion) scene.lookup("#graphLevels");
        AnchorPane newPanelContent = new AnchorPane();
        newPanelContent.getChildren().add(new Label("HELLO GRAPH"));
        TitledPane pane = new TitledPane("LEVEL 0", newPanelContent);
        a.getPanes().add(pane);
    }
}
