package algorithms;

import java.util.HashMap;
import java.util.Map;

public class MinMax {
  public static void main (String[] args) {
    // MinMax m = new MinMax()
    // 0 1 2 3 4 5 6
    int[] zz = zigzag(5);
    for (int n : zz)
      System.out.println(n);
  }

  private int empty = 0, plySum = 3;
  private int[] conqureTheMid;
  private Map<String, Character> visited = new HashMap<>();
  private Heurestic heurestic = new Heurestic();
  private Score score = new Score();

  public int minMax(int[][] state,int k,int player, Node root) {
    int alpha = Integer.MIN_VALUE;
    int beta = Integer.MAX_VALUE;
    conqureTheMid = zigzag(state.length);
    visited.clear();

    max(state, player, k, alpha, beta, root);
    return -1;
  }

  private int max(int[][] state, int player, int k, int alpha, int beta, Node node) {
    if (k <= 0) // maximum depth reached
      return heurestic.heurestic_function(state);
    else if(isTerminalState(state)) // game over (board is full)
      score.calcScore(state);

    int best = Integer.MIN_VALUE;

    for (int i : conqureTheMid) {
      if(state[0][i] == empty) {
        int[][] newState = state.clone();
        newState[emptyRow(state, i)][i] = player; // play in an avaliable place
        
        String stateStr = stateToString(newState);
        if (visited.containsKey(stateStr))
          continue;
        
        visited.put(stateStr, '0');
        Node child = new Node(node);
        node.childs.add(child);
        int minMaxVal = mini(newState, plySum - player, k-1, alpha, beta, child);
        
        best = Math.max(best, minMaxVal);
        alpha = Math.max(alpha, minMaxVal);
        
        if (alpha >= beta) break;
      }
    }
    node.setValues(best, alpha, beta);
    return best;
  }

  private int mini(int[][] state, int player, int k, int alpha, int beta, Node node) {
    if (k <= 0) // maximum depth reached
      return heurestic.heurestic_function(state);
    else if(isTerminalState(state)) // game over (board is full)
      score.calcScore(state);

    int best = Integer.MAX_VALUE;

    for (int i : conqureTheMid) {
      if(state[0][i] == empty) {
        int[][] newState = state.clone();
        newState[emptyRow(state, i)][i] = player; // play in an avaliable place
        
        String stateStr = stateToString(newState);
        if (visited.containsKey(stateStr))
          continue;
        
        visited.put(stateStr, '0');
        Node child = new Node(node);
        node.childs.add(child);
        int minMaxVal = mini(newState, plySum - player, k-1, alpha, beta, child);
        
        best = Math.min(best, minMaxVal);
        alpha = Math.min(alpha, minMaxVal);
        
        if (alpha >= beta) break;
      }
    }
    node.setValues(best, alpha, beta);
    return best;
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

  private static int[] zigzag(int n) {
    if(n <= 0) return new int[0];

    int[] z = new int[n];
    z[0] = n/2;

    for (int i = 1; i < n; i++) {
      if( i % 2 == 0)
        z[i] = z[i-1] + i;
      else
        z[i] = z[i-1] - i;
    }

    return z;
  }
}
