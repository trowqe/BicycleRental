<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header class="topheader">
	<nav class="content">
		<ul class="topnav">
			<li>
				<a href="#" class="logo">
					<img src="img/bike.png" width="70" height="70" class="logo"/>
				</a>
			</li>
			<li><a href="controller?command=filterbicycles" class="active">Велосипеды</a></li>
			<li><a href="controller?command=rentalpoints">Пункты проката</a></li>
			<li><a href="controller?command=prices">Цены</a></li>	
			<li class="right"><button type="button" onclick="logout()" class="loginbtn">Выйти</button></li>					
		</ul>
	</nav>
</header>