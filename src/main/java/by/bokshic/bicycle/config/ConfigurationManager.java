package by.bokshic.bicycle.config;

import java.util.ResourceBundle;

public class ConfigurationManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
	
	public static final String ROLE_USER = "role.user";
	public static final String USER_STATUS_ACTIVE = "user_status.active";
	public static final String USER_STATUS_BLOCKED = "user_status.blocked";
	public static final String LANGUAGE_DEFAULT = "language.default";
	
	private ConfigurationManager() { } 
	
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}

}
