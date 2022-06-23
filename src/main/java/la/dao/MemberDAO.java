package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import la.bean.MemberBean;

public class MemberDAO {
	private String url = "jdbc:postgresql:sample";
	private String user = "student";
	private String pass = "himitu";

	public MemberDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	public void saveMemberInfo(MemberBean member) throws DAOException {
// 顧客番号の取得 Serial型の暗黙シーケンスから取得
		/**
		 * int member_id = 0; String sql = "SELECT nextval('member_member_id_seq')";
		 * 
		 * try (// データベースへの接続 Connection con = DriverManager.getConnection(url, user,
		 * pass); // PreparedStatementオブジェクトの取得 PreparedStatement st =
		 * con.prepareStatement(sql); // SQLの実行 ResultSet rs = st.executeQuery();) { if
		 * (rs.next()) { member_id = rs.getInt(1); } member_id = member_id - 1; } catch
		 * (SQLException e) { e.printStackTrace(); throw new
		 * DAOException("レコードの操作に失敗しました。"); }
		 */
// 顧客情報の追加SQL文
		String sql = "INSERT INTO member(member_name, member_address, member_tel, member_email, member_pass) "
				+ "VALUES(?, ?, ?, ?, ?)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
// プレースホルダーの設定
			// st.setInt(1, member_id);
			st.setString(1, member.getMember_name());
			st.setString(2, member.getMember_address());
			st.setString(3, member.getMember_tel());
			st.setString(4, member.getMember_email());
			st.setString(5, member.getMember_pass());
// SQLの実行
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public MemberBean setMemberBean(String member_email) throws DAOException {

		// 顧客情報の追加SQL文
		String sql = "SELECT * FROM member WHERE member_email='" + member_email + "'";
		MemberBean bean = new MemberBean();

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
		// SQLの実行
		// ResultSet rs = st.executeQuery();
		) {
			// 結果の取得および表示
			try (ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				while (rs.next()) {
					// System.out.print(rs.getInt("member_id") + ":");
					// System.out.print(rs.getString("member_name") + ":");
					// System.out.println(rs.getString("member_email"));
					if (rs.getString("member_email").equals(member_email)) {
						bean = new MemberBean(rs.getInt("member_id"), rs.getString("member_name"),
								rs.getString("member_address"), rs.getString("member_tel"),
								rs.getString("member_email"), rs.getString("member_pass"), rs.getDate("admission_date"),
								rs.getDate("update_date"), rs.getDate("secession_date"), rs.getBoolean("is_seller"));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

	}

	public void updateMemberInfo(MemberBean member, HttpSession session) throws DAOException {
		// int member_id = 0;
		MemberBean bean = (MemberBean) session.getAttribute("member_info");
		String member_email = bean.getMember_email();
		String sql = "UPDATE member SET member_name = ?, member_address = ?, member_tel = ?, member_email = ? WHERE member_email= ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダーの設定
			st.setString(1, member.getMember_name());
			st.setString(2, member.getMember_address());
			st.setString(3, member.getMember_tel());
			st.setString(4, member.getMember_email());
			st.setString(5, member_email);
			// SQLの実行
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

	public boolean isCheckLogin(String member_email, String member_pass) throws DAOException {
		// SQL文の作成
		Boolean isCheckemail = false;
		Boolean isCheckpass = false;
		// Boolean isLogin = false;

		String sql = "SELECT * FROM member WHERE member_email='" + member_email + "'";
		// System.out.println(sql);

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
		// SQLの実行
		// ResultSet rs = st.executeQuery();
		) {
			// 結果の取得および表示
			try (ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				while (rs.next()) {
					// System.out.print(rs.getInt("member_id") + ":");
					// System.out.print(rs.getString("member_name") + ":");
					// System.out.println(rs.getString("member_email"));
					if (rs.getString("member_email").equals(member_email)) {
						isCheckemail = true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// カテゴリ一覧をListとして返す
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

		sql = "SELECT * FROM member WHERE member_pass='" + member_pass + "'";
		// System.out.println(sql);

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
		// SQLの実行
		// ResultSet rs = st.executeQuery();
		) {
			// 結果の取得および表示
			try (ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				while (rs.next()) {
					// System.out.print(rs.getInt("member_id") + ":");
					// System.out.print(rs.getString("member_name") + ":");
					// System.out.println(rs.getString("member_pass"));
					if (rs.getString("member_pass").equals(member_pass)) {
						isCheckpass = true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// カテゴリ一覧をListとして返す
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}

		// System.out.print(isCheckemail && isCheckpass);

		return isCheckemail && isCheckpass;
	}

	public void withdrawMember(HttpSession session) throws DAOException { 
		MemberBean bean = (MemberBean) session.getAttribute("member_info");
		String member_email = bean.getMember_email();
		String sql = "UPDATE member SET secession_date = ? WHERE member_email= ?";
		Date d =new Date();

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// プレースホルダーの設定
			st.setDate(1, new java.sql.Date(d.getTime()));
			System.out.println(new java.sql.Date(d.getTime()));
			st.setString(2, member_email);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}
	}

}
