<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="css/incFile.jsp" %>
<%@ page import="model.*"  %>
<%@ page import="controller.*" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>コース削除画面</title>
	<link rel="stylesheet" type="text/css" href="./css/button.css"></link>
	<link rel="stylesheet" type="text/css" href="./css/base2_ad.css"></link>
	<link rel="preconnect" href="https://fonts.gstatic.com"></link>
	<link href="https://fonts.googleapis.com/css2?family=Shippori+Mincho:wght@500&display=swap" rel="stylesheet"></link>
<script type="text/javascript">
	<!--
		function msgsChk(obj){
			var result = window.confirm("削除してもよろしいですか？");
			if(result){
				return true;
			}else{
				return false;
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
		<h2>コース削除</h2>
		<div>
		<%
			if (request.getAttribute("msg") != null) {
		%>
			<p><%=request.getAttribute("msg").toString()%></p>
		<% } %>
		</div>
		<div id="delete">	<table>
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
			//int typeId = (course == null) ? 0 : course.getTypeId();

			Map<Integer,Course> map = new HashMap<Integer,Course>();//typeIdをキーにコースを入れる

			System.out.println(cList);

			if(cList !=null){
				for(Course c:cList){
					map.put(c.getTypeId(),c);
				}
			}
			ArrayList<MenuType> types = MenuType.getAllType();
		%>
		<form name="check" action="CourseOperationSvl" method="post" onsubmit="return msgsChk(this)">
			<table>
				<tr><th>コース名</th><td><text><%=courseName%></text></td></tr>
				<tr><th>価格</th><td><text>\<%=price%></text></td></tr>
				<tr><th>オーダー可</th>
					<td>
						<%
							int i = 1 ;
							if(i==orderFlg){
						%>		可
						<% 	}else{ %>
								不可
						<% } %>
					</td>
				</tr>
				<tr><th>コメント</th><td><text><%= detail %></text></td></tr>
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
				%>
				<tr><th><%=mt.getTypeName() %></th><td>
					<%
						for(Menu menu:menuList.get(count)){
							//typeIdに対応したmenuListから全てのmenuを取り出す
							if(map.get(menu.getTypeId())!=null && map.get(menu.getTypeId()).getMenuId()==menu.getMenuId()){
								//typeIdに対応するオブジェクトがあればそれが初期値になる
					%>
						<option value="<%=menu.getMenuId() %>" selected="selected"><%= menu.getMenuName() %></option>
					<%		}
						}
						count++;
					%>
					</td></tr>
				<% } %>
				<tr>
					<td id="sub" colspan="2" class="panel orange">
						<button>削除</button>
					</td>
				</tr>
					<input type="hidden" name="mode" value="<%=CourseOperationSvl.DELETE %>" />
					<input type="hidden" name="courseId" value="<%= courseId %>" />
					<input type="hidden" name="typeId" value="100" />
			</table>
		</form>
	</table>
	<p id="boder"><a href = "MenuMaintenanceSvl">一覧画面に戻る</a></p>
	</div>
	</div>
</body>
</html>