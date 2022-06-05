<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>ご予約</title>
<link rel="stylesheet" type="text/css" href="css/completion.css"/>
</head>
<body>
	<jsp:useBean id="reserve" class="model.Reserve" scope="request"/>
	<jsp:useBean id="userInfo" class="model.User" scope="session"/>
	<h2><jsp:getProperty name="userInfo" property="usrName"/>
		様、ご予約が完了いたしました。</h2>
		<%if(request.getAttribute("msg")!=null){ %>
		<h2>メッセージ:<%= request.getAttribute("msg").toString() %></h2>
	<%} %>
	<br/>
	<h2><jsp:getProperty name="reserve" property="rsvYy"/>年
		<jsp:getProperty name="reserve" property="rsvMm"/>月
		<jsp:getProperty name="reserve" property="rsvDd"/>日
		<jsp:getProperty name="reserve" property="rsvHh"/>時
		<jsp:getProperty name="reserve" property="rsvMi"/>分より</h2>
	<br/>
	<h2><jsp:getProperty name="reserve" property="courseName"/>
		<jsp:getProperty name="reserve" property="person"/>名様</h2>
	<br/>
	<h2>ご予約番号は<jsp:getProperty name="reserve" property="rsvId"/>番です。</h2>
	<br/>
	<h2>ご来店の際、受付にお申し付けください。</h2>
	<br/>
	<p id="boder"><a href="ReserveListSvl">予約一覧に戻る</a></p>
</body>
</html>