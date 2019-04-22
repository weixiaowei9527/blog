package bean;

public class UserBean {
	private int account;
	private String password;
	private String nickname;
	private String email;
	private String sex;
	private int age;
	private String zhucetime;
	public UserBean() {
		
	}
	public UserBean(int account,String nickname, String password,String Email,String sex,int age,String zhucetime) {
		this.nickname = nickname;
		this.password = password;
		this.account = account;
		this.email = Email;
		this.sex = sex;
		this.age = age;
		this.zhucetime = zhucetime;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNickname() {
		return this.nickname;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public int getAccount() {
		return this.account;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSex() {
		return this.sex;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge(){
		return age;
	}
	public void setZhucetime(String zhucetime) {
		this.zhucetime = zhucetime;
	}
	public String getZhucetime() {
		return this.zhucetime;
	}
	public boolean isEqual(String name, String password) {
		 int id = Integer.parseInt(name);
		if(id==account&& this.password.equals(password)) {
			return true;
		}else {
			return false;
		}
	}
}
