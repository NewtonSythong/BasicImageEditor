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
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerListModel;

/**
 * This class handles the actions related to language changes in the
 * application.
 * 
 * @author Newton
 * @author Jenny
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

        /**
         * Handles the action event triggered by the user.
         * This method is called when the user performs an action that this class is
         * listening to.
         * For example, if this class is used as an ActionListener for a button, this
         * method will be called when the button is clicked.
         *
         * @param e The action event triggered by the user.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String language = "English";
            String[] languageList = { "English", "Korean", "Italian"};

            SpinnerListModel languageModel = new SpinnerListModel(languageList);
            JSpinner languageSpinner = new JSpinner(languageModel);
            int option = JOptionPane.showOptionDialog(null, languageSpinner, "Select language",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            Preferences prefs = Preferences.userNodeForPackage(Andie.class);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                language = (String) languageModel.getValue();
                System.out.println(language);
                if (language.equals("Korean")) {
                    prefs.put("language", "ko");
                    prefs.put("country", "KR");
                    restartApplication();

                } else if (language.equals("English")) {
                    prefs.put("language", "en");
                    prefs.put("country", "NZ");
                    restartApplication();
                } else if (language.equals("Italian")) {
                    prefs.put("language", "it");
                    prefs.put("country", "IT");
                    restartApplication();
                }
            }

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