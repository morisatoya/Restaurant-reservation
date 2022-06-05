<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Restaurante IDEALLE</title>
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="css/button.css"/>
	<link rel="stylesheet" type="text/css" href="css/base2.css"></link>
	<link rel="preconnect" href="https://fonts.gstatic.com"></link>
	<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho:wght@500&display=swap" rel="stylesheet"></link>
	</head>
	<body>
		<div class="main_imgBox">
		<div class="main_img" style="background-image: url('css/img/bg00.jpg')"></div>
		<div class="main_img" style="background-image: url('css/img/bg01.jpg')"></div>
		<div class="main_img" style="background-image: url('css/img/bg02.jpg')"></div>
		<div class="main_img" style="background-image: url('css/img/bg03.jpg')"></div>
		<div class="main_img" style="background-image: url('css/img/bg04.jpg')"></div>
		<div class="main_img" style="background-image: url('css/img/bg05.jpg')"></div>
		<div class="main_img" style="background-image: url('css/img/bg06.jpg')"></div>
		<div class="main_img" style="background-image: url('css/img/bg07.jpg')"></div>
		<div class="main_img" style="background-image: url('css/img/bg08.jpg')"></div>
		<div class="main_img" style="background-image: url('css/img/bg09.jpg')"></div>
		<div class="main_img" style="background-image: url('css/img/bg10.jpg')"></div>
		<div class="main_img" style="background-image: url('css/img/bg11.jpg')"></div>
	<h2>Restaurante IDEALLE</h2>
	<br/>
	<%
		String msg = "";
		if(request.getAttribute("msg") != null){
		 msg = (String)request.getAttribute("msg");
	%>
	</div>
		<div><%= msg %></div><% } %><br/>
		<div id="home">
		<p id="boder"><a href="ShowMenuSvl">●メニューを見る</a></p>
		<p id="boder"><a href="userLogin.jsp">●ログイン</a></p>
		<p id="boder"><a href="userInsert.jsp">●新規お客様登録</a></p>
		<br/>
		<br/>
		<br/>
		<p id="boder"><a href="adminLogin.jsp">●管理者ログイン</a></p>
	</div>
	</body>
</html>