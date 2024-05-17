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
import model.AdminBean;
import model.EventBean;

@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータより属性を取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("adminId");
		String pass = request.getParameter("adminPass");
		AdminBean adminUser = new AdminBean(id, pass);
		
		String action = request.getParameter("action");
		
		
		// セッションスコープでユーザー情報格納
		HttpSession session = request.getSession();
		session.setAttribute("adminUser", adminUser);
		

		// actionの属性で分岐
		if ("edit".equals(action)) {// action=edit(編集)→編集する
			String event_id = request.getParameter("event_id");

			// Eventテーブルから検索(DAO)
			EventDAO eventDAO = new EventDAO();
			List<EventBean> events = eventDAO.select(event_id);

			request.setAttribute("editEvents", events);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/eventEdit.jsp");
			dispatcher.forward(request, response);

		} else if ("delete".equals(action)) {// action=delete(削除)→削除する
			String event_id = request.getParameter("event_id");

			// Eventテーブルから検索(DAO)
			EventDAO eventDAO = new EventDAO();
			List<EventBean> events = eventDAO.select(event_id);

			request.setAttribute("deleteEvents", events);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/eventDelete.jsp");
			dispatcher.forward(request, response);
		}else if("register".equals(action)) {//action=register(登録)→登録する
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/eventRegister.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = ""; // 削除完了メッセージ用

		// リクエストパラメータより属性を取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("adminId");
		String pass = request.getParameter("adminPass");
		AdminBean adminUser = new AdminBean(id, pass);

		// セッションスコープでユーザー情報格納
		HttpSession session = request.getSession();
		session.setAttribute("adminUser", adminUser);

		// Eventテーブルから検索(DAO)
		List<EventBean> eventList = EventDAO.allEvent();

		request.setAttribute("eventList", eventList);

		request.setAttribute("message", message);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/eventList.jsp");
		dispatcher.forward(request, response);
	}

}
