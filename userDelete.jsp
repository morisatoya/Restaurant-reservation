<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="css/incFile.jsp" %>
<%@ page import="model.*" %>
<%@ page import="controller.UserOperationSvl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>お客様脱会手続き</title>
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
<%
User userInfo = (User)session.getAttribute("userInfo");
%>
<h2>お客様脱会手続き</h2>
<br/>
	<%
		String msg = "";
		if(request.getAttribute("msg") != null){
			msg = (String)request.getAttribute("msg");
		}
	%>
	<p><%= msg %></p>
	<br/>
<form action="UserOperationSvl" method="post">
	<div id="delete">
	<table>
		<tr><th>お客様ID</th><td><%=userInfo.getUsrId()%></td></tr>
		<tr><th>お名前</th><td><%=userInfo.getUsrName()%></td></tr>
		<tr><th>住所</th><td><%=userInfo.getAddress()%></td></tr>
		<tr><th>電話番号</th><td><%=userInfo.getPhone()%></td></tr>
		<tr><th>e-mail</th><td><%=userInfo.getMail()%></td></tr>
		<tr><td id="sub" colspan="2" class="panel pink">確認し、脱会ボタンをクリックしてください。
				<button onclick="chackData()">脱会</button>
			</td>
		</table>
		<input type="hidden" name="mode" value="<%=UserOperationSvl.DELETE %>"/>
		<input type="hidden" name="usrId" value="<%=userInfo.getUsrId() %>"/>
	</form>
	<a href="userIndex.jsp">会員様メニューに戻る</a>
	</div>
	</div>
</body>
</html>