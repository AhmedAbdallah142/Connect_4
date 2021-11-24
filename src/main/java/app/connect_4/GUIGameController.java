package app.connect_4;

import Connect_4.Game;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GUIGameController {
    private final LayoutBuilder layout;
    private boolean userWait;
    private final Label P1Score;
    private final Label P2Score;
    private final Accordion graphLayout;
    private final Game game;
    private int count;
    private final CheckBox fastMode;

    public GUIGameController(Label P1Score, Label P2Score,Accordion graphLayout,CheckBox fastMode) {
        userWait = false;
        count = 0;
        this.P1Score = P1Score;
        this.P2Score = P2Score;
        this.graphLayout = graphLayout;
        this.fastMode = fastMode;
        layout = LayoutBuilder.getInstance();
        game = Game.getInstance();
    }


    public void insertBall(int colIndex) {
        if ((userWait) | (haveBall(layout.getBoardCircles()[5 * 7 + colIndex]))) {
            System.out.println("Can't insert");
            return;
        }
        userWait = true;
        game.insertBall(colIndex,2);
        insertBallAction(colIndex,Color.RED);
        // Call Computer Solver Algorithm here // the algorithm will run beside the ball motion
        // Call insertBallAction()
        // this method verify very fast Gui Motion (User Wait Less)
        ComputerTurn();
    }

    private void ComputerTurn() {
        new Thread(() -> {
            try {
                int col = game.ComputerTurn(10,fastMode.isSelected());
                addGraphLevel(game.Graph(this));
                Thread.sleep(500);
                insertBallAction(col, Color.YELLOW);
                userWait = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    int[] oldState;
    public void DrawState(int [] state){
        oldState = game.getGUIState();
        for (int i = 0 ; i < state.length ; i++ ){
            layout.getBoardCircles()[i].setFill(nodeColor(state[i]));
        }
    }

    public void DrawState(){
        for (int i = 0 ; i < oldState.length ; i++ ){
            layout.getBoardCircles()[i].setFill(nodeColor(oldState[i]));
        }
    }

    private Color nodeColor(int player){
        return switch (player) {
            case 1 -> Color.YELLOW;
            case 2 -> Color.RED;
            default -> Color.valueOf("#b8b8b8");
        };
    }

    private void addGraphLevel(ScrollPane panel){
        Platform.runLater(() -> {
            TitledPane pane = new TitledPane("LEVEL " + count++, panel);
            pane.setMinHeight(500);
            graphLayout.getPanes().add(pane);
        });
    }


    private void setScore() {
        int[] temp = game.getScore();
        Platform.runLater(() -> {
            P1Score.setText("" + temp[1]);
            P2Score.setText("" + temp[0]);
        });
    }

    private void insertBallAction(int colIndex, Color ballColor) {
        setScore();
        new Thread(() -> {
            try {
                for (int i = 5; i >= 0; i--) {
                    Circle c = layout.getBoardCircles()[i * 7 + colIndex];
                    if (haveBall(c)) {
                        break;
                    }
                    if (i != 5)
                        layout.getBoardCircles()[(i + 1) * 7 + colIndex].setFill(Color.valueOf("#b8b8b8"));
                    c.setFill(ballColor);
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }

    private boolean haveBall(Circle c) {
        return !c.getFill().equals(Color.valueOf("#b8b8b8"));
    }

    public void EnterColumn(int index) {
        Circle c = layout.getBarCircles()[index];
        c.setVisible(true);
    }

    public void LeaveColumn(int index) {
        Circle c = layout.getBarCircles()[index];
        c.setVisible(false);
    }


}
