package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EventDAO;
import model.EventBean;

@WebServlet("/EventEditServlet")
public class EventEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String message = "";

		String id = request.getParameter("event_id");
		String name = request.getParameter("event_name");
		String category = request.getParameter("event_category");
		String date = request.getParameter("event_date");
		String time = request.getParameter("event_time");
		String place = request.getParameter("event_place");
		
		EventDAO eventDAO = new EventDAO();
		List<EventBean> eventBean = eventDAO.select(id);
		if (!(eventBean.isEmpty())) {
			message += "既に登録済みのイベントIDです。<br>";
			request.setAttribute("message", message);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/eventRegister.jsp");
			dispatcher.forward(request, response);
		}
		EventBean eb = new EventBean(id, name, category, date, time, place);
		request.setAttribute("editEvent", eb);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/eventRegisterConfirm.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータより属性を取得
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String event_id = request.getParameter("delete_id");

		// actionの属性で分岐
		if ("edit".equals(action)) { //action=edit(編集)→編集する
			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("event_id");
			String name = request.getParameter("name");
			String category = request.getParameter("category");
			String da = request.getParameter("date");
			String time = request.getParameter("time");
			String place = request.getParameter("place");

			Date date2 = Date.valueOf(da);
			EventDAO eventDAO = new EventDAO();
			eventDAO.edit(id, name, category, date2, time, place);

		} else if ("delete".equals(action)) {// action=delete(削除)→削除する

			// 検索条件に該当する情報をテーブルから削除
			EventDAO eventDAO = new EventDAO();
			eventDAO.delete(event_id);
		} else if ("register".equals(action)) { //action=register(登録)→登録する
			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("event_id");
			String name = request.getParameter("event_name");
			String category = request.getParameter("event_category");
			String da = request.getParameter("event_date");
			String time = request.getParameter("event_time");
			String place = request.getParameter("event_place");

			Date date2 = Date.valueOf(da);
			EventDAO eventDAO = new EventDAO();
			eventDAO.eventRegister(id, name, category, date2, time, place);
		}
		ServletContext sc = getServletContext();
		RequestDispatcher dispatcher = sc.getRequestDispatcher("/EventServlet");
		dispatcher.forward(request, response);
	}
}
