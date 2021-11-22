package app.connect_4;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import algorithms.Node;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class HelloApplication {
    private static  int k_level;
    private static  int max_width;
    private static  int height = 50;
    private static  int V ;
    private static  int[] levet_visited;
    private static  int[] width_level;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final int R = 50;
    private static Group group_gl =new Group();

    // public void countNode(tree root){
    //     if(root==null)
    //         return ;
    //     V++;
    //     for(int i=0 ;i< root.childern.size() ;i++){    
    //         countNode(root.childern.get(i));
    //     }
    // }
    // public int getLevelUtil(Node node, StackPane data, int level)
    // {
    //     if (node == null)
    //         return 0;
 
    //     if (node.value == data)
    //         return level;
    //     int downlevel=0;
    //     for(int i=0 ;i< node.childs.size() ;i++){   
    //         downlevel +=getLevelUtil(node.childs.get(i), data, level + 1);
    //     }
    //     return downlevel;
    // }
    // int getLevel(Node node, StackPane value)
    // {
    //     return getLevelUtil(node, value, 0);
    // }

    private void Bfs(Node goal) {
		HashMap<Node, Node> seen = new HashMap<Node, Node>();
		Queue<Node> frontier = new LinkedList<Node>();
		
		seen.put(goal, null);
		frontier.add(goal);
		
		while (!frontier.isEmpty()) {
			Node state = frontier.remove();
            state.value=create_circle_position();
            group_gl.getChildren().add(state.value);
            int level=	state.level;
            if(levet_visited[level]==0){
                state.value.setTranslateX(0);
                height+=200;
                state.value.setTranslateY(height);
            }
            else{
                state.value.setTranslateX(150*levet_visited[level]);
                state.value.setTranslateY(height); 
            }	
            levet_visited[level]++;
            Line l=state.add_line(state.value);
            if(l!=null){
                group_gl.getChildren().add(l);
            }
			for (Node move : state.childs) {
				if (!seen.containsKey(move)) {
					seen.put(move, state);
					frontier.add(move);
				}
			}
		}  
	}

    public ScrollPane draw_graph(Node root, int j){
        k_level=j;
        max_width=  (int) Math.pow(7, k_level-1)*70;
        levet_visited=new int[k_level+1];
        width_level=new int[k_level];
        for(int i=0 ; i<k_level;i++){
            levet_visited[i]=0;
        }
        for(int i=0 ; i<k_level;i++){
            width_level[i]=(int) (max_width/(Math.pow(7, i)));
        }


        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(700, 700);
        Bfs(root);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(group_gl);
        scroll.setContent(stack);
        return scroll;

    }
    private static StackPane create_circle_position(){  

        final Circle circle = createCircle();
        final Text   text   = createText();

        StackPane stack = new StackPane();
        stack.getChildren().addAll(circle,text);

        return stack;
    }

    private static Circle createCircle() {
        final Circle circle = new Circle(R);

        circle.setStroke(Color.FORESTGREEN);
        circle.setStrokeWidth(2);
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setFill(Color.AZURE);
        circle.relocate(0, 0);

        return circle;
    }

    private static Text createText() {
        final Text text = new Text("A");
        text.setFont(new Font(30));
        text.setBoundsType(TextBoundsType.VISUAL);

        return text;
    }
}