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
		/* ��ȡԭ�����ж� ��ȡ�������޸� */
		DBDao dao = new DBDao();
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		String yuanmima = new String(request.getParameter("yuanmima"));
		String xinmima = new String(request.getParameter("xinmima"));
		//System.out.println(yuanmima + "#" + xinmima);
		//System.out.println(user);
		//PrintWriter out = response.getWriter();
		if (!user.getPassword().equals(yuanmima)) {
			response.getWriter().println("<p>ԭ�������󣬵�������̨�����޸�<a href=\"" + request.getContextPath() + "/houtai/index_h.jsp\">��¼</a></p>");
			//System.out.println("ԭ������������");
			//System.out.println(yuanmima);
			// ������ʾ ԭ���벻��2
		} else {
			user.setPassword(xinmima);// ���˸������Ϊ������
			try {
				boolean b = dao.updatepassword(user);
				if (b) {
					// ������ĳɹ� ������Ϣ
					// System.out.println("�����޸ĳɹ�");
					response.getWriter().println("<p>�޸ĳɹ�,����<a href=\"" + request.getContextPath() + "/houtai/register_h.jsp\">��¼</a></p>");
					//response.sendRedirect(request.getContextPath() + "/houtai/index_h.jsp");
				} else {
					// �������ʧ�� ������Ϣ
					// System.out.println("�����޸�ʧ��");
					
					response.getWriter().println("<p>�޸�ʧ�ܣ�������غ�̨�����޸�<a href=\"" + request.getContextPath() + "/houtai/index_h.jsp\">��¼</a></p>");
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
