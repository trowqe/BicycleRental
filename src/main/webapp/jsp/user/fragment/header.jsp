<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="localization.data" />
<!DOCTYPE html>
<header class="topheader">
	<nav class="content">
		<ul class="topnav">
			<li>
				<a href="#" class="logo">
					<img src="img/bike.png" height="70" class="logo"/>
				</a>
			</li>
			<li><a href="controller?command=filterbicycles" class="${sessionScope.page.equals('bicycles') ? 'active' : ''}"><fmt:message key="user.header.bicycles"/></a></li>
			<li><a href="controller?command=rents" class="${sessionScope.page.equals('rents') ? 'active' : ''}"><fmt:message key="user.header.rents"/></a></li>
			<!-- <li><a href="controller?command=rentalpoints"><fmt:message key="user.header.rentalpoints"/></a></li>
			<li><a href="controller?command=prices"><fmt:message key="user.header.prices"/></a></li>-->	
			<li class="right dropdown">
				<a href="javascript:void(0)" class="dropbtn">
					<span class="user-name">${sessionScope.user.getName()}</span> 
					<span class="user-icon">
						<i class="fa fa-user-circle-o fa-2x" aria-hidden="true"></i>
						<i class="fa fa-sort-desc" aria-hidden="true" style="font-size: 10px"></i>
					</span>
				</a>
				<div class="dropdown-content"> 
					<c:set var="userBalance" scope="page" value="${sessionScope.user.getBalance()}"/>
					<a class="info-li divider" href="javascript:void(0)"><fmt:message key="user.header.balance"/> <b class="${userBalance < 0 ? 'red' : ''}">${userBalance == null ? '0.00' : userBalance} <fmt:message key="user.header.currency"/></b></a>
					<a href="javascript:logout()"><fmt:message key="common.button.exit"/></a>
				</div>	
			</li>									
		</ul>
	</nav>
</header>