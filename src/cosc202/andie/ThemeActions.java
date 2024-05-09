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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * <p>
 * Actions to apply different UI themes as users choice
 * </p>
 * 
 * <p>
 * The Theme menu allows user to select what theme they want their ANDIE UI to be
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Katrina Hogg
 * @version 1.0
 */
public class ThemeActions {
    
    /** A list of actions for the Theme menu. */
    protected ArrayList<Action> actions;
    private ResourceBundle bundle;

    private String defaultLookFeel = UIManager.getLookAndFeel().getClass().getName();//Save default UI theme
    private String currentTheme = defaultLookFeel;//Keep track of what the current UI is

    /**
     * <p>
     * Create a set of Theme menu actions.
     * </p>
     */
    public ThemeActions() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);        
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));

        //Preferences prefers = Preferences.userNodeForPackage(ThemeActions.class);
        //currentTheme = prefers.get(PREFS_KEY_LOOK_AND_FEEL, defaultLookFeel);

        this.bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
        if (this.bundle == null) {
            throw new RuntimeException("Resource bundle not found!");
        }

        actions = new ArrayList<Action>();
        actions.add(new DefaultAction(bundle.getString("DefaultTheme"), null, "Default Theme", Integer.valueOf(KeyEvent.VK_D)));
        actions.add(new MetalAction(bundle.getString("MetalTheme"), null, "Metal Theme", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new NimbusAction(bundle.getString("NimbusTheme"), null, "Nimbus Theme", Integer.valueOf(KeyEvent.VK_N)));
        actions.add(new MotifAction(bundle.getString("MotifTheme"), null, "Motif Theme", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new WindowsAction(bundle.getString("WindowsTheme"), null, "Windows Theme", Integer.valueOf(KeyEvent.VK_W)));
        actions.add(new WindowsClassicAction(bundle.getString("WindowsClassicTheme"), null, "Windows Classic Theme", Integer.valueOf(KeyEvent.VK_D)));
    }

    /**
     * <p>
     * Create a menu containing the list of Theme actions.
     * </p>
     * 
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu themeMenu = new JMenu(bundle.getString("Theme"));
        for (Action action : actions) {
            themeMenu.add(new JMenuItem(action));
        }
        return themeMenu;
    }

    /**
     * <p>
     * Action to change UI theme to Metal theme
     * </p>
     * 
     * @see 
     */
    public class MetalAction extends ImageAction {

        /**
         * <p>
         * Create a new action to change UI to Metal theme action
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MetalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
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
            try {
                
                if(currentTheme == ("javax.swing.plaf.metal.MetalLookAndFeel")){
                    JOptionPane.showMessageDialog(null, "This theme is already applied");
                }else{
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(Andie.frame); //Repaints the UI
                    currentTheme = ("javax.swing.plaf.metal.MetalLookAndFeel");//Updates current theme
                }

            } catch (UnsupportedLookAndFeelException exception) {
                JOptionPane.showMessageDialog(null, "UnsupportedLookAndFeelException occurred");
            }catch (ClassNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "ClassNotFoundException occurred");
            }catch (InstantiationException exception) {
                JOptionPane.showMessageDialog(null, "InstantiationException occurred");
            }catch (IllegalAccessException exception) {
                JOptionPane.showMessageDialog(null, "IllegalAccessException occurred");
            }
        }
    }

    /**
     * <p>
     * Action to change UI theme to Nimbus theme
     * </p>
     * 
     * @see 
     */
    public class NimbusAction extends ImageAction {
        /**
         * <p>
         * Create a new action to change UI to Metal theme action
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        NimbusAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
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
            try {
                if(currentTheme == ("javax.swing.plaf.nimbus.NimbusLookAndFeel")){
                    JOptionPane.showMessageDialog(null, "This theme is already applied");
                }else{
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(Andie.frame); //Repaints the UI
                    currentTheme = ("javax.swing.plaf.nimbus.NimbusLookAndFeel");//Updates current theme
                }

            } catch (UnsupportedLookAndFeelException exception) {
                JOptionPane.showMessageDialog(null, "UnsupportedLookAndFeelException occurred");
            }catch (ClassNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "ClassNotFoundException occurred");
            }catch (InstantiationException exception) {
                JOptionPane.showMessageDialog(null, "InstantiationException occurred");
            }catch (IllegalAccessException exception) {
                JOptionPane.showMessageDialog(null, "IllegalAccessException occurred");
            }
        }
    }

    /**
     * <p>
     * Action to change UI theme to Motif theme
     * </p>
     * 
     * @see 
     */
    public class MotifAction extends ImageAction {

        /**
         * <p>
         * Create a new action to change UI to Metal theme action
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MotifAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
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
            try {
                if(currentTheme == ("com.sun.java.swing.plaf.motif.MotifLookAndFeel")){
                    JOptionPane.showMessageDialog(null, "This theme is already applied");
                }else{
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(Andie.frame); //Repaints the UI
                    currentTheme = ("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Updates current theme
                }

            } catch (UnsupportedLookAndFeelException exception) {
                JOptionPane.showMessageDialog(null, "UnsupportedLookAndFeelException occurred");
            }catch (ClassNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "ClassNotFoundException occurred");
            }catch (InstantiationException exception) {
                JOptionPane.showMessageDialog(null, "InstantiationException occurred");
            }catch (IllegalAccessException exception) {
                JOptionPane.showMessageDialog(null, "IllegalAccessException occurred");
            }
        }
    }

    /**
     * <p>
     * Action to change UI theme to Windows theme
     * </p>
     * 
     * @see 
     */
    public class WindowsAction extends ImageAction {

        /**
         * <p>
         * Create a new action to change UI to Metal theme action
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        WindowsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
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
            try {
                if(currentTheme == ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel")){
                    JOptionPane.showMessageDialog(null, "This theme is already applied");
                }else{
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(Andie.frame); //Repaints the UI
                    currentTheme = ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//Updates current theme
                }

            } catch (UnsupportedLookAndFeelException exception) {
                JOptionPane.showMessageDialog(null, "UnsupportedLookAndFeelException occurred");
            }catch (ClassNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "ClassNotFoundException occurred\nTheme is not OS compatible.");//Occurs if on MAC
            }catch (InstantiationException exception) {
                JOptionPane.showMessageDialog(null, "InstantiationException occurred");
            }catch (IllegalAccessException exception) {
                JOptionPane.showMessageDialog(null, "IllegalAccessException occurred");
            }
        }
    }

    /**
     * <p>
     * Action to change UI theme to Windows Classic theme
     * </p>
     * 
     * @see 
     */
    public class WindowsClassicAction extends ImageAction {

        /**
         * <p>
         * Create a new action to change UI to Windows Classic theme action
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        WindowsClassicAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
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
            try {
                if(currentTheme == ("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel")){
                    JOptionPane.showMessageDialog(null, "This theme is already applied");
                }else{
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(Andie.frame); //Repaints the UI
                    currentTheme = ("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");//Updates current theme
                }

            } catch (UnsupportedLookAndFeelException exception) {
                JOptionPane.showMessageDialog(null, "UnsupportedLookAndFeelException occurred");
            }catch (ClassNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "ClassNotFoundException occurred \nTheme is not OS compatible.");//Occurs if on MAC
            }catch (InstantiationException exception) {
                JOptionPane.showMessageDialog(null, "InstantiationException occurred");
            }catch (IllegalAccessException exception) {
                JOptionPane.showMessageDialog(null, "IllegalAccessException occurred");
            }
        }
    }

    /**
     * <p>
     * Action to change UI theme to Default theme
     * </p>
     * 
     * @see 
     */
    public class DefaultAction extends ImageAction {
        /**
         * <p>
         * Create a new action to change UI to Default theme action
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DefaultAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
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
            try {
                if(currentTheme == (defaultLookFeel)){
                    JOptionPane.showMessageDialog(null, "This theme is already applied");
                }else{
                    UIManager.setLookAndFeel(defaultLookFeel);
                    SwingUtilities.updateComponentTreeUI(Andie.frame); //Repaints the UI
                    currentTheme = (defaultLookFeel);//Updates current theme
                }

            } catch (UnsupportedLookAndFeelException exception) {
                JOptionPane.showMessageDialog(null, "UnsupportedLookAndFeelException occurred");
            }catch (ClassNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "ClassNotFoundException occurred");
            }catch (InstantiationException exception) {
                JOptionPane.showMessageDialog(null, "InstantiationException occurred");
            }catch (IllegalAccessException exception) {
                JOptionPane.showMessageDialog(null, "IllegalAccessException occurred");
            }
        }
    }

}


