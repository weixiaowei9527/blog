package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ArticleBean;
import bean.CommentBean;
import dao.DBDao;

/**
 * Servlet implementation class getspecial
 */
@WebServlet("/getspecial")
public class getspecial extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		DBDao dao = new DBDao();
		List<ArticleBean> listtime = dao.getTenArticle();
		List<ArticleBean> listnum = dao.getSixArticle();
		List<ArticleBean> listcom = dao.getFiveArticle();
		/*RequestDispatcher dispatcher = request.getRequestDispatcher("/pinglun.jsp");
		dispatcher.include(request, response);*/
		for(int i = 0;i<listcom.size();i++) {
			 System.out.println(listcom.get(i).getId()+" "+listcom.get(i).getTitle());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
