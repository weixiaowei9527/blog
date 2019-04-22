package houtai;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBDao;

@WebServlet("/deletearticle")
public class deletearticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*在上一个传过来的id应该确定其不为空 为空不能传过来 在这里就不判断了*/
		/*id 是传过来的那个*/
		response.setContentType("text/html;charset=utf-8");
		int id = Integer.parseInt(request.getParameter("id"));
		DBDao dao = new DBDao();
		try {
			boolean b = dao.deletearticle(id);
			 PrintWriter out = response.getWriter();   
		        if(b){
		        	/*删除成功或失败后看看要转到哪个页面？ 再填上*/
		        	out.println("<script language='javascript'>alert('删除成功！');</script>");
		        	response.getWriter().println("<p>删除成功，返回首页<a href=\"" + request.getContextPath() + "/houtai/index_h.jsp\">登录</a></p>");
		            //request.getSession().setAttribute("bianliang", user.getName());
		            /*out.println("<script language='javascript'>alert('删除成功！');window.location.href='/dengluyanzheng/zengshangai/shan.jsp';</script>");*/
		        }else {
		        	/* out.println("<script language='javascript'>alert('删除失败，请查找原因并重新删除！');window.location.href='/dengluyanzheng/zengshangai/shan.jsp';</script>");*/
		        	 out.println("<script language='javascript'>alert('删除失败，请查找原因并重新删除！');</script>");
		        	 response.getWriter().println("<p>删除失败，点击进入后台重新删除<a href=\"" + request.getContextPath() + "/houtai/index_h.jsp\">登录</a></p>");
		        }
		}catch (Exception e) {
			// TODO: handle exception
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
