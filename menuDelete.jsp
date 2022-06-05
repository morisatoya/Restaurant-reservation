<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="controller.*" %>
<%@ page import="java.util.*" %>
<%@ include file="incFile.jsp" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>メニューの削除</title>
		<!-- CSS -->
		<link rel="stylesheet" type="text/css" href="css/button.css"/>
		<link rel="stylesheet" type="text/css" href="css/base2_ad.css"></link>
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
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<jsp:useBean id="oneMenu" class="model.Menu" scope="request"/>
	<%
		//通貨フォーマット
		NumberFormat cf = NumberFormat.getCurrencyInstance(new Locale("ja","JP"));
		int typeID = oneMenu.getTypeId();
	%>
		<div id="delete">
		<table>
			<h2>メニューの削除</h2>
			<form id="frm1"  action="MenuOperationSvl" method="post">
				<tbody>
					<tr>
						<th>メニュー名</th>
						<td>
							<jsp:getProperty name="oneMenu" property="menuName"/>
						</td>
					</tr>
					<tr>
						<th>価格</th>
						<td>
							<%= cf.format(oneMenu.getPrice()) %>
						</td>
					</tr>
					<tr>
						<th>オーダー可</th>
						<td>
							<!-- orderFrg:1==>可、0==>不可) -->
							<%	if(oneMenu.getOrderFlg() == 1) { %>
									オーダー可
							<%	} else { %>
									オーダー不可
							<%	} %>
						</td>
					</tr>
					<tr>
						<th>分類</th>
						<td>
							<jsp:getProperty name="oneMenu" property="typeName"/>
						</td>
					</tr>
					<tr>
						<th>コメント</th>
						<td>
							<%= fmtNull(oneMenu.getDetail()) %>
						</td>
					</tr>
						<input type="hidden" name="typeId" value='<%=oneMenu.getTypeId() %>' />
						<input type="hidden" name="menuId" value='<%=oneMenu.getMenuId() %>' />
						<input type="hidden" name="mode" value="<%= MenuOperationSvl.DELETE %>" />
						<input type="hidden" name="price" value="<%= oneMenu.getPrice()%>"/>
						<input type="hidden" name="orderFlg" value="<%= oneMenu.getOrderFlg()%>"/>
						<input type="hidden" name="menuName" value="<%=oneMenu.getMenuName() %>"/>
						<input type="hidden" name="detail" value="<%= oneMenu.getDetail()%>"/>
					<tr>
						<td id="sub" colspan="2" class="panel orange">
							<button>削除</button>
						</td>
					</tr>
				</tbody>
			</form>
		</table>
		<p id="boder" align="center">
			<a href="javascript:history.back();">一覧表示画面へ戻る</a>
		</p>
		</div>
		</div>
	</body>
</html>