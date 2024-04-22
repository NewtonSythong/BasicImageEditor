package cosc202.andie;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.Icon;
import java.net.URL;

public class Toolbar {
    protected static ArrayList<Action> actions = new ArrayList<Action>();

    public Toolbar() {
    }

    public static JToolBar setToolBar(JToolBar tBar) {
        ArrayList<Action> fileActions = new FileActions().actions ;
        ArrayList<Action> EditActions = new EditActions().actions ;
        ArrayList<Action> imageActions = new ImageActions().actions ;
        actions.add(0, fileActions.get(0));        
        actions.add(1, fileActions.get(1));
        actions.add(2, EditActions.get(0));
        actions.add(3, EditActions.get(1));
        actions.add(4, imageActions.get(4));
        actions.add(5, imageActions.get(5));
        actions.add(6, fileActions.get(4));
        // Icon openFileIcon = createIcon("open-file-folder-icon.png");
        // JButton openFile = new JButton(openFileIcon);
        JButton openFile = new  JButton("OpenFile");
        openFile.setAction(actions.get(0));
        tBar.add(openFile);
        JButton saveFile = new JButton( "Save File");
        saveFile.setAction(actions.get(1));
        tBar.add(saveFile);
        JButton undoButton = new JButton("undo");
        undoButton.setAction(actions.get(2));
        tBar.add(undoButton);
        JButton redoButton = new JButton("redo");
        redoButton.setAction(actions.get(3));
        tBar.add(redoButton);
        JButton resizeBigger = new JButton("Bigger");
        resizeBigger.setAction(actions.get(4));
        tBar.add(resizeBigger);
        JButton resizeSmaller = new JButton("Smaller");
        resizeSmaller.setAction(actions.get(5));
        tBar.add(resizeSmaller);
        JButton exitFile = new JButton("Exit");
        exitFile.setAction(actions.get(6));
        tBar.add(exitFile);
        // System.out.println("does it get here?");

return tBar;
}
        
// private ImageIcon createIcon(String file){
//     URL image = Toolbar.class.getResource(file);
//     if(image != null){
//         return new ImageIcon(image);
//     }else{
//         System.out.println("this is broken cam");
//     return null;
//     }       
//     }

}

//Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));

