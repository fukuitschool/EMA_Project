package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.EntryBean;
import model.EventBean;
import model.EventEntryBean;

public class EventEntryDAO {

	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "root";

	// Working OK
	public List<EventEntryBean> adminJoinerList(String eventId, String status) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<EventEntryBean> eeBeans = new ArrayList<>();

		try {
			System.out.println();
			System.out.println("Executed adminJoinerList and processing JDBC connection.");

			Class.forName("org.postgresql.Driver");

			/* PostgreSQLへの接続 */
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Success! DriverManager connection: " + con);

			/* SELECT文の準備 */
			String sql = "SELECT ee.*, a.admin_name\n"
					+ "FROM event_entry AS ee\n"
					+ "JOIN admin AS a ON ee.admin_id = a.admin_id\n"
					+ "WHERE ee.admin_id IS NOT NULL\n"
					+ "AND event_id = ?\n"
					+ "AND ee.status = ?";
			st = con.prepareStatement(sql);
			st.setString(1, eventId);
			st.setString(2, status);

			rs = st.executeQuery();

			System.out.println("Retrieved adminJoinerList results:");

		
			// process result set
			while (rs.next()) {
				
				// Test データ取得出来てるか確認
				System.out.println("entryId: " + rs.getInt("entry_id"));
				System.out.println("eventId: " + rs.getString("event_id"));
				System.out.println("adminId: " + rs.getString("admin_id"));
				System.out.println("stId: " + rs.getString("student_id"));
				System.out.println("adminName: " + rs.getString("admin_name"));
				System.out.println("status: " + rs.getString("status"));
				
				// retrieve data from result set row
				int entryId = rs.getInt("entry_id");
				String evId = rs.getString("event_id");
				String adId = rs.getString("admin_id");
				String stId = rs.getString("student_id");
				String adminName = rs.getString("admin_name");
				String state = rs.getString("status");
				
				// create new student object
				EventEntryBean eventEntry = new EventEntryBean(entryId, eventId, adId, "", adminName, "", status);
				
//					EventEntryBean eventEntry = new EventEntryBean(status); -- test
				System.out.println("Finished! stored: " + eventEntry);
				// add it to the list of students
				eeBeans.add(eventEntry);
				System.out.println("eeBeans.add(eventEntry) added list in eeBeans");
			}
			
			// NULL CHECK
			if (eeBeans.isEmpty()) {
				System.out.println("eeBeans is NULL: " + eeBeans);
				return null;
			} else {
				System.out.println("Success! get list " + rs);

			}

		} catch (Exception e) {
			System.out.println();
		} finally {
			close(con, st, rs);
		}
		System.out.println("Success! listed admin joining events"); // For test
		System.out.println("Finished! adminJoiningEventsList: " + eeBeans);
		System.out.println();
		/* 結果をリストに */
		return eeBeans;
	}

	// Working OK
	public List<EventEntryBean> studentJoinerList(String eventId, String status) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<EventEntryBean> eeBeans = new ArrayList<>();

		try {
			System.out.println();
			System.out.println("Executed studentJoinerList and processing JDBC connection.");

			Class.forName("org.postgresql.Driver");

			/* PostgreSQLへの接続 */
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Success! DriverManager connection: " + con);

			/* SELECT文の準備 */
			String sql = "SELECT ee.*, s.student_name\n"
					+ "FROM event_entry AS ee\n"
					+ "JOIN student AS s ON ee.student_id = s.student_id\n"
					+ "WHERE ee.student_id IS NOT NULL\n"
					+ "AND event_id = ?\n"
					+ "AND ee.status = ?;";
			st = con.prepareStatement(sql);
			st.setString(1, eventId);
			st.setString(2, status);

			rs = st.executeQuery();

			System.out.println("Retrieved studentJoinerList results:");

		
			// process result set
			while (rs.next()) {
				
				// Test データ取得出来てるか確認
				System.out.println("entryId: " + rs.getInt("entry_id"));
				System.out.println("eventId: " + rs.getString("event_id"));
				System.out.println("adminId: " + rs.getString("admin_id"));
				System.out.println("stId: " + rs.getString("student_id"));
				System.out.println("studentName: " + rs.getString("student_name"));
				System.out.println("status: " + rs.getString("status"));
				
				// retrieve data from result set row
				int entryId = rs.getInt("entry_id");
				String evId = rs.getString("event_id");
				String adId = rs.getString("admin_id");
				String stId = rs.getString("student_id");
				String studentName = rs.getString("student_name");
				String state = rs.getString("status");
				
				// create new student object
				EventEntryBean eventEntry = new EventEntryBean(entryId, eventId, "", stId, "", studentName, state);
				
//					EventEntryBean eventEntry = new EventEntryBean(status); -- test
				System.out.println("Finished! stored: " + eventEntry);
				// add it to the list of students
				eeBeans.add(eventEntry);
				System.out.println("eeBeans.add(eventEntry) added list in eeBeans");
			}
			
			// NULL CHECK
			if (eeBeans.isEmpty()) {
				System.out.println("eeBeans is NULL: " + eeBeans);
				return null;
			} else {
				System.out.println("Success! get list " + rs);

			}

		} catch (Exception e) {
			System.out.println();
		} finally {
			close(con, st, rs);
		}
		System.out.println("Success! listed studentJoinerList joining events"); // For test
		System.out.println("Finished! studentJoinerList: " + eeBeans);
		System.out.println();
		/* 結果をリストに */
		return eeBeans;
	}
	
	private void close(Connection con, PreparedStatement st, ResultSet rs) throws SQLException, Exception {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// eventテーブルからデータを検索し、検索結果をリストで返す
	public List<EventBean> select(String event_id) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<EventBean> eRecord = null;

		try {
			// JCBCドライバの定義
			Class.forName("org.postgresql.Driver");

			// PostgreSQLへの接続
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			// SQL文の準備(SELECT文)
			// SQL文の準備(SELECT文)
			String sql = "SELECT * ";
			sql += "FROM event ";
			sql += "WHERE event_id = ? ; ";
			st = con.prepareStatement(sql);
			st.setString(1, event_id);

			// SQL文の実行(SELECT文)
			rs = st.executeQuery();

			// ArrayList<ShohinRecord>を生成する
			eRecord = new ArrayList<EventBean>();

			// 結果(ResurtSet)をList<EventBean>に移し替える処理

			while (rs.next()) {
				// 1行分のデータを読込む
				String id = rs.getString("event_id");
				String name = rs.getString("event_name");
				String category = rs.getString("event_category");
				String date = rs.getString("event_date");
				String time = rs.getString("event_time");
				String place = rs.getString("event_place");

				// 1レコード文のデータをフィールドに持つEvantBeanインスタンス
				EventBean eb = new EventBean(id, name, category, date, time, place);

				// リストに1行分のデータを追加する
				eRecord.add(eb);
			}

		} catch (Exception e) {
			System.out.println("DBアクセスエラー");
			e.printStackTrace();
		} finally {
			// PostgreSQLとの接続を切断
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

		return eRecord;
	}
	// データ削除(admin)
			public void deleteAdmin(String event_id, String admin_id) {
				Connection con = null;
				PreparedStatement st = null;

				try {
					// JDBCドライバの定義
					Class.forName("org.postgresql.Driver");

					// PostgreSQLへの接続
					con = DriverManager.getConnection(URL, USER, PASSWORD);

					String sql = "DELETE FROM event_entry ";
					sql += "WHERE event_id = ? ";
					sql += "AND admin_id = ? ;";

					st = con.prepareStatement(sql);
					st.setString(1, event_id);
					st.setString(2, admin_id);

					st.executeUpdate();

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
			}
	// データ削除(student)
		public void deleteStudent(String event_id, String student_id) {
			Connection con = null;
			PreparedStatement st = null;

			try {
				// JDBCドライバの定義
				Class.forName("org.postgresql.Driver");

				// PostgreSQLへの接続
				con = DriverManager.getConnection(URL, USER, PASSWORD);

				String sql = "DELETE FROM event_entry ";
				sql += "WHERE event_id = ? ";
				sql += "AND student_id = ? ;";

				st = con.prepareStatement(sql);
				st.setString(1, event_id);
				st.setString(2, student_id);

				st.executeUpdate();

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
		}

		// イベント受付(student)
		public void eventRegisterStudent(String event_id, String student_id) {
			Connection con = null;
			PreparedStatement st = null;

			try {
				String join = "Joined";
				/* JDBCドライバの定義 */
				Class.forName("org.postgresql.Driver");

				/* PostgreSQLへの接続 */
				con = DriverManager.getConnection(URL, USER, PASSWORD);

				/* INSERT文の準備 */
				String sql = "";
				sql = "INSERT INTO event_entry(event_id, student_id, status) ";
				sql += "VALUES(?, ?, ?);";

				st = con.prepareStatement(sql);
				st.setString(1, event_id);
				st.setString(2, student_id);
				st.setString(3, join);

				st.executeUpdate();

			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			} finally {
				/* PostgreSQLとの接続を切断 */
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
		}
		// イベント受付(admin)
				public void eventRegisterAdmin(String event_id, String admin_id) {
					Connection con = null;
					PreparedStatement st = null;

					try {
						String join = "Joined";
						/* JDBCドライバの定義 */
						Class.forName("org.postgresql.Driver");

						/* PostgreSQLへの接続 */
						con = DriverManager.getConnection(URL, USER, PASSWORD);

						/* INSERT文の準備 */
						String sql = "";
						sql = "INSERT INTO event_entry(event_id, admin_id, status) ";
						sql += "VALUES(?, ?, ?);";

						st = con.prepareStatement(sql);
						st.setString(1, event_id);
						st.setString(2, admin_id);
						st.setString(3, join);

						st.executeUpdate();

					} catch (Exception e) {
						System.out.println("DBアクセス時にエラーが発生しました。");
						e.printStackTrace();
					} finally {
						/* PostgreSQLとの接続を切断 */
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
				}
		// イベントの一覧表示(student)
		public static List<EntryBean> userEntryEvent(String student_id) {
			Connection con = null;
			PreparedStatement st = null;
			ResultSet rs = null;

			List<EntryBean> eRecords = null;
			String entryS_id = student_id;
			try {
				// JCBCドライバの定義
				Class.forName("org.postgresql.Driver");

				// PostgreSQLへの接続
				con = DriverManager.getConnection(URL, USER, PASSWORD);

				// SQL文の準備(SELECT文)
				String sql = "SELECT * ";
				sql += "FROM event_entry ";
				sql += "WHERE student_id = ? ;";
				st = con.prepareStatement(sql);
				st.setString(1, entryS_id);

				// SQL文の実行(SELECT文)
				rs = st.executeQuery();

				// ArrayList<ShohinRecord>を生成する
				eRecords = new ArrayList<EntryBean>();

				// 結果(ResurtSet)をList<EntryBean>に移し替える処理

				while (rs.next()) {
					// 1行分のデータを読込む
					String s_id = rs.getString("student_id");
					String e_id = rs.getString("event_id");
					String status = rs.getString("status");

					// 1レコード文のデータをフィールドに持つEvantBeanインスタンス
					EntryBean eb = new EntryBean(e_id, s_id, status);

					// リストに1行分のデータを追加する
					eRecords.add(eb);
				}

			} catch (Exception e) {
				System.out.println("DBアクセスエラー");
				e.printStackTrace();
			} finally {
				// PostgreSQLとの接続を切断
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

			return eRecords;
		}
		// イベントの一覧表示(admin)
				public static List<EntryBean> userAdEntryEvent(String admin_id) {
					Connection con = null;
					PreparedStatement st = null;
					ResultSet rs = null;

					List<EntryBean> eRecords = null;
					String entryA_id = admin_id;
					try {
						// JCBCドライバの定義
						Class.forName("org.postgresql.Driver");

						// PostgreSQLへの接続
						con = DriverManager.getConnection(URL, USER, PASSWORD);

						// SQL文の準備(SELECT文)
						String sql = "SELECT * ";
						sql += "FROM event_entry ";
						sql += "WHERE admin_id = ? ;";
						st = con.prepareStatement(sql);
						st.setString(1, entryA_id);

						// SQL文の実行(SELECT文)
						rs = st.executeQuery();

						// ArrayList<ShohinRecord>を生成する
						eRecords = new ArrayList<EntryBean>();

						// 結果(ResurtSet)をList<EntryBean>に移し替える処理

						while (rs.next()) {
							// 1行分のデータを読込む
							String a_id = rs.getString("admin_id");
							String e_id = rs.getString("event_id");

							// 1レコード文のデータをフィールドに持つEvantBeanインスタンス
							EntryBean eb = new EntryBean(e_id, a_id);

							// リストに1行分のデータを追加する
							eRecords.add(eb);
						}

					} catch (Exception e) {
						System.out.println("DBアクセスエラー");
						e.printStackTrace();
					} finally {
						// PostgreSQLとの接続を切断
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

					return eRecords;
				}
		// イベント参加してるか判定(student)
			public static List<EntryBean> entryCheck(String student_id,String event_id) {
				Connection con = null;
				PreparedStatement st = null;
				ResultSet rs = null;

				List<EntryBean> eRecords = null;
				String entryS_id = student_id;
				String entryE_id = event_id;
				try {
					// JCBCドライバの定義
					Class.forName("org.postgresql.Driver");

					// PostgreSQLへの接続
					con = DriverManager.getConnection(URL, USER, PASSWORD);

					// SQL文の準備(SELECT文)
					String sql = "SELECT * ";
					sql += "FROM event_entry ";
					sql += "WHERE student_id = ? ";
					sql += "AND event_id = ? ;";
					st = con.prepareStatement(sql);
					st.setString(1, entryS_id);
					st.setString(2, entryE_id);

					// SQL文の実行(SELECT文)
					rs = st.executeQuery();

					// ArrayList<ShohinRecord>を生成する
					eRecords = new ArrayList<EntryBean>();

					// 結果(ResurtSet)をList<EntryBean>に移し替える処理

					while (rs.next()) {
						// 1行分のデータを読込む
						String s_id = rs.getString("student_id");
						String e_id = rs.getString("event_id");
						String status = rs.getString("status");

						// 1レコード文のデータをフィールドに持つEvantBeanインスタンス
						EntryBean eb = new EntryBean(e_id, s_id, status);

						// リストに1行分のデータを追加する
						eRecords.add(eb);
					}

				} catch (Exception e) {
					System.out.println("DBアクセスエラー");
					e.printStackTrace();
				} finally {
					// PostgreSQLとの接続を切断
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

				return eRecords;
			}
			// イベント参加してるか判定(admin)
			public static List<EntryBean> entryAdCheck(String admin_id,String event_id) {
				Connection con = null;
				PreparedStatement st = null;
				ResultSet rs = null;

				List<EntryBean> eRecords = null;
				String entryA_id = admin_id;
				String entryE_id = event_id;
				try {
					// JCBCドライバの定義
					Class.forName("org.postgresql.Driver");

					// PostgreSQLへの接続
					con = DriverManager.getConnection(URL, USER, PASSWORD);

					// SQL文の準備(SELECT文)
					String sql = "SELECT * ";
					sql += "FROM event_entry ";
					sql += "WHERE admin_id = ? ";
					sql += "AND event_id = ? ;";
					st = con.prepareStatement(sql);
					st.setString(1, entryA_id);
					st.setString(2, entryE_id);

					// SQL文の実行(SELECT文)
					rs = st.executeQuery();

					// ArrayList<ShohinRecord>を生成する
					eRecords = new ArrayList<EntryBean>();

					// 結果(ResurtSet)をList<EntryBean>に移し替える処理

					while (rs.next()) {
						// 1行分のデータを読込む
						String a_id = rs.getString("admin_id");
						String e_id = rs.getString("event_id");

						// 1レコード文のデータをフィールドに持つEvantBeanインスタンス
						EntryBean eb = new EntryBean(e_id, a_id);

						// リストに1行分のデータを追加する
						eRecords.add(eb);
					}

				} catch (Exception e) {
					System.out.println("DBアクセスエラー");
					e.printStackTrace();
				} finally {
					// PostgreSQLとの接続を切断
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

				return eRecords;
			}
		// イベント更新
		public void edit(String id, String name, String category, Date date, String time, String place) {
			Connection con = null;
			PreparedStatement st = null;

			try {
				/* JDBCドライバの定義 */
				Class.forName("org.postgresql.Driver");

				/* PostgreSQLへの接続 */
				con = DriverManager.getConnection(URL, USER, PASSWORD);

				/* INSERT文の準備 */
				String sql = "";
				sql = "UPDATE event ";
				sql += "SET event_name = ? ,";
				sql += "event_category = ? ,";
				sql += "event_date = ? ,";
				sql += "event_time = ? ,";
				sql += "event_place = ? ";
				sql += "WHERE event_id = ? ;";

				st = con.prepareStatement(sql);
				st.setString(1, name);
				st.setString(2, category);
				st.setDate(3, date);
				st.setString(4, time);
				st.setString(5, place);
				st.setString(6, id);
				st.executeUpdate();

			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			} finally {
				/* PostgreSQLとの接続を切断 */
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
		}	
}
