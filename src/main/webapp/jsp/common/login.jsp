<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Прокат велосипедов</title>
	<c:import url="fragment\links.jsp"/> 
</head>
<body class="loginpage">
	<c:import url="fragment\header.jsp"/>
	
	<main>
		<section>
			<div class="content-single animate grey">
				<div class="tab">
  					<button class="tablinks active left" onclick="openTab(event, 'loginForm')">Вход в систему</button>
 					<button class="tablinks right" onclick="openTab(event, 'registrationForm')">Регистрация</button>
				</div>
				
				<form name="loginForm" id="loginForm" method="POST" action="controller" class="tabcontent active">
					<div class="container">
						<input type="hidden" name="command" value="login" /> 
						<label for="login"><b>Логин: </b></label>
						<input type="text" name="login" id="login" required />
						<label for="password"><b>Пароль: </b></label>
						<input type="password" name="password" id="password" required />
					</div>
					<div class="container center">
						<button type="submit" class="enterbtn">Войти</button>
    				</div>
    				<div id="loginmsg" class="container"></div>
				</form>

				<form name="registrationForm" id="registrationForm" method="POST" action="controller" class="tabcontent"  onsubmit="return validateRegistrationForm()">
					<div class="container">
						<input type="hidden" name="command" value="register" /> 
						<label for="name"><b>Имя: </b></label>
						<input type="text" name="name" id="name" required />
						<label for="surname"><b>Фамилия: </b></label>
						<input type="text" name="surname" id="surname" required />
						<label for="patronymic"><b>Отчество: </b></label>
						<input type="text" name="patronymic" id="patronymic" />
						<label for="age"><b>Возраст: </b></label>
						<input type="number" name="age" id="age" min="7" max="120" />
						<label for="mobilephone"><b>Мобильный телефон: </b></label>
						<input type="text" name="mobilephone" id="mobilephone" placeholder="+ 375 XX XXX XX XX" required/>
						<label for="email"><b>Электронный адрес: </b></label>
						<input type="text" name="email" id="email" placeholder="test@test.com" pattern="^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}" required/>
						<label for="newlogin"><b>Логин: </b></label>
						<input type="text" name="newlogin" id="newlogin" pattern="^[a-zA-Z][a-zA-Z0-9_]{5,}" title="Должен содержать не менее 5 символов, разрешены латинские символы, цифры, _ (первый символ - латинская буква)" required/>
						<label for="newpassword"><b>Пароль: </b></label>
						<input type="password" name="newpassword" id="newpassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" title="Должен содержать не менее 6 символов, не менее одной буквы в каждом регистре и не менее одной цифры" required />
						<label for="repeatpassword"><b>Повторить пароль: </b></label>
						<input type="password" name="repeatpassword" id="repeatpassword" required />
					</div>
					<div class="container center">
						<button type="submit" class="enterbtn">Зарегистрироваться</button>
    				</div>
    				<div id="registermsg" class="container"></div>
				</form>					
			</div>
		</section>
	</main>
	
	<c:import url="fragment\footer.jsp"/>
</body>
</html>