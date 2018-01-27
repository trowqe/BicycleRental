package by.epam.bicycle.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
	
	private MessageManager() { }
	
	public static String getProperty(String language, String key) {
		Locale locale = new Locale(language);
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localization.messages", locale);
		return resourceBundle.getString(key);
	}

}
