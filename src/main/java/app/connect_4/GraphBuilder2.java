package app.connect_4;

import java.util.LinkedList;

import Connect_4.Node;
import Connect_4.State;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GraphBuilder2 {
  private int treeWidth = 3500, treeHeight = 600;
	private int nodeW = 60, nodeH = 80;
	private ScrollPane sp;
	private GUIGameController gameGUI;
	private int[][] state;
  public ScrollPane draw_graph(Node root , GUIGameController gameGUI , int[][] state) {
    Group group = new Group();

		sp = new ScrollPane();
		sp.setContent(group);

		fillTree(group, new TreeNode(root, 1, 0), treeWidth, treeHeight, 7, 2);
    return sp;
  }

  private void fillTree(Group g, TreeNode root, double width, double height, int b, int d) {
		if (d < 0)
			return;

		int depth = 0;
		double stepX = width / 2, stepY = height / (d + 2);
		double y = stepY;
		root.orderX = 1;

		// add background
		g.getChildren().clear();
		Rectangle rect = new Rectangle(0, 0, treeWidth, treeHeight);
		rect.setFill(Color.CORNFLOWERBLUE);
		// Add back button
		Button backBtn = new Button("Back | current depth: " + root.depth);
		backBtn.setLayoutX(width / 2 - 50);
		backBtn.setOnAction(event -> {
			Node curr = root.node;
			int i = 0;
			for (; i < 2 && curr.parent != null; i++)
				curr = curr.parent;

			fillTree(g, new TreeNode(curr, 1, root.depth - i), width, height, b, d);
		});

		// add root node
		g.getChildren().addAll(rect, backBtn, 
										makeSomeGUI(root.node, root.orderX * stepX, stepY, root.depth));

		LinkedList<TreeNode> que = new LinkedList<>();
		que.addLast(new TreeNode(root.node, 1, root.depth));

		while (!que.isEmpty()) {
			TreeNode tNode = que.removeFirst();

			// if depth increased
			if (tNode.depth - root.depth >= depth) {
				stepX = width / (Math.pow(b, ++depth) + 1);
				y += stepY;
			}

			if (depth > d)
				break;

			int pdiv = (int)Math.pow(b, depth - 1.0);
			double pStep = width / (pdiv + 1);
			int skip = b - tNode.node.childs.size();
			int i = (skip / 2 + 1) + pdiv * (tNode.orderX - 1);

			for (Node child : tNode.node.childs) {
				TreeNode chTreeNode = new TreeNode(child, i, tNode.depth + 1);
				que.addLast(chTreeNode);

				Line l = new Line(pStep * tNode.orderX, y - stepY, stepX * i, y);
				StackPane gnode = makeSomeGUI(chTreeNode.node, stepX * i, y, chTreeNode.depth);
				gnode.setOnMouseClicked(event -> fillTree(g, chTreeNode, width, height, b, d));
				
				gnode.setOnMouseEntered(mouseEvent -> {
					System.out.println("entered");
					int[] state = null;
//					gameGUI.DrawState(state);
				});
				gnode.setOnMouseExited(event -> {
					System.out.println("leaved");
//					gameGUI.DrawState();
				});

				g.getChildren().addAll(l, gnode);
				i++;
			}
		}
		sp.setHvalue(0.5);
	}

	private StackPane makeSomeGUI(Node root, double x, double y, int depth) {
		StackPane stackp = new StackPane();
		stackp.setLayoutX(x - nodeW / 2.0);
		stackp.setLayoutY(y - nodeH / 2.0);
		stackp.setViewOrder(-1);

		Rectangle rect = new Rectangle(0, 0, nodeW, nodeH);
		if (depth % 2 == 0)
			rect.setFill(Color.GOLD);
		else
			rect.setFill(Color.INDIANRED);

		String txt = numTostr(root.minmax) + ", " + root.col + "\n-------\n" + 
									numTostr(root.alpha) + "\n" + numTostr(root.beta);
 
		Text text = new Text(txt);
		text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15));

		stackp.getChildren().addAll(rect, text);
		return stackp;
	}

	private String numTostr(int num) {
		if (num == Integer.MAX_VALUE) 
			return "inf";
		if (num == Integer.MIN_VALUE)
			return "-inf";
		else
			return String.valueOf(num);
	}

}
