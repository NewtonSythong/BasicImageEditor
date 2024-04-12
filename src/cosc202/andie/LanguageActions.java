package cosc202.andie;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.*;

/** 
 * This class creates a menu with language options
 *
 */
public class LanguageActions {

    private ArrayList<Action> actions;
    private ResourceBundle bundle;
    private JFrame frame;

    /** 
     * Constructor for LanguageActions
     * Adds menu items for English and Korean
     */
    public LanguageActions(ResourceBundle bundle, JFrame frame) {
        this.bundle = bundle;
        this.frame = frame;
        this.actions = new ArrayList<Action>();
        this.actions.add(new ChangeLanguage(bundle.getString("English"), null, null, "en", "NZ"));
        this.actions.add(new ChangeLanguage(bundle.getString("Korean"), null, null, "ko", "KR"));
    }

    public JMenu createMenu() {
        JMenu langMenu = new JMenu(bundle.getString("Language"));
        for (Action a : actions) {
            langMenu.add(new JMenuItem(a));
        }
        return langMenu;
    }

    /** 
     * This class changes the language of the image editor
     * based on user selection
     */
    public class ChangeLanguage extends AbstractAction {

        private String language;

        public ChangeLanguage(String name, ImageIcon icon, Integer mnemonic, String language, String country) {
            super(name, icon);
            this.language = language;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Preferences prefs = Preferences.userNodeForPackage(Andie.class);
            //System.out.println("action method called"); 

            String language = null; 
            String country = null;

            if ("en".equals(this.language)) {
                language = "en";
                country = "NZ";
                System.out.println("English"); 
            } else if ("ko".equals(this.language)) {
                language = "ko";
                country = "KR";
                System.out.println("Korean"); 
            }

            if (language != null && country != null) {
                prefs.put("language", language);
                prefs.put("country", country);

              
                Locale.setDefault(new Locale(language, country));

               
                ResourceBundle bundle = ResourceBundle.getBundle("cosc202/andie/MessageBundle", Locale.getDefault());

                updateUIComponents(frame.getContentPane(), bundle);

              
                SwingUtilities.invokeLater(() -> {
                    frame.getContentPane().removeAll();
                    try {
                        Andie.createAndShowGUI(); 
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    frame.getContentPane().revalidate();
                    frame.getContentPane().repaint();
                });
            }

        }

        private void updateUIComponents(Container container, ResourceBundle bundle) {
            for (Component component : container.getComponents()) {
                if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                    label.setText(bundle.getString(label.getText()));
                } else if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    button.setText(bundle.getString(button.getText()));
                } else if (component instanceof JMenuItem) {
                    JMenuItem menuItem = (JMenuItem) component;
                    menuItem.setText(bundle.getString(menuItem.getText()));
                }

                if (component instanceof Container && component != frame) {
                    updateUIComponents((Container) component, bundle);
                }
            }
        }

    }
}
