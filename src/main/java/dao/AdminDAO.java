package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AdminBean;

public class AdminDAO {

	private final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private final String USER = "postgres";
	private final String PASS = "root";

	public List<AdminBean> findAll() {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<AdminBean> admList = null;

		try {
			Class.forName("org.postgresql.Driver");

			con = DriverManager.getConnection(URL, USER, PASS);

			String sql = "SELECT * ";
			sql += "FROM admin ";

			st = con.prepareStatement(sql);

			rs = st.executeQuery();

			admList = resultData(rs);

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
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				;
			}
		}
		return admList;
	}

	public ArrayList<AdminBean> resultData(ResultSet rs) throws Exception {

		ArrayList<AdminBean> adminRecords = new ArrayList<AdminBean>();

		while (rs.next()) {

			String adminId = rs.getString("admin_id");
			String adminPass = rs.getString("admin_pass");
			String adminName = rs.getString("admin_name");

			AdminBean adminRecord = new AdminBean(adminId, adminPass, adminName);

			adminRecords.add(adminRecord);
		}
		return adminRecords;

	}
	
	public int registerAdmin(String adminId, String adminPass, String adminName) {
		Connection con = null;
		PreparedStatement st = null;
		int regCnt = 0;
		try {
			Class.forName("org.postgresql.Driver");

			con = DriverManager.getConnection(URL, USER, PASS);

			String sql = "";
			sql += "INSERT INTO admin(admin_id,admin_pass,admin_name) ";
			sql += "VALUES(?,?,?); ";

			st = con.prepareStatement(sql);
			st.setString(1, adminId);
			st.setString(2, adminPass);
			st.setString(3, adminName);

			regCnt = st.executeUpdate();
		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}

		return regCnt;
	}

	public AdminBean loadEditDbAdmin(String adminId) throws SQLException, Exception {

		AdminBean adminBeans = null;

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			System.out.println();
			System.out.println("Executed loadEditDBAdmin and processing JDBC connection.");


			/* JDBCドライバの定義 */
			Class.forName("org.postgresql.Driver");
			System.out.println("getStudent Start - JDBCドライバの定義 " + Class.forName("org.postgresql.Driver"));

			/* PostgreSQLへの接続 */
			con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Success! DriverManager connection: " + con);

			/* SELECT文の準備 */
			String sql = "select * from admin where admin_id = ?";

			st = con.prepareStatement(sql);
			st.setString(1, adminId);

			rs = st.executeQuery();

			if (rs.next()) {
				String adId = rs.getString("admin_id");
				String adName = rs.getString("admin_name");

				// Store data
				adminBeans = new AdminBean(adId, adName);
				System.out.println("Success! loaded " + adminBeans);
			} else {
				throw new Exception("No student with admin_id " + adminId);
			}

		} catch (SQLException e) {
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

		System.out.println("Finished! loadEditDBAdmin progress: " + con);
		System.out.println();

		return adminBeans;
	}
		
	public int editAdmin(String adminId, String adminName) {
		Connection con = null;
		PreparedStatement st = null;
		int updCnt = 0;

		try {
			Class.forName("org.postgresql.Driver");

			con = DriverManager.getConnection(URL, USER, PASS);

			String sql = "";
			sql = "UPDATE admin ";
			sql += " SET admin_name = ?";
			sql += " WHERE admin_id = ?;";

			st = con.prepareStatement(sql);
			st.setString(1, adminName);
			st.setString(2, adminId);

			updCnt = st.executeUpdate();
		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return updCnt;
	}

	public AdminBean loadDeleteDbAdmin(String deleteAdminId) throws SQLException, Exception {
		AdminBean adminBeans = null;

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			System.out.println();
			System.out.println("Executed loadDeleteDbAdmin and processing JDBC connection.");


	         /* JDBCドライバの定義 */
	         Class.forName("org.postgresql.Driver");
	         System.out.println("getStudent Start - JDBCドライバの定義 " + Class.forName("org.postgresql.Driver"));

	         /* PostgreSQLへの接続 */
	         con = DriverManager.getConnection(URL, USER, PASS);
	         System.out.println("Success! DriverManager connection: " + con);

	         /* SELECT文の準備 */
			 String sql = "select * from admin where admin_id = ?";

			 st = con.prepareStatement(sql);
			 st.setString(1, deleteAdminId);

				
			 rs = st.executeQuery();
			 
			 if (rs.next()) {
				    String adId = rs.getString("admin_id");
				    String adPass = rs.getString("admin_pass");
				    String adName = rs.getString("admin_name");

				    // Store data
				    adminBeans = new AdminBean(adId, adPass, adName);
				    System.out.println("Success! loaded " + adminBeans);
				} else {
				    throw new Exception("No student with admin_id " + deleteAdminId);
				}

		} catch (SQLException e) {
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
		
		System.out.println("Finished! loadDeleteDbAdmin progress: " + con);
		System.out.println();
		
		return adminBeans;
	}	
	
	
	public int deleteAdmin(String adminId) {
		Connection con = null;
		PreparedStatement st = null;
		int delCnt = 0;
		try {
			Class.forName("org.postgresql.Driver");

			con = DriverManager.getConnection(URL, USER, PASS);

			String sql = "";
			sql += "DELETE FROM admin ";
			sql += "WHERE admin_id = ?; ";

			st = con.prepareStatement(sql);
			st.setString(1, adminId);

			delCnt = st.executeUpdate();
		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}

		return delCnt;
	}

	public List<AdminBean> login(String adminId, String adminPass) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<AdminBean> admLoginList = null;

		try {
			Class.forName("org.postgresql.Driver");

			con = DriverManager.getConnection(URL, USER, PASS);

			String sql = "SELECT * ";
			sql += "FROM admin ";
			sql += "WHERE admin_id = ? AND admin_pass = ? ;";

			st = con.prepareStatement(sql);
			st.setString(1, adminId);
			st.setString(2, adminPass);

			rs = st.executeQuery();

			admLoginList = resultLoginAdmin(rs);

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
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				;
			}
		}
		return admLoginList;
	}

	public ArrayList<AdminBean> resultLoginAdmin(ResultSet rs) throws Exception {

		ArrayList<AdminBean> adminLogins = new ArrayList<AdminBean>();

		while (rs.next()) {

			String adminId = rs.getString("admin_id");
			String adminPass = rs.getString("admin_pass");
			String adminName = rs.getString("admin_name");

			AdminBean adminLogin = new AdminBean();
			adminLogin.setAdminId(adminId);
			adminLogin.setAdminPass(adminPass);
			adminLogin.setAdminName(adminName);

			adminLogins.add(adminLogin);
		}
		return adminLogins;

	}
	
	public boolean isIdAvailable(String adminId) throws SQLException, Exception {
		boolean isIdAvailable = true;

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			System.out.println();
			System.out.println("Executed isIdAvailable and processing JDBC connection.");

			/* JDBCドライバの定義 */
			Class.forName("org.postgresql.Driver");

			/* PostgreSQLへの接続 */
			con = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Success! DriverManager connection: " + con);

			/* SELECT文の準備 */
			String sql = "select * from admin where admin_id = ?";
			st = con.prepareStatement(sql);
			st.setString(1, adminId);

			/* SELECT文の実行 */
			rs = st.executeQuery();

			if (rs.next()) {
				System.out.println("Start! searching available id " + rs);
				System.out.println(rs.getString("admin_id") + " is not available.");
				isIdAvailable = false;
			} else {
				System.out.println(adminId + " is available.");
				isIdAvailable = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		System.out.println("Success! admin_id is available!"); // For test
		System.out.println("Finished! isIdAvailable progress: " + con);
		System.out.println();
		return isIdAvailable;
	}
}
