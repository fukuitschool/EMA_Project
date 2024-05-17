
import java.util.Scanner;

import dao.AdminDAO;

public class test3 {

	public static void main(String[] args) {
		
		System.out.println("追加する管理者IDを入力してください");
		System.out.print("管理者ID：");
		String adminId = new Scanner(System.in).nextLine();

		System.out.println("追加するパスワードを入力してください");
		System.out.print("パスワード：");
		String adminPass = new Scanner(System.in).nextLine();		
		
		System.out.println("追加する氏名入力してください。");
		System.out.print("氏名：");
		String adminName = new Scanner(System.in).nextLine();

		AdminDAO adminDAO = new AdminDAO();
		adminDAO.registerAdmin(adminId,adminPass,adminName);
		
		
	}
}
