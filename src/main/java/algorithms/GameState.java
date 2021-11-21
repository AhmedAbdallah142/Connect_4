package algorithms;

import java.util.Arrays;

public class GameState {
    private int[][] game;

    public GameState() {
        game = new int[6][7];
        for (int i = 0; i < 6; i++) {
            Arrays.fill(game[i], 0);
        }
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

}
