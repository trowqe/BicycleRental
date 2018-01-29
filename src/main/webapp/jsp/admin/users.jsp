<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="m" uri="/WEB-INF/tld/alertbox.tld"%>  
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="localization.data" />
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><fmt:message key="common.head.title"/></title>
		<c:import url="..\common\fragment\links.jsp" />
	</head>
	<body>
		<c:import url="fragment\header.jsp" />
		<header class="subheader" >
				<div class="content">
				<div class="row">
					<nav >
					<ul class="topnav">
						<li><a href="javascript:blockUser()"><i class="fa fa-lock" aria-hidden="true"></i> <fmt:message key="subheader.users.block"/></a></li>
						<li><a href="javascript:unblockUser()"><i class="fa fa-unlock" aria-hidden="true"></i> <fmt:message key="subheader.users.unblock"/></a></li>
					</ul>
				</nav>
				</div>
			</div>
		</header>
		<main>
			<m:alertbox/>
			<section class="content p-b-15">
				<div class="row">
					<h2><fmt:message key="users.main.h2"/></h2>
					<table id="users">
						<tr>
							<th></th>
							<th><fmt:message key="users.table.id"/></th>
							<th><fmt:message key="users.table.fio"/></th>
							<th><fmt:message key="users.table.mobilephone"/></th>
							<th><fmt:message key="users.table.email"/></th>
							<th><fmt:message key="users.table.login"/></th>
							<th><fmt:message key="users.table.lastenterdatetime"/></th>
							<th><fmt:message key="users.table.status"/></th>
							<th class="center"><fmt:message key="users.table.balance"/></th>
						</tr>
						<c:forEach var="elem" items="${users}" varStatus="status">
 							<tr onclick="setChecked(this, ${elem.getId()});">
								<td><input type="radio" name="user" value="${elem.getId()}" id="user${elem.getId()}"></td>
								<td>${elem.getId()}</td>
								<td>${elem.getSurname()} ${elem.getName()} ${elem.getPatronymic()}</td>
								<td>${elem.getMobilePhone()}</td>
								<td>${elem.getEmail()}</td>
								<td>${elem.getLogin()}</td>
								<td><fmt:formatDate type = "both" value = "${elem.getLastEnterDateTime()}" /></td>
								<td>
									<c:choose>
									<c:when test="${elem.getStatus() == 0}">
										<fmt:message key="users.status.active"/>
									</c:when>
									<c:otherwise>
										<fmt:message key="users.status.blocked"/>
									</c:otherwise>
									</c:choose>
								
								</td>
								<td class="center ${elem.getBalance() < 0 ? 'red' : ''}">${elem.getBalance() == null ? '0.00' : elem.getBalance()}</td>
							</tr>
  						</c:forEach>
					</table>
				</div>
			</section>
		</main>
		<c:import url="..\common\fragment\footer.jsp" />
	</body>
</html>