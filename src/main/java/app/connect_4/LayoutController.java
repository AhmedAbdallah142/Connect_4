package app.connect_4;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class LayoutController{
    @FXML
    private HBox Bar_Container;
    @FXML
    private HBox Board_container;
    @FXML
    private Label P1Score;
    @FXML
    private Label P2Score;
    @FXML
    private Accordion graphLevels;

    public static GUIGameController game;

    public void initialize() {
        LayoutBuilder l = LayoutBuilder.getInstance();
        l.CreateBarCircle(Bar_Container);
        l.CreateBoardCircle(Board_container);
        game = new GUIGameController(P1Score,P2Score,graphLevels);
    }
}
