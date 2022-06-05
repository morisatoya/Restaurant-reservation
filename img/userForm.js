
				function checkData(obj){
				//1.メッセージを宣言
				var msg = "";
				var regex = /[!"#$%&'()\*\+\-\.,\/:;<=>?@\[\\\]^_`{|}~]+/;

					//2.名前が未入力の時、
					if(obj.usrName.value == null || obj.usrName.value == ""){
					msg += "お名前を入力してください！\n";
					}
					//名前に記号が含まれていた時
					if(obj.usrName.value.match(regex)){
						msg += "お名前には記号を含めないでください!\n"
					}



					//7.パスワードが未入力の時、
					if(obj.password.value == null || obj.password.value == ""){
						msg += "パスワードを入力してください!\n";
					}

					if(obj.phone.value == null || obj.phone.value==""){
						msg += "電話番号を入力してください!\n";
					}else if(!obj.phone.value.match(/^[0-9]+/)){
						msg += "電話番号は半角数字で入力してください!\n";
					}else if(!(obj.phone.value.length == 10 || obj.phone.value.length == 11)){
						msg += "無効な電話番号です!\n";
					}

					//8.メッセージが空白の時、
					if(msg == ""){
						//1."true"を返す。
						return true;
					}else{
					//9.メッセージが空白以外の時、
						//1.アラーとでメッセージを表示し"false"を返す。
						alert(msg);
						return false;
					}
				}