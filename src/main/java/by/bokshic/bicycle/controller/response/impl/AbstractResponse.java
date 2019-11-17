package by.bokshic.bicycle.controller.response.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.bokshic.bicycle.controller.response.CommandMessage;
import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.config.SessionAttributes;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((page == null) ? 0 : page.hashCode());
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
		AbstractResponse other = (AbstractResponse) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (page == null) {
			if (other.page != null)
				return false;
		} else if (!page.equals(other.page))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractResponse [page=" + page + ", message=" + message + "]";
	}
	
}
