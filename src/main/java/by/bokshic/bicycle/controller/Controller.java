package by.bokshic.bicycle.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.bokshic.bicycle.controller.command.ActionCommand;
import by.bokshic.bicycle.controller.exception.CommandException;
import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.RedirectResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bokshic.bicycle.config.SessionAttributes;
import by.bokshic.bicycle.controller.command.ActionCommand;
import by.bokshic.bicycle.controller.exception.CommandException;
import by.bokshic.bicycle.controller.response.CommandResponse;
import by.bokshic.bicycle.controller.response.impl.RedirectResponse;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionFactory client = new ActionFactory();
		
		CommandResponse commandResponse = null;
		try {
			ActionCommand command = client.defineCommand(request);
			commandResponse = command.execute(request);
		} catch (CommandException e) {
			logger.error(e);
			HttpSession session = request.getSession();
			session.setAttribute(SessionAttributes.PAGE, SessionAttributes.ERROR_PAGE);
			commandResponse = new RedirectResponse(CommandResponse.ERROR_PAGE);
		}	
		
		commandResponse.sendResponse(request, response);
		
	}

}
