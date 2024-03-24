package cosc202.andie;


import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class LanguageActions {
    private ChangeLanguage changeLanguage;

    public LanguageActions() {
        this.changeLanguage = new ChangeLanguage();
    }

    public String getMessage(String key) {
        return this.changeLanguage.getMessages().getString(key);
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