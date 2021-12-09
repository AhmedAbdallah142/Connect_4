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
    conquerTheMid = zigzag(state[0].length);
    visited.clear();
    hTime = 0L;
    nodesCount = 0L;

    long tik = System.nanoTime();
    int bestCol = max(state, player, k, alpha, beta, root)[1]; // best column to play in
    long tok = System.nanoTime();

    System.out.println("Total Minimax Time: " + (tok - tik) / 1000000 + " ms");
    System.out.println("Heuristic Uses: " + (hTime * 100) / (tok - tik) + " % of the time");
    System.out.println("Expanded Nodes: " + nodesCount);
    System.out.println("--------------------------------------");

    if(root != null) root.col = bestCol + 1;
    return bestCol;
  }

  private int[] max(int[][] state, int player, int k, int alpha, int beta, Node node) {
    nodesCount++; // increase number of expanded nodes
    if (k <= 0 || isTerminalState(state)) // maximum depth reached or game over
      return terminalCase(state, alpha, beta, node);
    
    String stateStr = "";
    if (memo) { // if using memoization check if the state is already visited
      stateStr = stateToString(state, player);
      
      Integer val = visited.get(stateStr);
      if (val != null) return new int[] {val, -1};
    }

    int best = Integer.MIN_VALUE;
    int bestCol = 0; 

    for (int i : conquerTheMid) {
      if(state[0][i] == empty) {
        int empRow = emptyRow(state, i); // find empty row
        state[empRow][i] = player; // play in an available place
        
        int minVal;
        if(node != null) {
          // i + 1 -> to make columns start from 1 (this number will be displayed in the graph)
          Node child = new Node(node, i + 1);
          node.children.add(child);
          minVal = mini(state, plySum - player, k-1, alpha, beta, child)[0];
        }
        else
          minVal = mini(state, plySum - player, k-1, alpha, beta, null)[0];

        if (minVal > best) { // if better value found: update
          best = minVal;
          bestCol = i;
        }

        alpha = Math.max(alpha, minVal);
        state[empRow][i] = empty; // undo
        if (alpha >= beta) break; // alpha beta check
      }
    }
    if(node != null) node.setValues(best, alpha, beta);
    if(memo) visited.put(stateStr, best);
    return new int[] {best, bestCol};
  }

  private int[] mini(int[][] state, int player, int k, int alpha, int beta, Node node) {
    nodesCount++; // increase number of expanded nodes
    if (k <= 0 || isTerminalState(state)) // maximum depth reached or game over
      return terminalCase(state, alpha, beta, node);

    String stateStr = "";
    if (memo) { // if using memoization check if the state is already visited
      stateStr = stateToString(state, player);
      
      Integer val = visited.get(stateStr);
      if (val != null) return new int[] {val, -1};
    }

    int best = Integer.MAX_VALUE;
    int bestCol = 0;

    for (int i : conquerTheMid) {
      if(state[0][i] == empty) {
        int empRow = emptyRow(state, i); // find empty row
        state[empRow][i] = player; // play in an available place
        
        int maxVal;
        if(node != null) {
          // i + 1 -> to make columns start from 1 (this number will be displayed in the graph)
          Node child = new Node(node, i + 1);
          node.children.add(child);
          maxVal = max(state, plySum - player, k-1, alpha, beta, child)[0];
        }
        else
          maxVal = max(state, plySum - player, k-1, alpha, beta, null)[0];
        
        if (maxVal < best) {
          best = maxVal;
          bestCol = i;
        }
        
        beta = Math.min(beta, maxVal);
        state[empRow][i] = empty; // undo
        if (alpha >= beta) break; // alpha beta check
      }
    }
    if(node != null) node.setValues(best, alpha, beta);
    if(memo) visited.put(stateStr, best);
    return new int[] {best, bestCol};
  }
}
