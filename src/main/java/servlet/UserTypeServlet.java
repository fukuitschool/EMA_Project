package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This UserTypeServlet work switching register form between admin and student
 */
@WebServlet("/userType")
public class UserTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String selectedType = request.getParameter("usertype");
			
			if (selectedType == null) {
				selectedType = "ADMIN";
				System.out.println();
				System.out.println("selectedType is null, then : " + selectedType);
			}
			switch (selectedType) {
				
				case "ADMIN":
					System.out.println();
					System.out.println("ADMIN is worked: " + selectedType + " forward to addAdmin.jsp");
					request.getRequestDispatcher("/WEB-INF/jsp/addAdmin.jsp").forward(request, response);
					break;
					
				case "STUDENT":
					System.out.println();
					System.out.println("STUDENT is worked: " + selectedType + " forward to addStudent.jsp");
					request.getRequestDispatcher("/WEB-INF/jsp/addStudent.jsp").forward(request, response);
					break;
					
				default:
					// Send back
					System.out.println();
					System.out.println("default is worked: " + selectedType);
					request.getRequestDispatcher("/WEB-INF/jsp/addAdmin.jsp").forward(request, response);
			}				
		} catch (ServletException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
