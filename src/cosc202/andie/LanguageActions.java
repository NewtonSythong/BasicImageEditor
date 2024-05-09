package cosc202.andie;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * This class handles the actions related to language changes in the
 * application.
 * 
 * @author Newton Sythong
 * @author Jenny Kim
 */
public class LanguageActions {
    /**
     * A list of actions that can be performed.
     */
    protected ArrayList<Action> actions;

    /**
     * Constructor for LanguageActions.
     * It sets the default locale based on saved preferences and initializes the
     * actions list.
     */
    public LanguageActions() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");

        actions = new ArrayList<Action>();
        actions.add(new LangChangeLanguage(bundle.getString("ChangeLanguage"), null, "changes language", null));
    }

    /**
     * Creates a menu for language actions.
     * 
     * @return a JMenu object containing menu items for each action.
     */
    public JMenu createMenu() {
        Preferences prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");

        JMenu languageMenu = new JMenu(bundle.getString("Language"));

        for (Action action : actions) {
            languageMenu.add(new JMenuItem(action));
        }

        return languageMenu;
    }

    /**
     * This class represents an action to change the language.
     */
    public class LangChangeLanguage extends ImageAction {

        LangChangeLanguage(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            this.putValue(Action.ACCELERATOR_KEY,
                    KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String[] languageList = { "English (NZ)", "Korean (한국어)", "Italian (Italiano)" };

            JComboBox<String> languageComboBox = new JComboBox<>(languageList);
            ResourceBundle bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");


            int option = JOptionPane.showOptionDialog(null, languageComboBox, bundle.getString("SelectLanguage"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == 0) {
                String language = (String) languageComboBox.getSelectedItem();
                Preferences prefs = Preferences.userNodeForPackage(Andie.class);

                if (language.equals("Korean (한국어)")) {
                    prefs.put("language", "ko");
                    prefs.put("country", "KR");
                } else if (language.equals("English (NZ)")) {
                    prefs.put("language", "en");
                    prefs.put("country", "NZ");
                } else if (language.equals("Italian (Italiano)")) {
                    prefs.put("language", "it");
                    prefs.put("country", "IT");
                }
                restartApplication();

            }
        }

        /**
         * Restarts the application by disposing the current frame and creating a new
         * one.
         * This method is typically called when the user changes the language, to
         * refresh the UI with the new language.
         * If an exception occurs while creating the new frame, the application will
         * exit.
         */
        private void restartApplication() {
            Andie.getFrame().dispose();
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Andie.createAndShowGUI();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.exit(1);
                    }
                }
            });
        }
    }
}