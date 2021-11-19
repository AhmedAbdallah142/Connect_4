package app.connect_4;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Game {
    private Color turn;
    private final boolean wait;
    private final Circle[] barCircles;
    private final Circle[] boardCircles;

    public Game(Circle[] barCircles, Circle[] boardCircles) {
        wait = false;
        turn = Color.RED;
        this.barCircles = barCircles;
        this.boardCircles = boardCircles;
    }

    public void insertBall(int colIndex) {
        if ((wait)|(haveBall(boardCircles[5 * 7 + colIndex]))) {
            System.out.println("Can't insert");
            return;
        }
        Color tempTurn = turn;
//        wait = true;
        changeTurn();
        barCircles[colIndex].setFill(turn);
        new Thread(() -> {
            try {
                for (int i = 5; i >= 0; i--) {
                    Circle c = boardCircles[i * 7 + colIndex];
                    if (haveBall(c)) {
                        break;
                    }
                    if (i != 5)
                        boardCircles[(i + 1) * 7 + colIndex].setFill(Color.valueOf("#b8b8b8"));
                    c.setFill(tempTurn);
                    Thread.sleep(100);
                }
                //Check For Completion // Call Algorithm to get Column number insert Computer turn then make
//                wait = false;
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
        c.setFill(turn);
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
