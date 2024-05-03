package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.plaf.*;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

public class ThemeActions {
    
    /** A list of actions for the Theme menu. */
    protected ArrayList<Action> actions;
    private ResourceBundle bundle;

    public ThemeActions() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        this.bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");
        if (this.bundle == null) {
            throw new RuntimeException("Resource bundle not found!");
        }
        actions = new ArrayList<Action>();
        actions.add(new MetalAction(bundle.getString("MetalTheme"), null, "Metal Theme", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new NimbusAction(bundle.getString("NimbusTheme"), null, "Nimbus Theme", Integer.valueOf(KeyEvent.VK_N)));
        actions.add(new MotifAction(bundle.getString("MotifTheme"), null, "Motif Theme", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new WindowsAction(bundle.getString("WindowsTheme"), null, "Windows Theme", Integer.valueOf(KeyEvent.VK_W)));
        actions.add(new WindowsClassicAction(bundle.getString("WindowsClassicTheme"), null, "Windows Classic Theme", Integer.valueOf(KeyEvent.VK_C)));

    }


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

    /** */
    public class MetalAction extends ImageAction {

        MetalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                SwingUtilities.updateComponentTreeUI(Andie.frame); //Repaints the UI

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

    /** */
    public class NimbusAction extends ImageAction {

        NimbusAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            try {

                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                SwingUtilities.updateComponentTreeUI(Andie.frame); //Repaints the UI

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

    /** */
    public class MotifAction extends ImageAction {

        MotifAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            try {

                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                SwingUtilities.updateComponentTreeUI(Andie.frame); //Repaints the UI

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

    /** */
    public class WindowsAction extends ImageAction {

        WindowsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            try {

                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                SwingUtilities.updateComponentTreeUI(Andie.frame); //Repaints the UI

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

    /** */
    public class WindowsClassicAction extends ImageAction {

        WindowsClassicAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            try {

                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                SwingUtilities.updateComponentTreeUI(Andie.frame); //Repaints the UI

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
