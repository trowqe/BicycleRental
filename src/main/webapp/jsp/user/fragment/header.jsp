<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="localization.data" />
<!DOCTYPE html>
<header class="topheader">
	<nav class="content">
		<ul class="topnav">
			<li>
				<a href="#" class="logo">
					<img src="img/bike.png" height="70" class="logo"/>
				</a>
			</li>
			<li><a href="controller?command=filterbicycles" class="active"><fmt:message key="user.header.bicycles"/></a></li>
			<li><a href="controller?command=rentalpoints"><fmt:message key="user.header.rentalpoints"/></a></li>
			<li><a href="controller?command=prices"><fmt:message key="user.header.prices"/></a></li>	
			<li class="right"><button type="button" onclick="logout()" class="loginbtn"><fmt:message key="common.button.exit"/></button></li>					
		</ul>
	</nav>
</header>