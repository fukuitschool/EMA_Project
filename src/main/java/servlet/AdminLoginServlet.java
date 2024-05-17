package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;
import dao.EventDAO;
import dao.EventEntryDAO;
import model.AdminBean;
import model.EntryBean;
import model.EventBean;
import model.LoginCheckLogic;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = null;
		String message = null;
		
		AdminDAO adminDAO = new AdminDAO();
		
		// ログイン処理
		LoginCheckLogic loginLogic = new LoginCheckLogic();
		List<AdminBean> isLogin = adminDAO.login(id,pass);
		if (isLogin.isEmpty() == true) {
			message = loginLogic.checkAdmin(id,pass);
			if("".equals(id) && "".equals(pass)) {
				;
			}else{
				message += "ユーザーID・パスワードが一致しません。<br>";
			}
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminLogin.jsp");
			dispatcher.forward(request, response);

		} else {   // ログイン成功時の処理
			for(AdminBean ad : isLogin) {
				id = ad.getAdminId();
				pass = ad.getAdminPass();
				name = ad.getAdminName();
			}
			// Userインスタンス(ユーザー情報)の生成
			AdminBean admin = new AdminBean(id, pass,name);
			// ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", admin);
			
			String admin_id = admin.getAdminId();
			List<EntryBean> EntryBean = EventEntryDAO.userAdEntryEvent(admin_id);
			List<EventBean> eventList = EventDAO.allEvent();

			request.setAttribute("eventList", eventList);
			request.setAttribute("entryList", EntryBean);
		}
		// ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminDashboard.jsp");
		dispatcher.forward(request, response);
	}
}
