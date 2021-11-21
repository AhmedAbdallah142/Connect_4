package app.connect_4;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import algorithms.Node;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

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

    public static void printInorder(tree s,Node node)
    {
        if (node == null) {
            return;
        }
        for(int i=0 ;i< node.childs.size() ;i++){
           tree child = new tree(create_circle_position(),s);
           s.childern.add(child);
           printInorder(child,node.childs.get(i));
        }
    }

    public static void printInorder(tree s)
    {
        if (s == null) {
            return;
        }
        System.out.println(s.t);
        group_gl.getChildren().add(s.value);
        for(int i=0 ;i< s.childern.size() ;i++){    
            printInorder(s.childern.get(i));
        }
    }
    public void countNode(tree root){
        if(root==null)
            return ;
        V++;
        for(int i=0 ;i< root.childern.size() ;i++){    
            countNode(root.childern.get(i));
        }
    }
    public int getLevelUtil(tree node, BorderPane data, int level)
    {
        if (node == null)
            return 0;
 
        if (node.value == data)
            return level;
        int downlevel=0;
        for(int i=0 ;i< node.childern.size() ;i++){   
            downlevel +=getLevelUtil(node.childern.get(i), data, level + 1);
        }
        return downlevel;
    }
    int getLevel(tree node, BorderPane data)
    {
        return getLevelUtil(node, data, 0);
    }

    private void Bfs(tree goal) {
		HashMap<tree, tree> seen = new HashMap<tree, tree>();
		Queue<tree> frontier = new LinkedList<tree>();
		
		seen.put(goal, null);
		frontier.add(goal);
		
		while (!frontier.isEmpty()) {
			tree state = frontier.remove();
            int level=	getLevel(goal,state.value);
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
			for (tree move : state.childern) {
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
        tree root_tree = new tree(create_circle_position(),null);

        printInorder(root_tree,root);
        printInorder(root_tree);
        Bfs(root_tree);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(group_gl);
        scroll.setContent(stack);
        return scroll;

    }

    /*@Override
    public void start(final Stage stage) throws Exception {
        Node root=new Node(null);
        Node n1=new Node(root);
        Node n2=new Node(root);
        Node n3=new Node(n1);
        Node n4=new Node(n2);
        Node n5=new Node(n3);
        Node n6=new Node(n2);
        Node n7=new Node(n1);

        root.childs.add(n1);
        root.childs.add(n2);
        n1.childs.add(n3);
        n1.childs.add(n7);
        n2.childs.add(n4);
        n2.childs.add(n6);
        n3.childs.add(n5);

        ScrollPane scroll = new ScrollPane();
        scroll=draw_graph(root,10);
        stage.setScene(new Scene(scroll));
        stage.show();

    }*/

    private static BorderPane create_circle_position(){  

        final Circle circle = createCircle();
        final Text   text   = createText();

        Group group = new Group(circle);
            
        StackPane stack = new StackPane();
        stack.getChildren().addAll(group,text);

        BorderPane border = new BorderPane();
        border.setCenter(stack);
        return border;
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
    // public static void main(String[] args) {
    //     launch();
    // }
}