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
<title>Menu Maintenance</title>
<link rel="stylesheet" type="text/css" href="./css/button.css"></link>
<link rel="stylesheet" type="text/css" href="./css/base_ad.css"></link>
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
<h1>メニューマスターメンテナンス</h1>
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
<jsp:useBean id = "menu" class = "java.util.ArrayList" scope = "request" />

<table width="95%" height="500px">
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
		<form name = "frm<%= i %>" action = "MenuMaintenanceSvl" method = "post">
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
				<th id = "code">ID</th>
				<th id = "menu">メニュー</th>
				<th id = "price">価格</th>
				<th id = "comm">コメント</th>
				<th id = "ord">オーダー可</th>
				<th id = "btn" colspan = "2"></th>
			</tr>
<%
		for(int i = 0; i < menu.size(); i++){
			Menu m = (Menu)menu.get(i);
%>
		<tr id = "data<%= i % 2 %>">
			<td id = "code"><%= m.getMenuId() %></td>
			<td id = "menu"><%= m.getMenuName() %></td>
			<td id = "price"><%= cf.format(m.getPrice()) %></td>
			<td id = "comm"><%= fmtNull(m.getDetail()) %></td>
			<td id = "ord"><%= order[m.getOrderFlg()] %></td>
			<form action = "MenuUpdateSvl" method = "post">
				<td id="sub" colspan="1" class="panel blue">
						<button onclick = "this.form.mode.value = '<%= MenuOperationSvl.UPDATE %>'" />更新</button>
				</td>
				<input type = "hidden" name = "mode" />
					<input type = "hidden" name = "menuId" value = "<%= m.getMenuId() %>" />
					<input type = "hidden" name = "typeId" value = "<%= typeID %>" />
			</form>
			<form action = "MenuDeleteSvl" method = "post">
				<td id="sub" colspan="1" class="panel pink">
						<button onclick = "this.form.mode.value = '<%= MenuOperationSvl.DELETE %>'" />削除</button>
				</td>
				<!-- 隠しフィールド -->
					<input type = "hidden" name = "mode" />
					<input type = "hidden" name = "menuId" value = "<%= m.getMenuId() %>" />
					<input type = "hidden" name = "typeId" value = "<%= typeID %>" />
			</form>
		</tr>
<%
		} %>
		<%String morc = "メニュー";
		if(typeID == 100)morc = "コース"; %>
		<tr>
			<form action = "MenuInsertSvl" method = "post">
				<th id="sub" colspan="7" class="panel bw">
					<button onclick="chackData()">新しい<%=morc %>の追加</button>
				</th>
				<input type = "hidden" name = "typeId" value = "<%= typeID %>" />
				<input type = "hidden" name = "mode" value = "<%= MenuOperationSvl.INSERT %>" />
			</form>
		</tr>
		</table><!-- インナーテーブル終了 -->
		</td>
	</tr>
</table>
<p id="menu"><a href="adminIndex.jsp"><font color="white">管理者メニューに戻る</font></a></p>
</div>
</body>
</html>