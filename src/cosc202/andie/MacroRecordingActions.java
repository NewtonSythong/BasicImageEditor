package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * This Class encapsulates all macro recording actions.
 * A user can record actions, save those actions to a designated file
 * and then open that macro on any image they have open inside ANDIE.
 * 
 * @author Cam Clark
 * 
 */

public class MacroRecordingActions {

    protected ArrayList<Action> actions;
    ResourceBundle bundle;

    public MacroRecordingActions() {

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        this.bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
        if (this.bundle == null) {
            throw new RuntimeException("Resource bundle not found!");
        }
        actions = new ArrayList<Action>();
        actions.add(
                new MacroRecordAction(bundle.getString("Record"), null, "Start a macro recording",
                        Integer.valueOf(KeyEvent.VK_R)));
        actions.add(
                new MacroStopAction(bundle.getString("Stop"), null, "stop a macro recording",
                        Integer.valueOf(KeyEvent.VK_U)));
        actions.add(
                new MacroOpenAction(bundle.getString("Open"), null, "Open a macro recording onto an image, shortcut",
                        Integer.valueOf(KeyEvent.VK_P)));
    }

    /**
     * Creation of a menu which holds all three macro actions
     * 
     * @return the menu for the macro operations
     */

    public JMenu createMenu() {
        JMenu macroMenu = new JMenu(bundle.getString("Macros"));

        for (Action action : actions) {
            macroMenu.add(new JMenuItem(action));
        }

        return macroMenu;
    }

    /**
     * Class that represents the action of starting a macro recording
     * It extends the ImageAction class.
     */
    public class MacroRecordAction extends ImageAction {
        /**
         * Constructor for the MacroRecordAction class.
         * 
         * @param name     The name of the action
         * @param icon     The icon of the action.
         * @param desc     The description of the action.
         * @param mnemonic The mnemonic of the action.
         */
        MacroRecordAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));
        }

        /**
         * This method is called when the action is triggered.
         * It begins the recording of all actions applied to the image thereafter.
         */
        public void actionPerformed(ActionEvent e) {
            if (!target.getImage().hasImage()) {
                JOptionPane.showMessageDialog(null, "Please open an image before starting a macro recording",
                        "No Image Open",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                target.getImage().isRecording = true;
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Class that represents the action of stopping a macro recording,
     * then prompting the user to save the 'ops' file.
     * It extends the ImageAction class.
     */

    public class MacroStopAction extends ImageAction {
        /**
         * Constructor for the MacroStopAction class.
         * 
         * @param name     The name of the action.
         * @param icon     The icon of the action.
         * @param desc     The description of the action.
         * @param mnemonic The mnemonic of the action.
         */
        MacroStopAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));

        }

        /**
         * Method is called when the action is triggered,
         * It prompts the user a file selecter frame where they are to either save or
         * discard
         * the macro recording
         * 
         */
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (target.getImage().isRecording == false) {
                JOptionPane.showMessageDialog(null, "You have not recorded anything ",
                        "No Recorded actions",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            target.getImage().isRecording = false;
            JFileChooser fileChooser = new JFileChooser();
            int decision = fileChooser.showSaveDialog(target);
            if (decision == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    String macroName = imageFilepath + "-macro.ops";
                    FileOutputStream fileOut = new FileOutputStream(macroName);
                    ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
                    objOut.writeObject(target.getImage().macros);
                    objOut.close();
                    fileOut.close();
                } catch (Exception e) {
                    System.out.println("its broken");
                }
            }

        }

    }

    /**
     * This class represents an action that allows the user to open a '.ops' file
     * onto
     * an image that is already open in ANDIE.
     * It extends the ImageAction class
     */
    public class MacroOpenAction extends ImageAction {

        /**
         * 
         * @param name     The name of the action.
         * @param icon     The icon of the action.
         * @param desc     The description of the action.
         * @param mnemonic The mnemonic of the action.
         * 
         */
        MacroOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, ActionEvent.CTRL_MASK));

        }

        /**
         * Method is called when action is triggered.
         * It opens a JFileChooser then attempts to add the selected file
         * onto the image open in ANDIE.
         */

        public void actionPerformed(ActionEvent e) {
            if(!target.getImage().hasImage()){
                JOptionPane.showMessageDialog(null, "Please open an image before trying to open a 'ops' file",
                "No Image Open",
                JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("ops", "ops");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String opsFileToAdd = fileChooser.getSelectedFile().getCanonicalPath();
                    System.out.println(opsFileToAdd);
                    target.getImage().openMacroFromFile(opsFileToAdd);
                    System.out.println(23421);
                    target.repaint();
                    target.getParent().revalidate();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "failure");
                }

            }

        }
    }
}