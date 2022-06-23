package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ItemBean;

public class SearchDAO {
    // URL、ユーザ名、パスワードの準備
    private String url = "jdbc:postgresql:sample";
    private String user = "student";
    private String pass = "himitu";

    public SearchDAO() throws DAOException {
        try {
            // JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの登録の失敗しました。");
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
}
