package cosc202.andie;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * This class represents a toolbar for an application.
 * It manages a list of actions that can be performed from the toolbar.
 * 
 * @author Cam Clark
 */
public class Toolbar {
    /**
     * A list of actions that can be performed from the toolbar.
     */
    protected static ArrayList<Action> actions = new ArrayList<Action>();

    /**
     * Constructs a new Toolbar object.
     */
    public Toolbar() {
    }

    /**
     * Sets up the toolbar with the specified actions.
     * 
     * @param tBar the toolbar to set up.
     * @return the set up toolbar.
     */
    public static JToolBar setToolBar(JToolBar tBar) {
        ArrayList<Action> fileActions = new FileActions().actions;
        ArrayList<Action> EditActions = new EditActions().actions;
        ArrayList<Action> imageActions = new ImageActions().actions;
        ArrayList<Action> macroActions = new MacroRecordingActions().actions;

        actions.add(0, fileActions.get(0));
        actions.add(1, fileActions.get(1));
        actions.add(2, EditActions.get(0));
        actions.add(3, EditActions.get(1));
        actions.add(4, imageActions.get(4));
        actions.add(5, imageActions.get(5));
        actions.add(6, fileActions.get(4));
        actions.add(7, macroActions.get(0));
        actions.add(8, macroActions.get(1));
        actions.add(9, macroActions.get(2));

        Image openFileImage;
        Image saveFileImage;
        Image undoOpImage;
        Image redoOpImage;
        Image resizeBiggerImage;
        Image resizeSmallerImage;
        Image exitAppImage;
        Image macroRecordImage;
        Image macroStopImage;
        Image macroOpenImage;
    
        ImageIcon OpenFileIcon = new ImageIcon();
        ImageIcon saveFileIcon = new ImageIcon();
        ImageIcon undoOpIcon = new ImageIcon();
        ImageIcon redoOpIcon = new ImageIcon();
        ImageIcon resizeBiggerIcon = new ImageIcon();
        ImageIcon resizeSmallerIcon = new ImageIcon();
        ImageIcon exitAppIcon = new ImageIcon();
        ImageIcon macroRecordIcon = new ImageIcon();
        ImageIcon macroStopIcon = new ImageIcon();
        ImageIcon macroOpenIcon = new ImageIcon();

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

            macroRecordImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("macro-record-icon.png"));
            macroRecordImage = macroRecordImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            macroRecordIcon = new ImageIcon(macroRecordImage);

            macroStopImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("macro-stop-icon.png"));
            macroStopImage = macroStopImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            macroStopIcon = new ImageIcon(macroStopImage);

            macroOpenImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("macro-open-icon.png"));
            macroOpenImage = macroOpenImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            macroOpenIcon = new ImageIcon(macroOpenImage);


        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton openFile = new JButton("");
        openFile.setAction(actions.get(0));
        openFile.setToolTipText(" " + actions.get(0).getValue("AcceleratorKey"));

        openFile.setIcon(OpenFileIcon);
        openFile.setText("");
        tBar.add(openFile);

        JButton saveFile = new JButton("");
        saveFile.setAction(actions.get(1));
        saveFile.setToolTipText(" " + actions.get(1).getValue("AcceleratorKey"));
        saveFile.setIcon(saveFileIcon);
        saveFile.setText("");
        tBar.add(saveFile);

        JButton undoOp = new JButton("");
        undoOp.setAction(actions.get(2));
        saveFile.setToolTipText(" " + actions.get(2).getValue("AcceleratorKey"));
        undoOp.setIcon(undoOpIcon);
        undoOp.setText("");
        tBar.add(undoOp);

        JButton redoOp = new JButton("");
        redoOp.setAction(actions.get(3));
        redoOp.setToolTipText(" " + actions.get(3).getValue("AcceleratorKey"));
        redoOp.setIcon(redoOpIcon);
        redoOp.setText("");
        tBar.add(redoOp);

        JButton resizeBigger = new JButton("");
        resizeBigger.setAction(actions.get(4));
        resizeBigger.setToolTipText(" " + actions.get(4).getValue("AcceleratorKey"));
        resizeBigger.setIcon(resizeBiggerIcon);
        resizeBigger.setText("");
        tBar.add(resizeBigger);

        JButton resizeSmaller = new JButton("");
        resizeSmaller.setAction(actions.get(5));
        resizeSmaller.setToolTipText(" " + actions.get(5).getValue("AcceleratorKey"));
        resizeSmaller.setIcon(resizeSmallerIcon);
        resizeSmaller.setText("");
        tBar.add(resizeSmaller);

        JButton exitFile = new JButton("");
        exitFile.setAction(actions.get(6));
        exitFile.setToolTipText(" " + actions.get(6).getValue("AcceleratorKey"));
        exitFile.setIcon(exitAppIcon);
        exitFile.setText("");
        tBar.add(exitFile);

        JButton recordMacro = new JButton("");
        recordMacro.setAction(actions.get(7));
        recordMacro.setToolTipText(" " + actions.get(7).getValue("AcceleratorKey"));
        recordMacro.setIcon(macroRecordIcon);
        recordMacro.setText("");
        tBar.add(recordMacro);

        JButton macroStop = new JButton("");
        macroStop.setAction(actions.get(8));
        macroStop.setToolTipText(" " + actions.get(8).getValue("AcceleratorKey"));
        macroStop.setIcon(macroStopIcon);
        macroStop.setText("");
        tBar.add(macroStop);

        JButton macroOpen = new JButton("");
        macroOpen.setAction(actions.get(9));
        macroOpen.setToolTipText(" " + actions.get(8).getValue("AcceleratorKey"));
        macroOpen.setIcon(macroOpenIcon);
        macroOpen.setText("");
        tBar.add(macroOpen);

        return tBar;
    }
}