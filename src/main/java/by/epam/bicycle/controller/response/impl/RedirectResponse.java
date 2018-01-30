package by.epam.bicycle.controller.response.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.bicycle.controller.response.AbstractResponse;
import by.epam.bicycle.controller.response.CommandMessage;

public class RedirectResponse extends AbstractResponse {
	
	public RedirectResponse() {	
	}
	
	public RedirectResponse(String page) {
		super(page);
	}
	
	public RedirectResponse(String page, CommandMessage message) {
		super(page, message);
	}
	
	@Override
	public void sendResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		setResponseMessage(request);
		String page = getPage();
		response.sendRedirect(request.getContextPath() + page);
	}

}
