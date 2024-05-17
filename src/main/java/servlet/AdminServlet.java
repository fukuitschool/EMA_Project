package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDAO;
import model.AdminBean;
import model.FormValidateLogic;
import model.PasswordSecurityLogic;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminDAO adminDAO;

	@Override
	public void init() throws ServletException {
		super.init();

		adminDAO = new AdminDAO();
	}

	@Override
	public void destroy() {
		getServletContext().removeAttribute("adminDAO");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
				displayListAdmin(request, response);
				break;

			case "LOAD_ADD":
				System.out.println();
				System.out.println("LOAD_ADD command worked: " + request + response); // Test用
				loadAddAdmin(request, response);
				break;

			case "ADD":
				System.out.println();
				System.out.println("ADD command worked: " + request + response); // Test用
				addAdmin(request, response);
				break;

			case "LOAD_EDIT":
				System.out.println();
				System.out.println("LOAD_EDIT command worked: " + request + response); // Test用
				loadEditAdmin(request, response);
				break;

			case "EDIT":
				System.out.println();
				System.out.println("EDIT command worked: " + request + response); // Test用
				editAdmin(request, response);
				break;

			case "LOAD_DELETE":
				System.out.println();
				System.out.println("LOAD_DELETE command worked: " + request + response); // Test用
				loadDeleteAdmin(request, response);
				break;

			case "DELETE":
				System.out.println();
				System.out.println("DELETE command worked: " + request + response); // Test用
				deleteAdmin(request, response);
				break;

			default:
				displayListAdmin(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				displayListAdmin(request, response);
				break;

			case "LOAD_ADD":
				System.out.println();
				System.out.println("LOAD_ADD command worked: " + request + response); // Test用
				loadAddAdmin(request, response);
				break;

			case "ADD":
				System.out.println();
				System.out.println("ADD command worked: " + request + response); // Test用
				addAdmin(request, response);
				break;

			case "LOAD_EDIT":
				System.out.println();
				System.out.println("LOAD_EDIT command worked: " + request + response); // Test用
				loadEditAdmin(request, response);
				break;

			case "EDIT":
				System.out.println();
				System.out.println("EDIT command worked: " + request + response); // Test用
				editAdmin(request, response);
				break;

			case "LOAD_DELETE":
				System.out.println();
				System.out.println("LOAD_DELETE command worked: " + request + response); // Test用
				loadDeleteAdmin(request, response);
				break;

			case "DELETE":
				System.out.println();
				System.out.println("DELETE command worked: " + request + response); // Test用
				deleteAdmin(request, response);
				break;

			default:
				displayListAdmin(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void displayListAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, Exception {
		System.out.println();
		System.out.println("Calling displayListAdmin");

		List<AdminBean> adminBeans = adminDAO.findAll();
		System.out.println();
		System.out.println("Created adminBeans" + adminBeans); // Test

		request.setAttribute("adminBeans", adminBeans);

		request.getRequestDispatcher("/WEB-INF/jsp/adminList.jsp");

		System.out.println("Forward adminBeans request" + adminBeans); // Test

	}

	private void loadAddAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, Exception {
		System.out.println();
		System.out.println("Calling loadAddAdmin");

		FormValidateLogic formValidate = new FormValidateLogic(adminDAO);

		String adminId = request.getParameter("id");
		String adminPass = request.getParameter("password");
		String adminName = request.getParameter("name");

		String userType = request.getParameter("usertype");
		System.out.println("Parameter check: " + adminId + " " + userType);

		if (adminId != null) {
			if (!formValidate.isAdminIdAvailable(adminId)) {
				// Back addAdmin.jsp
				System.out.println("Error: id is already exist: " + adminId);

				request.setAttribute("unavailableIdMsg", adminId + " は既に存在します。別の ID を設定してください。");

				request.getRequestDispatcher("/WEB-INF/jsp/addAdmin.jsp").forward(request, response);

			} else {

				if (!formValidate.isValidId(adminId)) {
					// Back addAdmin.jsp
					System.out.println("Error: id is not valid number or length: " + adminId);

					request.setAttribute("invalidIdMsg", "ID:" + adminId + " /ID設定が間違っています。4 桁の数字で設定してください。");

					request.getRequestDispatcher("/WEB-INF/jsp/addAdmin.jsp").forward(request, response);

				} else if (!formValidate.isValidPass(adminPass)) {
					// Back addAdmin.jsp
					System.out.println("Error: id is not valid number or length: " + adminPass);

					request.setAttribute("invalidPassMsg", "パスワード設定が間違っています。 8 桁の数字で設定してください。");

					request.getRequestDispatcher("/WEB-INF/jsp/addAdmin.jsp").forward(request, response);

				} else {

					// Added >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
					String hidePass = PasswordSecurityLogic.secureDisplayPass(adminPass);
					AdminBean adminBeans = new AdminBean(adminId, adminPass, adminName);
					System.out.println("Get adminId: " + adminId);

					request.setAttribute("addAdmin", adminBeans);
					// Added >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
					request.setAttribute("hidePass", hidePass);
					request.setAttribute("userAdmin", userType);

					request.getRequestDispatcher("/WEB-INF/jsp/confAddAdmin.jsp").forward(request, response);
				}
			}

		} else {
			System.out.println("Error: adminId is null, please check loadAddAdmin().");
		}
	}

	private void addAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {

		System.out.println();
		System.out.println("Calling addAdmin");

		// add_student form Data から 取得
		String adminId = request.getParameter("id");
		String adminPass = request.getParameter("password");
		String adminName = request.getParameter("name");
		System.out.println("Parameter check: " + adminId + adminPass + adminName);

		if (adminId != null & adminPass != null & adminName != null) {
			AdminBean adminBeans = new AdminBean(adminId, adminPass, adminName);

			// DAO に送る
			adminDAO.registerAdmin(adminId, adminPass, adminName);
			System.out.println("addAdmin() Sent adminBeans: " + adminBeans);

			response.sendRedirect("userlist");
		} else {
			System.out.println("Error: (adminId, adminPass and adminName are null" + adminId + adminPass + adminName);
		}
	}

	private void loadEditAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, Exception {

		System.out.println();
		System.out.println("Calling loadEditAdmin"); // For check progressing

		String adminId = request.getParameter("adminId");
		System.out.println("Parameter check: " + adminId); // For check progressing

		if (adminId != null) {
			// DB (DAO) から 取得
			AdminBean adminBeans = adminDAO.loadEditDbAdmin(adminId);

			request.setAttribute("adminId", adminBeans.getAdminId());
			request.setAttribute("adminName", adminBeans.getAdminName());
			System.out.println("adminBeans check: " + adminBeans); // For check progressing

			// Attribute 決める edtiAdmin.jsp で使う
			request.setAttribute("edtiAdmin", adminBeans);
		} else {
			System.out.println("Error: adminId is null");
		}

		// Forward to edtiAdmin.jsp
		request.getRequestDispatcher("/WEB-INF/jsp/edtiAdmin.jsp").forward(request, response);
	}

	private void editAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, Exception {

		System.out.println();
		System.out.println("Calling editAdmin");

		String adminId = request.getParameter("adminId");
		String adminName = request.getParameter("adminName");
//			    String studentPass = request.getParameter("adminPass");

		// Check if adminPass is not null before updating the admin
		if (adminId != null & adminName != null) {
			AdminBean adminBeans = new AdminBean(adminId, adminName);
			System.out.println("Edit parameter check: " + adminId + adminName);

			// DB (DAO) Student のデータ更新
			adminDAO.editAdmin(adminBeans.getAdminId(), adminBeans.getAdminName());

//			displayListAdmin(request, response);
			response.sendRedirect("userlist");
		} else {
			System.out.println("Error: adminId and studentName are null" + adminId + adminName);
		}
	}

	private void loadDeleteAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, Exception {
		System.out.println();
		System.out.println("Calling loadDeleteAdmin");

		String deleteAdminId = request.getParameter("adminId");
		String userType = request.getParameter("usertype");
		System.out.println("Parameter check: " + deleteAdminId + " " + userType);

		// null check
		if (deleteAdminId != null) {
			AdminBean adminBeans = adminDAO.loadDeleteDbAdmin(deleteAdminId);
			// Added >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			String hidePass = PasswordSecurityLogic.secureDisplayPass(adminBeans.getAdminPass());
			System.out.println("Get deleteAdminId: " + deleteAdminId);

			request.setAttribute("deleteAdmin", adminBeans);
			// Added >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			request.setAttribute("hidePass", hidePass);
			request.setAttribute("userAdmin", userType);

			System.out.println("userAdmin" + adminBeans);
		} else {
			System.out.println("Error: deleteAdminId is null, please check loadDeleteAdmin().");
		}

		// Forward to editStudent.jsp
		request.getRequestDispatcher("/WEB-INF/jsp/confDeleteAdmin.jsp").forward(request, response);
	}

	private void deleteAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, Exception {
		System.out.println();
		System.out.println("Calling deleteAdmin");

		// Get request delete admin by ID from confDeleteAdmin.jsp form
		String deleteAdminId = request.getParameter("adminId");
		System.out.println("Got deleteAdminId: " + deleteAdminId);

		if (deleteAdminId != null) {
			System.out.println("Send data to DB " + deleteAdminId);

			// DB (DAO) から 消す
			adminDAO.deleteAdmin(deleteAdminId);
			System.out.println("Complete delete admin " + deleteAdminId + " ,now redirect list page ");

//			displayListAdmin(request, response);
			response.sendRedirect("userlist");
		} else {
			System.out.println("Error: deleteAdminId is null. please check deleteAdmin()");
			response.sendRedirect("userlist");
		}
	}
}
