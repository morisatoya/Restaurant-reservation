<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="controller.*" %>
<%@ page import="model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>menuInsert</title>
		<link rel="stylesheet" type="text/css" href="./css/base.css"></link>
		<link rel="preconnect" href="https://fonts.gstatic.com"></link>
		<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho:wght@500&display=swap" rel="stylesheet"></link>
<style type="text/css">
	<!--
		table{text-align:center; margin:auto;background-color:#f0f8ff;}
	-->
</style>
	<script type="text/javascript">
			<!--
				function dataCheck(obj){
					var msg = "";
					if(obj.menuName.value.length < 1){
						msg += "メニュー名を入力してください。\n";
					}
					if(!obj.price.value.match(/^[0-9]+$/g)){
						msg += "価格を入力してください。\n";
					}
					var i ;
					for(i = 0; i < obj.orderFlg.length; i++){
						if(obj.orderFlg[i].checked){
							break;
						}
					}
					if(i >= obj.orderFlg.length){
							msg += "オーダーの可否を選んでください。\n";
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
		//request.setCharacterEncoding("UTF-8");
		int typeID;
		try{
			typeID = Integer.parseInt(request.getParameter("typeId"));
		}catch(NumberFormatException e){
			typeID = 100;
		}
	%>
	<jsp:useBean id = "mType" class = "java.util.ArrayList" scope = "request" />
	<jsp:useBean id = "menu" class = "java.util.ArrayList" scope = "request" />

	<table border>
		<h1>新しいメニューを追加</h1>
			<form action="MenuOperationSvl" method="post" onsubmit="return dataCheck(this);">
				<tr>
					<th>メニュー名</th>
						<td>
							<input type="text" name="menuName" size="30" />
						</td>
				</tr>
				<tr>
					<th>価格</th>
						<td>
							<input type="text" name="price" size="6" />
						</td>
				</tr>
				<tr>
					<th>オーダー可</th>
					<td>
						<input onChange="checkValue()" class="check" type="radio" name="orderFlg" id="button" value="1" />可
						<input onChange="checkValue()" class="check" type="radio" name="orderFlg" id="button" value="0" />不可
						<script type="text/javascript">
							/*const radioButtons = document.getElementsByClassName("check");
							const button = document.getElementById("button");

							function checkValue() {
 						 		const checkArray = [];

  								for (radioButton of radioButtons) {
   									 checkArray.push(radioButton.checked);
 								}

 								 button.disabled = !checkArray.some(value => value == true);
							}

							function unCheckButton() {
 	 							for (radioButton of radioButtons) {
    								radioButton.checked = false;
 	 							}

  							checkValue();
							}*/
						</script>
					</td>
				</tr>
				<tr>
					<th>分類</th>
						<td>
							<select name="typeId">
								<%
									for(Object o : mType){
										MenuType mt = (MenuType)o;
										String selected = "";
										if(typeID == mt.getTypeId()){
											selected = "selected";
										}else{
											selected = "";
										}
								%>
								<option value="<%= ((MenuType)o).getTypeId() %>"<%= selected %>>
											   <%= ((MenuType)o).getTypeName() %>
								</option>
								<% } %>
							</select>
						</td>
				</tr>
				<tr>
					<th>コメント</th>
					<td>
						<textarea name="detail" cols="30" rows="4" ></textarea>
					</td>
				</tr>
					<input type="hidden" name="mode" value="<%= MenuOperationSvl.INSERT %>" />
				<tr>
					<th colspan="2" id="bottom"><input type="submit" value="メニューを追加" /></th>
				</tr>
			</form>
	</table>
		<p align="center">
			<a href="javascript:history.back();">一覧表示画面へ戻る</a>
		</p>
	</div>
</body>
</html>