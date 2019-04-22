package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ArticleBean;
import bean.UserBean;
import dao.DBDao;
import utility.BTime;
@WebServlet("/fabiao")
public class fabiao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html);charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//取得从fabiaoneirong.jsp提交过来的数据
		String title = new String(request.getParameter("title"));
		String des = new String(request.getParameter("desc"));
		String content = new String(request.getParameter("content"));
		String type = new String(request.getParameter("type"));
		ArticleBean art = new ArticleBean();
		art.setTitle(title);
		art.setDes(des);
		art.setContent(content);
		art.setType(type);
		art.setTime(BTime.getTime());
		art.setNumber(0);
		UserBean user = (UserBean)request.getSession().getAttribute("user");
		System.out.println("标题:" + art.getTitle()
		+"\n作者:" + art.getAuthor()
		+"\n描述:" + art.getDes()
		+"\n内容:" + art.getContent()
		+"\n时间:" + art.getTime()
		+"\n分类:" + art.getType()
		+"\n浏览次数:" + art.getNumber());
		System.out.println(user);
		art.setAuthor(user.getNickname());
		/*还有time发表时间 	number编号？？  id用户名*/
		/*session获得用户名 打开页面后先检查是否登录 再进行文章发表  时间怎么获得 编号自动添加吗？*/
		/*弹出的提示为什么是乱码*/
		
		DBDao dao = new DBDao();
		int maxid = dao.getMaxNumber();
		art.setId(maxid + 1);
		
		int b = dao.addArticle(art);
		if(b >= 1) {
			request.getSession().setAttribute("article", art);
			response.sendRedirect(request.getContextPath()+"/houtai/tiaozhuan.html");
		}else {
			response.sendRedirect(request.getContextPath()+"/houtai/fabiaocuowu.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
