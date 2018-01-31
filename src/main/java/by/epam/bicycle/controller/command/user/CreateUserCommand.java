package by.epam.bicycle.controller.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.ConfigurationManager;
import by.epam.bicycle.config.MessageManager;
import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.command.ActionCommand;
import by.epam.bicycle.controller.exception.CommandException;
import by.epam.bicycle.controller.exception.ValidationException;
import by.epam.bicycle.controller.response.CommandMessage;
import by.epam.bicycle.controller.response.CommandMessageTypeEnum;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.controller.response.impl.RedirectResponse;
import by.epam.bicycle.entity.Role;
import by.epam.bicycle.entity.User;
import by.epam.bicycle.service.ServiceException;
import by.epam.bicycle.service.impl.UserService;
import by.epam.bicycle.utils.HashUtils;

public class CreateUserCommand implements ActionCommand {
	public final static String NAME_PARAM = "name";
	public final static String SURNAME_PARAM = "surname";
	public final static String PATRONYMIC_PARAM = "patronymic";
	public final static String MOBILEPHONE_PARAM = "mobilephone";
	public final static String EMAIL_PARAM = "email";
	public final static String LOGIN_PARAM = "newlogin";
	public final static String PASSWORD_PARAM = "newpassword";
	public final static String REPEATPASSWORD_PARAM = "repeatpassword";
	
	private UserService userService;
	
	private User getUserFromRequest(HttpServletRequest request) {
		String name = request.getParameter(NAME_PARAM);
		String surname = request.getParameter(SURNAME_PARAM);
		String patronymic = request.getParameter(PATRONYMIC_PARAM);
		String mobilephone = request.getParameter(MOBILEPHONE_PARAM);
		String email = request.getParameter(EMAIL_PARAM);
		String login = request.getParameter(LOGIN_PARAM);
		String password = request.getParameter(PASSWORD_PARAM);
		String passwordHash = HashUtils.getHashMD5(password);
		long roleId = Long.parseLong(ConfigurationManager.getProperty(ConfigurationManager.ROLE_USER));
		Role role = new Role(roleId);
	
		User user = new User(name, surname, patronymic, mobilephone, email, login, passwordHash, role);
		return user;		
	}
	
	private void checkRequest(HttpServletRequest request) throws ServiceException, ValidationException {
		HttpSession session = request.getSession(true);
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);

		//check that password and repeatpassword was the same 
		String password = request.getParameter(PASSWORD_PARAM);
		String repeatPassword = request.getParameter(REPEATPASSWORD_PARAM);
		if (!password.equals(repeatPassword)) {
			String message = MessageManager.getProperty(language, MessageManager.PASSWORDSNOTSAME);
			throw new ValidationException(message);
		}
		
		//check that login is unique
		String login = request.getParameter(LOGIN_PARAM);
		if (!userService.isLoginUnique(login)) {
			String message = MessageManager.getProperty(language, MessageManager.LOGINNOTUNIQUE);
			throw new ValidationException(message);
		}
	}
	
	@Override
	public CommandResponse execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession(true);
		User newUser = getUserFromRequest(request);
		try {
			checkRequest(request);
			userService.create(newUser);
		} catch (ValidationException e) {
			session.setAttribute(SessionAttributes.NEWUSER, newUser);
			CommandMessage message = new CommandMessage(e, CommandMessageTypeEnum.WRONG);
			return new RedirectResponse(CommandResponse.INDEX_PAGE, message);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}		
		
		String language = (String) session.getAttribute(SessionAttributes.LANGUAGE);
		CommandMessage message = new CommandMessage(language, MessageManager.USERCREATED, CommandMessageTypeEnum.SUCCESS);
		return new RedirectResponse(CommandResponse.INDEX_PAGE, message);
	}

}
