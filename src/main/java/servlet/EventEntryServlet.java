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
import model.StudentBean;

@WebServlet("/EventEntryServlet")
public class EventEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String ad;
		HttpSession session = request.getSession();
		
		if ((ad = request.getParameter("user")).equals("admin")) {
			AdminBean admin = (AdminBean) session.getAttribute("loginUser");
			String admin_id = admin.getAdminId();
			List<EntryBean> EntryBean = EventEntryDAO.userAdEntryEvent(admin_id);
			List<EventBean> eventList = EventDAO.allEvent();

			request.setAttribute("eventList", eventList);
			request.setAttribute("entryList", EntryBean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminDashboard.jsp");
			dispatcher.forward(request, response);
		}
		//生徒側の処理
		StudentBean e_student = (StudentBean) session.getAttribute("loginUser");
		String user_id = e_student.getStudentId();
		// UserIDから、参加しているイベントIDの一覧を出す
		List<EntryBean> EntryBean = EventEntryDAO.userEntryEvent(user_id);

		// イベント一覧を引っ張ってくる
		// Eventテーブルから検索(DAO)
		List<EventBean> eventList = EventDAO.allEvent();

		request.setAttribute("eventList", eventList);

		request.setAttribute("entryList", EntryBean);

		// ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/studentDashboard.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String ad = request.getParameter("user");

		HttpSession session = request.getSession();
		
		String event_id = request.getParameter("event_id");
		String message = "";

		if (ad.equals("admin")) {
			String admin_id = request.getParameter("adminId");
			EventEntryDAO eventEntryDAO = new EventEntryDAO();
			List<EntryBean> eb = EventEntryDAO.entryAdCheck(admin_id, event_id);
			if (!(eb.isEmpty())) { //イベントに参加済みかどうか判定
				message += "既に参加済みのイベントです。<br>";
				request.setAttribute("message", message);
				// UserIDから、参加しているイベントIDの一覧を出す
				List<EntryBean> EntryBean = EventEntryDAO.userAdEntryEvent(admin_id);

				// イベント一覧を引っ張ってくる
				// Eventテーブルから検索(DAO)
				List<EventBean> eventList = EventDAO.allEvent();

				request.setAttribute("eventList", eventList);

				request.setAttribute("entryList", EntryBean);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminDashboard.jsp");
				dispatcher.forward(request, response);
			}
			eventEntryDAO.deleteAdmin(event_id, admin_id);
			eventEntryDAO.eventRegisterAdmin(event_id, admin_id);

			doGet(request, response);
		}
		//生徒側の処理
		StudentBean student = (StudentBean) session.getAttribute("loginUser");
		String user_id = student.getStudentId();
		
		EventEntryDAO eventEntryDAO = new EventEntryDAO();
		List<EntryBean> eb = EventEntryDAO.entryCheck(user_id, event_id);
		if (!(eb.isEmpty())) {
			message += "既に参加済みのイベントです。<br>";
			request.setAttribute("message", message);
			// UserIDから、参加しているイベントIDの一覧を出す
			List<EntryBean> EntryBean = EventEntryDAO.userEntryEvent(user_id);

			// イベント一覧を引っ張ってくる
			// Eventテーブルから検索(DAO)
			List<EventBean> eventList = EventDAO.allEvent();

			request.setAttribute("eventList", eventList);

			request.setAttribute("entryList", EntryBean);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/studentDashboard.jsp");
			dispatcher.forward(request, response);
		}

		eventEntryDAO.deleteStudent(event_id, user_id);
		eventEntryDAO.eventRegisterStudent(event_id, user_id);

		doGet(request, response);
	}

}
