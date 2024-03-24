package cosc202.andie;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class LanguageActions {
    private Locale currentLocale;
    private ResourceBundle messages;

    public LanguageActions() {
        this.currentLocale = new Locale.Builder().setLanguage("ko").setRegion("KR").build();
        this.messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    public Locale getCurrentLocale() {
        return this.currentLocale;
    }

    public String getMessage(String key) {
        return this.messages.getString(key);
    }

    public JMenu createMenu() {
        JMenu languageMenu = new JMenu(getMessage("Language"));
        JMenuItem koreanItem = new JMenuItem(getMessage("Korean"));
        JMenuItem englishItem = new JMenuItem(getMessage("English"));
        languageMenu.add(koreanItem);
        languageMenu.add(englishItem);
        return languageMenu;
    }
}