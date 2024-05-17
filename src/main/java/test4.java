
import java.util.Scanner;

import dao.AdminDAO;

public class test4 {

	public static void main(String[] args) {
		
		System.out.println("変更を行う管理者IDを入力してください");
		System.out.print("管理者ID：");
		String adminId = new Scanner(System.in).nextLine();

		System.out.println("変更後の氏名を入力してください。");
		System.out.print("変更後の氏名：");
		String adminName = new Scanner(System.in).nextLine();

		
		AdminDAO adminDAO = new AdminDAO();
		adminDAO.editAdmin(adminId, adminName);		
		
	}
}
