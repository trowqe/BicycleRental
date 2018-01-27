package by.epam.bicycle.controller;

public class ValidationException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private ExceptionTypeEnum type = ExceptionTypeEnum.WRONG;
	
	public ValidationException() {
		super();
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}
	
	public ValidationException(String message, Throwable cause, ExceptionTypeEnum type) {
		super(message, cause);
		this.type = type;
	}
	
	public ValidationException(String message, ExceptionTypeEnum type) {
		super(message);
		this.type = type;
	}

	public ValidationException(Throwable cause, ExceptionTypeEnum type) {
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
