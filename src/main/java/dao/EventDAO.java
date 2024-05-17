package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.EventBean;

public class EventDAO {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "root";

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
			sql+="FROM event ";
			sql+="WHERE event_id = ? ; ";
			st=con.prepareStatement(sql);
			st.setString(1,event_id);

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
	
	// データ削除
	public void delete(String event_id) {
		Connection con = null;
		PreparedStatement st = null;

		try {
			// JDBCドライバの定義
			Class.forName("org.postgresql.Driver");

			// PostgreSQLへの接続
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			String sql = "DELETE FROM event ";
			sql += "WHERE event_id = ?;";

			st = con.prepareStatement(sql);
			st.setString(1, event_id);

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

	// データ登録
	public void eventRegister(String id, String name, String category, Date date, String time, String place) {
		Connection con = null;
		PreparedStatement st = null;

		try {
			/* JDBCドライバの定義 */
			Class.forName("org.postgresql.Driver");

			/* PostgreSQLへの接続 */
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			/* INSERT文の準備 */
			String sql = "";
			sql = "INSERT INTO event(event_id, event_name, event_category, event_date, event_time, event_place) ";
			sql += "VALUES(?, ?, ?, ?, ?, ?);";

			st = con.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, name);
			st.setString(3, category);
			st.setDate(4, date);
			st.setString(5, time);
			st.setString(6, place);

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

	// イベントの一覧表示
	public static List<EventBean> allEvent() {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<EventBean> eRecords = null;

		try {
			// JCBCドライバの定義
			Class.forName("org.postgresql.Driver");

			// PostgreSQLへの接続
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			// SQL文の準備(SELECT文)
			String sql = "SELECT * ";
			sql += "FROM event ";
			sql += "ORDER BY event_id; ";
			st = con.prepareStatement(sql);

			// SQL文の実行(SELECT文)
			rs = st.executeQuery();

			// ArrayList<ShohinRecord>を生成する
			eRecords = new ArrayList<EventBean>();

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
	
	//イベント更新
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
	// イベントの一覧表示用メソッド
	public ArrayList<EventBean> allEvents(ResultSet rs) throws Exception {

		// 全検索結果を格納するリストを準備
		ArrayList<EventBean> eRecords = new ArrayList<EventBean>();

		while (rs.next()) {
			// 1行分のデータを読込む
			String id = rs.getString("event_id");
			String name = rs.getString("event_name");
			String category = rs.getString("event_category");
			String date = rs.getString("event_date");
			String time = rs.getString("event_time");
			String place = rs.getString("event_place");

			// 1行分のデータを格納するインスタンス
			EventBean eb = new EventBean(id, name, category, date, time, place);

			// リストに1行分のデータを追加する
			eRecords.add(eb);
		}
		return eRecords;
	}
}
