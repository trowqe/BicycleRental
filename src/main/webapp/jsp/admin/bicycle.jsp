<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="localization.data" />
<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><fmt:message key="common.head.title"/></title>
	<c:import url="..\common\fragment\links.jsp" />
</head>
<body>
	<c:import url="fragment\header.jsp" />
	<main>
		<section>
			<div class="content-single grey filter">			
				<form name="bicycleForm" id="bicycleForm" method="POST" action="controller">
				<div class="container">
					<input type="hidden" name="command" value="${command}"/> 
					<input type="hidden" name="bicycleid" value="${bicycle.getId()}"/> 
						
					<label for="rentalpoint"><b><fmt:message key="bicycles.select.rentalpoint"/></b></label>
					<select name="rentalpoint" id="rentalpoint">
						<option></option>
						<c:forEach var="elem" items="${rentalPoints}" varStatus="status">
 							<option value="${elem.getId()}" <c:if test="${elem.getId() == bicycle.getPoint().getId()}">selected</c:if>>${elem.getAddress()}</option>
  						</c:forEach>
					</select>
						
					<label for="bicycletype"><b><fmt:message key="bicycles.select.bicyclemodel"/></b></label>
					<select name="bicyclemodel" id="bicyclemodel">
						<option></option>
						<c:forEach var="elem" items="${bicycleModels}" varStatus="status">
 							<option value="${elem.getId()}" <c:if test="${elem.getId() == bicycle.getModel().getId()}">selected</c:if>>${elem.getFirm()} ${elem.getModel()}</option>
  						</c:forEach>
					</select>						
				</div>
				<div class="container center">
					<button type="submit" class="enterbtn"><fmt:message key="common.button.save"/></button>
    				<button type="button" onclick="goBack()" class="cancelbtn"><fmt:message key="common.button.cancel"/></button>
    			</div>
			</form>
		</div>					
	</section>
</main>
<c:import url="..\common\fragment\footer.jsp" />
</body>