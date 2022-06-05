<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="model.*" %>
<%@ page import="controller.*" %>
<%@ include file ="incFile.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>メニュー一覧</title>
<link rel="stylesheet" type="text/css" href="./css/base.css"></link>
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
<h1>メニュー一覧</h1>
<%
	String[] order = {"不可","可"};
	//通貨フォーマット
	NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("ja","JP"));
	int typeID;
	String style = "id = 'type1'";

	try{
		typeID = Integer.parseInt(request.getParameter("typeId"));// typeIDを数値に変換
	}catch(NumberFormatException e){
		typeID = 100;// 変換できなかった時100(コースメニュー)
	}
	if(request.getAttribute("msg")!=null){%>
	<p><%=request.getAttribute("msg").toString()  %></p>
<%} %>
<!--  Beanを生成 -->
<jsp:useBean id = "mType" class = "java.util.ArrayList" scope = "request" />
<jsp:useBean id = "menuList" class = "java.util.ArrayList" scope = "request" />
<jsp:useBean id = "courseList" class = "java.util.ArrayList" scope = "request" />
<jsp:useBean id = "menu" class = "java.util.ArrayList" scope = "request" />


<table width="95%">
	<tr>
		<td id = "outer">
<%
	//選択されている種別のスタイルを変更
	for(int i = 0; i < mType.size(); i++){
		MenuType mt = (MenuType)mType.get(i);
		if(typeID == mt.getTypeId()){
			style = " id = 'type2'";
		}else{
			style = " id = 'type1'";
		}
%>
		<form name = "frm<%= i %>" action = "ShowMenuSvl" method = "post">
			<input type = "hidden" name = "mode" value = "<%= mt.getTypeName() %>"/>
			<input type = "hidden" name = "typeId" value = "<%= mt.getTypeId() %>"/>
			<div style="width:150px;" <%= style %> onclick = "document.frm<%= i %>.submit();">
				<%= mt.getTypeName() %>
			</div>
		</form>
<%	} %>
		</td>
		<td id = "outer">

		<table><!-- インナーテーブル -->
			<caption> &lt;&lt;&lt;
<%				try{ %>
					<%= ((Menu)menu.get(0)).getTypeName() %>
<%				}catch(Exception e){ %>
					メニューがありません。
<%				} %>
				&gt;&gt;&gt;
			</caption>
			<tr>
				<th id = "menu">メニュー</th>
				<th id = "price">価格</th>
				<th id = "comm">詳細</th>

			</tr>
<%
		for(int i = 0; i < menu.size(); i++){
			Menu m = (Menu)menu.get(i);

%>		<%if(m.getTypeId() == 100){ %>

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
		for (int d = 0; d < cal.size(); d++) {

				if(c.getCourseId() != cid ){%>
				<tr>

				<td id="menu"><a href="courseMenu.jsp"><%= c.getCourseName() %></a></td>
				<td id="price"><%= cf.format(c.getPrice()) %></td>
				<td id="comm"><%= c.getDetail() %></td>

				</tr>
				<%
				cid = c.getCourseId();
				} %>




			<%c = cal.get(d);
			}%>


<%
break;} %>
		<tr id = "data<%= i % 2 %>">
			<td id = "menu"><%= m.getMenuName() %></td>
			<td id = "price"><%= cf.format(m.getPrice()) %></td>
			<td id = "comm"><%= fmtNull(m.getDetail()) %></td>


		</tr>
<%
		} %>


		</td>
	</tr>
</table>
</div>
		<%if(session.getAttribute("userInfo") == null){ %>
		<center><p><a href="home.jsp">前の画面に戻る</a></p></center>
		<%}else{ %>
		<center><p><a href="userIndex.jsp">前の画面に戻る</a></p></center>
		<%} %>
</body>
</html>