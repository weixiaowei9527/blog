package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.bind.v2.model.runtime.RuntimeNonElementRef;

import bean.ArticleBean;
import bean.CommentBean;
import bean.NoteBean;
import bean.UserBean;
import utility.DBUtils;

/*数据库操作了*/
public class DBDao {
	/*
	 * public static DBDao dao; static { dao = new DBDao(); }
	 */



	public int addArticle(ArticleBean article) {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "insert into " + "article"
				+ " (title, author, des, content, type, time, number,id) values (?,?,?,?,?,?,?,?)";
		int rs = 0;
		try {
			rs = runner.update(sql, new Object[] { article.getTitle(), article.getAuthor(), article.getDes(),
					article.getContent(), article.getType(), article.getTime(), article.getNumber() ,article.getId()});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入文章失败");
			e.printStackTrace();
		}
		return rs;
	}

	public ArticleBean selectArticleBean(ArticleBean ArticleBean) {
		System.out.println("书籍名字是");
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from " + "ArticleBean" + " where b_name=?";
		ArticleBean reArticleBean = null;
		System.out.println("书籍名字是" + ArticleBean.getTitle());
		try {
			reArticleBean = (ArticleBean) runner.query(sql, new BeanHandler<ArticleBean>(ArticleBean.class),
					ArticleBean.getTitle());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入图书失败");
			e.printStackTrace();
		}
		return reArticleBean;
	}

	public int deleteArticleBean(ArticleBean ArticleBean) {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "delete from " + "ArticleBean" + " where b_name=?";
		int rs = 0;
		try {
			rs = runner.update(sql, ArticleBean.getTitle());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("删除图书失败");
			e.printStackTrace();
		}
		return rs;
	}

	public int updateArticleBean(ArticleBean ArticleBean) {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "update " + "ArticleBean" + " set b_desc=?" + " where b_name=?";
		int rs = 0;
		try {
			rs = runner.update(sql, new Object[] { ArticleBean.getContent(), ArticleBean.getTitle() });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("删除图书失败");
			e.printStackTrace();
		}
		return rs;
	}

	public List<ArticleBean> findallArticleBeans() {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from " + "ArticleBean";
		List<ArticleBean> ArticleBeans = null;
		try {
			ArticleBeans = runner.query(sql, new BeanListHandler<ArticleBean>(ArticleBean.class));
			System.out.println(ArticleBeans.size());
		} catch (SQLException e) {
			// todo auto-generated catch block
			System.out.println("获取arraylist<ArticleBean>ArticleBeans失败");
			e.printStackTrace();
		} finally {
			try {
				runner.getDataSource().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ArticleBeans;
	}

	// 通过用户名从数据库中获得用户
	public UserBean findUser(String u_name) {
		UserBean userBean = null;
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from user where account=?";
		try {
			userBean = runner.query(sql, new BeanHandler<UserBean>(UserBean.class), u_name);
			System.out.println(userBean.getAccount() + "   " + userBean.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				runner.getDataSource().getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userBean;
	}

	// 向数据库中增加用户
	public int addUser(UserBean userBean) {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "insert into user(password,nickname,email,sex,age,zhucetime)values(?,?,?,?,?,?)";
		int result = 0;
		try {
			result = runner.update(sql,
					new Object[] {  userBean.getPassword(), userBean.getNickname() ,userBean.getEmail(),userBean.getSex(),userBean.getAge(),userBean.getZhucetime()});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			System.out.println("插入失败");
		}
		return result;
	}

	// 删除用户
	public int removeUser(UserBean userBean) {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "delete from user where account=?";
		int result = 0;
		System.out.println(userBean.getAccount());
		try {
			result = runner.update(sql, userBean.getAccount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// 获得点赞数前6文章
	public List<ArticleBean> getSixArticle() {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from article order by number DESC LIMIT 6;";
		List<ArticleBean> articles = null;
		try {
			articles = runner.query(sql, new BeanListHandler<ArticleBean>(ArticleBean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articles;
	}

	// 获得发表时间前10文章
	public List<ArticleBean> getTenArticle() {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from article order by time DESC LIMIT 10;";
		List<ArticleBean> articles = null;
		try {
			articles = runner.query(sql, new BeanListHandler<ArticleBean>(ArticleBean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articles;
	}

	// 获得评论数目前5文章
	public List<ArticleBean> getFiveArticle() {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select article.* from article,comment where article.id = comment.id  group by comment.id order by count(comment.id);";
		List<ArticleBean> articles = null;
		try {
			articles = runner.query(sql, new BeanListHandler<ArticleBean>(ArticleBean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articles;
	}

	// 获得所有文章
	public List<ArticleBean> getAllArticles() {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from article";
		List<ArticleBean> articles = null;
		try {
			articles = runner.query(sql, new BeanListHandler<ArticleBean>(ArticleBean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articles;
	}

	// 通过文章题目，作者获得文章全部内容
	public ArticleBean getArticle(String title) {
		ArticleBean articleBean = null;
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from article where title=?";
		try {
			articleBean = runner.query(sql, new BeanHandler<ArticleBean>(ArticleBean.class), title);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articleBean;
	}
	// 通过id获得文章

	public ArticleBean getArticle(int id) {
		ArticleBean articleBean = null;
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from article where id=?";
		try {
			articleBean = runner.query(sql, new BeanHandler<ArticleBean>(ArticleBean.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articleBean;
	}

	// 添加评论
	public int addDiscuss(CommentBean commentBean) {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "insert into comment(user, txt, id, time)values(?,?,?,?)";
		int rs = 0;
		try {
			rs = runner.update(sql, new Object[] { commentBean.getUser(), commentBean.getTxt(), commentBean.getId(),
					commentBean.getTime() });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	// 获得某个文章的评论
	public List<CommentBean> getDiscuss(int id) {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from comment where id=?";
		List<CommentBean> comments = null;
		try {
			comments = runner.query(sql, new BeanListHandler<CommentBean>(CommentBean.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}

	// 修改观看数目
	public int changeNumber(String title) {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "update article set number=number+1 where title=?";
		int rs = 0;
		try {
			rs = runner.update(sql, title);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	

	public int getMaxNumber() {
		Connection connection = null;
		try {
			connection = DBUtils.getDatasource().getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int count = 0;
		String sql = "select count(*) from article";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = statement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	public boolean insertUser(UserBean user) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "insert into user(password,nickname) values(?,?)";
		int num = runner.update(sql, new Object[] { user.getPassword(), user.getNickname() });
		if (num > 0)
			return true;
		else
			return false;
	}

	public UserBean findUser(int id) {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from user where account = ?";
		UserBean user1 = null;
		try {
			user1 = runner.query(sql, new BeanHandler<UserBean>(UserBean.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user1;
	}
	public UserBean findUserByName(String name) {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from user where nickname = ?";
		UserBean user1 = null;
		try {
			user1 = runner.query(sql, new BeanHandler<UserBean>(UserBean.class), name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user1;
	}

	// 返回时间轴
	public List<NoteBean> findAllNotes(int id) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from notes where id=? order by time";
		List<NoteBean> list = runner.query(sql, new BeanListHandler<NoteBean>(NoteBean.class), id);
		return list;
	}

	// 通过作者id获得其全部文章
	public List<ArticleBean> getSomearticle(int id) {
		List<ArticleBean> articles = null;
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from article where author in (select nickname from user where account =?)";
		try {
			articles = runner.query(sql, new BeanListHandler<ArticleBean>(ArticleBean.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articles;
	}

	// 通过作者 获得该作者的所有文章ss
	public List<ArticleBean> findauthorArticle(String author) {
		List<ArticleBean> authorarticle = null;
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from article where author = ?";
		try {
			authorarticle = runner.query(sql, new BeanListHandler<ArticleBean>(ArticleBean.class), author);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(authorarticle);
		return authorarticle;
	}

	/* 修改用户密码 */
	public boolean updatepassword(UserBean UserBean) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		/*
		 * //写SQL语句 String sql = "update info set name = ?, price = ? where id = ?";
		 * //调用方法 int num = runner.update(sql, new Object[] { info.getName(),
		 * info.getPrice(), info.getId() });
		 */
		// 先改密码 然后再加上别的信息
		String sql = "update user set password = ? where account = ?";
		int num = runner.update(sql, new Object[] { UserBean.getPassword(), UserBean.getAccount() });
		if (num > 0)
			return true;
		else
			return false;
	}

	/* 修改用户信息 */
	public boolean update(UserBean UserBean) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		/*
		 * //写SQL语句 String sql = "update info set name = ?, price = ? where id = ?";
		 * //调用方法 int num = runner.update(sql, new Object[] { info.getName(),
		 * info.getPrice(), info.getId() });
		 */
		// 先改密码 然后再加上别的信息
		String sql = "update user set nickname = ?, email = ?, age = ? where account = ?";
		int num = runner.update(sql,
				new Object[] { UserBean.getNickname(), UserBean.getEmail(), UserBean.getAge(), UserBean.getAccount() });
		if (num > 0)
			return true;
		else
			return false;
	}



	//删除某个文章
			public boolean deletearticle(int id) throws SQLException {
				QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
				String sql = "delete from article where id = ?";
				int num = runner.update(sql, id);
				if(num > 0) {
					return true;
				}else {
					return false;
				}
			}

	//删除某个评论
		public boolean deletepinglun(String user) {
			QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
			String sql = "delete from comment where user = ?";
			int num = 0;
			try {
				num = runner.update(sql,user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(num > 0) {
				return true;
			}else {
				return false;
			}
		}
}
