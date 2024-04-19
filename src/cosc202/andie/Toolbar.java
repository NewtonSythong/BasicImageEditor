package cosc202.andie;

import javax.swing.Action;
import javax.swing.JToolBar;
import java.util.ArrayList;
import cosc202.andie.FileActions.*;

public class Toolbar {
    protected JToolBar tb;
    protected ArrayList<Action> actions;

    public Toolbar() {
    }

    public JToolBar setToolBar() {
        actions = new ArrayList<Action>();
        ArrayList<Action> fileActions = new FileActions().actions ;
        ArrayList<Action> editActions = new EditActions().actions ;
        ArrayList<Action> imageActions = new ImageActions().actions ;
        actions.set(0, fileActions.get(0));
        actions.set(1, fileActions.get(1));
        


        
     

        return tb;

        
       
    }
}
