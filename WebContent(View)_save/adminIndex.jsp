<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Restaurante IDEALLE</title>
		<!-- CSS -->
		<link rel="stylesheet" type="text/css" href="css/button.css"/>
		<link rel="stylesheet" type="text/css" href="css/base2_ad.css"></link>
		<link rel="preconnect" href="https://fonts.gstatic.com"></link>
		<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho:wght@500&display=swap" rel="stylesheet"></link>
</script>
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
		<jsp:useBean id="adminInfo" class="model.Admin" scope="session"/>
		<h2>Restaurante IDEALLE</h2>
		<br/>
		<%if(request.getAttribute("msg")!=null){ %>
		<h2>メッセージ:<%= request.getAttribute("msg").toString() %></h2>
		<% } %>
		<br/>
		<br/>
		<br/>
		<br/>
		<div id="home">
		<p id="boder"><a href="reserveAll.jsp">●管理用予約一覧</a></p>
		<%
			session = request.getSession(false);
			if(session != null){
		%>
		<p id="boder"><a href="MenuMaintenanceSvl">●メニューメンテナンス</a></p>
		<% } else { %>
		<p id="boder"><a href="adminLogin.jsp">●メニューメンテナンス</a></p>
		<% } %>
		<br/>
		<p id="boder"><a href="AdminLogoffSvl">●ログオフ</a></p>
		</div>
		</div>
	</body>
</html>