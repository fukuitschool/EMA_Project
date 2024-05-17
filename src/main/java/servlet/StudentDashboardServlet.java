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
import model.EntryBean;
import model.EventBean;
import model.StudentBean;

@WebServlet("/studentdashboard")
public class StudentDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		StudentBean e_student = (StudentBean)session.getAttribute("loginUser");
		String user_id = e_student.getStudentId();
		
		//UserIDから、参加しているイベントIDの一覧を出す
		List<EntryBean> EntryBean = EventEntryDAO.userEntryEvent(user_id);
		
		//イベント一覧を引っ張ってくる
		// Eventテーブルから検索(DAO)
		List<EventBean> eventList = EventDAO.allEvent();

		request.setAttribute("eventList", eventList);
		
		request.setAttribute("entryList", EntryBean);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/studentDashboard.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		StudentBean student = (StudentBean)session.getAttribute("loginUser");
		String user_id = student.getStudentId();
		//UserIDから、参加しているイベントIDの一覧を出す
		List<EntryBean> EntryBean = EventEntryDAO.userEntryEvent(user_id);
		
		//イベント一覧を引っ張ってくる
		// Eventテーブルから検索(DAO)
		List<EventBean> eventList = EventDAO.allEvent();

		request.setAttribute("eventList", eventList);
		
		request.setAttribute("entryList", EntryBean);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/studentMyPage.jsp");
		dispatcher.forward(request, response);
	}

}
