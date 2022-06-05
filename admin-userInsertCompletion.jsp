<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>お客様情報登録完了</title>
	<link rel="stylesheet" type="text/css" href="css/completion.css"/>
</head>
<body>
	<jsp:useBean id="userInfo" class="model.AdminUser" scope="session"/>
	<h2>
		<jsp:getProperty name="userInfo" property="usrName"/>様のお客様IDは
		<jsp:getProperty name="userInfo" property="usrId"/>でございます。
	</h2>
	<h2>ログインの際、必要となりますので</h2>
	<br/>
	<h2>必ずお客様に伝えて下さい。</h2>
	<br />
	<div id="boder"><a href="admin-userIndex.jsp">戻る</a></div>
</body>
</html>