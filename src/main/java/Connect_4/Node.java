package Connect_4;

import java.util.LinkedList;
import java.util.List;

public class Node {
	public int minimax;
	public int alpha;
	public int beta;
  public int col;
	public Node parent;
	public List <Node> children = new LinkedList<>();


	public Node (Node parent) {
		this.parent = parent;
	}

    public Node (Node parent, int col) {
		this.parent = parent;
        this.col = col;
	}

	public void setValues (int minimax, int alpha, int beta) {
		this.minimax = minimax;
		this.alpha = alpha;
		this.beta = beta;
	}
	
}
