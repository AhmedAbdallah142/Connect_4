package app.connect_4;

import Connect_4.Node;

public class TreeNode {
	public int depth, orderX;
	public Node node;
	
	public TreeNode(Node node, int orderX, int depth) {
		this.node = node;
		this.orderX = orderX;
		this.depth = depth;
	}
}