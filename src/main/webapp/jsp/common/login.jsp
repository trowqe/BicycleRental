<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib prefix="m" uri="/WEB-INF/tld/alertbox.tld"%>
 
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="localization.data" />
<!DOCTYPE html>
<html lang="${sessionScope.language}">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><fmt:message key="common.head.title"/></title>
	<c:import url="fragment\links.jsp"/> 
</head>
<body class="loginpage">
	<c:import url="fragment\header.jsp"/>
	
	<main>
		<m:alertbox/>
		<section>
			<div class="content-single animate grey">
				<div class="tab">
					<button class="tablinks left ${sessionScope.newUser == null ? 'active' : ''}" onclick="openTab(event, 'loginForm')"><fmt:message key="login.tab.login"/></button>
 					<button class="tablinks right ${sessionScope.newUser == null ? '' : 'active'}" onclick="openTab(event, 'registrationForm')"><fmt:message key="login.tab.signup"/></button>
				</div>
				
				<form name="loginForm" id="loginForm" method="POST" action="controller" class="tabcontent ${sessionScope.newUser == null ? 'active' : ''}">
					<div class="container">
						<input type="hidden" name="command" value="login" /> 
						<label for="login"><b><fmt:message key="login.input.login"/></b></label>
						<input type="text" name="login" id="login" required />
						<label for="password"><b><fmt:message key="login.input.password"/></b></label>
						<input type="password" name="password" id="password" required />
					</div>
					<div class="container center">
						<button type="submit" class="enterbtn"><fmt:message key="login.button.login"/></button>
    				</div>
   				</form>

				<form name="registrationForm" id="registrationForm" method="POST" action="controller" class="tabcontent ${sessionScope.newUser == null ? '' : 'active'}">
					<div class="container">
						<input type="hidden" name="command" value="createuser" /> 
						<label for="name"><b><fmt:message key="login.input.name"/></b></label>
						<input type="text" name="name" id="name" value="${sessionScope.newUser.getName()}" required />
						<label for="surname"><b><fmt:message key="login.input.surname"/></b></label>
						<input type="text" name="surname" id="surname" value="${sessionScope.newUser.getSurname()}" required />
						<label for="patronymic"><b><fmt:message key="login.input.patronymic"/></b></label>
						<input type="text" name="patronymic" id="patronymic" value="${sessionScope.newUser.getPatronymic()}" />
						<label for="mobilephone"><b><fmt:message key="login.input.mobilephone"/></b></label>
						<input type="text" name="mobilephone" id="mobilephone" value="${sessionScope.newUser.getMobilePhone()}" placeholder="+ 375 XX XXX XX XX" required/>
						<label for="email"><b><fmt:message key="login.input.email"/></b></label>
						<input type="text" name="email" id="email" placeholder="test@test.com" value="${sessionScope.newUser.getEmail()}" pattern="^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}" required/>
						<label for="newlogin"><b><fmt:message key="login.input.newlogin"/></b></label>
						<input type="text" name="newlogin" id="newlogin" value="${sessionScope.newUser.getLogin()}"  pattern="^[a-zA-Z][a-zA-Z0-9_]{5,}" title="Должен содержать не менее 5 символов, разрешены латинские символы, цифры, _ (первый символ - латинская буква)" required/>
						<label for="newpassword"><b><fmt:message key="login.input.newpassword"/></b></label>
						<input type="password" name="newpassword" id="newpassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="Должен содержать не менее 6 символов, не менее одной буквы в каждом регистре и не менее одной цифры" required />
						<label for="repeatpassword"><b><fmt:message key="login.input.repeatpassword"/></b></label>
						<input type="password" name="repeatpassword" id="repeatpassword" required />
					</div>
					<div class="container center">
						<button type="submit" class="enterbtn"><fmt:message key="login.button.signup"/></button>
    				</div>
    			</form>					
			</div>
		</section>
	</main>
	
	<c:import url="fragment\footer.jsp"/>
</body>
</html>