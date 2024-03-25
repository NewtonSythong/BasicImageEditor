package cosc202.andie;

import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeLanguage implements java.io.Serializable{
    private Locale currentLocale;
    private ResourceBundle messages;

    public ChangeLanguage() {
        this.currentLocale = Locale.getDefault();
        this.messages = ResourceBundle.getBundle("cosc202.andie.MessagesBundle", currentLocale);
    }

    public void changeLanguage(String languageCode, String countryCode) {
        currentLocale = new Locale(languageCode, countryCode);
        messages = ResourceBundle.getBundle("cosc202.andie.MessagesBundle", currentLocale);
    }

    public ResourceBundle getMessages() {
        return this.messages;
    }
}