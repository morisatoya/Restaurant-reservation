<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="controller.UserOperationSvl" %>
<%@ include file="css/incFile.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>お客様情報変更</title>
<!-- CSS --> type="text/css" href="css/meisai.css"/>
<link rel="stylesheet" type="text/css" href="css/button.css"/>
<link rel="stylesheet" type="text/css" href="css/base2.css"></link>
<link rel="preconnect" href="https://fonts.gstatic.com"></link>
<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho:wght@500&display=swap" rel="stylesheet"></link>

<script type="text/JavaScript" src="css/userForm.js"></script>
		<!--
			！！！このJSPは外部のjavascriptファイルを参照しています。！！！
			！！！このJSPは外部のjavascriptファイルを参照しています。！！！
			！！！このJSPは外部のjavascriptファイルを参照しています。！！！
			！！！このJSPは外部のjavascriptファイルを参照しています。！！！
			！！！このJSPは外部のjavascriptファイルを参照しています。！！！
		 -->
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
	<h2>お客様情報変更</h2>
	<br/>
	<%
	User user = (User)request.getAttribute("user");
	%>
	<%
		String msg = "";
		if(request.getAttribute("msg") != null){
		 msg = (String)request.getAttribute("msg");
		}
	%>
		<div><%= msg %></div><br/>
		<form method="post" action="UserOperationSvl" onsubmit="return checkData(this);">
		<div id="user">
		<table id="user">
				<tr><th id="user">お客様ID</th><td><%=user.getUsrId()%></td></tr>
				<tr>
					<th id="user">お名前<font color="red" size="0.5"><br/>[必須]</font></th>
					<td><input type="text" id="nam" name="usrName" size="30" maxlength="30" style="ime-mode: active;" value="<%=user.getUsrName()%>"/></td>
				</tr>
				<tr>

					<th id="user">住 所</th>
					<td><input type="text" name="address" size="45" maxlength="200" style="ime-mode: active;" value="<%=user.getAddress()%>"/></td>
				</tr>
				<tr>

					<th id="user">電話番号<font color="red" size="0.5"><br/>[必須]</font></th>
					<td><input type="text" name="phone" size="15" maxlength="20" style="ime-mode: disabled;" value="<%=user.getPhone()%>"/></td>
				</tr>
				<tr>
					<th id="user">e-mail</th>
					<td><input type="text" name="mail" size="30" maxlength="100" style="ime-mode: disabled;" value="<%=user.getMail()%>"/></td>
				</tr>
				<tr>
					<th id="user">新規パスワード<font color="red" size="0.5"><br/>[必須]</font></th>
					<td><input type="password" name="password" size="8" maxlength="8" style="ime-mode: inactive;"/></td>
				</tr>

				<tr>
					<td id="sub" colspan="2" class="panel bw">空席を確認します。確認を押してください。
						<button onclick="chackData()">更新</button>
					</td>
				</tr>
			</table>
	<input type = "hidden" name = "mode" value = "<%=UserOperationSvl.UPDATE %>" />
	<input type="hidden"name="usrId" value="<%=user.getUsrId()%>"/>
	<input type="hidden" name="passmode" value="0"/>

	<input type="hidden" name="pastpass" value="<%=user.getPassword()%>"/>

	</form>
	<p id="boder"><a href="userIndex.jsp">会員様メニューに戻る</a></p>
	</div>
	</div>
</body>
</html>