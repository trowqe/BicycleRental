<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="localization.data" />

<!DOCTYPE html>
<footer class="footer">
	<div class="content">
		&copy; <fmt:message key="common.footer.rights"/>
	</div>
</footer>