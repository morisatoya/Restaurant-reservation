//入力値の前後の空白を削除するJavaScript関数
function trim(str) {
	str = str.replace(/^\s+|\s+$/g, "");
	return str;
}