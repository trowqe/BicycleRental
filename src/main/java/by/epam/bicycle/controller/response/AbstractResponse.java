package by.epam.bicycle.controller.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.SessionAttributes;

public abstract class AbstractResponse implements CommandResponse {
	private String page;
	private CommandMessage message;
	
	public AbstractResponse() {
	}
	
	public AbstractResponse(String page) {
		this.page = page;
	}
	
	public AbstractResponse(String page, CommandMessage message) {
		this.page = page;
		this.message = message;
	}
	
	public String getPage() {
		return page;
	}

	public CommandMessage getMessage() {
		return message;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setMessage(CommandMessage message) {
		this.message = message;
	}

	public void setResponseMessage(HttpServletRequest request) {
		HttpSession session = request.getSession(true);	
		if (message != null) {
			session.setAttribute(SessionAttributes.ALERT, message);
		}
	}
	
}
