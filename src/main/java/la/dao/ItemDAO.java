package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import la.bean.CartBean;
import la.bean.CategoryBean;
import la.bean.ItemBean;

public class ItemDAO {
	// URL、ユーザ名、パスワードの準備
	private String url = "jdbc:postgresql:sample";
	private String user = "student";
	private String pass = "himitu";

	public ItemDAO() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録に失敗しました。");
		}
	}

	public List<CategoryBean> findAllCategory() throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM category ORDER BY category_id";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);
				// SQLの実行
				ResultSet rs = st.executeQuery();) {
			// 結果の取得および表示
			List<CategoryBean> list = new ArrayList<CategoryBean>();
			while (rs.next()) {
				int category_id = rs.getInt("category_id");
				String category_name = rs.getString("category_name");
				CategoryBean bean = new CategoryBean(category_id, category_name);
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public List<ItemBean> findByCategory(int category_id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM item WHERE category_id = ? ORDER BY item_id";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// カテゴリの設定
			st.setInt(1, category_id);

			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				List<ItemBean> list = new ArrayList<ItemBean>();
				while (rs.next()) {
					int item_id = rs.getInt("item_id");
					String item_name = rs.getString("item_name");
					int price = rs.getInt("price");
					int stock = rs.getInt("stock");
					Date stop_date=rs.getDate("stop_date");
					Date start_date=rs.getDate("start_date");
					ItemBean bean = new ItemBean(item_id, item_name, price, stock, stop_date, start_date);
					list.add(bean);
				}
				// 商品一覧をListとして返す
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public ItemBean findByPrimaryKey(int key) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM item WHERE item_id = ?";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			// カテゴリの設定
			st.setInt(1, key);

			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				if (rs.next()) {
					int item_id = rs.getInt("item_id");
					String item_name = rs.getString("item_name");
					int price = rs.getInt("price");
					int stock = rs.getInt("stock");
					ItemBean bean = new ItemBean(item_id, item_name, price, stock);
					return bean; // 主キーに該当するレコードを返す
				} else {
					return null; // 主キーに該当するレコードなし
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
	
	public void updateSales_figures(ItemBean item1, CartBean cart) throws DAOException {
		// SQL文の作成
		String sql = "UPDATE item SET sales_figures = ? WHERE item_id = ?";

		try (// データベースへの接続
				 Connection con = DriverManager.getConnection(url, user, pass);
				 // PreparedStatementオブジェクトの取得
				 PreparedStatement st = con.prepareStatement(sql);) {
				// プレースホルダーの設定
	        	List<ItemBean> items = cart.getItems();
				for (ItemBean item : items) {
				    st.setInt(1, (int)(item.getSales_figures()+item.getQuantity()));
				    st.setInt(2, item.getItem_id());
				    st.executeUpdate();
				}
				// SQLの実行
				st.executeUpdate();
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました。");
	        }
	}
	
	public List<ItemBean> getItemRanking() throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM item ORDER BY sales_figures DESC";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {

			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				List<ItemBean> list = new ArrayList<ItemBean>();
				while (rs.next()) {
					int item_id = rs.getInt("item_id");
					String item_name = rs.getString("item_name");
					int price = rs.getInt("price");
					int stock = rs.getInt("stock");
					Date stop_date=rs.getDate("stop_date");
					Date start_date=rs.getDate("start_date");
					int sales_figures=rs.getInt("sales_figures");
					ItemBean bean = new ItemBean(item_id, item_name, price, stock, stop_date, start_date,sales_figures);
					list.add(bean);
				}
				// 商品一覧をListとして返す
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
	
	public List<ItemBean> getByCategoryRanking(int category_id) throws DAOException {
		// SQL文の作成
		String sql = "SELECT * FROM item WHERE category_id = ? ORDER BY sales_figures DESC";

		try (// データベースへの接続
				Connection con = DriverManager.getConnection(url, user, pass);
				// PreparedStatementオブジェクトの取得
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, category_id);

			try (// SQLの実行
					ResultSet rs = st.executeQuery();) {
				// 結果の取得および表示
				List<ItemBean> list = new ArrayList<ItemBean>();
				while (rs.next()) {
					int item_id = rs.getInt("item_id");
					String item_name = rs.getString("item_name");
					int price = rs.getInt("price");
					int stock = rs.getInt("stock");
					Date stop_date=rs.getDate("stop_date");
					Date start_date=rs.getDate("start_date");
					int sales_figures=rs.getInt("sales_figures");
					ItemBean bean = new ItemBean(item_id, item_name, price, stock, stop_date, start_date,sales_figures);
					list.add(bean);
				}
				// 商品一覧をListとして返す
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの取得に失敗しました。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public List<ItemBean> searchItem(int category_id, String searchString)
			                                    throws DAOException {
		String sql;
    	if (category_id==-1) {
			sql = "SELECT * FROM item where item_name LIKE ? ";
		} else {
			sql = "SELECT * FROM item where item_name LIKE ? AND category_id = ?";
		}

        try (// データベースへの接続
             Connection con = DriverManager.getConnection(url, user, pass);
			 // PreparedStatementオブジェクトの取得
			 PreparedStatement st = con.prepareStatement(sql);) {
			if (category_id==-1) {
				st.setString(1, "%" + searchString + "%");
			} else {
				st.setString(1, "%" + searchString + "%");
				st.setInt(2, category_id);
			}
        	
        	try ( // SQLの実行
				 ResultSet rs = st.executeQuery();) {
				List<ItemBean> list = new ArrayList<ItemBean>();
				while (rs.next()) {
					int item_id = rs.getInt("item_id");
					String item_name = rs.getString("item_name");
					int item_price = rs.getInt("price");
					int stock = rs.getInt("stock");
					ItemBean itemBean = new ItemBean(item_id, item_name, item_price, stock);
					list.add(itemBean);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました。");
			}
        } catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
        }

    }

	// テストコード
	// テストコード
	// テストコード
	// テストコード
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
					
					ItemBean bean = new ItemBean(item_id, item_name, category_id, stock, price, start_date,
							member_id,stop_date);
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
	public void createNewItem(int category_id,String item_name
			, int price, int stock,int seller_id) throws SQLException {
		Date d =new Date();
		String sql =  "INSERT INTO item(category_id,item_name,price,stock,seller_id,start_date)"
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
			ItemDAO dao = new ItemDAO();
			dao.updateItem("price", "300", 1);
		} catch (Exception e) {

		}
	}

	// 既存のデータにstop_dateを追加する
	public void setStopDateByID(int member_id) throws SQLException{
		Date d =new Date();
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