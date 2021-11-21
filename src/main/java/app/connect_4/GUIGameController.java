package app.connect_4;

import algorithms.GameState;
import algorithms.MinMax;
import algorithms.Node;
import algorithms.Score;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GUIGameController {
    private Color turn;
    private final Score score;
    private boolean userWait;
    private final Circle[] barCircles;
    private final Circle[] boardCircles;
    private final MinMax AI;
    private final GameState state;
    private final Label P1Score;
    private final Label P2Score;

    public GUIGameController(Circle[] barCircles, Circle[] boardCircles, Label P1Score, Label P2Score) {
        userWait = false;
        turn = Color.RED;
        AI = new MinMax();
        state = new GameState();
        score = new Score();
        this.barCircles = barCircles;
        this.boardCircles = boardCircles;
        this.P1Score = P1Score;
        this.P2Score = P2Score;
    }

    public void insertBall(int colIndex) {
        if ((userWait) | (haveBall(boardCircles[5 * 7 + colIndex]))) {
            System.out.println("Can't insert");
            return;
        }
        userWait = true;
        state.Play(colIndex, 2);
        insertBallAction(colIndex, turn);
        // Call Computer Solver Algorithm here // the algorithm will run beside the ball motion
        // Call insertBallAction()
        // this method verify very fast Gui Motion (User Wait Less)
        ComputerTurn();
    }

    private void ComputerTurn() {
        new Thread(() -> {
            try {
                int p = AI.minMax(state.getState(), 1, 10, new Node(null));
                state.Play(p, 1);
                Thread.sleep(500);
                insertBallAction(p, turn);
                userWait = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void setScore() {
        int[] temp = score.calcScore(state.getState());
        System.out.println(temp[0] + " " + temp[1]);
        Platform.runLater(() -> {
            P1Score.setText("" + temp[1]);
            P2Score.setText("" + temp[0]);
        });

    }

    private void insertBallAction(int colIndex, Color ballColor) {
        changeTurn();
        setScore();
        new Thread(() -> {
            try {
                for (int i = 5; i >= 0; i--) {
                    Circle c = boardCircles[i * 7 + colIndex];
                    if (haveBall(c)) {
                        break;
                    }
                    if (i != 5)
                        boardCircles[(i + 1) * 7 + colIndex].setFill(Color.valueOf("#b8b8b8"));
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
        Circle c = barCircles[index];
        c.setVisible(true);
    }

    public void LeaveColumn(int index) {
        Circle c = barCircles[index];
        c.setVisible(false);
    }

    public void changeTurn() {
        if (turn.equals(Color.RED))
            turn = Color.YELLOW;
        else
            turn = Color.RED;
    }
}
