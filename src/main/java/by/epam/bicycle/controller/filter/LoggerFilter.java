package by.epam.bicycle.controller.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(urlPatterns = { "/*" })
public class LoggerFilter implements Filter {
	private static Logger logger = LogManager.getLogger(LoggerFilter.class);
	
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Enumeration<String> headerNames = httpRequest.getHeaderNames();
		while(headerNames.hasMoreElements()) {
		  String headerName = headerNames.nextElement();
		  logger.debug("Header Name - " + headerName + ", Value - " + httpRequest.getHeader(headerName));
		}
		
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String paramName = params.nextElement();
		  logger.debug("param name - " + paramName + ", value - " + request.getParameter(paramName));
		}	
		chain.doFilter(request, response);
	}

	public void destroy() {		
	}
	
}
