package app.connect_4;

import algorithms.GameState;
import algorithms.MinMax;
import algorithms.Node;
import algorithms.Score;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Game {
    private Color turn;
    private boolean userWait;
    private final Circle[] barCircles;
    private final Circle[] boardCircles;
    private MinMax AI;
    private GameState state;

    public Game(Circle[] barCircles, Circle[] boardCircles) {
        userWait = false;
        turn = Color.RED;
        AI = new MinMax();
        state = new GameState();
        this.barCircles = barCircles;
        this.boardCircles = boardCircles;
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
                int p = AI.minMax(state.getState(), 1, 1, new Node(null));
                System.out.println(p);
                state.Play(p, 1);
                Thread.sleep(500);
                insertBallAction(p, turn);
//                Thread.sleep(100);
                userWait = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void insertBallAction(int colIndex, Color ballColor) {
//        System.out.println("hello");
        changeTurn();
//        barCircles[colIndex].setFill(turn);
//        barCircles[colIndex].setVisible(!userWait);
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
//        c.setFill(turn);
//        c.setFill(Color.RED);
//        c.setVisible(!userWait);
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
