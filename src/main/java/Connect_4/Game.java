package Connect_4;

import algorithms.MinMax;
import app.connect_4.GUIGameController;
import app.connect_4.GraphBuilder;
import app.connect_4.GraphBuilder2;
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
    public int[] getGUIState(){
        return state.get1dState();
    }

    public void insertBall(int colIndex,int playerNum){
        state.Play(colIndex, playerNum);
        changeTurn();
    }
    int lastState[][];
    public int ComputerTurn(int depth,boolean fastMode){
        lastState = state.getState();
        Graph = new Node(null);
        int p = AI.minMax(state.getState(), 1, depth, fastMode, Graph);
        insertBall(p,1);
        return p;
    }

    public ScrollPane Graph(GUIGameController gameGUI){
        //// write your code here yousef
        GraphBuilder2 gb2 = new GraphBuilder2();
        return gb2.draw_graph(Graph,gameGUI,AI.clone2D(lastState));
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
