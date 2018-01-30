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
	
	private MessageManager() { }
	
	public static String getProperty(String language, String key) {
		if (language == null || language.isEmpty()) {
			language = ConfigurationManager.getProperty("language.default");
		}
		Locale locale = new Locale(language);
		ResourceBundle resourceBundle = ResourceBundle.getBundle("localization.messages", locale);
		return resourceBundle.getString(key);
	}

}
