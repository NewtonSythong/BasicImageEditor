package cosc202.andie;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.net.URL;

public class Toolbar {
    protected static ArrayList<Action> actions = new ArrayList<Action>();
    //private static Image[] iconImages;

    public Toolbar() {
    }

    public static JToolBar setToolBar(JToolBar tBar) {
        ArrayList<Action> fileActions = new FileActions().actions;
        ArrayList<Action> EditActions = new EditActions().actions;
        ArrayList<Action> imageActions = new ImageActions().actions;

        actions.add(0, fileActions.get(0));
        actions.add(1, fileActions.get(1));
        actions.add(2, EditActions.get(0));
        actions.add(3, EditActions.get(1));
        actions.add(4, imageActions.get(4));
        actions.add(5, imageActions.get(5));
        actions.add(6, fileActions.get(4));
        
        
        Image openFileImage;
        Image saveFileImage;
        Image undoOpImage;
        Image redoOpImage;
        Image resizeBiggerImage;
        Image resizeSmallerImage;
        Image exitAppImage;

        ImageIcon OpenFileIcon = new ImageIcon();
        ImageIcon saveFileIcon = new ImageIcon();  
        ImageIcon undoOpIcon = new ImageIcon();
        ImageIcon redoOpIcon = new ImageIcon();
        ImageIcon resizeBiggerIcon = new ImageIcon();
        ImageIcon resizeSmallerIcon = new ImageIcon();
        ImageIcon exitAppIcon = new ImageIcon();
        
        try {
            openFileImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("open-file-folder-icon.jpg"));
            openFileImage = openFileImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            OpenFileIcon = new ImageIcon(openFileImage);

            saveFileImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("save-icon.png"));
            saveFileImage = saveFileImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            saveFileIcon = new ImageIcon(saveFileImage);

            undoOpImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("undo-arrow-icon.png"));
            undoOpImage = undoOpImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            undoOpIcon = new ImageIcon(undoOpImage);

            redoOpImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("redo-arrow-icon.png"));
            redoOpImage = redoOpImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            redoOpIcon = new ImageIcon(redoOpImage);

            resizeBiggerImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("resize-bigger-icon.png"));
            resizeBiggerImage = resizeBiggerImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            resizeBiggerIcon = new ImageIcon(resizeBiggerImage);

            resizeSmallerImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("resize-smaller-icon.png"));
            resizeSmallerImage = resizeSmallerImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            resizeSmallerIcon = new ImageIcon(resizeSmallerImage);

            exitAppImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("exit-icon.png"));
            exitAppImage = exitAppImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            exitAppIcon = new ImageIcon(exitAppImage);


        } catch (IOException e) {
            e.printStackTrace();
        }


        JButton openFile = new JButton("");
        openFile.setAction(actions.get(0));
        openFile.setIcon(OpenFileIcon);
        openFile.setText("");
        tBar.add(openFile);

        JButton saveFile = new JButton("");
        saveFile.setAction(actions.get(1));
        saveFile.setIcon(saveFileIcon);
        saveFile.setText("");
        tBar.add(saveFile);

        JButton undoOp = new JButton("");
        undoOp.setAction(actions.get(2));
        undoOp.setIcon(undoOpIcon);
        undoOp.setText("");
        tBar.add(undoOp);

        JButton redoOp = new JButton("");
        redoOp.setAction(actions.get(3));
        redoOp.setIcon(redoOpIcon);
        redoOp.setText("");
        tBar.add(redoOp);

        JButton resizeBigger = new JButton("");
        resizeBigger.setAction(actions.get(4));
        resizeBigger.setIcon(resizeBiggerIcon);
        resizeBigger.setText("");
        tBar.add(resizeBigger);

        JButton resizeSmaller = new JButton("");
        resizeSmaller.setAction(actions.get(5));
        resizeSmaller.setIcon(resizeSmallerIcon);
        resizeSmaller.setText("");
        tBar.add(resizeSmaller);

        JButton exitFile = new JButton("");
        exitFile.setAction(actions.get(6));
        exitFile.setIcon(exitAppIcon);
        exitFile.setText("");
        tBar.add(exitFile);
        // System.out.println("does it get here?");

        return tBar;
    }

    // private ImageIcon createIcon(String file){
    // URL image = Toolbar.class.getResource(file);
    // if(image != null){
    // return new ImageIcon(image);
    // }else{
    // System.out.println("this is broken cam");
    // return null;
    // }
    // }

}

// Image image =
// ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
