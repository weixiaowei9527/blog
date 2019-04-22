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

/*���ݿ������*/
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
			System.out.println("��������ʧ��");
			e.printStackTrace();
		}
		return rs;
	}

	public ArticleBean selectArticleBean(ArticleBean ArticleBean) {
		System.out.println("�鼮������");
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from " + "ArticleBean" + " where b_name=?";
		ArticleBean reArticleBean = null;
		System.out.println("�鼮������" + ArticleBean.getTitle());
		try {
			reArticleBean = (ArticleBean) runner.query(sql, new BeanHandler<ArticleBean>(ArticleBean.class),
					ArticleBean.getTitle());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("����ͼ��ʧ��");
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
			System.out.println("ɾ��ͼ��ʧ��");
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
			System.out.println("ɾ��ͼ��ʧ��");
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
			System.out.println("��ȡarraylist<ArticleBean>ArticleBeansʧ��");
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

	// ͨ���û��������ݿ��л���û�
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

	// �����ݿ��������û�
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
			System.out.println("����ʧ��");
		}
		return result;
	}

	// ɾ���û�
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

	// ��õ�����ǰ6����
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

	// ��÷���ʱ��ǰ10����
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

	// ���������Ŀǰ5����
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

	// �����������
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

	// ͨ��������Ŀ�����߻������ȫ������
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
	// ͨ��id�������

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

	// �������
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

	// ���ĳ�����µ�����
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

	// �޸Ĺۿ���Ŀ
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

	// ����ʱ����
	public List<NoteBean> findAllNotes(int id) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		String sql = "select * from notes where id=? order by time";
		List<NoteBean> list = runner.query(sql, new BeanListHandler<NoteBean>(NoteBean.class), id);
		return list;
	}

	// ͨ������id�����ȫ������
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

	// ͨ������ ��ø����ߵ���������ss
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

	/* �޸��û����� */
	public boolean updatepassword(UserBean UserBean) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		/*
		 * //дSQL��� String sql = "update info set name = ?, price = ? where id = ?";
		 * //���÷��� int num = runner.update(sql, new Object[] { info.getName(),
		 * info.getPrice(), info.getId() });
		 */
		// �ȸ����� Ȼ���ټ��ϱ����Ϣ
		String sql = "update user set password = ? where account = ?";
		int num = runner.update(sql, new Object[] { UserBean.getPassword(), UserBean.getAccount() });
		if (num > 0)
			return true;
		else
			return false;
	}

	/* �޸��û���Ϣ */
	public boolean update(UserBean UserBean) throws SQLException {
		QueryRunner runner = new QueryRunner(DBUtils.getDatasource());
		/*
		 * //дSQL��� String sql = "update info set name = ?, price = ? where id = ?";
		 * //���÷��� int num = runner.update(sql, new Object[] { info.getName(),
		 * info.getPrice(), info.getId() });
		 */
		// �ȸ����� Ȼ���ټ��ϱ����Ϣ
		String sql = "update user set nickname = ?, email = ?, age = ? where account = ?";
		int num = runner.update(sql,
				new Object[] { UserBean.getNickname(), UserBean.getEmail(), UserBean.getAge(), UserBean.getAccount() });
		if (num > 0)
			return true;
		else
			return false;
	}



	//ɾ��ĳ������
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

	//ɾ��ĳ������
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
