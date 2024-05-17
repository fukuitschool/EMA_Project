package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.StudentBean;

public class StudentDAO {
	private final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private final String USER = "postgres";
	private final String PASSWORD = "root";
//	private static final Logger logger = Logger.getLogger(StudentDAO.class.getName());


	public List<StudentBean> getListStudents() throws Exception {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

        List<StudentBean> stBeans = new ArrayList<>();

		try {
//			logger .info("Executed getStudents and processing JDBC connection.");
			System.out.println();
			System.out.println("Executed getStudents and processing JDBC connection.");

	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);
	         System.out.println("Success! DriverManager connection: " + con);

	         /* SELECT文の準備 */
	         String sql = "select * from student order by student_id";
	         st = con.prepareStatement(sql);

	         /* SELECT文の実行 */
	         rs = st.executeQuery();
	         
			 // Check actually added record or not
			 if (rs == null) {
				 System.out.println("NOT executed st.executeUpdate() for " + rs);
			 }  else {
				 System.out.println("Success! get list students " + rs);

				 // process result set
				 while (rs.next()) {

					 // Test データ取得出来てるか確認
					 System.out.println("student_id: " + rs.getString("student_id"));
					 System.out.println("student_pass: " + rs.getString("student_pass"));
					 System.out.println("student_name: " + rs.getString("student_name"));
					 
					 
					 // retrieve data from result set row
					 String studentId = rs.getString("student_id");
					 String studentPass = rs.getString("student_pass");
					 String studentName = rs.getString("student_name");
					 
					 // create new student object
					 StudentBean student = new StudentBean(studentId, studentPass, studentName);
					 
					 // add it to the list of students
					 stBeans.add(student);
					 System.out.println("stBeans.add(student) added list in stBeans");
				 }
			 }

		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
//	        logger.severe("Error fetching student data: " + e.getMessage());
			e.printStackTrace();
		} finally {
	         /* PostgreSQLとの接続を切断 */
			close(con, st, rs);
		}
	   System.out.println("Success! listed student"); // For test
	   System.out.println("Finished! getListStudents progress: " + con);
	   System.out.println();
        /* 結果をリストに */
        return stBeans;
    }

	public void addDbStudent(StudentBean stBeans)throws SQLException, Exception {

		Connection con = null;
		PreparedStatement st = null;

		try {
			System.out.println();
			System.out.println("addDbStudent deleteDbStudent and processing JDBC connection.");
			
	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");
	         System.out.println("Start - JDBCドライバの定義 " + Class.forName("org.postgresql.Driver"));

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);
	         System.out.println("Success! DriverManager connection: " + con);

	         /* SELECT文の準備 */
	         String sql = "insert into student "
	        		 	+ "(student_id, student_pass, student_name)"
	        		 	+ "values (?, ?, ?)";
	         st = con.prepareStatement(sql);

	         // Test get parameters
	         System.out.println("student_id: " + stBeans.getStudentId());
	         System.out.println("student_pass: " + stBeans.getStudentPass());
	         System.out.println("student_name: " + stBeans.getStudentName());

	         /* Set parameter from StudentServlet */
	         st.setString(1, stBeans.getStudentId());
	         st.setString(2, stBeans.getStudentPass());
	         st.setString(3, stBeans.getStudentName());


	         /* INSERT文の実行 */
	         int addedRecored = st.executeUpdate();
	         
			 // Check actually added record or not
			 if (addedRecored == 0) {
				 System.out.println("NOT executed st.executeUpdate() for " + addedRecored);
			 }  else {
				 System.out.println("Success! added " + addedRecored + " student");
			 }

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, st, null);
		}

		System.out.println("Finished! addDbStudent progress: " + con);
		System.out.println();
	}

	private void close(Connection con, PreparedStatement st, ResultSet rs) {
		/* PostgreSQLとの接続を切断 */
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}

		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {}
		}

		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {}
		}
	}

	public StudentBean loadEditDBStudent(String studentId) throws SQLException, Exception {

		StudentBean stBeans = null;

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
//		String selectedId = null;

		try {
			System.out.println();
			System.out.println("Executed loadDbStudent and processing JDBC connection.");

//			selectedId = studentId;

	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");
	         System.out.println("getStudent Start - JDBCドライバの定義 " + Class.forName("org.postgresql.Driver"));

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);
	         System.out.println("Success! DriverManager connection: " + con);

	         /* SELECT文の準備 */
			 String sql = "select * from student where student_id = ?";

			 st = con.prepareStatement(sql);
			 st.setString(1, studentId);

				
			 rs = st.executeQuery();
			 
			 if (rs.next()) {
				    String stId = rs.getString("student_id");
//				    String stPass = rs.getString("student_pass");
				    String studentName = rs.getString("student_name");

				    // Store data
				    stBeans = new StudentBean(stId, studentName);
				    System.out.println("Success! loaded " + stBeans);
				} else {
				    throw new Exception("No student with student_id " + studentId);
				}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		
		System.out.println("Finished! loadDbStudent progress: " + con);
		System.out.println();
		
		return stBeans;
	}

	public void updateDbStudent(StudentBean stBeans) throws SQLException, Exception {

		Connection con = null;
		PreparedStatement st = null;

		try {
			System.out.println();
			System.out.println("Executed updateDbStudent and processing JDBC connection.");

			/* JDBCドライバの定義 */
			Class.forName("org.postgresql.Driver");
			System.out.println("Start - JDBCドライバの定義 " + Class.forName("org.postgresql.Driver")); // For test

			/* PostgreSQLへの接続 */
			con = DriverManager.getConnection(URL, USER, PASSWORD);
	         System.out.println("Success! DriverManager connection: " + con);

			/* SELECT文の準備 */
//	        String sqlMemo = "set student_id=?, student_pass=?, student_name=? "; // 必要ならこちらを使う
			String sql = "update student "
					+ "set student_name=? "
					+ "where student_id=?";

			st = con.prepareStatement(sql);

//			st.setString($n, stBeans.getStudentId());
//			st.setString($n, stBeans.getStudentPass());
			st.setString(1, stBeans.getStudentName());
			st.setString(2, stBeans.getStudentId()); // 更新用 student_id WHERE clause

			
			int updatedRecord = st.executeUpdate(); 
			
			// Check actually updated record or not
			if (updatedRecord == 0) {
				System.out.println("NOT executed st.executeUpdate() for " + updatedRecord);
			}  else {
				System.out.println("Success! updated " + updatedRecord + " student");
			}
			
		} finally {
			close(con, st, null);
		}


		System.out.println("Finished! updateDbStudent progress: " + con);
		System.out.println();
	}
	
	public StudentBean loadDeleteDbStudent(String deleteStId) throws SQLException, Exception {
		StudentBean stBeans = null;

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			System.out.println();
			System.out.println("Executed loadDbStudent and processing JDBC connection.");

	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");
	         System.out.println("getStudent Start - JDBCドライバの定義 " + Class.forName("org.postgresql.Driver"));

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);
	         System.out.println("Success! DriverManager connection: " + con);

	         /* SELECT文の準備 */
			 String sql = "select * from student where student_id = ?";

			 st = con.prepareStatement(sql);
			 st.setString(1, deleteStId);

				
			 rs = st.executeQuery();
			 
			 if (rs.next()) {
				    String stId = rs.getString("student_id");
				    String stPass = rs.getString("student_pass");
				    String studentName = rs.getString("student_name");

				    // Store data
				    stBeans = new StudentBean(stId, stPass, studentName);
				    System.out.println("Success! loaded " + stBeans);
				} else {
				    throw new Exception("No student with student_id " + deleteStId);
				}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
		
		System.out.println("Finished! loadDeleteDbStudent progress: " + con);
		System.out.println();
		
		return stBeans;
	}

	public void deleteDbStudent(String deleteStId) throws SQLException, Exception {

		// Connection
		Connection con = null;
		PreparedStatement st = null;

		// try with connection
		try {
			System.out.println();
			System.out.println("Executed deleteDbStudent and processing JDBC connection.");
			
			String studentId = deleteStId;

	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");
	         System.out.println("deleteDbStudent Start ( JDBCドライバの定義 ) " + Class.forName("org.postgresql.Driver"));

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);
	         System.out.println("Success! DriverManager connection: " + con);

	         // Say SQL query
			String sql = "delete from student where student_id = ?";
		   

	         // prepare statement
			st = con.prepareStatement(sql);

	        // 値 Set
			st.setString(1, studentId);
			
			/* INSERT文の実行 */
			int deletedRecord = st.executeUpdate(); 
			
			// Check actually deleted record or not
			if (deletedRecord == 0) {
				System.out.println("NOT executed st.executeUpdate() for " + deletedRecord);
			}  else {
				System.out.println("Success! deleted " + studentId + " student");
			}
		} finally {
			close(con, st, null);
		}

		System.out.println("Finished! deleteDbStudent progress: " + con);
		System.out.println();
	}

	public boolean isIdAvailable(String studentId) throws SQLException, Exception {
		boolean isIdAvailable = true; 
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
//			logger .info("Executed isIdAvailable and processing JDBC connection.");
			System.out.println();
			System.out.println("Executed getStudents and processing JDBC connection.");

	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASSWORD);
	         System.out.println("Success! DriverManager connection: " + con);

	         /* SELECT文の準備 */
	         String sql = "select * from student where student_id = ?";
	         st = con.prepareStatement(sql);
	         st.setString(1, studentId);

	         /* SELECT文の実行 */
	         rs = st.executeQuery();
	         
	         if (rs.next()) {
	        	 System.out.println("Start! searching available id " + rs);
//	        	 logger.info(rs.getString("student_id") + " is not available."  );		
	        	 isIdAvailable = false;
	         } else {
//	        	 logger.info(studentId + " is available.");
	        	 isIdAvailable = true;
	         }
	         
		} catch(Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
//			logger.severe("Error fetching student data: " + e.getMessage());
			e.printStackTrace();
		} finally {
	         /* PostgreSQLとの接続を切断 */
			close(con, st, rs);
		}
	   System.out.println("Success! student_id is available!"); // For test
	   System.out.println("Finished! isIdAvailable progress: " + con);
	   System.out.println();
	   return isIdAvailable;
	}
	
	public List<StudentBean> login(String studentId, String studentPass) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<StudentBean> stdLoginList = null;

		try {
			Class.forName("org.postgresql.Driver");

			con = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "SELECT student_id,student_pass,student_name ";
			sql += "FROM student ";
			sql += "WHERE student_id = ? AND student_pass = ? ;";

			st = con.prepareStatement(sql);
			st.setString(1, studentId);
			st.setString(2, studentPass);

			rs = st.executeQuery();

			stdLoginList = resultLoginStudent(rs);

		} catch (Exception e) {
			System.out.println("ＤＢアクセスエラー");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					;
				}
			}

			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					;
				}
			}
		}
		return stdLoginList;
	}

	public ArrayList<StudentBean> resultLoginStudent(ResultSet rs) throws Exception {

		ArrayList<StudentBean> stLogins = new ArrayList<StudentBean>();

		while (rs.next()) {

			String sId = rs.getString("student_id");
			String sPass = rs.getString("student_pass");
			String sName = rs.getString("student_name");
			
			StudentBean stLogin = new StudentBean();
			stLogin.setStudentId(sId);
			stLogin.setStudentPass(sPass);
			stLogin.setStudentName(sName);

			stLogins.add(stLogin);
		}
		return stLogins;

	}
}
