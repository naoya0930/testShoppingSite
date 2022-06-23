package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import la.bean.ItemBean;

public class SellerDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:sample";
	private String user = "student";
	private String pass = "himitu";

	public SellerDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	// テストコード
	public List<ItemBean> listItemByMenberID_test(int memberID) {
		List<ItemBean> sl = new ArrayList<ItemBean>();

		sl.add(new ItemBean(1, "わたあめ", 1, 2000, 1000, new Date(), 5));
		sl.add(new ItemBean(1, "わたあめ", 1, 2000, 1000, new Date(), 5));

		return sl;

	}

	//
	public List<ItemBean> listItemByMenberID(int memberID) throws DAOException {
		String sql = "SELECT * FROM item WHERE seller_id = ? ORDER BY item_id";

		List<ItemBean> sl = new ArrayList<ItemBean>();
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// カテゴリの設定
			st.setInt(1, memberID);

			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果に必要なの取得および表示
				while (rs.next()) {

					int item_id = rs.getInt("item_id");
					String item_name = rs.getString("item_name");
					int category_id = rs.getInt("category_id");
					int stock = rs.getInt("stock");
					int price = rs.getInt("price");
					Date start_date = rs.getDate("start_date");
					int member_id = rs.getInt("seller_id"); // seller_id
					// nullable
					Date stop_date = rs.getDate("stop_date");

					ItemBean bean = new ItemBean(item_id, item_name, category_id, stock, price, start_date, member_id,
							stop_date);
					// return bean; // 主キーに該当するレコードを返す
					sl.add(bean);
				}
				// return null; // 主キーに該当するレコードなし
				return sl;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	// 新しくデータを追加する
	public void createNewItem(int category_id, String item_name, int price, int stock, int seller_id)
			throws SQLException {
		Date d = new Date();
		String sql = "INSERT INTO item(category_id,item_name,price,stock,seller_id,start_date)"
				+ " VALUES(? ,?,?,?,?,?)";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得

				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, category_id);
			st.setString(2, item_name);
			st.setInt(3, price);
			st.setInt(4, stock);
			st.setInt(5, seller_id);
			st.setDate(6, new java.sql.Date(d.getTime()));
			int n = st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("レコードの取得に失敗しました。");
		}
	}

	// 既存のデータの個数を更新する
	public void updateItem(String columnStr, String value, int memberID) throws SQLException {
		String sql = "";
		if (columnStr.equals("price")) {
			sql = "UPDATE item SET price = ? WHERE item_id=?";
		} else if (columnStr.equals("stock")) {
			sql = "UPDATE item SET stock = ? WHERE item_id=?";
		}
		// TODO:カテゴリID追加？

		//////////////////////// この部分を書いていく/////////////////////////////////
		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// カテゴリの設定
			// st.setString(1, columnStr);
			st.setInt(1, Integer.parseInt(value));
			st.setInt(2, memberID);
			int n = st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("レコードの取得に失敗しました。");
		}
	}

	public static void main(String arg[]) {
		try {
			SellerDAO dao = new SellerDAO();
			dao.updateItem("price", "300", 1);
		} catch (Exception e) {

		}
	}

	// 既存のデータにstop_dateを追加する
	public void setStopDateByID(int member_id) throws SQLException {
		Date d = new Date();
		String sql = "UPDATE item SET stop_date = ? WHERE item_id=?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setDate(1, new java.sql.Date(d.getTime()));
			st.setInt(2, member_id);
			int n = st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("レコードの取得に失敗しました。");
		}
	}
}