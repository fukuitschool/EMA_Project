package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAO;
import model.FormValidateLogic;
import model.PasswordSecurityLogic;
import model.StudentBean;


@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StudentDAO studentDAO;

	@Override
	public void init() throws ServletException {
		super.init();

		studentDAO = new StudentDAO();
	}
	
	@Override
	public void destroy() {
		getServletContext().removeAttribute("studentDAO");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try {
			// Get the command type
			String doCommand = request.getParameter("cmd");
			System.out.println();
			System.out.println("Your command is: " + doCommand);

			if (doCommand == null) {
				doCommand = "LIST";
				System.out.println();
				System.out.println("Your command is null then : " + doCommand);
			}

			switch (doCommand) {

			case "LIST":
				System.out.println();
				System.out.println("LIST command worked: " + request + response); // Test用
				displayListStudents(request, response);
				break;
			
			case "LOAD_ADD":
				System.out.println();
				System.out.println("LOAD_ADD command worked: " + request + response); // Test用
				loadAddStudent(request, response);
				break;

			case "ADD":
				System.out.println();
				System.out.println("ADD command worked: " + request + response); // Test用
				addStudent(request, response);
				break;

			case "LOAD_EDIT":
				System.out.println();
				System.out.println("LOAD_EDIT command worked: " + request + response); // Test用
				loadEditStudent(request, response);
				break;
				
			case "EDIT":
				System.out.println();
				System.out.println("EDIT command worked: " + request + response); // Test用
				editStudent(request, response);
				break;
		
			case "LOAD_DELETE":
				System.out.println();
				System.out.println("LOAD_DELETE command worked: " + request + response); // Test用
				loadDeleteStudent(request, response);
				break;

			case "DELETE":
				System.out.println();
				System.out.println("DELETE command worked: " + request + response); // Test用
				deleteStudent(request, response);
				break;

			default:
				displayListStudents(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			// Get the command type
			String doCommand = request.getParameter("cmd");
			System.out.println();
			System.out.println("Your command is: " + doCommand);

			if (doCommand == null) {
				doCommand = "LIST";
				System.out.println();
				System.out.println("Your command is null then : " + doCommand);
			}

			switch (doCommand) {

			case "LIST":
				System.out.println();
				System.out.println("LIST command worked: " + request + response); // Test用
				displayListStudents(request, response);
				break;
			
			case "LOAD_ADD":
				System.out.println();
				System.out.println("LOAD_ADD command worked: " + request + response); // Test用
				loadAddStudent(request, response);
				break;

			case "ADD":
				System.out.println();
				System.out.println("ADD command worked: " + request + response); // Test用
				addStudent(request, response);
				break;

			case "LOAD_EDIT":
				System.out.println();
				System.out.println("LOAD_EDIT command worked: " + request + response); // Test用
				loadEditStudent(request, response);
				break;
		
			case "EDIT":
				System.out.println();
				System.out.println("EDIT command worked: " + request + response); // Test用
				editStudent(request, response);
				break;
			
			case "LOAD_DELETE":
				System.out.println();
				System.out.println("LOAD_DELETE command worked: " + request + response); // Test用
				loadDeleteStudent(request, response);
				break;

			case "DELETE":
				System.out.println();
				System.out.println("DELETE command worked: " + request + response); // Test用
				deleteStudent(request, response);
				break;

			default:
				displayListStudents(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void displayListStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		System.out.println();
		System.out.println("Calling displayListStudents");
		
		List<StudentBean> stBeans = studentDAO.getListStudents();
		System.out.println();
		System.out.println("Created stBeans" + stBeans); // Test

		request.setAttribute("stBeans", stBeans);
		System.out.println("Set stBeans request" + stBeans); // Test


		System.out.println("Start dispatch"); // Test
	    request.getRequestDispatcher("/WEB-INF/jsp/studentList.jsp");
	}

	private void loadAddStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		System.out.println();
		System.out.println("Calling loadAddStudent");
		
		FormValidateLogic formValidate = new FormValidateLogic(studentDAO);

	 	String studentId = request.getParameter("id");
	 	String studentPass = request.getParameter("password");
	 	String studentName = request.getParameter("name");
	 	
	 	String userType = request.getParameter("usertype");
	 	System.out.println("Parameter check: " + studentId + " " + userType);
	 	
	 	if (studentId != null) {
	 		if (!formValidate.isStudentIdAvailable(studentId)) {
	 			System.out.println("Error: id is already exist: " + studentId);
	 			
	 			request.setAttribute("unavailableIdMsg", studentId + " は既に存在します。別の ID を設定してください。");
	 			
	 			request.getRequestDispatcher("/WEB-INF/jsp/addStudent.jsp").forward(request, response);
	 			
	 		} else {
	 			
	 			if (!formValidate.isValidId(studentId)) {
		 			System.out.println("Error: id is not valid number or length: " + studentId);
		 			
		 			request.setAttribute("invalidIdMsg", "ID:" + studentId + "/ID設定が間違っています。4 桁の数字で設定してください。");
		 			
		 			request.getRequestDispatcher("/WEB-INF/jsp/addStudent.jsp").forward(request, response);
		 			
	 			} else if (!formValidate.isValidPass(studentPass)) {
		 			System.out.println("Error: id is not valid number or length: " + studentPass);
		 			
		 			request.setAttribute("invalidPassMsg", "パスワード設定が間違っています。 8 桁の数字で設定してください。");
		 			
		 			request.getRequestDispatcher("/WEB-INF/jsp/addStudent.jsp").forward(request, response);
		 			
	 			} else {
	 				
	 				// Added >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	 				String hidePass = PasswordSecurityLogic.secureDisplayPass(studentPass);
	 				StudentBean stBeans = new StudentBean(studentId, studentPass, studentName);
	 				System.out.println("Get studentId: " + studentId);
	 				
	 				request.setAttribute("addStudent", stBeans);
	 		 		// Added >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			 		request.setAttribute("hidePass", hidePass);
	 				request.setAttribute("userStudent", userType);

	 				
	 				request.getRequestDispatcher("/WEB-INF/jsp/confAddStudent.jsp").forward(request, response);			
	 			}
	 		}
	 		
	 	} else {
	 		System.out.println("Error: studentId is null, please check loadAddStudent().");
	 	}
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {

		System.out.println();
		System.out.println("Calling addStudent");
		
		// add_student form Data から 取得
		String studentId = request.getParameter("id");
		String studentPass = request.getParameter("password");
		String studentName = request.getParameter("name");
		System.out.println("Parameter check: " + studentId + studentPass + studentName);

		if (studentId != null & studentPass != null & studentName != null) {
			StudentBean stBeans = new StudentBean(studentId, studentPass, studentName);			
			
			// DAO に送る
			studentDAO.addDbStudent(stBeans);
			System.out.println("addStudent() Sent stBeans: " + stBeans);
			
			response.sendRedirect("userlist");
		} else {
			System.out.println("Error: studentId, studentPass and studentName are null" + studentId + studentPass + studentName);
		}

	}

	private void loadEditStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {

			System.out.println();
			System.out.println("Calling loadEditStudent"); // For check progressing
			
		 	String studentId = request.getParameter("studentId");
		 	System.out.println("Parameter check: " + studentId); // For check progressing
		 	
		 	if (studentId != null) {
		 		// DB (DAO) から 取得
		 		StudentBean stBeans = studentDAO.loadEditDBStudent(studentId);
		 		
		        request.setAttribute("studentId", stBeans.getStudentId());
		        request.setAttribute("studentName", stBeans.getStudentName());
		 		System.out.println("stBeans check: " + stBeans); // For check progressing
		 		
		 		// Attribute 決める editStudent.jsp で使う
		 		request.setAttribute("editStudent", stBeans);
		 	} else {
		 		System.out.println("Error: studentPass is null");
		 	}
		 	
		    // Forward to editStudent.jsp
		    request.getRequestDispatcher("/WEB-INF/jsp/editStudent.jsp").forward(request, response);
	}

	private void editStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		
			System.out.println();
			System.out.println("Calling editStudent");

		 	String studentId = request.getParameter("studentId");
		    String studentName = request.getParameter("studentName");
//		    String studentPass = request.getParameter("studentPass");
		    

		    // Check if studentPass is not null before updating the student
		    if (studentId != null & studentName != null) {
		    	StudentBean stBeans = new StudentBean(studentId, studentName);
		    	System.out.println("Edit parameter check: " + studentId + studentName);

		    	// DB (DAO) Student のデータ更新
		    	studentDAO.updateDbStudent(stBeans);
		    	
		    	response.sendRedirect("userlist");
		    } else {
		        System.out.println("Error: studentId and studentName are null" + studentId + studentName);
		    }	    
	}
	
	private void loadDeleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
			System.out.println();
			System.out.println("Calling loadDeleteStudent");
			
		
		 	String deleteStId = request.getParameter("studentId");
		 	String userType = request.getParameter("usertype");
		 	System.out.println("Parameter check: " + deleteStId + " " + userType);
 
		 	// null check
		 	if (deleteStId != null) {
		 		
		 		StudentBean stBeans = studentDAO.loadDeleteDbStudent(deleteStId);
		 		// Added >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		 		String hidePass = PasswordSecurityLogic.secureDisplayPass(stBeans.getStudentPass());
		 		System.out.println("Get studentId: " + deleteStId);

		 		// Attribute name should be "editStudent"
		 		request.setAttribute("deleteStudent", stBeans);
		 		// Added >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		 		request.setAttribute("hidePass", hidePass);
		 		request.setAttribute("userStudent", userType);
		 	} else {
		 		  System.out.println("Error: studentId is null, please check loadDeleteStudent().");
		 	}

		    // Forward to editStudent.jsp
		    request.getRequestDispatcher("/WEB-INF/jsp/confDeleteStudent.jsp").forward(request, response);
	}


	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, Exception {
		System.out.println();
		System.out.println("Calling deleteStudent");

		// Get request delete student by ID from deleteStudent.jsp form
		String deleteStId = request.getParameter("studentId");
		System.out.println("Got deleteStID: " + deleteStId);

		try {
			if (deleteStId != null) {
				System.out.println("Send data to DB " + deleteStId);

				// DB (DAO) から 消す
				studentDAO.deleteDbStudent(deleteStId);
				System.out.println("Complete delete student " + deleteStId + " , now redirect list page.");

//				displayListStudents(request, response);
				response.sendRedirect("userlist");
			} else {
				System.out.println("Error: deleteStId is null. please check deleteStudent()");
				request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			if (e.getSQLState().equals("23503")) {

				request.setAttribute("errorMsgRelationalTB", "外部キーを持つユーザーは削除できません");

				request.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(request, response);
			}
		}
	}
}
