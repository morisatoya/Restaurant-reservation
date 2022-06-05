<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="controller.*" %>
    <%@include file="css/incFile.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>お客様情報登録</title>
		<!-- CSS -->
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
		<h2>お客様情報登録</h2>
		<br/>
	<%
		String msg = "";
		if(request.getAttribute("msg") != null){
		 msg = (String)request.getAttribute("msg");
	%>
		<div><%= msg %></div><% } %><br/>
		<form action="UserOperationSvl" onsubmit="return checkData(this)" method="post">
			<div id="insert">
			<table>
				<tr>

					<th>お名前<font color="red" size="0.5"><br/>[必須]</font></th>
					<td><input type="text" name="usrName" size="30" maxlength="30" style="ime-mode: active;" /></td>
				</tr>
				<tr>

					<th>住 所</th>
					<td><input type="text" name="address" size="45" maxlength="200" style="ime-mode: active;" value=""/></td>
				</tr>
				<tr>

					<th>電話番号<font color="red" size="0.5"><br/>[必須]</font></th>
					<td><input type="text" name="phone" size="15" maxlength="20" style="ime-mode: disabled;" value=""/></td>
				</tr>
				<tr>

					<th>e-mail</th>
					<td><input type="text" name="mail" size="30" maxlength="100" style="ime-mode: disabled;" value=""/></td>
				</tr>
				<tr>

					<th>パスワード<font color="red" size="0.5"><br/>[必須]</font></th>
					<td><input type="password" name="password" size="8" maxlength="8" style="ime-mode: inactive;" value=""/></td>
				</tr>
				<input type = "hidden" name = "mode" value = "11" />
				<tr>

					<td id="sub" colspan="2" class="panel bw">
						<button onclick="chackData()">登録</button>
					</td>
				</tr>

			</table>
		</form>
		<p id="boder"><a href="home.jsp">ホームページに戻る</a></p>
		</div>
		</div>
	</body>
</html>