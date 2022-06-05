<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="controller.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/button.css"/>
<link rel="stylesheet" type="text/css" href="css/base2.css"/>
<link rel="preconnect" href="https://fonts.gstatic.com"/>
<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho:wght@500&display=swap" rel="stylesheet"/>
<title>予約取り消し</title>
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
	<jsp:useBean id="userInfo" class="model.User" scope="session" />
	<%String url = "";
	if(session.getAttribute("adminInfo") == null){
	url = "ReserveListSvl";%>
	<h2><jsp:getProperty  property="usrName"  name = "userInfo" />様ご予約取り消し</h2>
	<%}else{
	url = "ReserveAllSvl";%>
	<h2>管理者用予約一覧</h2>
	<%} %>
	<%if(request.getAttribute("msg")!=null){%>
		<div><p id = "border"><%= request.getAttribute("msg").toString() %></p></div>
	<%} %>
	<br/>
	<jsp:useBean id= "reserve" class ="model.Reserve" scope="request"/>
	<div id="delete">
	<table>
		<tr><th>予約番号</th><td><jsp:getProperty property="rsvId" name="reserve"/></td></tr>
		<tr><th>日付</th><td><%= reserve.getRsvYy()+"年"+reserve.getRsvMm()+"月"+reserve.getRsvDd()+"日" %></td></tr>
		<tr><th>時刻</th><td><%= reserve.getRsvHh()+"時"+reserve.getRsvMi()+"分" %></td></tr>
		<tr><th>人数</th><td><jsp:getProperty property="person" name="reserve"/>名</td></tr>
		<tr><th>コース</th><td><jsp:getProperty property="courseName" name="reserve"/></td></tr>
		<tr>
		<form action = "ReserveOperationSvl" method = "post">
			<td id="sub" colspan="2" class="panel pink">ご予約を取消します。確認後、取消ボタンを押してください。
				<button>取消</button>
				<input type="hidden" name= "mode" value="<%=ReserveOperationSvl.DELETE %>" />
				<input type="hidden" name = "rsvId" value = "<%=reserve.getRsvId()%>" />
			</td>
		</form>
		</tr>
	</table>
	<p id="boder"><a href = "<%=url%>">予約一覧に戻る</a></p>
	</div>
	</div>
</body>
</html>