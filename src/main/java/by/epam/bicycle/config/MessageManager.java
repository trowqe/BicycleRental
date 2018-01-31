package by.epam.bicycle.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
	public static final String USERCREATED = "message.usercreated";
	public static final String PASSWORDSNOTSAME = "message.passwordsnotsame";
	public static final String LOGINNOTUNIQUE = "message.loginnotunique";
	public static final String RENTCLOSED = "message.rentclosed";
	public static final String ORDERCREATED = "message.ordercreated";
	public static final String LOGINERROR = "message.loginerror";
	public static final String USERBLOCKED = "message.userblocked";
	public static final String BICYCLECREATED = "message.bicyclecreated";
	public static final String BICYCLEDELETED = "message.bicycledeleted";
	public static final String BICYCLEUPDATED = "message.bicycleupdated";
	public static final String COMMONERROR = "message.commonerror";
	public static final String USERSTATUSCHANGED = "message.userstatuschanged";
	public static final String MESSAGES_BUNDLE = "localization.messages";
	
	private MessageManager() { }
	
	public static String getProperty(String language, String key) {
		if (language == null || language.isEmpty()) {
			language = ConfigurationManager.getProperty(ConfigurationManager.LANGUAGE_DEFAULT);
		}
		Locale locale = new Locale(language);
		ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_BUNDLE, locale);
		return resourceBundle.getString(key);
	}

}
