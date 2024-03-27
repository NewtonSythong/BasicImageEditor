package cosc202.andie;

import java.awt.event.ActionEvent;
import java.util.*;
import java.util.prefs.Preferences;
import javax.swing.*;

public class LanguageActions2 {

    private ArrayList<Action> actions;
    private ResourceBundle bundle;

    public LanguageActions2(ResourceBundle bundle) {
        this.bundle = bundle;
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

    public class ChangeLanguage extends AbstractAction {

        private String language;

        public ChangeLanguage(String name, ImageIcon icon, Integer mnemonic, String language, String country) {
            super(name, icon);
            this.language = language;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            Preferences prefs = Preferences.userNodeForPackage(Andie.class);
            System.out.println("action method called"); //test

            if ("en".equals(this.language)) {
                prefs.put("language", "en");
                prefs.put("country", "NZ");
                System.out.println("English"); // test

            } else if ("ko".equals(this.language)) {
                prefs.put("language", "ko");
                prefs.put("country", "KR");
                System.out.println("Korean"); // test
            }
        }

    }
}
