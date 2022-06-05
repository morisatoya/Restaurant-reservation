<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Restaurante IDEALLE</title>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
	</head>
	<body>
	<jsp:useBean id="adminInfo" class="model.Admin" scope="session"/>
		<h2>Restaurante IDEALLE 管理画面</h2>
		<br/>

		<%if(request.getAttribute("msg")!=null){ %>
		<h2>メッセージ:<%= request.getAttribute("msg").toString() %></h2>
		<% } %>
		<br />
		<p><a href="admin-userInsert.jsp">●新規ご予約</a></p>
		<p><a href="admin-userLogin.jsp">●予約の変更・キャンセル</a></p>
		<p><a href="MenuMaintenanceSvl">●メニューメンテナンス</a></p>
		<p><a href="AdminLogoffSvl">●ログオフ</a></p>
	</body>
</html>