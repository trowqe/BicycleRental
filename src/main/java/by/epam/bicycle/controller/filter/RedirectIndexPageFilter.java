package by.epam.bicycle.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.entity.User;

@WebFilter(urlPatterns = { "/index.jsp" })
public class RedirectIndexPageFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute(SessionAttributes.USER);

		String page = CommandResponse.LOGIN_PAGE;
		if (user != null) {
			if (user.getRole().isUser()) {
				page = CommandResponse.BICYCLES_COMMAND;
			} else if (user.getRole().isAdmin()) {
				page = CommandResponse.USERS_COMMAND;
			}
		}
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
