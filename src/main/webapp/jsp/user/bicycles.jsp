<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Прокат велосипедов</title>
		<c:import url="..\common\fragment\links.jsp" />
	</head>
	<body>
		<c:import url="fragment\header.jsp" />
		<main>
			<section class="content p-t-15">
				<div class="row">
					<div class="col-4 filter grey">
						<form name="filterbikes" id="filterbikes" method="POST" action="controller">
							<input type="hidden" name="command" value="filterbicycles" />
							<label for="rentalpoint"><b>Пункт проката: </b></label>
							
							<select name="rentalpoint" id="rentalpoint">
								<option></option>
								<c:forEach var="elem" items="${rentalPoints}" varStatus="status">
 									<option value="${elem.getId()}" <c:if test="${elem.getId() == rentalpoint}">selected</c:if>>${elem.getAddress()}</option>
  								</c:forEach>
							</select>
							
							<label for="bicycletype"><b>Тип велосипеда: </b></label>
							<select name="bicycletype" id="bicycletype">
								<option></option>
								<c:forEach var="elem" items="${bicycleTypes}" varStatus="status">
 									<option value="${elem.getId()}" <c:if test="${elem.getId() == bicycletype}">selected</c:if>>${elem.getName()}</option>
  								</c:forEach>
							</select>
							
							<label for="firm"><b>Марка:</b></label> 
							<input type="text" name="firm" id="firm" value="${firm}" />
							
							<label for="model"><b>Модель:</b></label> 
							<input type="text" name="model" id="model" value="${model}" />
							
							<div class="container center">
								<button type="submit" class="enterbtn">Фильтр</button>
      							<button type="button" onclick="clearBicycleFilter();" class="cancelbtn">Сброс</button>
    						</div>
						</form>
					</div>
					
					<div class="col-8">
						<c:forEach var="elem" items="${bicycles}" varStatus="status" step="2">
 							<div class="row">
								<div id="b-${elem.getId()}" class="col-6 bike">
									<img src="img/bikes/${elem.getModel().getId()}.jpg"/>
									<h3>${elem.getModel().getFirm()}  ${elem.getModel().getModel()}</h3>
									<p>${elem.getModel().getNotes()}</p>
									<button id="btn-${elem.getId()}" value="${elem.getModel().getFirm()} ${elem.getModel().getModel()}" type="button" onclick="createOrder(this);">Взять в прокат</button>
								</div>
								<c:if test="${bicycles[status.index+1] != null}">
									<div id="b-${bicycles[status.index+1].getId()}" class="col-6 bike">
										<img src="img/bikes/${bicycles[status.index+1].getModel().getId()}.jpg"/>
										<h3>${bicycles[status.index+1].getModel().getFirm()} ${bicycles[status.index+1].getModel().getModel()}</h3>
										<p>${bicycles[status.index+1].getModel().getNotes()}</p>
										<button id="btn-${bicycles[status.index+1].getId()}" value="${bicycles[status.index+1].getModel().getFirm()} ${bicycles[status.index+1].getModel().getModel()}" type="button" onclick="createOrder(this);">Взять в прокат</button>
									</div>
								</c:if>
							</div>
  						</c:forEach>
				
						
						<!--  <div class="row center p-t-15">
							<div class="pagination ">
  								<a href="#">&laquo;</a>
 								<a class="active" href="#">1</a>
  								<a href="#">2</a>
  								<a href="#">3</a>
  								<a href="#">&raquo;</a>
							</div>
						</div>-->
					</div>
				</div>
			</section>
		</main>
		<c:import url="..\common\fragment\footer.jsp" />
	</body>
</html>