package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginCheckLogic;

@WebServlet("/AdminJadgeServlet")
public class AdminJadgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminJadge.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
				request.setCharacterEncoding("UTF-8");
				String password = request.getParameter("checkpass");
				
				//ログイン処理
				LoginCheckLogic loginLogic = new LoginCheckLogic();
				boolean isLogin = loginLogic.certify(password);
				
				//認証失敗時の処理
				if(isLogin == false) {
					String message = loginLogic.checkpass(isLogin,password);
					
					request.setAttribute("message", message);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminJadge.jsp");
					dispatcher.forward(request, response);
				}
				//認証成功時→ログイン画面にフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminLogin.jsp");
				dispatcher.forward(request, response);
	}
}
