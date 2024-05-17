import java.util.List;

import dao.AdminDAO;
import model.AdminBean;

public class test5 {

	public static void main(String[] args) {
		AdminDAO admDAO = new AdminDAO();
		List<AdminBean> admList = admDAO.findAll();

		for(AdminBean adm : admList) {
			System.out.print(adm.getAdminId() + " ");
			System.out.println(adm.getAdminName()+ " ");
		}
	}
}
