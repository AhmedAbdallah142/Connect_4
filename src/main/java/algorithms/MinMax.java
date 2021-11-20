package algorithms;

import java.util.HashMap;
import java.util.Map;

public class MinMax {
  public static void main (String[] args) {
    // MinMax m = new MinMax()
    System.out.println("Hello MinMax");
  }

  private int empty = 0, player1 = 1, player2 = 2;

  public int minMax(int[][] state, int player, int k) {
    int alpha = Integer.MIN_VALUE;
    int beta = Integer.MAX_VALUE;
    HashMap<String, Character> visited = new HashMap<>();

    minMaxRun(state, player, k, alpha, beta, visited);
    return -1;
  }

  private int minMaxRun(int[][] state, int player, int k, int alpha, int beta, Map<String, Character> visited) {
    if (k <= 0) // maximum depth reached
      return 0; // return heuristic
    else if(isTerminalState(state)) // game over (board is full)
      return 1; //return Score

    if (player == player1) { // max
      int best = Integer.MIN_VALUE;

      for (int i = 0; i < state[0].length; i++) {
        if(state[0][i] == empty) {
          int[][] newState = state.clone();
          newState[emptyRow(state, i)][i] = player1; // play in an avaliable place
          
          String stateStr = stateToString(newState);
          if (visited.containsKey(stateStr))
            continue;
          else
            visited.put(stateStr, '0');

          int minMaxVal = minMaxRun(newState, player2, k-1, alpha, beta, visited);
          
          best = Math.max(best, minMaxVal);
          alpha = Math.max(alpha, minMaxVal);
          
          if (alpha >= beta) break;
        }
      }
      return best;
    }
    else if (player == player2) { //min
      int best = Integer.MAX_VALUE;
      
      for (int i = 0; i < state[0].length; i++) {
        if(state[0][i] == empty) {
          int[][] newState = state.clone();
          newState[emptyRow(state, i)][i] = player2; // play in an avaliable place
          
          String stateStr = stateToString(newState);
          if (visited.containsKey(stateStr))
            continue;
          else
            visited.put(stateStr, '0');
          
          int minMaxVal = minMaxRun(newState, player1, k-1, alpha, beta, visited);
          
          best = Math.min(best, minMaxVal);
          beta = Math.min(beta, minMaxVal);
          
          if (alpha >= beta) break;
        }
      }
      return best;
    }
    else
      throw new RuntimeException("Player should have value 1 or 2");
  }

  private boolean isTerminalState(int[][] state) {
    boolean terminal = true;
    for (int i = 0; terminal && i < state[0].length; i++)
      terminal = state[0][i] != empty;
    return terminal;
  }

  private int emptyRow(int[][] state, int column) {
    for(int i = state.length - 1; i >= 0; i--) {
      if (state[i][column] == empty)
        return i;
    }
    return -1;
  }

  private String stateToString(int[][] state) {
    StringBuilder str = new StringBuilder("");
    for (int[] row : state)
      for (int col : row)
        str.append(col);
    return str.toString();
  }

}
