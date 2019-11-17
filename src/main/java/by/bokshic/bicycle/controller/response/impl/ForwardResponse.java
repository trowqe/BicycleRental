package by.bokshic.bicycle.controller.response.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bokshic.bicycle.controller.response.CommandMessage;

public class ForwardResponse extends AbstractResponse {
	public ForwardResponse() {	
	}
	
	public ForwardResponse(String page) {
		super(page);
	}
	
	public ForwardResponse(String page, CommandMessage message) {
		super(page, message);
	}
	
	@Override
	public void sendResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setResponseMessage(request);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(getPage());
		dispatcher.forward(request, response);
	}

}
