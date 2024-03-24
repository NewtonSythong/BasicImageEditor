package cosc202.andie;

import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeLanguage implements java.io.Serializable{
    private Locale currentLocale;
    private ResourceBundle messages;

    public ChangeLanguage() {
        this.currentLocale = Locale.getDefault();
        this.messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    public void changeLanguage(String languageCode, String countryCode) {
        this.currentLocale = new Locale.Builder().setLanguage(languageCode).setRegion(countryCode).build();
        this.messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    public ResourceBundle getMessages() {
        return this.messages;
    }
}
