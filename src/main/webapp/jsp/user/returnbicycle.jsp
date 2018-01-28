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
		<section id="id03">
			<div class="content-single grey filter">			
				<form name="orderForm" id="orderForm" method="POST" action="controller">
				<div class="container">
					<input type="hidden" name="command" value="closerent"/> 
					
					<label for="rentid"><b><fmt:message key="returnbicycle.input.rentid"/></b></label>
					<input type="text" name="rentid" id="rentid" value="${rentid}" readonly />
					
					<label for="orderrentpoint"><b><fmt:message key="returnbicycle.input.rentalpoint"/></b></label>
					<input type="text" name="orderrentpoint" id="orderrentpoint" value="${orderRentPoint}" readonly />
					<label for="orderbikemodel"><b><fmt:message key="returnbicycle.input.model"/></b></label>
					<input type="text" name="orderbikemodel" id="orderbikemodel" value="${orderBicycleModel}" readonly />
					
				
					<label for="createdatetime"><b><fmt:message key="returnbicycle.input.createdatetime"/></b></label>
					<input type="text" name="createdatetime" id="createdatetime" value="<fmt:formatDate type = "both" value = "${createdatetime}" />" readonly />
					
					
					<label for="tariff"><b><fmt:message key="returnbicycle.input.tariff"/></b></label>
					<input type="text" name="tariff" id="tariff" value="${tariff.getDescription()} (${tariff.getPrice()} <fmt:message key="common.string.currency"/>)" readonly />
					
					<label for="amount"><b><fmt:message key="returnbicycle.input.amount"/></b></label>
					<input type="text" name="amount" id="amount" value="${amount}" readonly />
				</div>
				<div class="container center">
					<button type="submit" class="enterbtn"><fmt:message key="returnbicycle.button.return"/></button>
    				<button type="button" onclick="goBack()" class="cancelbtn"><fmt:message key="common.button.cancel"/></button>
    			</div>
			</form>
		</div>					
	</section>
</main>
<c:import url="..\common\fragment\footer.jsp" />
</body>