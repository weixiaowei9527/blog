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
		/*����һ����������idӦ��ȷ���䲻Ϊ�� Ϊ�ղ��ܴ����� ������Ͳ��ж���*/
		/*id �Ǵ��������Ǹ�*/
		response.setContentType("text/html;charset=utf-8");
		int id = Integer.parseInt(request.getParameter("id"));
		DBDao dao = new DBDao();
		try {
			boolean b = dao.deletearticle(id);
			 PrintWriter out = response.getWriter();   
		        if(b){
		        	/*ɾ���ɹ���ʧ�ܺ󿴿�Ҫת���ĸ�ҳ�棿 ������*/
		        	out.println("<script language='javascript'>alert('ɾ���ɹ���');</script>");
		        	response.getWriter().println("<p>ɾ���ɹ���������ҳ<a href=\"" + request.getContextPath() + "/houtai/index_h.jsp\">��¼</a></p>");
		            //request.getSession().setAttribute("bianliang", user.getName());
		            /*out.println("<script language='javascript'>alert('ɾ���ɹ���');window.location.href='/dengluyanzheng/zengshangai/shan.jsp';</script>");*/
		        }else {
		        	/* out.println("<script language='javascript'>alert('ɾ��ʧ�ܣ������ԭ������ɾ����');window.location.href='/dengluyanzheng/zengshangai/shan.jsp';</script>");*/
		        	 out.println("<script language='javascript'>alert('ɾ��ʧ�ܣ������ԭ������ɾ����');</script>");
		        	 response.getWriter().println("<p>ɾ��ʧ�ܣ���������̨����ɾ��<a href=\"" + request.getContextPath() + "/houtai/index_h.jsp\">��¼</a></p>");
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
