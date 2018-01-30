package by.epam.bicycle.controller.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.config.MessageManager;
import by.epam.bicycle.controller.CommandException;
import by.epam.bicycle.controller.ExceptionTypeEnum;
import by.epam.bicycle.controller.ValidationException;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.entity.Role;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.UserService;
import by.epam.bicycle.utils.HashUtils;

public class CreateUserCommand implements ActionCommand {
	private final static String NAME_PARAM = "name";
	private final static String SURNAME_PARAM = "surname";
	private final static String PATRONYMIC_PARAM = "patronymic";
	private final static String MOBILEPHONE_PARAM = "mobilephone";
	private final static String EMAIL_PARAM = "email";
	private final static String LOGIN_PARAM = "newlogin";
	private final static String PASSWORD_PARAM = "newpassword";
	private final static String REPEATPASSWORD_PARAM = "repeatpassword";
	
	private User getUserFromRequest(HttpServletRequest request) {
		String name = request.getParameter(NAME_PARAM);
		String surname = request.getParameter(SURNAME_PARAM);
		String patronymic = request.getParameter(PATRONYMIC_PARAM);
		String mobilephone = request.getParameter(MOBILEPHONE_PARAM);
		String email = request.getParameter(EMAIL_PARAM);
		String login = request.getParameter(LOGIN_PARAM);
		String password = request.getParameter(PASSWORD_PARAM);
		String passwordHash = HashUtils.getHashMD5(password);
		long roleId = Long.parseLong(ConfigurationManager.getProperty("role.user"));
		Role role = new Role(roleId);
	
		User user = new User(name, surname, patronymic, mobilephone, email, login, passwordHash, role);
		return user;		
	}
	
	private void checkRequest(HttpServletRequest request) throws ValidationException, ServiceException {
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute("language");

		//check that password and repeatpassword was the same 
		String password = request.getParameter(PASSWORD_PARAM);
		String repeatPassword = request.getParameter(REPEATPASSWORD_PARAM);
		if (!password.equals(repeatPassword)) {
			String message = MessageManager.getProperty(language, "message.passwordsnotsame");
			throw new ValidationException(message);
		}
		
		//check that login is unique
		String login = request.getParameter(LOGIN_PARAM);
		UserService userService = new UserService();
		if (!userService.isLoginUnique(login)) {
			String message = MessageManager.getProperty(language, "message.loginnotunique");
			throw new ValidationException(message);
		}
	}
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession(true);
		
		try {
			User newUser = getUserFromRequest(request);
			try {
				checkRequest(request);
			} catch (ValidationException e) {
				session.setAttribute("newUser", newUser);
				throw new CommandException(e);
			} 
	
			UserService userService = new UserService();
			userService.create(newUser);
		} catch (ServiceException e) {
			throw new CommandException(e);	
		}		
		
		
		String language = (String) session.getAttribute("language");
		String message = MessageManager.getProperty(language, "message.usercreated");
		throw new CommandException(message, ExceptionTypeEnum.SUCCESS);		
	}

}
