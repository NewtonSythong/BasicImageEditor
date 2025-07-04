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
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel
 * directly
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations
 * will need to be added.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ColourActions {

    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;
    private ResourceBundle bundle;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        this.bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
        if (this.bundle == null) {
            throw new RuntimeException("Resource bundle not found!");
        }
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction(bundle.getString("Greyscale"), null, "Convert to greyscale",
                Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new InvertColourAction(bundle.getString("Invert"), null, "Invert image colours",
                Integer.valueOf(KeyEvent.VK_N)));
        actions.add(new CycleColourAction(bundle.getString("Cycle"), null, "Cycle between different colour channels",
                Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new BrightnessContrastAction(bundle.getString("BrightnessContrast"), null, "Change the brightness and or contrast of the image",
                Integer.valueOf(KeyEvent.VK_B)));
    }

    /**
     * <p>
     * Create a menu containing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {

        JMenu colourMenu = new JMenu(bundle.getString("Colour"));

        for (Action action : actions) {
            colourMenu.add(new JMenuItem(action));
        }

        return colourMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to converts image colour values
     * </p>
     * 
     * @see InvertColour
     */
    public class InvertColourAction extends ImageAction {

        /**
         * <p>
         * Create a new invert-colour action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        InvertColourAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));
        }

        /**
         * <p>
         * Callback for when the invert-colour action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the InvertColourAction is triggered.
         * It changes the image by inverting the colour values
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new InvertColour());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to cycle between re-ordering of colour channels
     * </p>
     * 
     * @see CycleColour
     */
    public class CycleColourAction extends ImageAction{
        /** 
         * <p>
         * Create a new cycle-colour action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        CycleColourAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));
        }

        /**
         * <p>
         * Callback for when the cycle-colour action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the CycleColourAction is triggered.
         * It changes the image by re-ordering the RGB colour channels
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            String[] configOptions = {"RBG","GRB","GBR","BGR","BRG"};
            JComboBox optionList = new JComboBox(configOptions);
            String configSelected = "RGB"; 

            int option = JOptionPane.showOptionDialog(null, optionList, "Choose a re-ordered RGB colour channel",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
           
           // Check the return value from the dialog box.
           if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                configSelected = (String) optionList.getSelectedItem();
                System.out.println(configSelected);
            }

            target.getImage().apply(new CycleColour(configSelected));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * 
     */
    public class BrightnessContrastAction extends ImageAction{

        /**
         * <p>
         * Create a new invert-colour action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        BrightnessContrastAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));
        }

        /**
         * <p>
         * Callback for when the brightness-contrast action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the BrightnessContrastAction is triggered.
         * It changes the image by inverting the colour values
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            //Determine brightness and contrast percentage change
            int b = 0;
            int c = 0;

            //Show two text boxes
            JTextField brightnessBox = new JTextField("", 15);
            JTextField contrastBox = new JTextField("", 15);

            JPanel panel = new JPanel();
            panel.add(new JLabel("Brightness:"));
            panel.add(brightnessBox);
            panel.add(new JLabel("Contrast:"));
            panel.add(contrastBox);

            int option = JOptionPane.showOptionDialog(null, panel, "Enter brightness and contrast values", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
           
            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                b = Integer.parseInt(brightnessBox.getText());
                c = Integer.parseInt(contrastBox.getText());

                //add something here, like if the box is empty default the input value to zero 
            }

            //Create and apply colour change
            target.getImage().apply(new BrightnessContrastAdjustment(b, c));
            target.repaint();
            target.getParent().revalidate();
        }
    
    }
}
