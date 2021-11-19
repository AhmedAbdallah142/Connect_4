package app.connect_4;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class AppController {
    @FXML
    private HBox Bar_Container;
    @FXML
    private HBox Board_container;

    Circle[] barCircles;
    VBox[] boardColumns;
    Circle[] boardCircles;

    public void initialize() {
        CreateBarCircle();
        CreateBoardCircle();
    }

    private void CreateBarCircle() {
        barCircles = new Circle[7];
        for (int i = 0; i < 7; i++) {
            barCircles[i] = new Circle();
            CreateCircle(barCircles[i]);
            barCircles[i].setVisible(false);
        }
        Bar_Container.getChildren().addAll(barCircles);
    }

    private void CreateBoardCircle() {
        boardCircles = new Circle[42];
        boardColumns = new VBox[7];
        for (int i = 0; i < 7; i++) {
            boardColumns[i] = new VBox();
            boardColumns[i].setId("V" + i);
            boardColumns[i].prefHeight(705.0);
            boardColumns[i].prefWidth(83.0);
            boardColumns[i].setStyle("-fx-padding:10;-fx-spacing:5;");
            AddMouseEvents(boardColumns[i]);
        }
        Board_container.getChildren().addAll(boardColumns);
        for (int i = 0; i < 42; i++) {
            boardCircles[i] = new Circle();
            CreateCircle(boardCircles[i]);
            boardColumns[i%7].getChildren().add(boardCircles[i]);
        }
    }

    private  void CreateCircle(Circle c){
        c.setFill(Color.valueOf("#b8b8b8"));
        c.setRadius(40.0f);
        c.setStroke(Color.BLACK);
        c.setStrokeType(StrokeType.INSIDE);
    }

    private void AddMouseEvents(VBox col){
        col.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                EnterColumn(mouseEvent);
            }
        });
        col.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                LeaveColumn(mouseEvent);
            }
        });
    }

    @FXML
    protected void EnterColumn(MouseEvent mouseEvent) {
        VBox v = (VBox) mouseEvent.getSource();
        int index = Character.getNumericValue(v.getId().charAt(1));
        Circle c = barCircles[index];
        c.setVisible(true);
    }

    @FXML
    protected void LeaveColumn(MouseEvent mouseEvent) {
        VBox v = (VBox) mouseEvent.getSource();
        int index = Character.getNumericValue(v.getId().charAt(1));
        Circle c = barCircles[index];
        c.setVisible(false);
    }
}
