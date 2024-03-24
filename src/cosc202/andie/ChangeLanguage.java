package cosc202.andie;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class is responsible for changing the language of the application.
 */
public class ChangeLanguage implements java.io.Serializable{
    private Locale currentLocale;

    /**
     * Constructor for the ChangeLanguage class.
     * It initializes the current locale to the default locale.
     */
    public ChangeLanguage() {
        this.currentLocale = Locale.getDefault();
    }
    

    public void changeLanguage(String languageCode, String countryCode) {
        this.currentLocale = new Locale.Builder().setLanguage(languageCode).setRegion(countryCode).build();
        ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }
}
