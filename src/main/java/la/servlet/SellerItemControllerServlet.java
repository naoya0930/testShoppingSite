package la.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.MemberBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class SellerItemControllerServlet
 */
@WebServlet("/SellerItemControllerServlet")
public class SellerItemControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellerItemControllerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MemberBean bean = (MemberBean) session.getAttribute("member_info");
		// actionで更新か削除か切り分ける
		try {
			// パラメータの解析
			String action = request.getParameter("action");
			if (action == null || action.length() == 0 || action.equals("top")) {
				// topまたはパラメータなしの場合はトップページを表示
				gotoPage(request, response, "/top.jsp");
			} else if (action.equals("update")) {
				// 更新が「在庫」か「値段」か調べる
				//String column = request.getParameter("column");
				//int stock = Integer.parseInt(request.getParameter("stock"));

				// SQL 実行
				ItemDAO dao = new ItemDAO();
				try {
				dao.updateItem(request.getParameter("column"), request.getParameter("c_value"),
						Integer.parseInt(request.getParameter("id")));
				request.setAttribute("isChanged", request.getParameter("item_name")+"の"+request.getParameter("column")+"を"+
						request.getParameter("c_value")+"に更新しました。");
				gotoPage(request, response, "/SellerServlet");
				
				}catch(SQLException e){
					e.printStackTrace();
				}

				// int categoryCode = Integer.parseInt(request.getParameter("code"));
				// ItemDAO dao = new ItemDAO();
				// List<ItemBean> list = dao.findByCategory(categoryCode);
				// Listをリクエストスコープに入れてJSPへフォーワードする
				// request.setAttribute("items", list);
				// gotoPage(request, response, "/list.jsp");
			} else if (action.equals("stop")) {
				// 取引停止日を追加する
				ItemDAO dao = new ItemDAO();
				try {
				dao.setStopDateByID(
						Integer.parseInt(request.getParameter("id")));
					
				request.setAttribute("isChanged", request.getParameter("item_name")+"の取引を停止しました。売買を再開する場合は新規作成から作成してください");
				gotoPage(request, response, "/SellerServlet");
				
				}catch(SQLException e){
					e.printStackTrace();
				}
			} else if(action.equals("add")){
				// 新しいレコードを追加する
				ItemDAO dao = new ItemDAO();
				try {
					int category_id = Integer.parseInt(request.getParameter("category_id"));
					String item_name = request.getParameter("item_name");
					int price = Integer.parseInt(request.getParameter("price"));
					int stock = Integer.parseInt(request.getParameter("stock"));
					
					//TODO: ここは会員のIDに変更
					int member_id=bean.getMember_id();
					dao.createNewItem(
						category_id,
						item_name,
						price,
						stock,
						member_id
						);
				//(int category_id,String item_name, int price, int stock,int seller_id)
					
				request.setAttribute("isChanged", "データを追加しました："+request.getParameter("item_name"));
				gotoPage(request, response, "/SellerServlet");
				
				}catch(SQLException e){
					e.printStackTrace();
				}
				
			}else{
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
			

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
