<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,model.*,controller.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ご予約一覧画面</title>
<link rel="stylesheet" type="text/css" href="css/ichirann_a.css" />
<script type="text/javascript">
<!--
function sort(mode) {
	let
	cnt = 0;
	try {
		let ary = [];   //各レコードのノードを収納する配列
		let tr = document.getElementById("col0");  //現在のレコードを格納する変数
		let preAry = [];   //レコード手前の空白を格納する配列
		let pre = document.getElementById("pre0"); //現在のレコード手前の空白を格納する変数

		//配列にレコードを入れていく
		for (var i = 0; tr != null; i++) {
			ary[i] = tr;
			preAry[i] = pre;
			tr.parentNode.removeChild(tr);
			pre.parentNode.removeChild(pre);
			tr = document.getElementById("col" + (i + 1));
			pre = document.getElementById("pre" + (i + 1));
		}

		//モードごとに配列をソート
		if (mode == 10) {
			ary.sort((e1, e2)=> {
				//idでソート ただの数値なのでそのまま差をリターン
				//window.alert(e1.children[0].textContent.length);
				return e1.children[0].textContent - e2.children[0].textContent;
			});

		} else if (mode == 11) {
			ary.sort((e1, e2) => {
				//日付をソート
				let strAry1 = e1.children[1].textContent.match(/\d+/g); //日付の文字列の数字部分を配列に入れる
				let strAry2 = e2.children[1].textContent.match(/\d+/g);
				//window.alert(Array.isArray(strAry1));
				//先頭から比較して差があれば差をリターン
				for(var i=0 ;i < strAry1.length;i++){
					if(strAry1[i] !== strAry2[i]){
						return strAry1[i] - strAry2[i];
					}
				}
				//まったく同じ日時なら入れ替えない
				return 1
			});
		} else if(mode == 12) {

			ary.sort((e1, e2)=> {
				//人数でソート
				return e1.children[2].textContent - e2.children[2].textContent;
			});
			//ary.reverse();
		}else if (mode == 13) {
			ary.sort((e1, e2)=> {
				//コース名でソート
				if(e1.children[3].textContent < e2.children[3].textContent){
					return -1
				}
				return 1
			});
		}else if (mode == 14) {
			ary.sort((e1, e2)=> {
				//テーブル名でソート
				if(e1.children[4].textContent < e2.children[4].textContent){
					return -1
				}
				return 1
			});
		} else if (mode == 15) {
			ary.sort((e1, e2)=> {
				//登録日時でソート
				let strAry1 = e1.children[5].textContent.match(/\d+/g);
				let strAry2 = e2.children[5].textContent.match(/\d+/g);
				//window.alert(Array.isArray(strAry1));
				for(var i=0 ;i < strAry1.length;i++){
					if(strAry1[i] !== strAry2[i]){
						return strAry1[i] - strAry2[i];
					}
				}
				return 1
			});
		}
		//ary.forEach(function(s){window.alert(s.textContent)});

		let
		next = document.getElementById("next");

		//ソートした配列を先頭からDOMツリーに追加
		for (var i = 0; i < ary.length; i++) {
			ary[i].id = "col" + i//idをつけなおす
			preAry[i].id = "pre" + i;
			next.parentNode.insertBefore(preAry[i], next);
			next.parentNode.insertBefore(ary[i], next);
			cnt++;
		}
	} catch (error) {
		window.alert("" + cnt + error.message);
	}
}
//-->
</script>
</head>
<body>
	<jsp:useBean id="reserve" class="model.Reserve" scope="request" />
	<jsp:useBean id="userInfo" class="model.User" scope="session" />
	<h2><jsp:getProperty name="userInfo" property="usrName" />様 ご予約一覧
	</h2>
	<%
		if (request.getAttribute("msg") != null) {
	%>
	<h2>
		メッセージ:<%=request.getAttribute("msg").toString()%></h2>
	<%
		}
	%>
	<br />
	<table border="1" id="table">
		<tr>
			<td id="head">NO<a href="#" onclick="sort(10)">▼</a></td>
			<td id="head">ご予約日時<a href="#" onclick="sort(11)">▼</a></td>
			<td id="head">人数<a href="#" onclick="sort(12)">▼</a></td>
			<td id="head">コース名<a href="#" onclick="sort(13)">▼</a></td>
			<td id="head">テーブル名<a href="#" onclick="sort(14)">▼</a></td>
			<td id="head">登録日時<a href="#" onclick="sort(15)">▼</a></td>
			<td id="head" colspan="2"></td>
		</tr>
		<!--
		<tr>
			<td><form action="ReserveListSortSvl" method="post">
					<input name="sortMode" type="hidden"
						value="<%=ReserveListSortSvl.SORT_BY_ID%>" /><input type="submit"
						value="a" />
				</form></td>
			<td><form action="ReserveListSortSvl" method="post">
					<input name="sortMode" type="hidden"
						value="<%=ReserveListSortSvl.SORT_BY_RSV_DATE%>" /><input
						type="submit" value="a" />
				</form></td>
			<td></td>
			<td><form action="ReserveListSortSvl" method="post">
					<input name="sortMode" type="hidden"
						value="<%=ReserveListSortSvl.SORT_BY_COURSE_NAME%>" /><input
						type="submit" value="a" />
				</form></td>
			<td></td>
			<td><form action="ReserveListSortSvl" method="post">
					<input name="sortMode" type="hidden"
						value="<%=ReserveListSortSvl.SORT_BY_APP_DATE%>" /><input
						type="submit" value="a" />
				</form></td>
		</tr>
		 -->
		<%
			User user = User.getUser(1);
			session.setAttribute("userInfo", user);
			ArrayList<Reserve> al = (ArrayList<Reserve>) (request
					.getAttribute("reserveList"));
			if (al == null) {
				response.sendRedirect("ReserveListSortSvl");
				System.out.println("al.get() = NULL");
				return;
			}
			for (int i = 0; i < al.size(); i++) {
				Reserve r = al.get(i);
		%>
		<tr id="pre<%=al.size() -1 - i %>">
			<td width="50px"></td>
			<td width="250px"></td>
			<td width="50px"></td>
			<td width="300px"></td>
			<td width="200px"></td>
			<td width="300px"></td>
			<td width="50px"></td>
			<td width="50px"></td>
		</tr>
		<tr id="col<%=al.size()-1 - i%>" class="group">
			<td id="meisai"><%=r.getRsvId()%></td>
			<td id="meisai"><%=r.getRsvYy()%>年<%=r.getRsvMm()%>月<%=r.getRsvDd()%>日
				<%=r.getRsvHh()%>時<%=r.getRsvMi()%>分</td>
			<td id="meisai"><%=r.getPerson()%></td>
			<td id="meisai"><%=r.getCourseName()%></td>
			<td id="meisai"><%=r.getTableName()%></td>
			<td id="meisai"><%=r.getAppYy()%>年<%=r.getAppMm() + 1%>月<%=r.getAppDd()%>日
				<%=r.getAppHh()%>時<%=r.getAppMi()%>分</td>
			<form action="ReserveUpdateSvl" method="post">
				<td id="meisai"><input type="submit" value="変更" /> <input
					type="hidden" name="rsvId" value="<%=r.getRsvId()%>" /></td>
			</form>
			<form action="ReserveDeleteSvl" method="post">
				<td id="meisai"><input type="submit" value="取消" /> <input
					type="hidden" name="rsvId" value="<%=r.getRsvId()%>" /></td>
			</form>
		</tr>
		<%
			}
		%>
		<tr id="next">
			<td colspan="10" id="boder">
				<form action="ReserveInsertSvl" method="post">
					<input type="submit" value="新規ご予約" />
				</form>
			</td>
		</tr>
	</table>
	<br />
	<br />
	<p id="boder">
		<a href="userIndex.jsp">戻る</a>
	</p>
</body>
</html>