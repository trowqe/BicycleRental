package by.epam.bicycle.controller.response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandResponse {
	String INDEX_PAGE = "/index.jsp";
	String LOGIN_PAGE = "/jsp/common/login.jsp";
	String BICYCLES_PAGE = "/jsp/user/bicycles.jsp";
	String PREPAREORDER_PAGE = "/jsp/user/prepareorder.jsp";
	String RENTS_PAGE = "/jsp/user/rents.jsp";
	String RETURNBICYCLE_PAGE = "/jsp/user/returnbicycle.jsp";
	String USERS_PAGE = "/jsp/admin/users.jsp";
	String ADMINBICYCLES_PAGE = "/jsp/admin/bicycles.jsp";
	String BICYCLE_PAGE = "/jsp/admin/bicycle.jsp";
	String ERROR_PAGE = "/error.jsp";;
	
	String RENTS_COMMAND = "/controller?command=rents";
	String BICYCLES_COMMAND = "/controller?command=filterbicycles";
	String USERS_COMMAND = "/controller?command=users";
	
	
	void sendResponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
