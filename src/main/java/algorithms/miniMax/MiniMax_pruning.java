package algorithms.miniMax;

import Connect_4.Node;

public class MiniMax_pruning extends MiniMax{
  
  public MiniMax_pruning() {
    super();
  }

  @Override
  public int get_bestPlay(int[][] state, int player, int k, boolean memoization, Node root) {
    // initialize variables
    this.memo = memoization;
    int alpha = Integer.MIN_VALUE;
    int beta = Integer.MAX_VALUE;
    conqureTheMid = zigzag(state[0].length);
    visited.clear();
    hTime = 0l;

    long tik = System.nanoTime();
    int bestCol = max(state, player, k, alpha, beta, root)[1]; // best column to play in
    long tok = System.nanoTime();

    System.out.println("Total Minimax Time: " + (tok - tik) / 1000000 + " ms");
    System.out.println("Heuristic Uses: " + (hTime * 100) / (tok - tik) + " % of the time");
    System.out.println("--------------------------------------");

    root.col = bestCol + 1;
    return bestCol;
  }

  private int[] max(int[][] state, int player, int k, int alpha, int beta, Node node) {
    if (k <= 0 || isTerminalState(state)) // maximum depth reached or game over
      return terminalCase(state, alpha, beta, node);
    
    int best = Integer.MIN_VALUE;
    int bestCol = 0; 

    for (int i : conqureTheMid) {
      if(state[0][i] == empty) {
        int empRow = emptyRow(state, i); // find empty row
        state[empRow][i] = player; // play in an avaliable place
        
        if (memo) { // if using memoization check if the state is already visited
          String stateStr = stateToString(state, player);
          
          if (visited.containsKey(stateStr)) {
            state[empRow][i] = empty; // undo
            continue;
          }
          visited.put(stateStr, '0');
        }
        
        // i + 1 -> to make columns start from 1 (this number will be displayed in the graph)
        Node child = new Node(node, i + 1);
        node.childs.add(child);
        int minVal = mini(state, plySum - player, k-1, alpha, beta, child)[0];
        
        if (minVal > best) { // if better value found: update
          best = minVal;
          bestCol = i;
        }

        alpha = Math.max(alpha, minVal);
        state[empRow][i] = empty; // undo
        if (alpha >= beta) break; // alpha beta check
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
        int empRow = emptyRow(state, i); // find empty row
        state[empRow][i] = player; // play in an avaliable place
        
        if (memo) { // if using memoization check if the state is already visited
          String stateStr = stateToString(state, player);
          
          if (visited.containsKey(stateStr)) {
            state[empRow][i] = empty; // undo
            continue;
          }
          visited.put(stateStr, '0');
        }
        
        // i + 1 -> to make columns start from 1 (this number will be displayed in the graph)
        Node child = new Node(node, i + 1);
        node.childs.add(child);
        int maxVal = max(state, plySum - player, k-1, alpha, beta, child)[0];
        
        if (maxVal < best) {
          best = maxVal;
          bestCol = i;
        }
        
        beta = Math.min(beta, maxVal);
        state[empRow][i] = empty; // undo
        if (alpha >= beta) break; // alpha beta check
      }
    }
    node.setValues(best, alpha, beta);
    return new int[] {best, bestCol};
  }
}