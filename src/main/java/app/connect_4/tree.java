package app.connect_4;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;

public class tree {

    public BorderPane value ;
    public int t=1;
	public tree parent ;
	public List <tree> childern= new LinkedList<>();
    //public List <Line> lines_of_children= new LinkedList<>();

    public tree(BorderPane t,tree p){
        value=t;
        parent=p;
    }

    public Line add_line(BorderPane s){
        if(parent!=null){
            int index=-1;
            int i=0;
            for(i=0 ;i< parent.childern.size() ;i++){   
                if (parent.childern.get(i).value == s){
                    index=i;
                    break;
                }
            }
            if(index!=-1){
                Line line = new Line(parent.value.getTranslateX()+50, parent.value.getTranslateY()+2*50,value.getTranslateX()+50, value.getTranslateY());
                //lines_of_children.add(line);
                return line; 
            }
           
        }
        return null;
        
    }

}
