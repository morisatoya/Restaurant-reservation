<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="model.IdealException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>管理者ログイン</title>
		<!-- CSS -->
		<link rel="stylesheet" type="text/css" href="css/button.css"/>
		<link rel="stylesheet" type="text/css" href="css/base2_ad.css"></link>
		<link rel="preconnect" href="https://fonts.gstatic.com"></link>
		<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho:wght@500&display=swap" rel="stylesheet"></link>
		<script type="text/JavaScript" src="css/incScript.js"></script>
		<script type="text/JavaScript">
			<!--
				function dataCheck(obj){
					//1.名前、パスワードの未入力チェック
					var admName = obj.admName.value;

					if(obj.admName.value.length == 0 || obj.password.value.length == 0){
						//1.名前、パスワードが未入力の時、アラートを表示し、"false"をリターンする。
						var mse = "管理者名、パスワードは必ず入力してください。";
						alert(mse);
						return false;
					}

					}
				function idCheck(obj) {
					var admName = obj;
					if(admName == "" || admName == null){
						document.getElementById( "idcheck" ).innerHTML = "管理者名を入力してください。";
					}else{
						document.getElementById( "idcheck" ).innerHTML = "";
					}
				}
				function psCheck(obj) {
					var ps = obj;
					if(ps == '' || ps ==null){
						document.getElementById( "pscheck" ).innerHTML = "パスワードを入力してください。";
					}else{
						document.getElementById( "pscheck" ).innerHTML = "";

					}
				}

			//-->
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
		<h2>管理者ログイン</h2>
		<br />
<!-- 	20210430	DBから直接した管理者情報(パスワード非暗号化)のみログイン可能
					暗号化済の管理者情報でログインするには？ -->
		<%
		String msg = "";
		if(request.getAttribute("msg") != null){
		 msg = (String)request.getAttribute("msg");
		}
		%>
		<p><%=msg%></p>


	<form name="loginForm" action="AdminLoginSvl" method="post" onsubmit="return dataCheck(this);">
		<div id="home">
		<table>
				<tr>

				<th>管理者名</th>
					<td><input type="text" onkeyup="idCheck(this.value)" name="admName" size="10" maxlength="10" style="ime-mode: active;"/>
					<span id="check">　</span></td>
				</tr>
				<tr>

				<th>パスワード</th>
				<td><input type="password" onkeyup="psCheck(this.value)" name="password" size="8" maxlength="8" style="ime-mode: inactive;"/></td>
				</tr>

				<tr>

				<td id="sub" colspan="2" class="panel bw">
					<button>送信</button>
					<input type="hidden" name="msg"/>
				</td>
			</tr>

		</table>
	</form>
	<span id="idcheck" style="color:red;">管理者名を入力してください。</span><br/>
		<span id="pscheck" style="color:red;">パスワードを入力してください。</span><br/>
			<p id="boder"><a href="home.jsp">ホームページに戻る</a></p>
	</div>
	</div>
</body>
</html>