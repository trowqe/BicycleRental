package by.epam.bicycle.controller.response;

import by.epam.bicycle.config.MessageManager;

public class CommandMessage {
	private String message;
	private CommandMessageTypeEnum type;
	
	
	public CommandMessage() {
	}

	public CommandMessage(String message, CommandMessageTypeEnum type) {
		this.message = message;
		this.type = type;
	}
	
	public CommandMessage(String language, String messagePath, CommandMessageTypeEnum type) {
		setMessage(language, messagePath);
		this.type = type;
	}
	
	public CommandMessage(Exception e, CommandMessageTypeEnum type) {
		this.message = e.getMessage();
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public CommandMessageTypeEnum getType() {
		return type;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setMessage(String language, String messagePath) {
		String message = MessageManager.getProperty(language, messagePath);
		this.message = message;
	}
	
	public void setType(CommandMessageTypeEnum type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CommandMessage [message=" + message + ", type=" + type + "]";
	}
	
}
