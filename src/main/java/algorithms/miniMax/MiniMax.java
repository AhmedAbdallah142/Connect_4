package algorithms.miniMax;

import java.util.HashMap;
import java.util.Map;

import Connect_4.Node;
import algorithms.Heuristic;

public class MiniMax {
  protected int empty = 0, plySum = 3;
  protected boolean memo = true;
  protected int[] conqureTheMid;
  protected Map<String, Character> visited;
  protected Heuristic heurestic;
  protected long hTime, nodesCount;

  protected MiniMax () {
    this.visited = new HashMap<>();
    this.heurestic = new Heuristic();
  }

  public int get_bestPlay(int[][] state, int player, int k, boolean memoization, Node root) {
    return 0;
  }

  protected boolean isTerminalState(int[][] state) {
    boolean terminal = true;
    for (int i = 0; terminal && i < state[0].length; i++)
      terminal = state[0][i] != empty;
    return terminal;
  }

  protected int[] terminalCase(int[][] state, int alpha, int beta, Node node) {
    long tik = System.nanoTime();
    int[] h = new int[] {heurestic.heuristic_function(state), -1};
    long tok = System.nanoTime();
    hTime += (tok-tik);
    node.setValues(h[0], alpha, beta);
    return h;
  }

  protected int emptyRow(int[][] state, int column) {
    for(int i = state.length - 1; i >= 0; i--) {
      if (state[i][column] == empty)
        return i;
    }
    return -1;
  }

  protected String stateToString(int[][] state, int player) {
    StringBuilder str = new StringBuilder();
    for (int[] row : state)
      for (int col : row)
        str.append(col);
    str.append(player);
    return str.toString();
  }

  protected int[] zigzag(int n) {
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
