package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.dao.DAOException;
import la.dao.MemberDAO;

/**
 * Servlet implementation class withdrawMemberServlet
 */
@WebServlet("/withdrawMemberServlet")
public class withdrawMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public withdrawMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
		if(action == null || action.length() == 0) {
			gotoPage(request, response, "/confirmWithdraw.jsp");
		}else if(action.equals("withdraw")) {
			
			try {
				MemberDAO dao = new MemberDAO();
				//MemberBean bean = new MemberBean(name, address, tel, email, pw);
				HttpSession session = request.getSession(false);
				dao.withdrawMember(session);
				//session.setAttribute("isLogin", "false");
				session.invalidate();
				gotoPage(request, response, "/successWithdraw.jsp");
				
				//if (isLogin.equals(true)) {
					//HttpSession session = request.getSession();
					//session.setAttribute("isLogin", "true");
					//gotoPage(request, response, "/top.jsp");
				//} else {
					//gotoPage(request, response, "/login.jsp");
				//}
				
			} catch (DAOException e) {
				// TODO: handle exception
				e.printStackTrace();
				request.setAttribute("message", "内部エラーが発生しました。");
				RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void gotoPage(HttpServletRequest request,
            HttpServletResponse response, String page) throws ServletException,
            IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

}
