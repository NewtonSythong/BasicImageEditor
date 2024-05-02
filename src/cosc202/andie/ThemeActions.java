package cosc202.andie;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ThemeActions {
    
    /** A list of actions for the Theme menu. */
    protected ArrayList<Action> actions;
    private ResourceBundle bundle;

    /**Private boolean to know if light mode is on or not */
    private boolean isLightMode = true;
    Color backgroundColor;
    Color textColor;

    public ThemeActions() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        this.bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
        if (this.bundle == null) {
            throw new RuntimeException("Resource bundle not found!");
        }
        actions = new ArrayList<Action>();
        actions.add(new DarkModeAction(bundle.getString("Dark Mode"), null, "Dark Mode", Integer.valueOf(KeyEvent.VK_D)));
        actions.add(new LightModeAction(bundle.getString("Light Mode"), null, "Light Mode", Integer.valueOf(KeyEvent.VK_L)));

    }


    public JMenu createMenu() {
        JMenu themeMenu = new JMenu(bundle.getString("Theme"));
        for (Action action : actions) {
            themeMenu.add(new JMenuItem(action));
        }
        return themeMenu;
    }

    /** */
    public class DarkModeAction extends ImageAction {

        DarkModeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                if (isLightMode) {//If light mode is on
                    //Change to dark mode
                    backgroundColor = Color.DARK_GRAY;
                    textColor = Color.WHITE;
                } else {//If light mode is not on
                    JOptionPane.showMessageDialog(null, "Dark mode is already applied.");
                    return;
                }

                //Changing background colour
                Andie.frame.getContentPane().setBackground(backgroundColor);
                UIManager.put("Panel.background", backgroundColor);

                //Changing menu bar color
                UIManager.put("MenuBar.background", backgroundColor);
                UIManager.put("MenuBar.foreground", textColor);

                //Repaints the UI
                 SwingUtilities.updateComponentTreeUI(Andie.frame);

            } catch (EmptyStackException exception) {
                JOptionPane.showMessageDialog(null, "Error occured");
            }
        }
    }

    /** */
    public class LightModeAction extends ImageAction {

        LightModeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                if (!isLightMode) {//If light mode is not on
                    //Change to dark mode
                    backgroundColor = Color.WHITE;
                    textColor = Color.DARK_GRAY;
                } else {//If light mode is not on
                    JOptionPane.showMessageDialog(null, "Light mode is already applied.");
                    return;
                }

                //Changing background colour
                Andie.frame.getContentPane().setBackground(backgroundColor);//idk if UI manager will work
                UIManager.put("Panel.background", backgroundColor);

                //Changing menu bar color
                UIManager.put("MenuBar.background", backgroundColor);
                UIManager.put("MenuBar.foreground", textColor);

                //Repaints the UI
                SwingUtilities.updateComponentTreeUI(Andie.frame);

            } catch (EmptyStackException exception) {
                JOptionPane.showMessageDialog(null, "Error occured");
            }
        }
    }

}
