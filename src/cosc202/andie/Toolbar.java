package cosc202.andie;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.util.ResourceBundle;

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
        ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");

        // Arraylists which hold all the actions from the actions class arraylists.
        ArrayList<Action> fileActions = new FileActions().actions;
        ArrayList<Action> EditActions = new EditActions().actions;
        ArrayList<Action> imageActions = new ImageActions().actions;
        ArrayList<Action> macroActions = new MacroRecordingActions().actions;

        // Adding all the desired actions to the toolbar actions arraylist
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
        
        // Attempt to create all buttons and add to toolbar
        try {
            Image openFileImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("open-file-folder-icon.jpg"));
            openFileImage = openFileImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon OpenFileIcon = new ImageIcon(openFileImage);

            Image saveFileImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("save-icon.png"));
            saveFileImage = saveFileImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon saveFileIcon = new ImageIcon(saveFileImage);

            Image undoOpImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("undo-arrow-icon.png"));
            undoOpImage = undoOpImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon undoOpIcon = new ImageIcon(undoOpImage);

            Image redoOpImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("redo-arrow-icon.png"));
            redoOpImage = redoOpImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon redoOpIcon = new ImageIcon(redoOpImage);

            Image resizeBiggerImage = ImageIO
                    .read(Toolbar.class.getClassLoader().getResource("resize-bigger-icon.png"));
            resizeBiggerImage = resizeBiggerImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon resizeBiggerIcon = new ImageIcon(resizeBiggerImage);

            Image resizeSmallerImage = ImageIO
                    .read(Toolbar.class.getClassLoader().getResource("resize-smaller-icon.png"));
            resizeSmallerImage = resizeSmallerImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon resizeSmallerIcon = new ImageIcon(resizeSmallerImage);

            Image exitAppImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("exit-icon.png"));
            exitAppImage = exitAppImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon exitAppIcon = new ImageIcon(exitAppImage);

            Image macroRecordImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("macro-record-icon.png"));
            macroRecordImage = macroRecordImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon macroRecordIcon = new ImageIcon(macroRecordImage);

            Image macroStopImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("macro-stop-icon.png"));
            macroStopImage = macroStopImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon macroStopIcon = new ImageIcon(macroStopImage);

            Image macroOpenImage = ImageIO.read(Toolbar.class.getClassLoader().getResource("macro-open-icon.png"));
            macroOpenImage = macroOpenImage.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon macroOpenIcon = new ImageIcon(macroOpenImage);

            JButton openFile = new JButton("");
            openFile.setAction(actions.get(0));
            openFile.setToolTipText(bundle.getString("Open"));

            openFile.setIcon(OpenFileIcon);
            openFile.setText("");
            tBar.add(openFile);

            JButton saveFile = new JButton("");
            saveFile.setAction(actions.get(1));
            saveFile.setToolTipText(bundle.getString("Save"));
            saveFile.setIcon(saveFileIcon);
            saveFile.setText("");
            tBar.add(saveFile);

            JButton undoOp = new JButton("");
            undoOp.setAction(actions.get(2));
            undoOp.setToolTipText(bundle.getString("Undo"));
            undoOp.setIcon(undoOpIcon);
            undoOp.setText("");
            tBar.add(undoOp);

            JButton redoOp = new JButton("");
            redoOp.setAction(actions.get(3));
            redoOp.setToolTipText(bundle.getString("Redo"));
            redoOp.setIcon(redoOpIcon);
            redoOp.setText("");
            tBar.add(redoOp);

            JButton resizeBigger = new JButton("");
            resizeBigger.setAction(actions.get(4));
            resizeBigger.setToolTipText(bundle.getString("ResizeBigger"));
            resizeBigger.setIcon(resizeBiggerIcon);
            resizeBigger.setText("");
            tBar.add(resizeBigger);

            JButton resizeSmaller = new JButton("");
            resizeSmaller.setAction(actions.get(5));
            resizeSmaller.setToolTipText(bundle.getString("ResizeSmaller"));
            resizeSmaller.setIcon(resizeSmallerIcon);
            resizeSmaller.setText("");
            tBar.add(resizeSmaller);

            JButton recordMacro = new JButton("");
            recordMacro.setAction(actions.get(7));
            recordMacro.setToolTipText(bundle.getString("Record"));
            recordMacro.setIcon(macroRecordIcon);
            recordMacro.setText("");
            tBar.add(recordMacro);

            JButton macroStop = new JButton("");
            macroStop.setAction(actions.get(8));
            macroStop.setToolTipText(bundle.getString("Stop"));
            macroStop.setIcon(macroStopIcon);
            macroStop.setText("");
            tBar.add(macroStop);

            JButton macroOpen = new JButton("");
            macroOpen.setAction(actions.get(9));
            macroOpen.setToolTipText(bundle.getString("OpenMacro"));
            macroOpen.setIcon(macroOpenIcon);
            macroOpen.setText("");
            tBar.add(macroOpen);

            JButton exitFile = new JButton("");
            exitFile.setAction(actions.get(6));
            exitFile.setToolTipText(bundle.getString("Exit"));
            exitFile.setIcon(exitAppIcon);
            exitFile.setText("");
            tBar.add(exitFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tBar;
    }
}