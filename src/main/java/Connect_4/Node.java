package Connect_4;

import java.util.LinkedList;
import java.util.List;

public class Node {
	public int minmax;
	public int alpha;
	public int beta;
	public Node parent;
	public List <Node> childs = new LinkedList<>();

	public int position_x;
	public int position_y;

	public Node (Node parent) {
		this.parent = parent;
	}

	public void setValues (int minmax, int alpha, int beta) {
		this.minmax = minmax;
		this.alpha = alpha;
		this.beta = beta;
	}
	
}
