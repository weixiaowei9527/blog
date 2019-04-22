package houtai;

/*任务是将当前title的文章的评论全部展示出来*/
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
		/*这个地方其实不用这么麻烦 不用从文章表里查id 直接从session里获取id就行 */
		/*从页面传入title题目 通过题目查询id 再通过id查询所有评论信息*/
		String strid = request.getParameter("id");
		int id = Integer.parseInt(strid);
		DBDao dao = new DBDao();
		
		
		/*这样将查询的list集合中的comment信息放入session*/
		List<CommentBean> list = dao.getDiscuss(id);
		request.getSession().setAttribute("discuss", list);
		//System.out.println("yunxing");
		//System.out.println(list.size());
		/*PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		list = (ArrayList<CommentBean>) session.getAttribute("list");*/
		
		/*for(int i = 0; i < list.size(); i++) {
			out.write("评论内容" + list.get(i).getTxt());
			out.write("评论时间" + list.get(i).getTime());
			out.write("评论用户昵称" + list.get(i).getUser());
			
			String url = "#?id=" + list.get(i).getId();
			
			这个url地址加上删除评论的servlet
			
			out.write("<a href='" + url + "'>删除此评论</a><br />");
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
