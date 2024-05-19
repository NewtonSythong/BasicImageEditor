package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;


/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 *
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * This includes a mean filter (a simple blur) in the sample code, but more
 * operations will need to be added.
 * </p>
 *
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 *
 * @author Steven Mills
 * @version 1.0
 */

public class FilterActions {

    /** A list of actions for the Filter menu. */

    protected ArrayList<Action> actions;
    ResourceBundle bundle;

    /**
     * 
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     * 
     */

    public FilterActions() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        this.bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
        if (this.bundle == null) {
            throw new RuntimeException("Resource bundle not found!");
        }
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction(bundle.getString("MeanFilter"), null, "Apply a mean filter",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new MedianFilterAction(bundle.getString("MedianFilter"), null, "Apply a median filter",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SoftBlurAction(bundle.getString("SoftBlur"), null, "Apply a soft blur",
                Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new GaussianFilterAction(bundle.getString("GaussianBlur"), null, "Apply a Gaussian filter",
                Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new SharpenFilterAction(bundle.getString("SharpenFilter"), null, "Apply a sharpen filter",
                Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new BlockAveragingAction(bundle.getString("BlockAverage"), null, "Apply a block average",
                Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new ScatterFilterAction(bundle.getString("ScatterFilter"), null, "Apply a Scatter filter",
                Integer.valueOf(KeyEvent.VK_T)));
        actions.add(new EmbossFilterAction(bundle.getString("EmbossFilter"), null, "Apply an Emboss filter",
                Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new SobelFilterAction(bundle.getString("SobelFilter"), null, "Apply a Sobel filter",
                Integer.valueOf(KeyEvent.VK_O)));

    }

    /**
     * 
     * <p>
     * 
     * Create a menu containing the list of Filter actions.
     * 
     * </p>
     *
     * 
     * 
     * @return The filter menu UI element.
     * 
     */

    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(bundle.getString("Filters"));
        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;

    }

    /**
     * 
     * <p>
     * 
     * Action to blur an image with a mean filter.
     * 
     * </p>
     *
     * 
     * 
     * @see MeanFilter
     * 
     */

    public class MeanFilterAction extends ImageAction {

        /**
         * 
         * <p>
         * 
         * Create a new mean-filter action.
         * 
         * </p>
         *
         * 
         * 
         * @param name     The name of the action (ignored if null).
         * 
         * @param icon     An icon to use to represent the action (ignored if null).
         * 
         * @param desc     A brief description of the action (ignored if null).
         * 
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * 
         */

        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic,  ActionEvent.CTRL_MASK));
        }
//Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()
        /**
         * 
         * <p>
         * Callback for when the mean-filter action is triggered.
         * </p>
         *
         * 
         * 
         * <p>
         * 
         * This method is called whenever the MeanFilterAction is triggered.
         * 
         * It prompts the user for a filter radius, then applies an appropriately sized
         * 
         * {@link MeanFilter}.
         * 
         * </p>
         *
         * 
         * 
         * @param e The event triggering this callback.
         * 
         */

        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.

            int radius = 1;

            // Pop-up dialog box to ask for the radius value.

            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);

            JSpinner radiusSpinner = new JSpinner(radiusModel);

            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",

                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter

            target.getImage().apply(new MeanFilter(radius));

            target.repaint();

            target.getParent().revalidate();

        }

    }

    /**
     * 
     * <p>
     * 
     * Action to softly blur an image with a soft filter.
     * 
     * </p>
     *
     * 
     * 
     * @see SoftBlur
     * 
     */
    public class SoftBlurAction extends ImageAction {

        /**
         * 
         * <p>
         * 
         * Create a new soft-blur-filter action.
         * 
         * </p>
         *
         * 
         * 
         * @param name     The name of the action (ignored if null).
         * 
         * @param icon     An icon to use to represent the action (ignored if null).
         * 
         * @param desc     A brief description of the action (ignored if null).
         * 
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * 
         */
        SoftBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));
        }

        public void actionPerformed(ActionEvent e) {

            // Create and apply the filter

            target.getImage().apply(new SoftBlur());

            target.repaint();

            target.getParent().revalidate();

        }

    }

    /**
     * 
     * <p>
     * 
     * Action to sharpen an image with a sharpen filter.
     * 
     * </p>
     *
     * 
     * 
     * @see SharpenFilter
     * 
     */
    public class SharpenFilterAction extends ImageAction {
        /**
         * 
         * <p>
         * 
         * Create a new sharpen-filter action.
         * 
         * </p>
         *
         * 
         * 
         * @param name     The name of the action (ignored if null).
         * 
         * @param icon     An icon to use to represent the action (ignored if null).
         * 
         * @param desc     A brief description of the action (ignored if null).
         * 
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * 
         */
        SharpenFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));
        }

        public void actionPerformed(ActionEvent e) {

            // Create and apply filter

            target.getImage().apply(new SharpenFilter());

            target.repaint();

            target.getParent().revalidate();

        }

    }

    /**
     * 
     * <p>
     * 
     * Action to blur an image with a Gaussian blur filter.
     * 
     * </p>
     *
     * 
     * 
     * @see GaussianBlur
     * 
     */
    public class GaussianFilterAction extends ImageAction {
        /**
         * 
         * <p>
         * 
         * Create a new gaussian-filter action.
         * 
         * </p>
         *
         * 
         * 
         * @param name     The name of the action (ignored if null).
         * 
         * @param icon     An icon to use to represent the action (ignored if null).
         * 
         * @param desc     A brief description of the action (ignored if null).
         * 
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * 
         */
        GaussianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {

            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));
        }

        public void actionPerformed(ActionEvent e) {

            // Create and apply filter

            // Determine the radius - ask the user.

            int radius = 1;

            // Pop-up dialog box to ask for the radius value.

            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            // Check the return value from the dialog box.

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            target.getImage().apply(new GaussianBlur(radius));
            target.repaint();
            target.getParent().revalidate();

        }

    }

    /**
     * 
     * <p>
     * 
     * Action to blur an image with a median filter.
     * 
     * </p>
     *
     * 
     * 
     * @see MedianFilter
     * 
     */
    public class MedianFilterAction extends ImageAction {
        /**
         * 
         * <p>
         * 
         * Create a new median-filter action.
         * 
         * </p>
         *
         * 
         * 
         * @param name     The name of the action (ignored if null).
         * 
         * @param icon     An icon to use to represent the action (ignored if null).
         * 
         * @param desc     A brief description of the action (ignored if null).
         * 
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * 
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {

            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));
        }

        public void actionPerformed(ActionEvent e) {

            // create and apply filter

            // determine radius - ask user.

            int radius = 1;

            // Pop-up dialog box to ask for radius value.

            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }
            target.getImage().apply(new MedianFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * This class represents an action to apply a block averaging filter to an
     * image.
     * It extends ImageAction, which is an abstract class for actions that can be
     * performed on an image.
     */
    public class BlockAveragingAction extends ImageAction {

        /**
         * Constructs a new BlockAveragingAction with the specified name, icon,
         * description, and mnemonic.
         * 
         * @param name     the name of the action.
         * @param icon     the icon for the action.
         * @param desc     the description of the action.
         * @param mnemonic the mnemonic for the action.
         */
        BlockAveragingAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));
        }

        /**
         * Performs the block averaging action.
         * This method is called when the action is triggered, e.g., when the user
         * clicks a button.
         * 
         * @param e the event that triggered the action.
         */
        public void actionPerformed(ActionEvent e) {
            int blockWidth = 1;
            int blockHeight = 1;

            JTextField widthBox = new JTextField("", 15);
            JTextField heightBox = new JTextField("", 15);
            JPanel panel = new JPanel();
            panel.add(new JLabel("Block Height"));
            panel.add((heightBox));
            panel.add(new JLabel("Block Width"));
            panel.add(widthBox);

            int option = JOptionPane.showOptionDialog(null, panel, "Enter block height and width",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.OK_CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                blockHeight = Integer.parseInt(heightBox.getText());
                blockWidth = Integer.parseInt(widthBox.getText());
            }

            target.getImage().apply(new BlockAveraging(blockHeight * blockWidth));
            target.repaint();
            target.getParent().revalidate();

        }

    }

    /**
     * This class represents an action to apply a scatter filter to an image.
     * It extends ImageAction, which is an abstract class for actions that can be
     * performed on an image.
     */
    public class ScatterFilterAction extends ImageAction {

        /**
         * Constructs a new ScatterFilterAction with the specified name, icon,
         * description, and mnemonic.
         * 
         * @param name     the name of the action.
         * @param icon     the icon for the action.
         * @param desc     the description of the action.
         * @param mnemonic the mnemonic for the action.
         */
        ScatterFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));

        }

        /**
         * Performs the scatter filter action.
         * This method is called when the action is triggered, e.g., when the user
         * clicks a button.
         * 
         * @param e the event that triggered the action.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            int radius = 1;

            SpinnerNumberModel chooseRadius = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(chooseRadius);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter radius (1 - 10): ",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = chooseRadius.getNumber().intValue();
            }

            target.getImage().apply(new ScatterFilter(radius));
            target.repaint();
            target.getParent().revalidate();

        }
    }

    public class EmbossFilterAction extends ImageAction {
        /**
         * Create a new emboss filter action.
         * 
         * </p>
         *
         * 
         * 
         * @param name     The name of the action (ignored if null).
         * 
         * @param icon     An icon to use to represent the action (ignored if null).
         * 
         * @param desc     A brief description of the action (ignored if null).
         * 
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * 
         */
        EmbossFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {

            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));

        }

        public void actionPerformed(ActionEvent e) {

            int direction = 1;

            SpinnerNumberModel directionModel = new SpinnerNumberModel(1, 1, 8, 1);
            JSpinner directionSpinner = new JSpinner(directionModel);
            int option = JOptionPane.showOptionDialog(null, directionSpinner, "Enter direction (1 - 8): ",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                direction = directionModel.getNumber().intValue();
            }

            target.getImage().apply(new EmbossFilter(direction));
            target.repaint();
            target.getParent().revalidate();

        }

    }

    public class SobelFilterAction extends ImageAction {
        /**
         * Create a new sobel filter action.
         * 
         * </p>
         *
         * 
         * 
         * @param name     The name of the action (ignored if null).
         * 
         * @param icon     An icon to use to represent the action (ignored if null).
         * 
         * @param desc     A brief description of the action (ignored if null).
         * 
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * 
         */
        SobelFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {

            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));
        }

        public void actionPerformed(ActionEvent e) {

            int direction = 1;

            SpinnerNumberModel directionModel = new SpinnerNumberModel(1, 1, 2, 1);
            JSpinner directionSpinner = new JSpinner(directionModel);
            int option = JOptionPane.showOptionDialog(null, directionSpinner, "Enter direction (1 or 2): ",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                direction = directionModel.getNumber().intValue();
            }

            target.getImage().apply(new EmbossFilter(direction));
            target.repaint();
            target.getParent().revalidate();

        }

    }

}
