<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ご予約変更</title>
<link rel="stylesheet" type="text/css" href="css/meisai.css" />
<script type="text/javascript">
<!--
	function checkData(obj) {
		var msg = "";
		var rsvYy = parseInt(obj.rsvYy.value);
		var rsvMm = parseInt(obj.rsvMm.value) - 1;
		var rsvDd = parseInt(obj.rsvDd.value);
		var rsvHh = parseInt(obj.rsvHh.value);
		var rsvMi = parseInt(obj.rsvMi.value);
		var rsvDay = new Date(rsvYy, rsvMm, rsvDd, rsvHh, rsvMi, 0);
		var toDay = new Date();

		if (rsvDay.getTime() < toDay.getTime()) {
			msg += "予約日時が過去日又は無効な日付です。\n";
		}
		if ((rsvDay.getTime() - toDay.getTime()) / 24 / 60 / 60 / 1000 >= 365) {
			msg += "一年以上先の予約は承れません。\n";
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
	<h2>テスト様 ご予約変更</h2>
	<br />
	<form action="ReserveOperationSvl" onsubmit="return checkData(this)" method="post">
		<table border>
			<tr>
				<th>ご予約番号</th>
				<td>1</td>
			</tr>
			<tr>
				<th>日付</th>
				<td><select name="rsvYy">
						<option value="2021">2021年</option>
						<option value="2022">2022年</option>
				</select> <select name="rsvMm">
						<option value="1">1月</option>
						<option value="2">2月</option>
						<option value="3">3月</option>
						<option value="4" >4月</option>
						<option value="5">5月</option>
						<option value="6">6月</option>
						<option value="7">7月</option>
						<option value="8">8月</option>
						<option value="9">9月</option>
						<option value="10">10月</option>
						<option value="11">11月</option>
						<option value="12">12月</option>
				</select> <select name="rsvDd">
						<option value="1">1日</option>
						<option value="2">2日</option>
						<option value="3">3日</option>
						<option value="4">4日</option>
						<option value="5">5日</option>
						<option value="6">6日</option>
						<option value="7">7日</option>
						<option value="8">8日</option>
						<option value="9">9日</option>
						<option value="10">10日</option>
						<option value="11">11日</option>
						<option value="12">12日</option>
						<option value="13">13日</option>
						<option value="14">14日</option>
						<option value="15">15日</option>
						<option value="16">16日</option>
						<option value="17">17日</option>
						<option value="18">18日</option>
						<option value="19">19日</option>
						<option value="20">20日</option>
						<option value="21">21日</option>
						<option value="22">22日</option>
						<option value="23">23日</option>
						<option value="24">24日</option>
						<option value="25">25日</option>
						<option value="26">26日</option>
						<option value="27">27日</option>
						<option value="28">28日</option>
						<option value="29">29日</option>
						<option value="30">30日</option>
						<option value="31">31日</option>
				</select></td>
			</tr>
			<tr>
				<th>時刻</th>
				<td><select name="rsvHh">
						<option value="17">17時</option>
						<option value="18">18時</option>
						<option value="19">19時</option>
						<option value="20">20時</option>
						<option value="21">21時</option>
				</select> <select name="rsvMi">
						<option value="0">0分</option>
						<option value="15">15分</option>
						<option value="30">30分</option>
						<option value="45">45分</option>
				</select></td>
			</tr>
			<tr>
				<th>人数</th>
				<td><select name="person">
						<option value="1">1 名</option>
						<option value="2">2 名</option>
						<option value="3">3 名</option>
						<option value="4">4 名</option>
						<option value="5">5 名</option>
						<option value="6">6 名</option>
				</select></td>
			</tr>
			<tr>
				<th>コース</th>
				<td><select name="courseId">
						<option value="1">Aコース（牛肉料理）</option>
						<option value="2">Bコース（鳥肉料理）</option>
						<option value="3">Cコース（魚介料理）</option>
				</select></td>
			</tr>
			<tr>
				<td id="sub" colspan="2">席を確認します。確認を押してください。
				<input type="submit" value=" 確認" />
				</td>
			</tr>
			<input type="hidden" name="rsvId" value="1" />
			<input type="hidden" name="usrId" value="1" />
			<input type="hidden" name="mode" value="12" />
		</table>
		<br />
	</form>
	<br />
	<p id="boder">
		<a href="ReserveListSvl">予約一覧に戻る</a>
	</p>
</body>
</html>