package by.epam.bicycle.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.RedirectResponse;

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
