package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.MemberBean;
import la.dao.DAOException;
import la.dao.MemberDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//public static final String USER = "Jack";
	//public static final String PASS = "abc";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		// 書き出し装置をresponseオブジェクトから取得
		//PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
		if(action.equals("login")) {
			String email = request.getParameter("member_email");
			String pw = request.getParameter("member_pass");
			Boolean isLogin = false;
			MemberBean bean = new MemberBean();
			
			try {
				MemberDAO dao = new MemberDAO();
				isLogin = dao.isCheckLogin(email, pw);
				
				if (isLogin.equals(true)) {
					HttpSession session = request.getSession();
					session.setAttribute("isLogin", "true");
					bean = dao.setMemberBean(email);
					//System.out.println(bean.getMember_name());
					//session.setAttribute("member_email", email);
					session.setAttribute("member_info", bean);
					gotoPage(request, response, "/top.jsp");
				} else {
					gotoPage(request, response, "/login.jsp");
				}
				
			} catch (DAOException e) {
				// TODO: handle exception
				e.printStackTrace();
				request.setAttribute("message", "内部エラーが発生しました。");
				RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
				rd.forward(request, response);
			}
			
			//if(name.equals(USER) && pw.equals(PASS)) {
				//HttpSession session = request.getSession();
				//session.setAttribute("isLogin", "true");
				//out.print("ログイン成功！");
			//}else {
				//out.print("ログイン失敗！");
			//}
			
		}else if(action.equals("logout")){
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
				gotoPage(request, response, "/logout.jsp");
			}
		}
	}
	
	private void gotoPage(HttpServletRequest request,
            HttpServletResponse response, String page) throws ServletException,
            IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
