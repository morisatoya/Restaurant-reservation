<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page import="controller.*" %>
<%@ include file="incFile.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>メニューの更新</title>
		<!-- CSS -->
		<link rel="stylesheet" type="text/css" href="css/button.css"/>
		<link rel="stylesheet" type="text/css" href="css/base2_ad.css"></link>
		<link rel="preconnect" href="https://fonts.gstatic.com"></link>
		<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho:wght@500&display=swap" rel="stylesheet"></link>
		<script type="text/javascript">
		<!--
		function dataCheck(obj) {
			var msg = "";
			if (obj.menuName.value.length < 1) {
				msg += "メニュー名を入力してください。\n";
			}
			if (!obj.price.value.match(/^[0-9]+$/g)) {
				msg += "価格を数値で入力してください。\n";
			}
			var i;
			for (i = 0; i<obj.orderFlg.length; i++) {
				if (obj.orderFlg[i].checked)
					break;
			}
			if (i >= obj.orderFlg.length) {
				msg += "オーダーの可否をチェックしてください\n";
			}

			if (msg.length > 0) {
				alert(msg);
				return false;
			} else {
				return true;
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
		<%
		request.setCharacterEncoding("UTF-8");
		int typeID=Integer.parseInt(request.getParameter("typeId"));
		%>
		<jsp:useBean id="oneMenu" class="model.Menu" scope="request" />
		<jsp:useBean id="mType" class="java.util.ArrayList" scope="request" />

		<div id="update">
		<table border="0">

		<h2>メニューの更新</h2>
		<form action="MenuOperationSvl" method="post" onsubmit="return dataCheck(this)" >
		<tr>
			<th>メニュー名</th>
			<td>
			<input type="text" name="menuName" size="30"
			value="<%= oneMenu.getMenuName() %>"/>
			</td>
		</tr>

		<tr>
			<th>価格</th>
			<td>
			<input type="text"size ="10" name="price"
			value='<%=oneMenu.getPrice() %>'/>
			</td>
		</tr>

		<tr>
			<th>オーダー可</th>
		<%
			String[]order={"可","不可"};
		%>
			<td>
	<%for(int i=1;i>=0;i--){
					if(i==oneMenu.getOrderFlg()){%>
						<input type="radio" name="orderFlg" checked="checked" value="<%=i %>"/>
					<%}else{%>
						<input type="radio" name="orderFlg"  value="<%=i %>"/>
					<%}
					if(i==1){%>
						可
					<%}else{ %>
						不可
					<%}
				}%>
			</td>
		</tr>

		<tr>
			<th>分類</th>

		<td>
		<select name="typeId">
	<%
		if(typeID != 100){
			for (Object o : mType) {

				MenuType mt = (MenuType) o;
				if(mt.getTypeId()==100){
					continue;
				}

					if (typeID == mt.getTypeId()) {
	%>
						<option value="<%=mt.getTypeId()%>" selected="selected">
						<%=((MenuType) o).getTypeName()%></option>
	<%
					} else {
	%>
						<option value="<%=mt.getTypeId()%>">
						<%=((MenuType) o).getTypeName()%></option>
	<%
					}
		}
		}else{  %>
			<option value="<%=((MenuType) mType.get(0)).getTypeId()%>" selected="selected">
			<%=((MenuType) mType.get(0)).getTypeName()%></option>
<%
		}%>
		</select>
		</td>
		</tr>

		<tr>
				<th>コメント</th>
		<td>

		<%if(oneMenu.getDetail() == null){%>
		<textarea name="detail"cols="30" rows="4"></textarea>
		<%}else{%>
		<textarea name="detail"cols="30" rows="4"><%=oneMenu.getDetail() %></textarea>
		<%}%>



		</td>
		</tr>
			<input type="hidden" name="mode" value="<%= MenuOperationSvl.UPDATE %>"/>
			<input type="hidden" name="menuId" value='<%= oneMenu.getMenuId()%>'/>
			<input type="hidden" name="typeId" value='<%= oneMenu.getTypeId()%>'/>

		<tr>
			<td id="sub" colspan="2" class="panel bw">
			<button>更新</button>
		</tr>
		</form>
		</table>
		<p id="boder" align="center">
			<a href="javascript:history.back();">一覧表示画面へ戻る</a>
		</p>
</div>
</div>
</body>
</html>