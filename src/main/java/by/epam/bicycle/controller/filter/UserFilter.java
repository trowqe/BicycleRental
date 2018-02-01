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
		urlPatterns = { "/jsp/user/*", "/controller?command=closerent", "/controller?command=createorder", "/controller?command=createuser",
				"/controller?command=prepareorder", "/controller?command=rents", "/controller?command=returnbicycle" },
		dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}
)
public class UserFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute(SessionAttributes.USER);

		if (user == null || !user.getRole().isUser()) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(CommandResponse.ERROR_PAGE);
			dispatcher.forward(request, response);
		}

		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {		
	}

}
