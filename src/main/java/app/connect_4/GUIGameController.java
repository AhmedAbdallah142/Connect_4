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
    private int levelCount;
    private int Speed;
    private int Depth;
    private boolean drawGraph;

    public GUIGameController(Label P1Score, Label P2Score, Accordion graphLayout) {
        userWait = false;
        levelCount = 0;
        this.P1Score = P1Score;
        this.P2Score = P2Score;
        this.graphLayout = graphLayout;
        this.Speed = 4;
        this.Depth = 10;
        this.drawGraph = true;
        layout = LayoutBuilder.getInstance();
        game = Game.getInstance();
    }


    public void insertBall(int colIndex) {
        if ((userWait) | (haveBall(layout.getBoardCircles()[5 * 7 + colIndex]))) {
            System.out.println("Can't insert");
            return;
        }
        userWait = true;
        game.insertBall(colIndex, 2);
        insertBallAction(colIndex, Color.RED);
        // Call Computer Solver Algorithm here // the algorithm will run beside the ball motion
        // Call insertBallAction()
        // this method verify very fast Gui Motion (User Wait Less)
        ComputerTurn();
    }

    private void ComputerTurn() {
        new Thread(() -> {
            try {
                int col = game.ComputerTurn(Depth, Speed,drawGraph);
                levelCount++;
                if (drawGraph)
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

    public void DrawState(int[] state) {
        oldState = game.getGUIState();
        for (int i = 0; i < state.length; i++) {
            layout.getBoardCircles()[i].setFill(nodeColor(state[i]));
        }
    }

    public void DrawState() {
        for (int i = 0; i < oldState.length; i++) {
            layout.getBoardCircles()[i].setFill(nodeColor(oldState[i]));
        }
    }

    private Color nodeColor(int player) {
        switch (player) {
            case 1 :return  Color.YELLOW;
            case 2 :return Color.RED;
            default :return Color.valueOf("#b8b8b8");
        }
    }

    private void addGraphLevel(ScrollPane panel) {
        Platform.runLater(() -> {
            TitledPane pane = new TitledPane("LEVEL " + levelCount, panel);
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


    public void setSpeed(int speed) {
        this.Speed = speed;
    }

    public void setDepth(int depth) {
        this.Depth = depth;
    }

    public void setDrawGraph(boolean drawGraph) {
        this.drawGraph = drawGraph;
    }
}
