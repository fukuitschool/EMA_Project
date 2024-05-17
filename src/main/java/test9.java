import java.util.List;

import dao.AdminDAO;
import model.AdminBean;

public class test9 {

	public static void main(String[] args) {
		AdminDAO admDAO3 = new AdminDAO();
		List<AdminBean> admLogin3 = admDAO3.login("0001", "1234");
		
		for(AdminBean adm : admLogin3) {
		System.out.print(adm.getAdminId() +" ");
		System.out.println(adm.getAdminPass());
		}
	}

}