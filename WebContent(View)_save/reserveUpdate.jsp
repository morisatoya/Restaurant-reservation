<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.*" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>ご予約</title>
		<!-- CSS -->
		<link rel="stylesheet" type="text/css" href="css/button.css"/>
		<link rel="stylesheet" type="text/css" href="css/base2.css"/>
		<link rel="preconnect" href="https://fonts.gstatic.com"/>
		<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho:wght@500&display=swap" rel="stylesheet"/>
		<!-- javascript -->
		<script type="text/javascript">
			<!--
				function checkData(obj){
					var msg = "";
					var rsvYy = parseInt(obj.rsvYy.value);
					var rsvMm = parseInt(obj.rsvMm.value) - 1;
					var rsvDd = parseInt(obj.rsvDd.value);
					var rsvHh = parseInt(obj.rsvHh.value);
					var rsvMi = parseInt(obj.rsvMi.value);
					var rsvDay = new Date( rsvYy, rsvMm, rsvDd, rsvHh, rsvMi,0);
					var toDay = new Date();

					if( rsvDay.getTime() < toDay.getTime() ){
						msg += "予約日時が過去日又は無効な日付です。\n";
					}
					if((rsvDay.getTime() - toDay.getTime()) / 24 / 60 / 60 / 1000 >= 365){
						msg += "一年以上先の予約は承れません。\n";
					}
					if(msg.length > 0){
						alert(msg);
						return false;
					}else{
						return true;
					}
				}
			//-->
		</script>
	</head>
	<body>
	<%

		// 予約情報
			Reserve reserve = null;
			if(session.getAttribute("reserve") != null){
				reserve = (Reserve)session.getAttribute("reserve");
			}else if(request.getAttribute("reserve") != null){
				reserve = (Reserve)request.getAttribute("reserve");
			}
		// ログインチェック
		User user = new User();
		String url = "";
		String ms = "";
		if(session.getAttribute("adminInfo") != null){
			url = "ReserveAllSvl";
			user.setUsrId((Integer)request.getAttribute("usrId"));
		}else if(session.getAttribute("userInfo") != null){
			url = "ReserveListSvl";
			user = (User)session.getAttribute("userInfo");
		}else{
			response.sendRedirect("");
		}



		// 注文可能なコース一覧
		ArrayList<Course> courseList = null;
		if(session.getAttribute("courseList") != null){
			courseList = (ArrayList<Course>)session.getAttribute("courseList");
		}else{
			courseList = Course.getOneCourseList();
		}

		// システムメッセージ初期化
		String msg = "";
		if(request.getAttribute("msg") != null){
			msg = (String)request.getAttribute("msg");
		}

		// プルダウン用配列
		int thisYear = LocalDateTime.now().getYear();
		int thisMonth = LocalDateTime.now().getMonthValue();
		int thisDate = LocalDateTime.now().getDayOfMonth();
		int[] yearArray = {thisYear, thisYear+1};
		int[] monthArray = {1,2,3,4,5,6,7,8,9,10,11,12};
		int[] dateArray = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		int[] hourArray = {17,18,19,20,21};
		int[] minuteArray = {0,15,30,45};
		int[] personArray = {1,2,3,4,5,6};
	%>
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
	     <%if(session.getAttribute("adminInfo") != null){ %>
			<h2>管理者用予約変更</h2>
	     <%}else{ %>
		<h2><%= user.getUsrName() %>様 ご予約変更</h2>
		<%} %>
		<div><%= msg %></div>
		<br/>
		<form id="frm1" name="frm1" action="ReserveOperationSvl" onsubmit="return checkData(this)" method="post">
			<% if(reserve != null){ %>
			<div id="update">
			<table>
				<tr>
					<th>ご予約番号</th>
					<td><%= reserve.getRsvId() %></td>
				</tr>
				<tr>
					<th>日付</th>
					<td>
						<select id="rsvYy" name = "rsvYy">
								<% for(int y : yearArray){ %>
									<% if(y != reserve.getRsvYy()){ %>
										<option value="<%= y %>"><%= y %></option>
									<% }else{ %>
										<option value="<%= y %>"selected="selected"><%= y %></option>
									<% } %>
								<% } %>
						</select>年
						<select id="rsvMm" name="rsvMm">
							<% for(int m : monthArray){ %>
								<% if(m != reserve.getRsvMm()){ %>
									<option value="<%= m %>"><%= m %></option>
								<% }else{ %>
									<option value="<%= m %>"selected="selected"><%= m %></option>
								<% } %>
							<% } %>
						</select>月
						<select id="rsvDd" name="rsvDd">
							<% for(int d : dateArray){ %>
								<% if(d != reserve.getRsvDd()){ %>
									<option value="<%= d %>"><%= d %></option>
								<% }else{ %>
									<option value="<%= d %>" selected="selected"><%= d %></option>
								<% } %>
							<% } %>
						</select>日
					</td>
				</tr>
				<tr>
					<th>時刻</th>
					<td>
						<select id="rsvHh" name="rsvHh">
							<% for(int h : hourArray){ %>
								<% if(h != reserve.getRsvHh()){ %>
									<option value="<%= h %>"><%= h %></option>
								<% }else{ %>
									<option value="<%= h %>" selected="selected"><%= h %></option>
								<% } %>
							<% } %>
						</select>時
						<select id="rsvMi" name="rsvMi">
							<% for(int m : minuteArray){ %>
								<% if(m != reserve.getRsvMi()){ %>
									<option value="<%= m %>"><%= m %></option>
								<% }else{ %>
									<option value="<%= m %>" selected="selected"><%= m %></option>
								<% } %>
							<% } %>
						</select>分
					</td>
				</tr>
				<tr>
					<th>人数</th>
					<td>
						<select id="person" name="person">
							<% for(int p : personArray){ %>
								<% if(p != reserve.getPerson()){ %>
									<option value="<%= p %>"><%= p %></option>
								<% }else{ %>
									<option value="<%= p %>" selected="selected"><%= p %></option>
								<% } %>
							<% } %>
						</select>名
					</td>
				</tr>
				<tr>
					<th>コース</th>
					<td>
						<select id="courseId" name="courseId">
							<% for(Course c : courseList){ %>
								<% if(c.getCourseId() != reserve.getCourseId()){ %>
									<option value="<%= c.getCourseId() %>"><%= c.getCourseName() %></option>
								<% }else{ %>
									<option value="<%= c.getCourseId() %>" selected="selected"><%= c.getCourseName() %></option>
								<% } %>
							<% } %>
						</select>
					</td>
				</tr>
				<tr>
					<td id="sub" colspan="2" class="panel bw">空席を確認します。確認を押してください。
						<button onclick="chackData()">確認</button>
					</td>
				</tr>
				<input type="hidden" name="rsvId" value="<%= reserve.getRsvId() %>" />
				<input type="hidden" name="usrId" value="<%= user.getUsrId() %>" />
				<input type="hidden" name="mode" value="<%= ReserveOperationSvl.UPDATE %>"/>
			</table>
			<% } %>
		</form>
		<p id="boder"><a href="<%=url%>">予約一覧に戻る</a></p>
		</div>
		</div>
	</body>
</html>