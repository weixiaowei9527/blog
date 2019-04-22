package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import bean.UserBean;
import dao.DBDao;
import utility.BTime;


/**
 * Servlet implementation class res
 */
@WebServlet("/res")
public class res extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");
		String psd = request.getParameter("password");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		try{
			int age1 = Integer.parseInt(age);
			UserBean user = new UserBean();
			user.setNickname(name);
			user.setPassword(psd);
			user.setEmail(email);
			user.setSex(sex);
			user.setAge(age1);
			user.setZhucetime(BTime.getTimeShort());
			System.out.println(name+" "+psd+" "+sex+" "+age+" "+email);
			DBDao dao = new DBDao();
			int num = dao.addUser(user);
			if(num == 0) {
				response.getWriter().println("<p>ע��ʧ�ܣ��������<a href=\""
			+ request.getContextPath() + "/res.jsp\">ע��</a></p>");
			}
			else {
				response.getWriter().println("<p>ע��ɹ�</p>"
						+ "<br/><p>���idΪ" + dao.findUserByName(name).getAccount()+ "</p><br><p>���<a href=\""
						+ request.getContextPath() + "/index.jsp\">��¼</a></p>");
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
