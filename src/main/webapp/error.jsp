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
	<c:import url="jsp\common\fragment\links.jsp"/> 
</head>
<body class="loginpage">
	<c:choose>
		<c:when test="${sessionScope.user.getRole().isAdmin()}">
			<c:import url="jsp\admin\fragment\header.jsp"/>
		</c:when>
		<c:when test="${sessionScope.user.getRole().isUser()}">
			<c:import url="jsp\user\fragment\header.jsp"/>
		</c:when>
		<c:otherwise>
			<c:import url="jsp\common\fragment\header.jsp"/>
		</c:otherwise>
	</c:choose>
	
	
	<main>
		<section>
			<div class="content-single filter grey red p-20">
				 <c:out value="${sessionScope.language == 'en' ? 'Sorry, there was a system crash while performing the operation. Try to do your actions again.' : 'Извините, произошёл сбой системы при выполнении операции. Попробуйте потворить свои действия ещё раз.'}"/>
				 <c:remove var="error" scope="session" />	
			</div>
		</section>
	</main>
	
	<c:import url="jsp\common\fragment\footer.jsp"/>
</body>
</html>