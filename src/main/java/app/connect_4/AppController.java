package app.connect_4;

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

    private Game game;
    Circle[] barCircles;
    VBox[] boardColumns;
    Circle[] boardCircles;

    public void initialize() {
        CreateBarCircle();
        CreateBoardCircle();
        game = new Game(barCircles,boardCircles);
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
            boardColumns[i].prefHeight(700.0);
            boardColumns[i].prefWidth(60.0);
            boardColumns[i].setStyle("-fx-padding:5;-fx-spacing:5;");
            AddMouseEvents(boardColumns[i]);
        }
        Board_container.getChildren().addAll(boardColumns);
        for (int i = 0; i < 42; i++) {
            boardCircles[i] = new Circle();
            CreateCircle(boardCircles[i]);
            boardColumns[i%7].getChildren().add(0,boardCircles[i]);
        }
    }

    private  void CreateCircle(Circle c){
        c.setFill(Color.valueOf("#b8b8b8"));
        c.setRadius(30.0f);
        c.setStroke(Color.BLACK);
        c.setStrokeType(StrokeType.INSIDE);
    }

    private void AddMouseEvents(VBox col){
        col.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
            VBox v = (VBox) mouseEvent.getSource();
            int index = Character.getNumericValue(v.getId().charAt(1));
            game.EnterColumn(index);
        });
        col.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> {
            VBox v = (VBox) mouseEvent.getSource();
            int index = Character.getNumericValue(v.getId().charAt(1));
            game.LeaveColumn(index);
        });
        col.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            VBox v = (VBox) mouseEvent.getSource();
            int index = Character.getNumericValue(v.getId().charAt(1));
            game.insertBall(index);
        });
    }
}
