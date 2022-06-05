<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>お客様情報登録完了</title>
	<link rel="stylesheet" type="text/css" href="css/completion.css"/>
</head>
<body>
	<jsp:useBean id="userInfo" class="model.User" scope="session"/>
	<h2>
		<jsp:getProperty name="userInfo" property="usrName"/>様のお客様IDは
		<jsp:getProperty name="userInfo" property="usrId"/>でございます。
	</h2><br/><br/>
	<h2>ログインの際、必要となりますので</h2>
	<br/>
	<h2>大切に保管なさってください。</h2>
	<br />
	<div id="boder"><a href="userIndex.jsp">会員様メニューに戻る</a></div>
</body>
</html>