package bean;

import java.util.ArrayList;

import utility.BTime;

public class ArticleBean {
	private String content;
	private String title;
	private String author;
	private int number;
	private String time;
	private String des;
	private String type;
	private int id;
	public ArticleBean() {
		// TODO Auto-generated constructor stub
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return this.content;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor() {
		return this.author;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getDes() {
		return this.des;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTime() {
		return this.time;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return this.type;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumber() {
		return this.number;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	static String changeContent(String content) {
		StringBuffer txt = new StringBuffer();
		if(content != null) {
			String[] text = content.split("<br/>");
			if (text != null) {
				for(String x: text) {
					txt.append("<p>" + x + "</p>");
				}
			}
		}
		return new String(txt);
		
	}
	public static ArticleBean getArticle(ArrayList<String>article) {
		ArticleBean articleBean = new ArticleBean();
		articleBean.setTitle(article.get(0));
		articleBean.setAuthor(article.get(1));
		articleBean.setContent(changeContent(article.get(2)));
		articleBean.setDes(article.get(3));
		articleBean.setType(article.get(4));
		articleBean.setTime(BTime.getTime());
		return articleBean;
	}
	public void showArticle() {
		ArticleBean art = this;
		if(art != null) {
			System.out.println("标题:" + art.getTitle()
			+"\n作者:" + art.getAuthor()
			+"\n描述:" + art.getDes()
			+"\n内容:" + art.getContent()
			+"\n时间:" + art.getTime()
			+"\n分类:" + art.getType()
			+"\n浏览次数:" + art.getNumber());
		}else {
			System.out.println("文章为空");
		}
		
	}
}
