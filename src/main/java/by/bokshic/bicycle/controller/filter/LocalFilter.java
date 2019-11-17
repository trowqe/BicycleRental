package by.bokshic.bicycle.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bokshic.bicycle.config.ConfigurationManager;
import by.bokshic.bicycle.config.SessionAttributes;

@WebFilter(urlPatterns = { "/*" })
public class LocalFilter implements Filter {
	private static Logger logger = LogManager.getLogger(LocalFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession(true);
		
		if (session.getAttribute(SessionAttributes.LANGUAGE) == null)  {
			String language = ConfigurationManager.getProperty(ConfigurationManager.LANGUAGE_DEFAULT);
			session.setAttribute(SessionAttributes.LANGUAGE, language);
			logger.debug("language = " + language);
		}	
				
		chain.doFilter(request, response);
	}
	

	@Override
	public void destroy() {
	}

}
