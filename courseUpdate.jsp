<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="controller.CourseOperationSvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="css/incFile.jsp"%>
<%@ page import="model.Course"%>
<%@ page import="model.Admin"%>
<%@ page import="model.Menu"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="model.MenuType"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>コース情報変更画面</title>
		<link rel="stylesheet" type="text/css" href="./css/button.css"></link>
		<link rel="stylesheet" type="text/css" href="./css/base2_ad.css"></link>
		<link rel="preconnect" href="https://fonts.gstatic.com"></link>
		<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho:wght@500&display=swap" rel="stylesheet"></link>
<script type="text/javascript">
			<!--
			function dataCheck(obj,val){
				var msg ="";
				var all = Number(0);
				if (obj.courseName.value == "" || obj.courseName.value == null){
					msg += "コース名を入力してください。\n";
				}
				if (obj.price.value == "" || obj.price.value == null){
					msg += "価格を設定してください。\n";
				}else if(!obj.price.value.match(/^[0-9]+$/g)){
					msg += "価格は数字で入力さしてください。\n";
				}
				var i;
				for(i = 0 ; i < obj.orderFlg.length; i++){
					if(obj.orderFlg[i].checked){
						break;
					}
				}
				if(i >= obj.orderFlg.length){
					msg += "オーダーの可否をチェックしてください。\n";
				}

				for(var p = 0; p < val;p++){
					var str = "err" + p;
					var element = document.getElementById(str);
					var elements = element.options;
					all += Number(element.value);

				}
				if(all == 0){
					msg += "コースに登録するメニューを選んでください。\n";
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
	<h2>コースの更新</h2>
	<%
		boolean test = false;
		Admin admin = (Admin) session.getAttribute("adminInfo");

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		if (admin == null && !test) {
			response.sendRedirect("home.jsp");
			return;
		}
		String[] orderFlgs = { "オーダー不可", "オーダー可" };
		String[] names={"appetizerId","soupId","pastaId","meatId","fishId","dessertId"};
		ArrayList<Course> cList = (ArrayList<Course>) request.getAttribute("oneCourse");
		ArrayList<ArrayList<Menu>> menuList = (ArrayList<ArrayList<Menu>>)request.getAttribute("typeMenuList");
		ArrayList<MenuType> mtList = MenuType.getAllType();
		if(test){
			cList = Course.getOneCourse(1); //とりあえずAコース取得
			//テスト中の場合とりあえずmenuList作成 中身は適当
			menuList = new ArrayList<>();
			for(MenuType mt:mtList){
				if(mt.getTypeId()!=100){
					menuList.add(Menu.getMenu(mt.getTypeId()));
				}
			}
		}

		Course course = null;
		if(cList!=null&&!cList.isEmpty()){
			//コースリストに中身があった場合はcourseに先頭の要素を入れる
			course = cList.get(0);
		}
		//各入力フィールドの初期値を設定
		int courseId = (course==null) ? 0 : course.getCourseId();
		String courseName = (course == null) ? "" : course.getCourseName();
		String price = (course == null) ? "" : String.valueOf(course.getPrice());
		int orderFlg = (course == null) ? 0 : course.getOrderFlg();
		String detail = (course == null) ? "" : course.getDetail();

		Map<Integer,Course> map = new HashMap<Integer,Course>();//typeIdをキーにコースを入れる

		System.out.println(cList);

		if(cList !=null){
			for(Course c:cList){
				map.put(c.getTypeId(),c);
			}
		}

		ArrayList<MenuType> types = MenuType.getAllType();
		if (request.getAttribute("msg") != null) {
	%>
	<p><%=request.getAttribute("msg").toString()%></p>
	<%
		}
	%>

	<form action="CourseOperationSvl" method="post" onsubmit="return dataCheck(this,<%=names.length%>)">
		<div id="update">
		<table>
			<tr><th>コース名</th><td><input type="text" name="courseName" size="30"value="<%=courseName%>" /></td></tr>
			<tr><th>価格</th><td><input type="text" name="price" size="6"value="<%=price%>" /></td></tr>
			<tr><th>オーダー可</th><td>
				<%for(int i=1;i>=0;i--){
					if(i==orderFlg){%>
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
			</td></tr>
			<tr><th>コメント</th><td><textarea name = "detail" cols="30" rows ="4"  ><%=detail %></textarea></td></tr>
			<%
			int count = 1;
			for (MenuType mt : mtList) {
				//すべてのMenuTypeに関してプルダウンメニュー作成
				if(mt.getTypeId()==100){
					//コースはいらないので飛ばす
					continue;
				}
				if(count > names.length){
					//とりあえずデザートまで(仕様書通り)
					break;
				}
			%>	<tr><th><%=mt.getTypeName() %></th><td>
				<select id="err<%=count -1 %>" name="<%= names[count - 1] %>">
				<option value="0">なし</option>
				<%
				for(Menu menu:menuList.get(count)){
					//typeIdに対応したmenuListから全てのmenuを取り出す
					if(map.get(menu.getTypeId())!=null && map.get(menu.getTypeId()).getMenuId()==menu.getMenuId()){
						//typeIdに対応するオブジェクトがあればそれが初期値になる
				%>
						<option value="<%=menu.getMenuId() %>" selected="selected"><%= menu.getMenuName() %></option>
				<%	}else{ %>
						<option value="<%=menu.getMenuId() %>"><%=menu.getMenuName() %></option>
			<%		}
				}
				count++;
			%>
				</select>
				</td></tr><%
			}%>
			<tr>
				<td id="sub" colspan="2" class="panel bw">
					<button>更新</button>
				</td>
			</tr>
			<input type="hidden" name="mode" value="<%=CourseOperationSvl.UPDATE %>" />
			<input type="hidden" name="courseId" value="<%=courseId %>" />
			<input type="hidden" name="mode" value="100" />
		</table>
	</form>
	<p id="boder"><a href="MenuMaintenanceSvl">一覧画面に戻る</a></p>
	</div>
	</div>
</body>
</html>