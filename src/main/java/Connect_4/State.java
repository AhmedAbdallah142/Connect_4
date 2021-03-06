package Connect_4;

import java.util.Arrays;

public class State {
    private final int[][] game;
    private static State instance;
    private State(){
        game = new int[6][7];
        for (int i = 0; i < 6; i++) {
            Arrays.fill(game[i], 0);
        }
    }

    public State (int[][] initState) {
        this.game = initState;
    }

    public static State getInstance(){
        if(instance==null)
            instance = new State();

        return instance;
    }


    public void Play(int colNum, int player) {
        for (int i = game.length - 1; i >= 0; i--) {
            if (game[i][colNum] == 0) {
                game[i][colNum] = player;
                break;
            }
        }
    }
    public int[][] getState(){
        return game;
    }

    public int[] get1dState(){
        int[] oneDgame = new int[game.length * game[0].length];
        int c = 0;
        for (int i = game.length - 1; i >= 0; i--) {
            for (int j : game[i])
                oneDgame[c++] = j;
        }
        return oneDgame;
    }
}
