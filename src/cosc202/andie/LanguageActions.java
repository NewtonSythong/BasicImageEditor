package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class LanguageActions {

    /** A list of actions for the Language menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Language menu actions.
     * </p>
     */
    public LanguageActions() {
        actions = new ArrayList<Action>();
        actions.add(new ChangeLanguageAction("Korean", null, "Change the language",
                Integer.valueOf(KeyEvent.VK_K), "ko", "KR"));
    }

    /**
     * <p>
     * Create a menu containing the list of Language actions.
     * </p>
     * 
     * @return The Language menu UI element.
     */
    public JMenu createMenu() {
        JMenu languageMenu = new JMenu("Language");
        for (Action action : actions) {
            JMenuItem item = new JMenuItem(action);
            languageMenu.add(item);
        }
        return languageMenu;
    }
    public class ChangeLanguageAction extends AbstractAction {

        private static final long serialVersionUID = 1L;
        private String languageCode;
        private String countryCode;

        public ChangeLanguageAction(String text, ImageIcon icon, String desc, Integer mnemonic, String languageCode, String countryCode) {
            super(text);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, desc);
            putValue(Action.MNEMONIC_KEY, mnemonic);
            this.languageCode = languageCode;
            this.countryCode = countryCode;
        }

        public void actionPerformed(ActionEvent e) {
            ChangeLanguage changeLanguage = new ChangeLanguage(); // Create an instance of the ChangeLanguage class
            changeLanguage.changeLanguage(languageCode, countryCode); // Invoke the changeLanguage method on the instance
        }
    }
}