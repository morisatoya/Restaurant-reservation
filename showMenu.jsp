<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*"%>
<%@ include file="incFile.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>メニュー紹介</title>
<link rel="stylesheet" type="text/css" href="css/showMenu.css" />
<script type="text/JavaScript" src="incScript.js"></script>
<script type="text/javascript">
<!--
	//1.メニュー名クリック時のコメント表示、
	function showDetail(id) {
		//1.引き渡されたメニュー番号の"style.display"が"block"の時、
		if (document.getElementById(id).style.display == 'block') {
			//1.引き渡されたメニュー番号の"style.display"を"none"に設定する。
			document.getElementById(id).style.display = 'none';
		}
		//2.引き渡されたメニュー番号の"style.display"が"block"以外の時、
		else {
			//1.引き渡されたメニュー番号の"style.display"を"block"に設定する。
			document.getElementById(id).style.display = 'block';
		}
	}
	//2.メニュー名クリック時のメニュー名のフォント変更、
	function change(id, val) {
		//1.引き渡された色区分(val)が"0"の時
		if (val == 0) {
			//1.引き渡されたメニュー番号のフォント色を"red"、フォントの太さを"bold"に設定する。
			document.getElementById(id).style.color = 'red';
			document.getElementById(id).style.fontWeight = 'bold';
		}
		//2.引き渡された色区分(val)が"0"以外の時
		else {
			//1.引き渡されたメニュー番号のフォント色を"DarkOliveGreen"、フォントの太さを"normal"に設定する。
			document.getElementById(id).style.color = 'DarkOliveGreen';
			document.getElementById(id).style.fontWeight = 'normal';
		}
	}
//-->
</script>
</head>
<body>
<jsp:useBean id="course" class="model.Course" scope="request" />
<jsp:useBean id="menu" class="model.Menu" scope="request" />
<% //通貨フォーマット
	NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("ja","JP"));
%>
<h1>メニュー紹介</h1>
<br/>
	<table cellspacing="0" >
	<tr>
		<td id="title" colspan="2">■コース料理<br /> <br />
		</td>
	</tr>
	<%
		ArrayList<Course> cal = (ArrayList<Course>) request.getAttribute("courseList");
		ArrayList<Menu> mal = (ArrayList<Menu>) request.getAttribute("menuList");

		if (cal == null) {
			response.sendRedirect("ShowMenuSvl");
			System.out.println("cal.get() = NULL");
			return;
		}

	%>

<%		Course c = cal.get(0);
		int cid = 0;
		for (int i = 0; i < cal.size(); i++) {

				if(c.getCourseId() != cid ){%>
				<tr>
				<td id="courseMenu"><h2><%= c.getCourseName() %></h2>
				<div id="courseDetail"><%= c.getDetail() %></div></td>
				<td id="coursePrice"><%= cf.format(c.getPrice()) %></td>
				</tr>
				<%
				cid = c.getCourseId();
				} %>


				<tr>
				<td id="subMenu"><div id="subMenu"><%= c.getMenuName() %></div></td>
				</tr>

			<%c = cal.get(i);
			}%>

	</table>
	<br />
	<br />
	<table>
		<tr>
			<td id="title" colspan="2">■一品料理</td>
		</tr>
	<%
		Menu m = mal.get(0);
		int cValue = 0;
		for(int j = 0; j < mal.size(); j++){
			m = mal.get(j);
			if(m.getTypeId() == 100){
				cValue++;
			}else{
				break;
			}
		}
			m = mal.get(0 + cValue);
			int b = 0;
		for (int i = 0 + cValue; i < mal.size(); i++) {
			m = mal.get(i);
	%>

			<%

			if(m.getTypeId() != b){ %>
			<tr id="type">
			<td colspan="2" id="type"><div id="type"><%= m.getTypeName() %></div></td>
			</tr>
			<%b = m.getTypeId();
			}%>


			<% if(m.getDetail() != null && !(m.getDetail().replace("　","").equals(""))){%>
				<tr>
				<td id="menu">
				<div class="menu" id="menu<%=i %>" onMouseOver="change('menu<%=i %>',0);"
				onMouseOut="change('menu<%=i %>',1);" onClick="showDetail('<%=i %>')">
				<%= m.getMenuName() %></div>
				<div class="exp" id="<%=i %>"><%= fmtNull(m.getDetail()) %></div>
				</td>
				<td id="price"><%= cf.format(m.getPrice())%></td>
				</tr>
			<%}else{%>
				<tr>
				<td id="menu">
				<div class="menu" ><%= m.getMenuName() %></div>
				</td>
				<td id="price"><%= cf.format(m.getPrice())%></td>
				</tr>
			<%} %>

			<% } %>
		</table>
		<br/>
		<%if(session.getAttribute("userInfo") == null){ %>
		<p><a href="home.jsp">前の画面に戻る</a></p>
		<%}else{ %>
		<p><a href="userIndex.jsp">前の画面に戻る</a></p>
		<%} %>
</body>
</html>