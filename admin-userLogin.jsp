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
		<title>お客様確認</title>

		<link rel="stylesheet" type="text/css" href="css/login.css"/>

		<script type="text/JavaScript" src="css/incScript.js"></script>
		<script type="text/JavaScript">
			<!--
				function dataCheck(obj){
					var regex = /[!"#$%&'()\*\+\-\.,\/:;<=>?@\[\\\]^_`{|}~]+/;
					var msgs = "";
					if (obj.usrName.value == "" || obj.usrName.value == null){
						msgs += "名前を入力してください。\n";
					}
					if(obj.usrName.value.match(regex)){
						msgs += "お名前には記号を含めないでください!\n"
					}
					if(!obj.usrPhone.value.match(/^[0-9]+/)){
						msgs += "電話番号は半角数字で入力してください!\n";
					}else if(obj.usrPhone.value.length < 10 || obj.usrPhone.value.length > 11){
						msgs += "無効な電話番号です!\n";
					}
					if(msgs == ""){
						return true;
					}else{
						alert(msgs);
						return false;
					}
				}
			 //-->
		</script>
	</head>
	<body>
		<h2>お客様確認</h2>
		<br />

		<%
		String msg = "";
		if(request.getAttribute("msg") != null){
		 msg = (String)request.getAttribute("msg");
		}
		%>
		<p><%=msg%></p>



	<form name="loginForm" action="AdminUserLoginSvl" method="post" onsubmit="return dataCheck(this);">
		<table>
				<tr>
					<th>お名前</th>
					<td>
						<input type="text" onkeyup="nameCheck(this.value)" name="usrName" size="10" maxlength="10" style="ime-mode: active;"/>
					</td>
				</tr>
				<tr>
				<th>お客様ID<br/></th>
					<td><input type="text" onkeyup="idCheck(this.value)" name="usrId" size="10" maxlength="10" style="ime-mode: active;"/>
					</td>
				</tr>
				<tr>
					<th>電話番号</th>
					<td>
						<input type="text" onkeyup="phoneCheck(this.value)" name="usrPhone" size="10" maxlength="11" style="ime-mode: active;"/>
					</td>
				</tr>
				<tr>
				<th>パスワード</th>
				<td><input type="password" onkeyup="psCheck(this.value)" name="password" size="8" maxlength="8" style="ime-mode: inactive;"/></td>
				</tr>
				<tr>

				<td id="sub" colspan="2">

				<input type="submit" value=" 送 信 " />
				<input type="hidden" name="msg"/>
				</td>
			</tr>

		</table>
	</form>
	<span id="phonecheck" style="color:red;font-size:12pt;">電話番号を入力してください。</span><br/>
		<span id="namecheck" style="color:red;font-size:12pt;">名前を入力してください。</span><br/>
	<br/>
			<p><a href="adminIndex.jsp">管理画面</a></p>


</body>
</html>