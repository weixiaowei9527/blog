package houtai;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

import bean.UserBean;
import dao.DBDao;

@WebServlet("/updateuser")
public class updateuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		DBDao dao = new DBDao();
		UserBean userBean = new UserBean();
		
		UserBean user = (UserBean)request.getSession().getAttribute("user");
		int age = Integer.parseInt(request.getParameter("nianling"));
		
		String nickname = new String(request.getParameter("mingzi"));
		
		String email = new String(request.getParameter("youxiang"));
		
		System.out.println(age);
		userBean.setAge(age);
		userBean.setNickname(nickname);
		userBean.setEmail(email);
		userBean.setAccount(user.getAccount());
		userBean.setPassword(user.getPassword());
		userBean.setSex(user.getSex());
		userBean.setZhucetime(user.getZhucetime());
		
		try {
			boolean b = dao.update(userBean);
			//PrintWriter out = response.getWriter();

			if(b){
				response.getWriter().println("<p>修改成功，点击进入后台<a href=\"" + request.getContextPath() + "/houtai/index_h.jsp\">登录</a></p>");
	        	request.getSession().setAttribute("user", userBean);
				/*修改成功或失败后看看要转到哪个页面？ 再填上*/
	        	//out.println("<script language='javascript'>alert('修改成功！');</script>");
	            //request.getSession().setAttribute("bianliang", user.getName());
	            /*out.println("<script language='javascript'>alert('修改成功！');window.location.href='/dengluyanzheng/zengshangai/shan.jsp';</script>");*/
	          
	        }else{  
	        	response.getWriter().println("<p>修改失败，点击返回后台重新修改<a href=\"" + request.getContextPath() + "/houtai/index_h.jsp\">登录</a></p>");
	           /* out.println("<script language='javascript'>alert('修改失败，请查找原因并重新修改！');window.location.href='/dengluyanzheng/zengshangai/shan.jsp';</script>");*/
	        	// out.println("<script language='javascript'>alert('修改失败，请查找原因并重新修改！');</script>");
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
