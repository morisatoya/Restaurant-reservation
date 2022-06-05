<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>記号チェックｈｔｍｌ</title>
<script type="text/javascript">
<!--
	function c(obj){

		var regex = /[!"#$%&'()\*\+\-\.,\/:;<=>?@\[\\\]^_`{|}~]+/;
		if(obj.box.value.match(regex)){
			alert("記号が入っています。");
			return false;
		}else{
			alert("記号が入っていません");
			return true;
			}

	}
	//-->
</script>
</head>
<body>
	<center>
	<form method="post" action="#" onsubmit="return c(this)">
	<table>
	<tr>
	<th>記号チェック</th>
	<td><input type="text" id="box"/></td>
	</tr>
	<tr><td colspan="2"><input type="submit" value="チェック"/></td></tr>

	</table>
	</form>
	</center>
</body>
</html>