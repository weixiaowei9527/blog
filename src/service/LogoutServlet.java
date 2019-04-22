package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import bean.UserBean;


/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		/*List<Book> books = (ArrayList<Book>) session.getAttribute("cart");
		// 把图书写入数据库中
		
		if(books != null) {
			UserBean userBean = (UserBean) session.getAttribute("user");
			System.out.println("购物车中的书籍数目" + books.size());
			int rs = 0;
			AccessDB accessDB  = new AccessDB();
			
			for(Book book: books) {
				
				rs = accessDB.addBookToCart(userBean.getU_name(), book.getB_name());
			}
			accessDB.closeConnection();
			System.out.println("成功插入" + rs + "条记录");
		}
		
		//用户注销
		request.getSession().removeAttribute("user");
		//从客户端删除自动登陆的cookie
		Cookie cookie = new Cookie("autologin", "msg");
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(0);
		response.addCookie(cookie);*/
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
