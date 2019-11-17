package by.bokshic.bicycle.controller.jsp;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.bokshic.bicycle.controller.response.CommandMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bokshic.bicycle.config.SessionAttributes;


public class AlertBoxHandler extends TagSupport {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(AlertBoxHandler.class);
	
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		HttpSession session = pageContext.getSession();
		CommandMessage message = (CommandMessage) session.getAttribute(SessionAttributes.ALERT);
		if (message != null) {
			try {
				logger.debug("messageType = " + message.getType().toString());
				logger.debug("messageType = " + message.getMessage());
				out.print("<div id=\"alert\" class=\"alert\" style=\"display:block;\">");
				out.print("<div class=\"alert-content " + message.getType().toString().toLowerCase() + "\">");
				out.print("<span class=\"closebtn\" onclick=\"closeAlert();\">&times;</span>");
				out.print("<span class=\"msg\">" + message.getMessage() + "</span>");
				out.print("</div></div>");
				out.print("<script type=\"text/javascript\">");
				out.print("closeAlertTimeout();");
				out.print("</script>");
			} catch (IOException e) {
				throw new JspException(e);	
			}
			session.removeAttribute(SessionAttributes.ALERT);
		}		
		return SKIP_BODY;
		
	}
}
