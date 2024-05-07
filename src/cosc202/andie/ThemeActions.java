package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * <p>
 * ImageOperation to apply different themes to the UI
 * </p>
 * 
 * <p>
 * A sharpen filter enhances the color contrast around edges in the image
 * by enhancing the differences between neighbouring pixel values
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
    private String defaultLookFeel = UIManager.getLookAndFeel().getClass().getName();
    private String currentTheme = defaultLookFeel;


    public ThemeActions() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);        
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
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
     * Create a menu containing the list of Edit actions.
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

    /*
     * javax.swing.plaf.metal.MetalLookAndFeel
        javax.swing.plaf.nimbus.NimbusLookAndFeel
        com.sun.java.swing.plaf.motif.MotifLookAndFeel
        com.sun.java.swing.plaf.windows.WindowsLookAndFeel
        com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
     */

    /**
     * <p>
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public class MetalAction extends ImageAction {

        MetalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

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
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public class NimbusAction extends ImageAction {

        NimbusAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

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
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public class MotifAction extends ImageAction {

        MotifAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

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
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public class WindowsAction extends ImageAction {

        WindowsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

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
                JOptionPane.showMessageDialog(null, "ClassNotFoundException occurred");//occuring
            }catch (InstantiationException exception) {
                JOptionPane.showMessageDialog(null, "InstantiationException occurred");
            }catch (IllegalAccessException exception) {
                JOptionPane.showMessageDialog(null, "IllegalAccessException occurred");
            }
        }
    }

    /**
     * <p>
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public class WindowsClassicAction extends ImageAction {

        WindowsClassicAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

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
                JOptionPane.showMessageDialog(null, "ClassNotFoundException occurred");//occur
            }catch (InstantiationException exception) {
                JOptionPane.showMessageDialog(null, "InstantiationException occurred");
            }catch (IllegalAccessException exception) {
                JOptionPane.showMessageDialog(null, "IllegalAccessException occurred");
            }
        }
    }

    /**
    */ 
    public class DefaultAction extends ImageAction {
        DefaultAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

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


