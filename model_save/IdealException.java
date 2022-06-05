package model;

public class IdealException extends Exception {

	private int errCd;
	private static String[] ERR_MSG = {//配列の形でエラーメッセージを返す。
		"障害が発生しました。",								//idx 0
		"データベース処理で例外が発生しました。",			//idx 1
		"お客様ID、パスワードを確認してください。",			//idx 2
		"管理者名、パスワードを確認してください。",			//idx 3
		"ご指定された日時に、空席がございませんでした。",	//idx 4
		"ご予約されているコースなので削除できません。",		//idx 5
		"コースに登録されているメニューなので削除できません。",//idx 6
	};
	public static final int ERR_NO_EXCEPTION = 0;
	public static final int ERR_NO_DB_EXCEPTION = 1;
	public static final int ERR_NO_NOT_MEMBER_EXCEPTION = 2;
	public static final int ERR_NO_ADMIN_EXCEPTION = 3;
	public static final int ERR_NO_NOT_VACANCY = 4;
	public static final int ERR_NO_NOT_RESERV_DELETE = 5;
	public static final int ERR_NO_NOT_MENU_DELETE = 6;

	//コンストラクター
	public IdealException(int errCd){
		this.errCd = errCd;

	}
	//エラーメッセージをかえすmethod
	public String getMsg(){
		return ERR_MSG[this.errCd];
	}
}
