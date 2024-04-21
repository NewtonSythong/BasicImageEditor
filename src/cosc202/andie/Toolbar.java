package cosc202.andie;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.Icon;


public class Toolbar {
    protected ArrayList<Action> actions;

    public Toolbar() {
    }

    public void setToolBar(JToolBar tBar) {
        actions = new ArrayList<Action>();
        ArrayList<Action> fileActions = new FileActions().actions ;
        ArrayList<Action> editActions = new EditActions().actions ;
        ArrayList<Action> imageActions = new ImageActions().actions ;
        actions.set(0, fileActions.get(0));        
        actions.set(1, fileActions.get(1));
        actions.set(2, editActions.get(0));
        actions.set(3, editActions.get(1));
        actions.set(4, imageActions.get(4));
        actions.set(5, imageActions.get(5));
        actions.set(6, fileActions.get(4));

        
        JButton openFile = new JButton("andie/icons/openIcon");



        for(int i  = 0; i < actions.size(); i++){
            tBar
            tBar.add(action);
        }
       

        
       
    }
}
