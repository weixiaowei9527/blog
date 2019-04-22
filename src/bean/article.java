package bean;

import java.util.Date;

public class article {
    private int aid;
    private String title;
    private String time;
    private String content;
    private int number;
    private String author;
    private String type;
    private String desc;
    public article(){
    	
    }
    public article(int id,String title,String time,String content,int number,String author,String type, String desc){
    	this.aid = id;
    	this.author = author;
    	this.content = content;
    	this.number = number;
    	this.time = time;
    	this.title = title;
    	this.type = type;
    	this.desc = desc;
    	
    }
    public void setAid(int aid){
    	this.aid = aid;
    }
    public int getAid(){
    	return aid;
    }
    public void setTitle(String title){
    	this.title = title;
    }
    public String getTitle(){
    	return title;
    }
    public void setTime(String time){
    	this.time = time;
    }
    public String getTime(){
    	return time;
    }
    public void setContent(String content){
    	this.content = content;
    }
    public String getContent(){
    	return content;
    }
    public void setNumber(int number){
    	this.number = number;
    }
    public int getNumber(){
    	return number;
    }
    public void setAuthor(String author){
    	this.author = author;
    }
    public String getAuthor(){
    	return author;
    }
    public void setType(String type){
    	this.type = type;
    }
    public String getType(){
    	return type;
    }
    public void setDesc(String desc){
    	this.desc = desc;
    }
    public String getDesc(){
    	return desc;
    }
   
    
}
