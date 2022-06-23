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
@WebServlet("/UpdateMemberServlet")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
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
		if(action == null || action.length() == 0) {
			gotoPage(request, response, "/updateMember.jsp");
		}else if(action.equals("update")) {
			String name = request.getParameter("member_name");
			String address = request.getParameter("member_address");
			String tel1 = request.getParameter("member_tel_1");
			String tel2 = request.getParameter("member_tel_2");
			String tel3 = request.getParameter("member_tel_3");
			String tel = tel1 + tel2 + tel3;
			String email = request.getParameter("member_email");
			String pw = "";
			//Boolean isLogin = false;
			//System.out.println(name);
			//System.out.println(address);
			
			try {
				MemberDAO dao = new MemberDAO();
				MemberBean bean = new MemberBean(name, address, tel, email, pw);
				HttpSession session = request.getSession(false);
				dao.updateMemberInfo(bean, session);
				
				session.setAttribute("isLogin", "true");
				bean = dao.setMemberBean(email);
				session.setAttribute("member_info", bean);
				gotoPage(request, response, "/successUpdate.jsp");
				
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
