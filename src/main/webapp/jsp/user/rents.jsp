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
		<main>
			<m:alertbox/>
			<section class="content p-b-15">
				<div class="row">
					<h2><fmt:message key="rents.main.h2"/></h2>
					<table id="rents">
						<tr>
							<th><fmt:message key="rents.table.rentid"/></th>
							<th><fmt:message key="rents.table.bicycle"/></th>
							<th><fmt:message key="rents.table.createdatetime"/></th>
							<th><fmt:message key="rents.table.finishdatetime"/></th>
							<th><fmt:message key="rents.table.rentalpoint"/></th>
							<th class="center"><fmt:message key="rents.table.amount"/></th>
						</tr>
						<c:forEach var="elem" items="${rents}" varStatus="status">
 							<tr>
								<td>${elem.getId()}</td>
								<td>${elem.getBicycle().getModel().getFirm()}  ${elem.getBicycle().getModel().getModel()}</td>
								<td><fmt:formatDate type = "both" value = "${elem.getCreateDateTime()}" /></td>
								<td>
									<c:choose>
									<c:when test="${elem.getFinishDateTime() == null}">
										<fmt:formatDate type = "both" value = "${elem.getPlanFinishDateTime()}" />
									</c:when>
									<c:otherwise>
										<fmt:formatDate type = "both" value = "${elem.getFinishDateTime()}" />
									</c:otherwise>
								</c:choose>								
								</td>
								<td>${elem.getBicycle().getPoint().getAddress()}</td>
								<td class="center">
								<c:choose>
									<c:when test="${elem.getFinishDateTime() == null}">
										<button onclick="returnBicycle(${elem.getId()})"><fmt:message key="rents.button.returnbike"/></button>
									</c:when>
									<c:otherwise>
										${elem.getAmount()}
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
  						</c:forEach>
					</table>
				</div>
			</section>
		</main>
		<c:import url="..\common\fragment\footer.jsp" />
	</body>
</html>