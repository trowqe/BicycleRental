<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="localization.data" />

<!DOCTYPE html>
<header class="topheader"> 
<nav class="content">
	<ul class="topnav">
		<li>
			<a href="index.jsp" class="logo"> 
				<img src="img/bike.png" height="70" class="logo" />
			</a>
		</li>
		<li><h1><fmt:message key="common.header.h1"/></h1></li>
		<li class="locale right">
			<form name="langForm" id="langForm" method="POST" action="controller">
				<input type="hidden" name="command" value="changelanguage" /> 
				<select id="language" name="language" onchange="changeLanguage(this)">
					<option value="ru" ${sessionScope.language == 'ru' ? 'selected' : ''}>Русский</option>
					<option value="en" ${sessionScope.language == 'en' ? 'selected' : ''}>English</option>
				</select>
			</form>
		</li>
	</ul>
</nav>
</header>