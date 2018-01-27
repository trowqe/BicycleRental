package by.epam.bicycle.controller.jsp;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.epam.bicycle.controller.CommandException;


public class AlertBoxHandler extends TagSupport {
	private static final long serialVersionUID = 1L;
	
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		HttpSession session = pageContext.getSession();
		CommandException exception = (CommandException) session.getAttribute("alert");
		if (exception != null) {
			try {
				out.print("<div id=\"alert\" class=\"alert\" style=\"display:block;\">");
				out.print("<div class=\"alert-content " + exception.getType().name().toLowerCase() + "\">");
				out.print("<span class=\"closebtn\" onclick=\"closeAlert();\">&times;</span>");
				out.print("<span class=\"msg\">" + exception.getMessage() + "</span>");
				out.print("</div></div>");
				out.print("<script type=\"text/javascript\">");
				out.print("closeAlertTimeout();");
				out.print("</script>");
			} catch (IOException e) {
				throw new JspException(e.getMessage());	
			}
			session.removeAttribute("alert");
		}		
		return SKIP_BODY;
		
	}
}
