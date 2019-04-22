package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.model.core.ID;

import bean.UserBean;

import dao.DBDao;


/**
 * Servlet implementation class login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String strid = request.getParameter("account");
		int id = Integer.parseInt(strid);
		String psd = request.getParameter("password");

		DBDao dao = new DBDao();
		UserBean user1 = dao.findUser(id);
		if(user1 != null) {
			if (psd.equals(user1.getPassword())) {
				request.getSession().setAttribute("user", user1);
				response.sendRedirect(request.getContextPath() + "/login_success.jsp");
			} else {
				response.getWriter().println("��¼ʧ��");
				response.getWriter().println("<p>���벻��ȷ��������<a href=\"index.jsp\">��¼</a></p>");
			}
		}else {
			response.getWriter().println("�û�������");
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
