package bean;

public class CommentBean {
	private int id;
	private String txt;
	private String time;
	private String user;
	public CommentBean() {
		
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return this.time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTxt() {
		return this.txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public String getUser() {
		return this.user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
