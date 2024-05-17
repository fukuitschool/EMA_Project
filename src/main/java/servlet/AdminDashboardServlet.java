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

import dao.EventDAO;
import dao.EventEntryDAO;
import model.AdminBean;
import model.EntryBean;
import model.EventBean;


@WebServlet("/admindashboard")
public class AdminDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
				request.setCharacterEncoding("UTF-8");
				// ユーザー情報をセッションスコープに保存
				HttpSession session = request.getSession();
				AdminBean admin = (AdminBean) session.getAttribute("loginUser");
				String admin_id = admin.getAdminId();
				List<EntryBean> EntryBean = EventEntryDAO.userAdEntryEvent(admin_id);
				List<EventBean> eventList = EventDAO.allEvent();

				request.setAttribute("eventList", eventList);
				request.setAttribute("entryList", EntryBean);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminDashboard.jsp");
				dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("adminId");
		// UserIDから、参加しているイベントIDの一覧を出す
		List<EntryBean> EntryBean = EventEntryDAO.userAdEntryEvent(user_id);

		// イベント一覧を引っ張ってくる
		// Eventテーブルから検索(DAO)
		List<EventBean> eventList = EventDAO.allEvent();

		request.setAttribute("eventList", eventList);

		request.setAttribute("entryList", EntryBean);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminMyPage.jsp");
		dispatcher.forward(request, response);
	}

}
