package app.connect_4;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class LayoutBuilder {
    private Circle[] barCircles;
    private Circle[] boardCircles;

    private static LayoutBuilder instance;
    private LayoutBuilder(){}

    public static LayoutBuilder getInstance(){
        if(instance==null)
            instance = new LayoutBuilder();

        return instance;
    }


    protected void CreateBarCircle(Pane Bar_Container) {
        barCircles = new Circle[7];
        for (int i = 0; i < 7; i++) {
            barCircles[i] = new Circle();
            CreateCircle(barCircles[i], Color.RED);
            barCircles[i].setVisible(false);
        }
        Bar_Container.getChildren().addAll(barCircles);
    }

    protected void CreateBoardCircle(Pane Board_Container) {
        boardCircles = new Circle[42];
        VBox[] boardColumns = new VBox[7];
        for (int i = 0; i < 7; i++) {
            boardColumns[i] = new VBox();
            boardColumns[i].setId("V" + i);
            boardColumns[i].prefHeight(700.0);
            boardColumns[i].prefWidth(60.0);
            boardColumns[i].setStyle("-fx-padding:5;-fx-spacing:5;");
            AddMouseEvents(boardColumns[i]);
        }
        Board_Container.getChildren().addAll(boardColumns);
        for (int i = 0; i < 42; i++) {
            boardCircles[i] = new Circle();
            CreateCircle(boardCircles[i], Color.valueOf("#b8b8b8"));
            boardColumns[i % 7].getChildren().add(0, boardCircles[i]);
        }
    }

    private void CreateCircle(Circle c, Color color) {
        c.setFill(color);
        c.setRadius(30.0f);
        c.setStroke(Color.BLACK);
        c.setStrokeType(StrokeType.INSIDE);
    }

    private void AddMouseEvents(VBox col) {
        col.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
            VBox v = (VBox) mouseEvent.getSource();
            int index = Character.getNumericValue(v.getId().charAt(1));
            LayoutController.game.EnterColumn(index);
        });
        col.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> {
            VBox v = (VBox) mouseEvent.getSource();
            int index = Character.getNumericValue(v.getId().charAt(1));
            LayoutController.game.LeaveColumn(index);
        });
        col.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            VBox v = (VBox) mouseEvent.getSource();
            int index = Character.getNumericValue(v.getId().charAt(1));
            LayoutController.game.insertBall(index);
        });
    }

    public Circle[] getBarCircles() {
        return barCircles;
    }
    public Circle[] getBoardCircles() {
        return boardCircles;
    }
}
