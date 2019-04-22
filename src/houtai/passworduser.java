package houtai;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import dao.DBDao;


@WebServlet("/passworduser")
public class passworduser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		/* 获取原密码判断 获取新密码修改 */
		DBDao dao = new DBDao();
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		String yuanmima = new String(request.getParameter("yuanmima"));
		String xinmima = new String(request.getParameter("xinmima"));
		//System.out.println(yuanmima + "#" + xinmima);
		//System.out.println(user);
		//PrintWriter out = response.getWriter();
		if (!user.getPassword().equals(yuanmima)) {
			response.getWriter().println("<p>原密码有误，点击进入后台重新修改<a href=\"" + request.getContextPath() + "/houtai/index_h.jsp\">登录</a></p>");
			//System.out.println("原密码输入有误");
			//System.out.println(yuanmima);
			// 返回提示 原密码不对2
		} else {
			user.setPassword(xinmima);// 将此个密码改为新密码
			try {
				boolean b = dao.updatepassword(user);
				if (b) {
					// 如果更改成功 返回信息
					// System.out.println("密码修改成功");
					response.getWriter().println("<p>修改成功,重新<a href=\"" + request.getContextPath() + "/houtai/register_h.jsp\">登录</a></p>");
					//response.sendRedirect(request.getContextPath() + "/houtai/index_h.jsp");
				} else {
					// 如果更改失败 返回信息
					// System.out.println("密码修改失败");
					
					response.getWriter().println("<p>修改失败，点击返回后台重新修改<a href=\"" + request.getContextPath() + "/houtai/index_h.jsp\">登录</a></p>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
