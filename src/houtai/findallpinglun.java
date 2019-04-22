package houtai;

/*�����ǽ���ǰtitle�����µ�����ȫ��չʾ����*/
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.jasper.tagplugins.jstl.core.Out;
import java.util.List;
import bean.ArticleBean;
import bean.CommentBean;
import dao.DBDao;
import service.res;

@WebServlet("/findallpinglun")
public class findallpinglun extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*����ط���ʵ������ô�鷳 ���ô����±����id ֱ�Ӵ�session���ȡid���� */
		/*��ҳ�洫��title��Ŀ ͨ����Ŀ��ѯid ��ͨ��id��ѯ����������Ϣ*/
		String strid = request.getParameter("id");
		int id = Integer.parseInt(strid);
		DBDao dao = new DBDao();
		
		
		/*��������ѯ��list�����е�comment��Ϣ����session*/
		List<CommentBean> list = dao.getDiscuss(id);
		request.getSession().setAttribute("discuss", list);
		//System.out.println("yunxing");
		//System.out.println(list.size());
		/*PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		list = (ArrayList<CommentBean>) session.getAttribute("list");*/
		
		/*for(int i = 0; i < list.size(); i++) {
			out.write("��������" + list.get(i).getTxt());
			out.write("����ʱ��" + list.get(i).getTime());
			out.write("�����û��ǳ�" + list.get(i).getUser());
			
			String url = "#?id=" + list.get(i).getId();
			
			���url��ַ����ɾ�����۵�servlet
			
			out.write("<a href='" + url + "'>ɾ��������</a><br />");
			//System.out.println(list.get(i).getId() + "," + list.get(i).getName() + "," + list.get(i).getPrice());
			
		}*/
		request.getSession().setAttribute("flag", true);
		response.sendRedirect(request.getContextPath()+"/houtai/index_h.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
