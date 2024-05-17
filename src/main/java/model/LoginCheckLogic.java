package model;

public class LoginCheckLogic {
	public boolean certify(String password) {
		if(password.equals("1234")) {return true;}
		return false;
	}
	public String checkpass(boolean isLogin,String pass) {
		String e_Message = "";
		// 認証パスワードチェック
		if (isLogin == false) {
			if(pass.isEmpty()) {
				e_Message += "管理者認証パスワードが入力されていません。<br>";
			}else {
				e_Message += "管理者認証パスワードが一致しません。<br>";
			}
		}
		return e_Message;
	}
	//生徒用チェックメソッド
	public static String checkStudent(String id, String pass) {
		String e_Message = "";
		// 必須項目チェック(ID,パスワード)
		if ("".equals(id)) {
			e_Message += "ユーザーIDが入力されていません。<br>";
		}
		if ("".equals(pass)) {
			e_Message += "パスワードが入力されていません。<br>";
		}
		return e_Message;
	}
	//管理者用チェックメソッド
	public String checkAdmin(String id, String pass) {
		String e_Message = "";
		// 必須項目チェック(ID,パスワード)
		if ("".equals(id)) {
			e_Message += "ユーザーIDが入力されていません。<br>";
		}
		if ("".equals(pass)) {
			e_Message += "パスワードが入力されていません。<br>";
		}
		return e_Message;
	}
}
