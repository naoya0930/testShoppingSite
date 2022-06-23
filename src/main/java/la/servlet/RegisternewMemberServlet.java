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
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet("/RegisternewMemberServlet")
public class RegisternewMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisternewMemberServlet() {
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
		
		String action = request.getParameter("action");
		if(action.equals("register")) {
			String name = request.getParameter("member_name");
			String address = request.getParameter("member_address");
			String tel1 = request.getParameter("member_tel_1");
			String tel2 = request.getParameter("member_tel_2");
			String tel3 = request.getParameter("member_tel_3");
			String tel = tel1 + tel2 + tel3;
			String email = request.getParameter("member_email");
			String pw = request.getParameter("member_pass");
			//Boolean isLogin = false;
			//System.out.println(name);
			//System.out.println(address);
			
			try {
				MemberDAO dao = new MemberDAO();
				MemberBean bean = new MemberBean(name, address, tel, email, pw);
				dao.saveMemberInfo(bean);
				bean = dao.setMemberBean(email);
				
				HttpSession session = request.getSession();
				session.setAttribute("isLogin", "true");
				session.setAttribute("member_info", bean);
				gotoPage(request, response, "/successRegistration.jsp");
				
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
			
			//if(name.equals(USER) && pw.equals(PASS)) {
				//HttpSession session = request.getSession();
				//session.setAttribute("isLogin", "true");
				//out.print("ログイン成功！");
			//}else {
				//out.print("ログイン失敗！");
			//}
			
			//else if(action.equals("logout")){
			//HttpSession session = request.getSession(false);
			//if (session != null) {
				//session.invalidate();
				//gotoPage(request, response, "/logout.jsp");
				//out.print("ログアウトしました");
				//session.setAttribute("isLogin", "false");
			//}
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
