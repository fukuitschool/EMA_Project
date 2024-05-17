package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EventEntryDAO;
import model.EventBean;
import model.EventEntryBean;

/**
 * Servlet implementation class EventEntryAllServlet
 */
@WebServlet("/eventAll")
public class EventEntryAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EventEntryDAO eeDAO;

	public void init() throws ServletException {
		super.init();

		eeDAO = new EventEntryDAO();
	}

	@Override
	public void destroy() {
		getServletContext().removeAttribute("studentDAO");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try {
			String doCommand = request.getParameter("cmd");
			System.out.println();
			System.out.println("Your command is: " + doCommand);

			if (doCommand == null) {
				doCommand = "LIST_PARTICIPANTS";
				System.out.println();
				System.out.println("Your command is null then : " + doCommand);
			}

			switch (doCommand) {
			case "LIST_PARTICIPANTS":
				System.out.println();
				System.out.println("LIST_PARTICIPANTS command worked: " + request + response); // Test用
				listParticipants(request, response);
				break;

			default:
				listParticipants(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private void listParticipants(HttpServletRequest request, HttpServletResponse response) {
		System.out.println();
		System.out.println("Calling listParticipants");

		String eventId = request.getParameter("eventId");
		String status = request.getParameter("status");

		System.out.println("Check: " + eventId + status);

		try {
			List<EventEntryBean> eeAdmin = eeDAO.adminJoinerList(eventId, status);
			List<EventEntryBean> eeStudent = eeDAO.studentJoinerList(eventId, status);

			// Added
			List<EventBean> eventList = eeDAO.select(eventId);

			System.out.println("Loaded adminJoiningEventsList");
			System.out.println("Loaded, studentJoiningEventsList");

			request.setAttribute("eeAdmin", eeAdmin);
			System.out.println("Scoped: " + eeAdmin);

			request.setAttribute("eeStudent", eeStudent);
			System.out.println("Scoped: " + eeStudent);

			// Added
			request.setAttribute("eventList", eventList);

			request.getRequestDispatcher("/WEB-INF/jsp/eventJoinersList.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
