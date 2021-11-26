package algorithms;

import Connect_4.Node;

import java.util.HashMap;
import java.util.Map;

public class MinMax {

  private int empty = 0, plySum = 3;
  private boolean pruning = true;
  private int[] conqureTheMid;
  private Map<String, Character> visited = new HashMap<>();
  private Heuristic heurestic = new Heuristic();
  private long hTime;
  
  public int minMax(int[][] state, int player, int k, boolean pruning, Node root) {
    this.pruning = pruning;
    int alpha = Integer.MIN_VALUE;
    int beta = Integer.MAX_VALUE;
    conqureTheMid = zigzag(state[0].length);
    visited.clear();
    hTime = 0l;

    long tik = System.nanoTime();
    int col = max(state, player, k, alpha, beta, root)[1];
    long tok = System.nanoTime();

    System.out.println("Total Minimax Time: " + (tok - tik) / 1000000 + " ms");
    System.out.println("Heuristic Time: " + hTime / 1000000 + " ms");
    System.out.println("Heuristic Uses: " + (hTime * 100) / (tok - tik) + " %");
    System.out.println("--------------------------------------");

    root.col = col + 1;
    return col;
  }

  private int[] max(int[][] state, int player, int k, int alpha, int beta, Node node) {
    if (k <= 0 || isTerminalState(state)) // maximum depth reached or game over
      return terminalCase(state, alpha, beta, node);

    int best = Integer.MIN_VALUE;
    int bestCol = 0; 

    for (int i : conqureTheMid) {
      if(state[0][i] == empty) {
        int empRow = emptyRow(state, i);
        state[empRow][i] = player; // play in an avaliable place
        
        String stateStr = stateToString(state, player);
        
        if (visited.containsKey(stateStr)) {
          state[empRow][i] = empty; // undo
          continue;
        }
        
        visited.put(stateStr, '0');
        Node child = new Node(node, i + 1);
        node.childs.add(child);
        int minVal = mini(state, plySum - player, k-1, alpha, beta, child)[0];
        
        if (minVal > best) {
          best = minVal;
          bestCol = i;
        }
        alpha = Math.max(alpha, minVal);

        state[empRow][i] = empty; // undo
        if (pruning && alpha >= beta) break;
      }
    }
    node.setValues(best, alpha, beta);
    return new int[] {best, bestCol};
  }

  private int[] mini(int[][] state, int player, int k, int alpha, int beta, Node node) {
    if (k <= 0 || isTerminalState(state)) // maximum depth reached or game over
      return terminalCase(state, alpha, beta, node);

    int best = Integer.MAX_VALUE;
    int bestCol = 0;

    for (int i : conqureTheMid) {
      if(state[0][i] == empty) {
        int empRow = emptyRow(state, i);
        state[empRow][i] = player; // play in an avaliable place
        
        String stateStr = stateToString(state, player);

        if (visited.containsKey(stateStr)) {
          state[empRow][i] = empty; // undo
          continue;
        }
        
        visited.put(stateStr, '0');
        Node child = new Node(node, i + 1);
        node.childs.add(child);
        int maxVal = max(state, plySum - player, k-1, alpha, beta, child)[0];
        
        if (maxVal < best) {
          best = maxVal;
          bestCol = i;
        }
        beta = Math.min(beta, maxVal);
        
        state[empRow][i] = empty; // undo
        if (pruning && alpha >= beta) break;
      }
    }
    node.setValues(best, alpha, beta);
    return new int[] {best, bestCol};
  }

  private boolean isTerminalState(int[][] state) {
    boolean terminal = true;
    for (int i = 0; terminal && i < state[0].length; i++)
      terminal = state[0][i] != empty;
    return terminal;
  }

  private int[] terminalCase(int[][] state, int alpha, int beta, Node node) {
    long tik = System.nanoTime();
    int[] h = new int[] {heurestic.heuristic_function(state), -1};
    long tok = System.nanoTime();
    hTime += (tok-tik);
    node.setValues(h[0], alpha, beta);
    return h;
  }

  private int emptyRow(int[][] state, int column) {
    for(int i = state.length - 1; i >= 0; i--) {
      if (state[i][column] == empty)
        return i;
    }
    return -1;
  }

  private String stateToString(int[][] state, int player) {
    StringBuilder str = new StringBuilder();
    for (int[] row : state)
      for (int col : row)
        str.append(col);
    str.append(player);
    return str.toString();
  }

  private int[] zigzag(int n) {
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

  public int[][] clone2D(int[][] arr) {
    int[][] copy = new int[arr.length][];
    for(int i = 0; i < arr.length; i++)
      copy[i] = arr[i].clone();
    return copy;
  }

  // private boolean deb(int[][] state) {
  //   boolean check = state[5][3] == 2 && state[4][3] == 1 && state[5][6] == 1 && state[4][6] == 2;
  //   if (!check)
  //     return check;
    
  //     for (int i=0; i < state.length; i++) {
  //       for(int j=0; j < state[0].length; j++) {
  //         if((i == 5 && j==3) || (i == 4 && j == 3) || (i == 5 && j == 6) || (i == 4 && j == 6))
  //           continue;
  //         if(state[i][j] != 0) return false;
  //       }
  //   }
  //   return true;
  // }
}
