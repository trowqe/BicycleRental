<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="localization.data" />
<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><fmt:message key="common.head.title"/></title>
	<c:import url="../common/fragment/links.jsp" />
</head>
<body>
	<c:import url="fragment/header.jsp" />
	<main>
		<section id="id03">
			<div class="content-single grey filter">			
				<form name="orderForm" id="orderForm" method="POST" action="controller">
				<div class="container">
					<input type="hidden" name="command" value="createorder"/> 
					<input type="hidden" name="orderrentpointid" value="${orderRentPointId}" /> 
					<input type="hidden" name="orderbikeid" value="${orderBicycleId}"/> 

					<label for="orderrentpoint"><b><fmt:message key="prepareorder.input.rentalpoint"/></b></label>
					<input type="text" name="orderrentpoint" id="orderrentpoint" value="${orderRentPoint}" readonly />
					<label for="orderbikemodel"><b><fmt:message key="prepareorder.input.model"/></b></label>
					<input type="text" name="orderbikemodel" id="orderbikemodel" value="${orderBicycleModel}" readonly />
					
					<label for="tariff"><b><fmt:message key="prepareorder.select.tariff"/></b></label>
					<select name="tariff" id="tariff">
						<c:forEach var="elem" items="${tariffs}" varStatus="status">
 							<option value="${elem.getId()}" <c:if test="${elem.getId() == tariff}">selected</c:if>>${elem.getDescription()} (${elem.getPrice()} <fmt:message key="common.string.currency"/>)</option>
  						</c:forEach>
					</select>
				</div>
				<div class="container center">
					<button type="submit" class="enterbtn"><fmt:message key="prepareorder.button.order"/></button>
    				<button type="button" onclick="goBack()" class="cancelbtn"><fmt:message key="common.button.cancel"/></button>
    			</div>
			</form>
		</div>					
	</section>
</main>
<c:import url="../common/fragment/footer.jsp" />
</body>