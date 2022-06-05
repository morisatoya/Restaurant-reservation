<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.*" %>
    <%@ page import="controller.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Restaurante IDEALLE</title>

<link rel="stylesheet" type="text/css" href="css/index.css"/>
<script type="text/javaScript">
	<!--
		function dataCheck(obj){

	}
	//-->
</script>
</head>
<body>
<jsp:useBean id="userInfo" class="model.AdminUser" scope="session"/>

		<h2>■ Restaurante IDEALLE ■</h2>
		<br />
	<h2><jsp:getProperty name="userInfo" property="usrName"/>様</h2>
	<br />
	<%
		String msg = "";
		if(request.getAttribute("msg") != null){
			msg = (String)request.getAttribute("msg");
		}
	%>
	<div><%= msg %></div>
	<br/>
	<p><a href="ShowMenuSvl">●メニュー紹介</a></p>
	<br />
	<p><a href="ReserveListSvl">●ご予約</a></p>
	<br/>
	<p><a href="adminIndex.jsp">●管理画面に戻る</a></p>




</body>
</html>