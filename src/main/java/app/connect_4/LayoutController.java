package app.connect_4;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class LayoutController {
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
    @FXML
    private CheckBox Revisit;
    @FXML
    private CheckBox Pruning;
    @FXML
    private Spinner<Integer> Depth;

    public static GUIGameController game;

    public void initialize() {
        LayoutBuilder l = LayoutBuilder.getInstance();
        l.CreateBarCircle(Bar_Container);
        l.CreateBoardCircle(Board_container);
        AddEvents();
        startNewGame();
    }

    private void startNewGame() {
        game = new GUIGameController(P1Score, P2Score, graphLevels);
    }

    private void AddEvents() {
        Depth.valueProperty().addListener((obs, oldValue, newValue) -> changeDepth(newValue));
    }


    public void changeSpeed(MouseEvent mouseEvent) {
        int temp = 2;
        if (Revisit.isSelected()) {
            temp = 1;
        }
        if (Pruning.isSelected()) {
            temp += 2;
        }
        game.setSpeed(temp);
    }

    public void changeDepth(int depth) {
        System.out.println();
        game.setDepth(depth);
    }
}
