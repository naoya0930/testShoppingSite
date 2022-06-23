package la.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.ItemBean;
import la.bean.MemberBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class SellerServlet
 */
@WebServlet("/SellerServlet")
public class SellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SellerServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// ログインしているか確認
		response.setContentType("text/html;charset=UTF-8");
		// PrintWriter out = response.getWriter();

		// セッション領域の取得
		HttpSession session = request.getSession();
		// @SuppressWarnings("unchecked")
		String loginStr = (String) session.getAttribute("isLogin");
		// ログインが実行されていない場合
		if (loginStr != null) {

			MemberBean bean = (MemberBean) session.getAttribute("member_info");
			try {
				// int categoryCode = Integer.parseInt(request.getParameter("code"));
				ItemDAO dao = new ItemDAO();
				// DBのitemの内容を持ってくる

				List<ItemBean> list = dao.listItemByMenberID(bean.getMember_id());
				// 出荷されていないものを除外する
				List<ItemBean> displayedList = new ArrayList<ItemBean>();
				List<ItemBean> noDisplayedList = new ArrayList<ItemBean>();
				for (ItemBean l : list) {
					if (l.getStop_date() == null) {
						displayedList.add(l);
					} else {
						noDisplayedList.add(l);
					}
				}
				// category_idとcategoryの変換
				// System.out.println(list.size()+"");
				request.setAttribute("sellerItems", displayedList);
				gotoPage(request, response, "/seller.jsp");
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("message", "内部エラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}
			// Listをリクエストスコープに入れてJSPへフォーワードする
		} else {

		}
		// ログインしている場合
		// DAOで自身の登録item情報を持ってくる

		// itemを渡す

		// ページ遷移
		// gotoPage(request, response, "/seller.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
