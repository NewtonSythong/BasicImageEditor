package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/**
 * <p>
 * Actions provided by the Image menu.
 * </p>
 * 
 * <p>
 * This class represents a collection of image actions.
 * It contains actions for rotating and flipping an image.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Newton Sythong
 * @version 1.0
 */
public class ImageActions {
    /**
     * A list of actions for manipulating an image.
     * These actions can be added to a View menu.
     */
    protected ArrayList<Action> actions;
    private ResourceBundle bundle;

    /**
     * Constructor for the ImageActions class.
     * It initializes the list of actions and adds the Rotate and Flip actions to
     * it.
     */
    public ImageActions() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        this.bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
        if (this.bundle == null) {
            throw new RuntimeException("Resource bundle not found!");
        }
        actions = new ArrayList<Action>();
        actions.add(new RotateClockwiseAction(bundle.getString("RotateClockwise"), null, "Rotate Clockwise",
                Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new RotateAntiClockwiseAction(bundle.getString("RotateAnti-Clockwise"), null, "Rotate Anti-Clockwise",
                Integer.valueOf(KeyEvent.VK_Q)));
        actions.add(new FlipHorizontalAction(bundle.getString("FlipHorizontal"), null, "Flip Horizontal", Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new FlipVerticalAction(bundle.getString("FlipVertical"), null,"Flip Vertical", Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new ResizeBiggerAction(bundle.getString("ResizeBigger"), null, "Resize the image twice the size", KeyEvent.VK_B));
        actions.add(new ResizeSmallerAction(bundle.getString("ResizeSmaller"), null, "Resize the image half it's size", KeyEvent.VK_L));
    }

    /**
     * This method creates a menu containing the list of image manipulation actions.
     * It can be used to add these actions to a View menu.
     */

    public JMenu createMenu() {
        JMenu viewMenu = new JMenu(bundle.getString("Image"));

        for (Action action : actions) {
            viewMenu.add(new JMenuItem(action));
        }

        return viewMenu;
    }

    /**
     * This class represents an action that rotates an image clockwise.
     * It extends the ImageAction class.
     */
    public class RotateClockwiseAction extends ImageAction {
        /**
         * Constructor for the RotateClockwiseAction class.
         * 
         * @param name     The name of the action.
         * @param icon     The icon of the action.
         * @param desc     The description of the action.
         * @param mnemonic The mnemonic of the action.
         */
        RotateClockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * This method is called when the action is triggered.
         * It applies a clockwise rotation to the target image.
         * 
         * @param e The event that triggered the action.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageRotation(true));
            target.repaint();
            target.getParent().revalidate();

        }

    }

    /**
     * This class represents an action that rotates an image anti-clockwise.
     * It extends the ImageAction class.
     */
    public class RotateAntiClockwiseAction extends ImageAction {
        /**
         * Constructor for the RotateAntiClockwiseAction class.
         * 
         * @param name     The name of the action.
         * @param icon     The icon of the action.
         * @param desc     The description of the action.
         * @param mnemonic The mnemonic of the action.
         */
        RotateAntiClockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * This method is called when the action is triggered.
         * It applies an anti-clockwise rotation to the target image.
         * 
         * @param e The event that triggered the action.
         */
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new ImageRotation(false));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * This class represents an action that flips an image horizontally.
     * It extends the ImageAction class.
     */
    public class FlipHorizontalAction extends ImageAction {
        /**
         * Constructor for the FlipHorizontalAction class.
         * 
         * @param name     The name of the action.
         * @param icon     The icon of the action.
         * @param desc     The description of the action.
         * @param mnemonic The mnemonic of the action.
         */
        FlipHorizontalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * This method is called when the action is triggered.
         * It applies a horizontal flip to the target image.
         * 
         * @param e The event that triggered the action.
         */
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new ImageFlip(true));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * This class represents an action that flips an image vertically.
     * It extends the ImageAction class.
     */
    public class FlipVerticalAction extends ImageAction {
        /**
         * Constructor for the FlipVerticalAction class.
         * 
         * @param name     The name of the action.
         * @param icon     The icon of the action.
         * @param desc     The description of the action.
         * @param mnemonic The mnemonic of the action.
         */
        FlipVerticalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * This method is called when the action is triggered.
         * It applies a vertical flip to the target image.
         * 
         * @param e The event that triggered the action.
         */
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new ImageFlip(false));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * This class represents an action that resizes an image to be 100% bigger.
     * It extends the ImageAction class.
     */
    public class ResizeBiggerAction extends ImageAction {
        ResizeBiggerAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new ImageResize(200)); // 100% bigger means doubling the size (scale factor 2.0)
            target.repaint();
            target.getParent().revalidate();

        }
    }

     /**
     * This class represents an action that resizes an image to be 50% smaller.
     * It extends the ImageAction class.
     */
    public class ResizeSmallerAction extends ImageAction {
        ResizeSmallerAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new ImageResize(50)); // 50% smaller means half of the original size (scale factor 0.5)
            target.repaint();
            target.getParent().revalidate();
    }
    }
}
