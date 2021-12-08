package Connect_4;

import algorithms.miniMax.MiniMax;
import algorithms.miniMax.MiniMax_naive;
import algorithms.miniMax.MiniMax_pruning;
import app.connect_4.GUIGameController;
// import app.connect_4.GraphBuilder;
import app.connect_4.GraphBuilder2;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;

public class Game {
    private Color turn;
    private final State state;
    private final Score score;
    MiniMax pruningInstance;
    MiniMax naiveInstance;
    Node Graph;

    private static Game instance;

    private Game() {
        turn = Color.RED;
        score = new Score();
        state = State.getInstance();
        pruningInstance = new MiniMax_pruning();
        naiveInstance = new MiniMax_naive();
    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    public int[] getGUIState() {
        return state.get1dState();
    }

    public void insertBall(int colIndex, int playerNum) {
        state.Play(colIndex, playerNum);
        changeTurn();
    }

    int[][] lastState;

    public int ComputerTurn(int depth, int Speed,boolean drawGraph) {
        lastState = clone2D(state.getState());
        if (drawGraph)
            Graph = new Node(null);
        else
            Graph = null;
        int p = bestPlay(Speed, depth);
        insertBall(p, 1);
        return p;
    }

    private int[][] clone2D(int[][] arr) {
        int[][] copy = new int[arr.length][];
        for(int i = 0; i < arr.length; i++)
            copy[i] = arr[i].clone();
        return copy;
    }

    private int bestPlay(int speed, int depth) {
        MiniMax m = ChooseAlg(speed);
        return switch (speed % 2) {
            case 0 -> m.get_bestPlay(state.getState(), 1, depth, true, Graph);
            case 1 -> m.get_bestPlay(state.getState(), 1, depth, false, Graph);
            default -> -1;
        };
    }

    private MiniMax ChooseAlg(int speed) {
        if (speed > 2)
            return pruningInstance;
        else
            return naiveInstance;
    }

    public ScrollPane Graph(GUIGameController gameGUI) {
        //// write your code here yousef
        GraphBuilder2 gb2 = new GraphBuilder2();
        return gb2.draw_graph(Graph, gameGUI, lastState);
    }

    public int[] getScore() {
        return score.calcScore(state.getState());
    }

    public void changeTurn() {
        if (turn.equals(Color.RED))
            turn = Color.YELLOW;
        else
            turn = Color.RED;
    }

}
