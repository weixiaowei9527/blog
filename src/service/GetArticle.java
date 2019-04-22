package service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.model.core.ID;

import bean.ArticleBean;
import dao.DBDao;

/**
 * Servlet implementation class GetArticle
 */
@WebServlet("/GetArticle")
public class GetArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String strid = request.getParameter("id");
		int id = Integer.parseInt(strid);
		System.out.println("getarticle:" + id);
		DBDao dao = new DBDao();
		ArticleBean articleBean = dao.getArticle(id);
		request.getSession().setAttribute("article", articleBean);
		/*RequestDispatcher dispatcher = request.getRequestDispatcher("/pinglun.jsp");
		dispatcher.include(request, response);*/
		response.sendRedirect(request.getContextPath() + "/blog.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
