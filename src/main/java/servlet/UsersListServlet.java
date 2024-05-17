package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/userlist")
public class UsersListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		try {
			String doAction = request.getParameter("action");
			System.out.println();
			System.out.println("Your action is: " + doAction);
			
			if (doAction == null) {
				doAction = "DISPATCH";
				System.out.println();
				System.out.println("Your action is null then : " + doAction);
			}
			
			switch (doAction) {
			case "DISPATCH":
				System.out.println();
				System.out.println("DISPATCH action worked: " + request + response); // Test用
				dispatchAction(request, response);
				break;
//			default:
//				System.out.println();
//				System.out.println("default action worked: " + request + response); // Test用
//				dispatchAction(request, response);
			}

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	
	
	private void dispatchAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		
		request.getRequestDispatcher("student").include(request, response);
		request.getRequestDispatcher("admin").include(request, response);
		request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp").include(request, response);
	}

}
