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
import dao.StudentDAO;
import model.EntryBean;
import model.EventBean;
import model.LoginCheckLogic;
import model.StudentBean;

@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = null;
		String message = null;
		
		StudentDAO studentDAO = new StudentDAO();
		
		//ログイン処理
		LoginCheckLogic loginLogic = new LoginCheckLogic();
		
		List<StudentBean> isLogin = studentDAO.login(id,pass);
		System.out.println(isLogin.isEmpty());
		if (isLogin.isEmpty() == true) {
			message = loginLogic.checkAdmin(id,pass);
			if("".equals(id) && "".equals(pass)) {
				;
			}else{
				message += "ユーザーID・パスワードが一致しません。<br>";
			}
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
			dispatcher.forward(request, response);
		}else {  //ログイン成功時の処理
			for(StudentBean sb : isLogin) {
				id = sb.getStudentId();
				pass = sb.getStudentPass();
				name = sb.getStudentName();
			}
			//Userインスタンス(ユーザー情報)の生成
			StudentBean student = new StudentBean(id,pass,name);
			//ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", student);
			StudentBean e_student = (StudentBean)session.getAttribute("loginUser");
			String user_id = e_student.getStudentId();
			
			//UserIDから、参加しているイベントIDの一覧を出す
			List<EntryBean> EntryBean = EventEntryDAO.userEntryEvent(user_id);
			
			//イベント一覧を引っ張ってくる
			// Eventテーブルから検索(DAO)
			List<EventBean> eventList = EventDAO.allEvent();

			request.setAttribute("eventList", eventList);
			request.setAttribute("entryList", EntryBean);
		}
		//ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/studentDashboard.jsp");
		dispatcher.forward(request, response);
	}
}
