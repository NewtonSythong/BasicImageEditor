package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * <p>
 * Actions provided by the View menu.
 * this is a non Destructive change 
 * </p>
 * 
 * <p>
 * The View menu contains actions that affect how the image is displayed in the application.
 * These actions do not affect the contents of the image itself, just the way it is displayed.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ImageActions {
    
    /**
     * A list of actions for the View menu.
     */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of View menu actions.
     * </p>
     */
    public ImageActions() {
        actions = new ArrayList<Action>();
        actions.add(new RotateClockwiseAction("Rotate Clockwise", null, "Rotate Clockwise", Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new RotateAntiClockwiseAction("Rotate Anti-Clockwise", null, "Rotate Anti-Clockwise", Integer.valueOf(KeyEvent.VK_L)));
    }

    /**
     * <p>
     * Create a menu containing the list of View actions.
     * </p>
     * 
     * @return The view menu UI element.
     */
    public JMenu createMenu() {
        JMenu viewMenu = new JMenu("Image");

        for (Action action: actions) {
            viewMenu.add(new JMenuItem(action));
        }

        return viewMenu;
    }


    public class RotateClockwiseAction extends ImageAction{
        RotateClockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageRotation(true));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class RotateAntiClockwiseAction extends ImageAction{
        RotateAntiClockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageRotation(false));
            target.repaint();
            target.getParent().revalidate();
        }
    }
}
