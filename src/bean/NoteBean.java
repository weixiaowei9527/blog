package bean;

public class NoteBean {
  int id;
  String txt;
  String time;
  public NoteBean(){
	  
  }
  public NoteBean(int id,String txt,String time){
	  this.id = id;
	  this.txt = txt;
	  this.time = time;
  }
  public void setTime(String time){
  	this.time = time;
  }
  public String getTime(){
  	return time;
  }
  public void setId(int aid){
  	this.id = aid;
  }
  public int getId(){
  	return id;
  }
  public void setTxt(String txt){
	  this.txt = txt;
  }
  public String getTxt(){
	  return txt;
  }
}
