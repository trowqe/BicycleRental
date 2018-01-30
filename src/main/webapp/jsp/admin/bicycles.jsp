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
			<section class="content p-t-15">
				<div class="row">
					<div class="col-4 filter grey">
						<form name="filterbikes" id="filterbikes" method="POST" action="controller">
							<input type="hidden" name="command" value="filterbicycles" />
							<label for="rentalpoint"><b><fmt:message key="bicycles.select.rentalpoint"/></b></label>
							
							<select name="rentalpoint" id="rentalpoint">
								<option></option>
								<c:forEach var="elem" items="${rentalPoints}" varStatus="status">
 									<option value="${elem.getId()}" <c:if test="${elem.getId() == rentalpoint}">selected</c:if>>${elem.getAddress()}</option>
  								</c:forEach>
							</select>
							
							<label for="bicycletype"><b><fmt:message key="bicycles.select.bicycletype"/></b></label>
							<select name="bicycletype" id="bicycletype">
								<option></option>
								<c:forEach var="elem" items="${bicycleTypes}" varStatus="status">
 									<option value="${elem.getId()}" <c:if test="${elem.getId() == bicycletype}">selected</c:if>>${elem.getName()}</option>
  								</c:forEach>
							</select>
							
							<label for="firm"><b><fmt:message key="bicycles.input.firm"/></b></label> 
							<input type="text" name="firm" id="firm" value="${firm}" />
							
							<label for="model"><b><fmt:message key="bicycles.input.model"/></b></label> 
							<input type="text" name="model" id="model" value="${model}" />
							
							<div class="container center">
								<button type="submit" class="enterbtn"><fmt:message key="common.button.filter"/></button>
      							<button type="button" onclick="clearBicycleFilter();" class="cancelbtn"><fmt:message key="common.button.clear"/></button>
    						</div>
						</form>
					</div>
					
					<div class="col-8">
						<table id="freeBicycles">
						<tr>
							<th></th>
							<th><fmt:message key="bicycles.table.id"/></th>
							<th><fmt:message key="bicycles.table.firm"/></th>
							<th><fmt:message key="bicycles.table.model"/></th>
							<th><fmt:message key="bicycles.table.rentalpoint"/></th>
						</tr>
						<c:forEach var="elem" items="${bicycles}" varStatus="status">
 							<tr onclick="setCheckedBicycle(this, ${elem.getId()});">
								<td><input type="radio" name="bicycle" value="${elem.getId()}" id="bicycle${elem.getId()}"></td>
								<td>${elem.getId()}</td>
								<td>${elem.getModel().getFirm()}</td>
								<td>${elem.getModel().getModel()}</td>
								<td>${elem.getPoint().getAddress()}</td>
							</tr>
  						</c:forEach>
					</table>
					</div>
				</div>
			</section>
		</main>
		<c:import url="..\common\fragment\footer.jsp" />
	</body>
</html>