package service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jrockit.jfr.RequestableEvent;

import bean.CommentBean;
import dao.DBDao;
import utility.BTime;

/**
 * Servlet implementation class GetDiscuss
 */
@WebServlet("/GetDiscuss")
public class GetDiscuss extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String user = request.getParameter("user");
		String txt = request.getParameter("txt");
		String code1 = request.getParameter("key");
		String code = (String)request.getSession().getAttribute("check-code");
		System.out.println(txt+code+user);
		if(code1.equals(code)) {
			String strid = request.getParameter("id");
			request.setAttribute("id", strid);
			CommentBean comment = new CommentBean();
			comment.setTxt(txt);
			comment.setTime(BTime.getTime());
			comment.setUser(user);
			comment.setId(Integer.parseInt(strid));
			DBDao dao = new DBDao();
			dao.addDiscuss(comment);
			/*RequestDispatcher dispatcher = request.getRequestDispatcher("/PutDiscuss");
			dispatcher.include(request, response);*/
			response.sendRedirect(request.getContextPath()+"/discuss_success.jsp");
		}
		else {
			response.sendRedirect(request.getContextPath()+"/discuss_false.jsp");
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
