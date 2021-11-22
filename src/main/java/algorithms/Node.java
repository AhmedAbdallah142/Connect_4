package algorithms;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

public class Node {
	public int minmax;
	public int alpha;
	public int beta;
    public int level=0;
	public Node parent;
	public List <Node> childs = new LinkedList<>();
	public StackPane value ;


	public Node (Node parent) {
		this.parent = parent;
        if(parent==null){
            level=0;
        }
        else{
          level=parent.level+1;  
        } 
	}

	public void setValues (int minmax, int alpha, int beta) {
		this.minmax = minmax;
		this.alpha = alpha;
		this.beta = beta;
	}
    public String getValues () {
        return "minmax="+minmax+"\nalpha="+alpha+"\nbeta="+beta;
	}

	public Line add_line(StackPane s){
        if(parent!=null){
            int index=-1;
            int i=0;
            for(i=0 ;i< parent.childs.size() ;i++){   
                if (parent.childs.get(i).value == s){
                    index=i;
                    break;
                }
            }
            if(index!=-1){
                Line line = new Line(parent.value.getTranslateX()+50, parent.value.getTranslateY()+2*50,value.getTranslateX()+50, value.getTranslateY());
                return line; 
            }
           
        }
        return null;
        
    }
	
}
