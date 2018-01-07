<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Прокат велосипедов (администратор)</title>
</head>
<body>
	<h3>Welcome, admin!</h3>
	<hr />
	${user.getName()}, hello!
	<hr />
	<a href="controller?command=logout">Logout</a>
</body>
</html>