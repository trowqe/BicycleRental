<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="localization.data" />
<!DOCTYPE html>
<html lang="${sessionScope.language}">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><fmt:message key="common.head.title"/></title>
	<c:import url="jsp\common\fragment\links.jsp"/> 
</head>
<body>
</body>
</html>