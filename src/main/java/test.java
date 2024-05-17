import java.util.List;

import dao.AdminDAO;
import model.AdminBean;

public class test {

	public static void main(String[] args) {
		AdminDAO admDAO = new AdminDAO();
		List<AdminBean> admList = admDAO.login("0001","1234");
		
		System.out.println(admList);
		}
	}

