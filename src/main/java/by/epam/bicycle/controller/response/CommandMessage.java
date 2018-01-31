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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommandMessage other = (CommandMessage) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommandMessage [message=" + message + ", type=" + type + "]";
	}
	
}
