package Connect_4;

import algorithms.MinMax;
import app.connect_4.GraphBuilder;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;

public class Game {
    private Color turn;
    private final State state;
    private final MinMax AI;
    private final Score score;
    private final GraphBuilder graphClass;
    Node Graph;

    private static Game instance;
    private Game(){
        turn = Color.RED;
        AI = new MinMax();
        score = new Score();
        state = State.getInstance();
        graphClass = new GraphBuilder();
        Graph = new Node(null);
    }

    public static Game getInstance(){
        if(instance==null)
            instance = new Game();
        return instance;
    }

    public void insertBall(int colIndex,int playerNum){
        state.Play(colIndex, playerNum);
        changeTurn();
    }
    public int ComputerTurn(int depth){
        Graph = new Node(null);
        int p = AI.minMax(state.getState(), 1, depth, true, Graph);
        insertBall(p,1);
        return p;
    }

    public ScrollPane Graph(){
        //// write your code here yousef
       
        return  graphClass.draw_graph(Graph, 15);
    }

    public int[] getScore (){
        return score.calcScore(state.getState());
    }

    public void changeTurn() {
        if (turn.equals(Color.RED))
            turn = Color.YELLOW;
        else
            turn = Color.RED;
    }

}
