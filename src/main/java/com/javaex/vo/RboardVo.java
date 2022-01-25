package com.javaex.vo;

public class RboardVo {
	
	//필드
	private int no;
	private int user_no;
	private String name;
	private String title;
	private int group_no=1;
	private int order_no=1;
	private int depth=0;
	
	
	
	//생성자
	
	public RboardVo() {
		
	}
	
	
	public RboardVo(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}



	public RboardVo(int no, int user_no, String name, String title, int group_no, int order_no, int depth) {
		super();
		this.no = no;
		this.user_no = user_no;
		this.name = name;
		this.title = title;
		this.group_no = group_no;
		this.order_no = order_no;
		this.depth = depth;
	}
	
	
	//메소드 g/s
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getGroup_no() {
		return group_no;
	}
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	
	//메소드 일반
	@Override
	public String toString() {
		return "RboardVo [no=" + no + ", user_no=" + user_no + ", name=" + name + ", title=" + title + ", group_no="
				+ group_no + ", order_no=" + order_no + ", depth=" + depth + "]";
	}
	

}
