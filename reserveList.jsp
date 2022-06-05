<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>ご予約一覧画面</title>
<link rel="stylesheet" type="text/css" href="css/ichirann_c.css"/>
<link rel="stylesheet" type="text/css" href="css/base2.css"/>
<link rel="stylesheet" type="text/css" href="css/button.css"/>
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
	<jsp:useBean id="reserve" class="model.Reserve" scope="request"/>
	<jsp:useBean id="userInfo" class="model.User" scope="session"/>
	<h2><jsp:getProperty name="userInfo" property="usrName"/>様　ご予約一覧</h2>
		<%if(request.getAttribute("msg")!=null){ %>
		<h2>メッセージ:<%= request.getAttribute("msg").toString() %></h2>
	<% } %>
	<br/>
	<div id="list">
	<table>
		<tr>
			<td id="head" width="50px">NO</td>
			<td id="head" width="250px">ご予約日時</td>
			<td id="head" width="50px">人数</td>
			<td id="head" width="300px">コース名</td>
			<td id="head" width="200px">テーブル名</td>
			<td id="head" width="300px">登録日時</td>
			<td colspan="2" id="head" width="50px"></td>
		</tr>
		<%
			ArrayList<Reserve> al = (ArrayList<Reserve>)(request.getAttribute("reserveList"));
			if(al == null){
				response.sendRedirect("ReserveListSvl");
				System.out.println("al.get() = NULL");
				return;
			}
			for(int i = 0; i < al.size(); i++){
				Reserve r = al.get(i);
		%>

		<tr>
			<td><%= r.getRsvId() %></td>
			<td><%= r.getRsvYy() %>年<%= r.getRsvMm() %>月<%= r.getRsvDd() %>日
							<%= r.getRsvHh() %>時<%= r.getRsvMi() %>分</td>
			<td><%= r.getPerson() %></td>
			<td><%= r.getCourseName() %></td>
			<td><%= r.getTableName() %></td>
			<td><%= r.getAppYy() %>年<%= r.getAppMm()+1 %>月<%= r.getAppDd() %>日
							<%= r.getAppHh() %>時<%= r.getAppMi() %>分</td>
			<form action="ReserveUpdateSvl" method="post">
				<td class="panel blue">
				<button>変更</button>
				<input type="hidden" name="rsvId" value="<%= r.getRsvId() %>"/></td>
			</form>
			<form action="ReserveDeleteSvl" method="post">
				<td class="panel pink">
				<button>取消</button>
				<input type="hidden" name="rsvId" value="<%= r.getRsvId() %>"/></td>
			</form>
		</tr>
		<% } %>
		<tr>
			<td colspan="10" id="boder" class="panel bw">
				<form action="ReserveInsertSvl" method="post">
					<button>新規ご予約</button>
				</form>
			</td>
		</tr>
	</table>
	<p id="boder"><a href="userIndex.jsp">会員様メニューに戻る</a></p>
	</div>
	</div>
</body>
</html>