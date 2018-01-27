package by.epam.bicycle.controller;

public class CommandException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private ExceptionTypeEnum type = ExceptionTypeEnum.WRONG;
	
	public CommandException() {
		super();
	}

	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandException(String message) {
		super(message);
	}

	public CommandException(Throwable cause) {
		super(cause);
	}
	
	public CommandException(String message, Throwable cause, ExceptionTypeEnum type) {
		super(message, cause);
		this.type = type;
	}
	
	public CommandException(String message, ExceptionTypeEnum type) {
		super(message);
		this.type = type;
	}

	public CommandException(Throwable cause, ExceptionTypeEnum type) {
		super(cause);
		this.type = type;
	}

	public ExceptionTypeEnum getType() {
		return type;
	}

	public void setType(ExceptionTypeEnum type) {
		this.type = type;
	}

	
}
