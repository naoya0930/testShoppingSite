package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, 
          HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getParameter("action");
            if (action == null || action.length() == 0)  {
                gotoPage(request, response, "/top.jsp");
                //TODO エラーページにする？
            } else if (action.equals("search")) {
				int category_id = Integer.parseInt(request.getParameter("category_id"));
			    String searchString = request.getParameter("searchString");
            	if (searchString == null || searchString.length()==0) {
					gotoPage(request, response, "/top.jsp");
					//TODO エラーページにする？
				} else {
					ItemDAO itemDAO = new ItemDAO();
					List<ItemBean> list = itemDAO.searchItem(category_id, searchString);
					request.setAttribute("items", list);
					request.setAttribute("category_id", category_id);
					request.setAttribute("searchString", searchString);
					gotoPage(request, response, "/searchResultList.jsp");
				}
            } else { 
                //actionの値が不正
                request.setAttribute("message", "正しく操作してください。");
                gotoPage(request, response, "/errInternal.jsp");
            }
        } catch (DAOException e) {
            e.printStackTrace();
            request.setAttribute("message", "内部エラーが発生しました。");
            gotoPage(request, response, "/errInternal.jsp");
        }
    }

    private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
