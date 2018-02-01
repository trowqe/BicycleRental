package by.epam.bicycle.controller.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epam.bicycle.config.SessionAttributes;
import by.epam.bicycle.controller.response.CommandResponse;
import by.epam.bicycle.entity.User;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@WebFilter(
		urlPatterns = {"/controller?command=bicycle", "/controller?command=filterbicycles", "/controller?command=logout" },
		dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}
)
public class AuthorizeClientFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute(SessionAttributes.USER);

		if (user == null) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(CommandResponse.ERROR_PAGE);
			dispatcher.forward(request, response);
		}

		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {		
	}

}
